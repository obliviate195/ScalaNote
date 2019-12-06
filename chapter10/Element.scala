import Element.elem

abstract class Element {
  def contents: Array[String]

  def width: Int = contents(0).length
  def height: Int = contents.length
  
  def above(that: Element): Element ={
    //++ : 두 배열을 이어붙이는 연산
    //new ArrayElement(this.contents++that.contents)
    val this1=this widen that.width
    val that1=that widen this.width
    // 단위테스트
    assert(this1.width==that1.width)
    elem(this1.contents ++ that1.contents)
  }
  def beside(that: Element): Element = {
    /* val contents=new Array[String](this.contents.length)
    for (i<-0 until this.contents.length)
      contents(i)=this.contents(i)+that.contents(i)
    new ArrayElement(contents) */
    /* new ArrayElement(
      for(
        (line1, line2)<-this.contents zip that.contents
      ) yield line1+line2
    ) */
    //zip : 두 인자에서 각각의 대응하는 원소를 추출해 순서쌍의 배열을 만듦
    //Array(1,2,3) zip Array("a", "b")
    //Array((1, "a"), (2, "b"))
    val this1=this heighten that.height
    val that1=that heighten this.height
    elem(
      for ((line1, line2) <- this1.contents zip that1.contents)
      yield line1 + line2
    )
  }

  def widen(w: Int):Element=
    if(w<=width) this
    else{
      val left=elem(' ', (w-width)/2, height)
      val right=elem(' ', w-width-left.width, height)
      left beside this beside right
    } ensuring (w<=_.width)
  
  def heighten(h: Int): Element = 
    if (h <= height) this
    else {
      val top = elem(' ', width, (h - height) / 2)
      var bot = elem(' ', width, h - height - top.height)
      top above this above bot
    }
  override def toString: String = contents mkString "\n"
}

// 팩토리 메소드를 갖춘 팩토리 객체
// 팩토리 객체 : 다른 객체를 생성하는 메소드를 제공하는 객체
object Element {
  // private 선언으로 구현 감추기
  private class ArrayElement(
      val contents: Array[String]
  ) extends Element

  //ArrayElement를 상속받고 ArrayElement에 문자열을 인자로 넘겨줌
  /* class LineElement(s: String) extends ArrayElement(Array(s)){
  override def width: Int = s.length
  override def height: Int = 1
} */

  private class LineElement(s: String) extends Element {
    val contents = Array(s)
    override def width = s.length
    override def height = 1
  }
  private class UniformElement(
    ch: Char,
    override val width: Int,
    override val height: Int
  ) extends Element {
    private val line = ch.toString * width
    def contents = Array.fill(height)(line)
  }

  def elem(contents: Array[String]): Element =
    new ArrayElement(contents)
  def elem(chr: Char, width: Int, height: Int): Element =
    new UniformElement(chr, width, height)
  def elem(line: String): Element =
    new LineElement(line)
}

object Spiral{
  val space=elem(" ")
  val corner=elem("+")
  def spiral(nEdges: Int, direction: Int): Element={
    if(nEdges==1)
      elem("+")
    else{
      val sp=spiral(nEdges-1, (direction+3)%4)
      def verticalBar=elem('|', 1, sp.height)
      def horizontalBar=elem('-', sp.width, 1)
      if(direction==0)
        (corner beside horizontalBar) above (sp beside space)
      else if(direction==1)
        (sp above space) beside (corner above verticalBar)
      else if(direction==2)
        (space beside sp) above (horizontalBar beside corner)
      else
        (verticalBar above corner) beside (space above sp)
    }
  }

  def main(args: Array[String]){
    val nSides=args(0).toInt
    println(spiral(nSides, 0))
  }
}