object ch6_note{
  class Rational(n:Int, d:Int){ //클래스 생성할때 인자들을 이용하여 바로 객체 생성-주생성자
    //println("Created "+n+"/"+d)
    require(d!=0)
    private val g=gcd(n.abs, d.abs)
    val numer: Int=n/g
    val denom: Int=d/g
    def this(n: Int)=this(n,1)  //보조 생성자
    
    
    override def toString(): String = n+"/"+d
  
    def +(that: Rational): Rational=
      new Rational(numer* that.denom+ that.numer*denom, denom*that.denom)
    def +(i: Int): Rational = 
      new Rational(numer+i*denom, denom)
    def - (that: Rational): Rational = 
      new Rational(
        numer*that.denom - that.numer*denom,
        denom*that.denom
      )
    def -(i: Int): Rational = new Rational(numer-i*denom, denom)
      
    def *(that: Rational): Rational = 
      new Rational(numer* that.numer, denom * that.denom)
    def *(i: Int): Rational=new Rational(numer*i, denom)
  
    def / (that: Rational): Rational = 
      new Rational(numer*that.denom, denom*that.numer)
    def / (i: Int): Rational = new Rational(numer, denom*i)
    def lessThan(that: Rational)=this.numer*that.denom<that.numer+this.denom
    def max(that: Rational)=
      if (this.lessThan(that)) that else this
    private def gcd(a: Int, b: Int): Int=
      if(b==0) a else gcd(b, a%b)
  }
  //암시적 변환.
  implicit def intToRational(x: Int)=new Rational(x)
}