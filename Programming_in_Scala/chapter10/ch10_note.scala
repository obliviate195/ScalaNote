

object ch10_note{
  class Cat{
    val dangerous=false
  }
  class Tiger(
    override val dangerous: Boolean,
    private var age: Int
  ) extends Cat
  class Tiger(param1: Boolean, param2: Int) extends Cat{
    override val dangerous = param1
    private var age = param2
  }

  abstract class Element {
    def demo() {
      println("Element's implementation invoked")
    }
  }

  class ArrayElement extends Element {
    override def demo() {
      println("ArrayElemnt's implementation invoked")
    }
  }

  class LineElement extends ArrayElement {
    override def demo() {
      println("LineElemnt's implementation invoked")
    }
  }

  class UniformElemnt extends Element

  def invokeDemo(e: Element) {
    e.demo()
  }

  //멤버를 상속 못하게 하려면 멤버 앞에 final-final override def demo()
  //클래스를 상속 못하게 하려면 class 앞에 final-final class ArrayElement

  def above(that: Element): Element=
    new ArrayElement(this.contents ++ that.contents)

  //명령형 방식.
  def beside(that: Element): Element = {
    val contents = new Array[String](this.contents.length)
    for (i <- 0 until this.contents.length)
      contents(i) = this.contents(i) + that.contents(i)
    new ArrayElement(contents)
  }

  //함수형 방식.
  def beside(that: Element): Element = {
    new ArrayElement(
      for (
        (line1, line2) <- this.contents zip that.contents
      ) yield line1 + line2 //yield: 결과값을 모아서 Array형식으로 반환.
    )
  }
  //zip: Array(1, 2, 3) zip Array("a", "b") => Array((1, "a"), (2, "b"))
  override def toString = contents mkString "\n"

  
}
