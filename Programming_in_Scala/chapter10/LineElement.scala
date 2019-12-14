//ArrayElement를 상속받고 ArrayElement에 문자열을 인자로 넘겨줌
/* class LineElement(s: String) extends ArrayElement(Array(s)){
  override def width: Int = s.length
  override def height: Int = 1
} */

class LineElement(s:String) extends Element{
  val contents=Array(s)
  override def width=s.length
  override def height=1
}