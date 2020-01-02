
class LineElement(s: String) extends Element { // ArrayElement(Array(s)) {
  val contents=Array(s)
  override def width = s.length
  override val height = 1

}