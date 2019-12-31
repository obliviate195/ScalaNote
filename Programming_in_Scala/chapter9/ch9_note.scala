import java.io.PrintWriter
//chapter9 흐름 제어 추상화

object ch9_note {
  //리스트에 음수가 있는지 확인
  /* def containsNeg(nums: List[Int]): Boolean={
    var exists=false
    for (num <- nums)
      if(num<0)
        exists=true
    exists
  } */
  def containsNeg(nums: List[Int]) = nums.exists(_ < 0)
  def containsOdd(nums: List[Int]) = nums.exists(_ % 2 == 1)

  //currying

  def plainOldSum(x: Int, y: Int) = x + y
  //커링한 함수. 2개의 전통적인 함수를 연달아 호출한 것
  def curriedSum(x: Int)(y: Int) = x + y
  def first(x: Int) = (y: Int) => x + y
  val onePlus = curriedSum(1) _

  def twice(op: Double => Double, x: Double) = op(op(x))

  //빌려주기 패턴
  //PrinterWriter를 함수op에게 빌려줌
  def WithPrintWriter(file: File, op: PrintWriter => Unit) {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }
  //커링 이용
  def withPrintWriter(file: File)(op: PrintWriter => Unit) {
    val writer = new PrintWriter(file)
    try {
      op(writer)
    } finally {
      writer.close()
    }
  }
  WithPrintWriter(
    new File("date.txt"),
    writer => writer.println(new java.util.Date)
  )
  val file = new File("date.txt")
  withPrintWriter(file) { writer =>
    writer.println(new java.util.Date) //하나의 인자
  }

  //인자를 단 하나만 전달하는 경우 소괄호 대신 중괄호 사용 가능
  println("Hello, world!")
  println { "Hello, world!" }

  //이름에 의한 호출 파라미터
  //myAssert: 함수값을 입력받고 함수 밖에 있는 플래그를 참조해 어떠한 일을 할지 결정
  //이름에 의한 호출X
  var assertionsEnabled = true
  def myAsssert(predicate: () => Boolean) =
    if (assertionsEnabled && !predicate())
      throw new AssertionError
  myAsssert(() => 5 > 3)

  //이름에 의한 호출: () => 대신 =>
  def byNameAssert(predicate: => Boolean) =
    if (assertionsEnabled && !predicate)
      throw new AssertionError
  byNameAssert(5 > 3)
  
  //단언문을 사용하지 않도록 플래그를 설정해놓아도 괄호 안의 표현식을 계산
  def boolAssert(predicate: Boolean) =
    if (assertionsEnabled && !predicate)
      throw new AssertionError
}
