
//extends: Element에서 private이 아닌 멤버를 모두 물려받는다.
/* class ArrayElement(conts: Array[String]) extends Element{
  def contents: Array[String] = conts
  //스칼라에서는 필드와 메소드가 같은 네임스페이스->필드가 파라미터 없는 메소드 오버라이드 가능
  //val contents: Array[String] = conts
} */
class ArrayElement(
  val contents: Array[String]
) extends Element 