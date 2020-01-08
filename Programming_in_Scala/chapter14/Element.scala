import Element.elem

abstract class Element {
  //파라미터 없는 method
  def contents: Array[String] //Element 클래스의 추상 멤버. 메소드의 구현이 없으면 추상 메소드

  def height: Int = contents.length //내용이 몇 줄인지 반환

  def width: Int = if (height == 0) 0 else contents(0).length

  def above(that: Element): Element = {
    //new ArrayElement(this.contents ++ that.contents)
    //팩토리 메소드 사용.

    val this1 = this widen that.width
    val that1 = that widen this.width
    //단언문의 사용.
    assert(this1.width==that1.width)
    elem(this1.contents ++ that1.contents)
  }
  def beside(that: Element): Element = {
    //팩토리 메소드 사용.
    val this1 = this heighten that.height
    val that1 = that heighten this.height
    elem( //new ArrayElement(
      for ((line1, line2) <- this1.contents zip that1.contents)
        yield line1 + line2
    )
  }
  def widen(w: Int): Element =
    if (w <= width) this
    else {
      val left = elem(' ', (w - width) / 2, height)
      val right = elem(' ', w - width - left.width, height)
      left beside this beside right
    } ensuring (w <= _.width) //단언문 사용.
    
  def heighten(h: Int): Element =
    if (h <= height) this
    else {
      val top = elem(' ', width, (h - height) / 2)
      val bot = elem(' ', width, h - height - top.height)
      top above this above bot
    }
  override def toString = contents mkString "\n"
}

//팩토리 객체: 다른 객체를 생성하는 메소드를 제공하는 객체.
object Element {
  private class ArrayElement(
      val contents: Array[String]
  ) extends Element

  private class LineElement(s: String) extends Element { // ArrayElement(Array(s)) {
    val contents = Array(s)
    override def width = s.length
    override val height = 1

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
