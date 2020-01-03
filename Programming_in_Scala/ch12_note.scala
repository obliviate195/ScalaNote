//chapter12 트레이트

object ch12_note {
  trait Philosophical {
    def philosophize() {
      println("I consume memory, therfore I am!")
    }
  }

  //extends로 믹스인.
  class Frog extends Philosophical {
    override def toString = "green"
  }

  //class 상속 트레이트 믹스인.
  class Animal
  class Frog extends Animal with Philosophical {
    override def toString = "green"
    override def philosophize(): Unit = {
      println("It ain't easy being " + toString + "!")
    }
  }

  //트레이트 여러개 믹스인.
  trait HasLegs
  class Frog extends Animal with Philosophical with HasLegs {
    override def toString = "green"
  }

  trait CharSequence {
    def charAt(index: Int): Char
    def length: Int
    def subSequnce(start: Int, end: Int): CharSequence
    def toString(): String
  }

  class Point(val x: Int, val y: Int)
  //풍부한 트레이트 정의.
  trait Rectangular {
    def topLeft: Point
    def bottomRight: Point
    def left = topLeft.x
    def right = bottomRight.x
    def width = right - left
  }

  abstract class Component extends Rectangular {
    //기타 메소드.
  }

  class Rectangle(val topLeft: Point, val bottomRight: Point)
      extends Rectangular {
    //기타 메소드.
  }

  class Rational(n: Int, d: Int) extends Ordered[Rational] { //클래스 생성할때 인자들을 이용하여 바로 객체 생성-주생성자
    //println("Created "+n+"/"+d)
    require(d != 0)
    private val g = gcd(n.abs, d.abs)
    val numer: Int = n / g
    val denom: Int = d / g
    def this(n: Int) = this(n, 1) //보조 생성자

    override def toString(): String = n + "/" + d

    def +(that: Rational): Rational =
      new Rational(numer * that.denom + that.numer * denom, denom * that.denom)
    def +(i: Int): Rational =
      new Rational(numer + i * denom, denom)
    def -(that: Rational): Rational =
      new Rational(
        numer * that.denom - that.numer * denom,
        denom * that.denom
      )
    def -(i: Int): Rational = new Rational(numer - i * denom, denom)

    def *(that: Rational): Rational =
      new Rational(numer * that.numer, denom * that.denom)
    def *(i: Int): Rational = new Rational(numer * i, denom)

    def /(that: Rational): Rational =
      new Rational(numer * that.denom, denom * that.numer)
    def /(i: Int): Rational = new Rational(numer, denom * i)
    def lessThan(that: Rational) =
      this.numer * that.denom < that.numer + this.denom
    def max(that: Rational) =
      if (this.lessThan(that)) that else this
    /* def < (that: Rational) = this.numer * that.denom > that.numer*this.denom
    def > (that: Rational) = that < this
    def <= (that: Rational) = (this < that) || (this == that)
    def >= (that: Rational) = (this > that) || (this == that) */
    //ordered 트레이트를 믹스인 하고 compare 함수만 정의되면 자동으로 비교연산자 사용 가능.
    def compare(that: Rational) =
      (this.numer * that.denom) - (that.numer * this.denom)

    private def gcd(a: Int, b: Int): Int =
      if (b == 0) a else gcd(b, a % b)
  }

  abstract class IntQueue {
    def get(): Int
    def put(x: Int)
  }

  import scala.collection.mutable.ArrayBuffer
  class BasicIntQueue extends IntQueue {
    private val buf = new ArrayBuffer[Int]
    def get() = buf.remove(0)
    def put(x: Int) { buf += x }
  }
  //Doubling 트레이트는 IntQueue를 상속한 클래스에서만 믹스인 가능.
  //트레이트를 믹스인한 클래스의 super를 호출 가능.
  trait Doubling extends IntQueue {
    abstract override def put(x: Int) { super.put(2 * x) }
  }

  trait Incrementing extends IntQueue {
    abstract override def put(x: Int) { super.put(x+1)}
  }

  trait Filtering extends IntQueue {
    abstract override def put(x: Int) {
      if (x>=0) super.put(x)
    }
  }

  //믹스인 순서에 따라 달라짐. 오른쪽 먼저 수행하고 왼쪽 수행.
  val queue1 = (new BasicIntQueue with Incrementing with Filtering)
  val queue2 = (new BasicIntQueue with Filtering with Incrementing)
}
