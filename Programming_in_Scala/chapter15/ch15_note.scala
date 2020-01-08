//chapter15 케이스 클래스와 패턴매치

object ch15_note{
  //class C와 class C {}는 같다.
  abstract class Expr
  case class Var(name: String) extends Expr
  case class Number(num: Double) extends Expr
  case class UnOp(operator: String, arg: Expr) extends Expr
  case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

  //케이스 클래스
  //첫째, 컴파일러는 클래스 이름과 같은 이름의 팩토리 메소드를 추가.
  var v=Var("x")
  val op=BinOp("+", Number(1), v)
  //둘째, 케이스 클래스의 파라미터 목록에 있는 모든 클래스 파라미터에 암시적으로 val 접두사를 붙인다.
  v.name //String=x
  op.left //Expr=Number(1.0)
  //셋째, toString, hashCode, equals 메소드를 추가해준다.
  println(op)
  //넷째, copy 메소드를 추가해준다.
  op.copy(operator = "-")

  def simplifyTop(expr: Expr): Expr=expr match {
    case UnOp("-", UnOp("-", e)) => e //부호를 두 번 반전.
    case BinOp("+", e, Number(0)) => e //0을 더함.
    case BinOp("*", e, Number(1)) => e //1을 곱함.
    case _ => expr
  }

  expr match {
    case BinOp(op, left, right) =>
      println(expr + " is a binary operation")
    //디폴트 케이스 추가.
    case _ =>
  }

  //와일드카드 패턴.
  expr match {
    case BinOp(_, _, _) => println(expr +" is a binary operation")
    case _ => println("It's a something else")
  }

  //상수 패턴.
  def describe(x: Any) = x match {
    case 5 => "five"
    case true => "truth"
    case "hello" => "hi!"
    case Nil => "the empty list" //Nil: 빈 리스트.
    case _ => "something else"
  }

  //변수 패턴. 변수에 객체를 바인딩. 값이 무엇이든 간에 매칭 가능 -> 디폴트
  expr match {
    case 0 => "zero"
    case somethingElse => "not zero: " + somethingElse
  }

  //생성자 패턴.
  expr match {
    case BinOp("+", e, Number(0)) => println("a deep match")
    case _ =>
  }

  //시퀀스 패턴.
  expr match {
    case List(0, _, _) => println("found it")
    case _ =>
  }
  expr match {
    case List(0, _*) => println("found it") //길이와 관계없이.
    case _ =>
  }

  //튜플 패턴.
  def tupleDemo(expr: Any) =
    expr match {
      case (a, b, c) => println("matched " + a +b+c)
      case _ =>
    }

  //타입 지정 패턴.
  def generalSize(x: Any) = x match{
    case s: String => s.length()
    case m: Map[_, _] => m.size
    case _ => -1
  }

  //변수 바인딩.
  expr match {
    //전체 패턴을 매치시키는 데 성공하면 UnOp("abs", _) 부분과 매치된 값이 e 변수에 들어간다.
    case UnOp("abs", e @ UnOp("abs", _)) => e 
    case _ =>
  }

  //패턴 가드.
  def simplifyAdd(e: Expr) = e match { 
    //패턴 뒤에 if
    case BinOp("x", x, y) if x== y =>
      BinOp("*", x, Number(2))
    case _ => e
  }

  //패턴 겹침.
  //코드에 있는 순서대로 패턴을 매치.
  def simplifyAll(expr: Expr): Expr = expr match {
    case UnOp("-", UnOp("-", e)) =>
      simplifyAll(e) //e를 두 번 적용하는 경우.
    case BinOp("+", e, Number(0)) =>
      simplifyAll(e) //0은 + 연산의 항등원.
    case BinOp("*", e, Number(1)) =>
      simplifyAll(e) //1은 * 연산의 항등원
    case UnOp(op, e) =>
      UnOp(op, simplifyAll(e))
    case BinOp(op, 1, r) =>
      BinOp(op, simplifyAll(1), simplifyAll(r))
    case _ => expr
  }

  //할당문 하나로 여러 배열 정의하기.
  val myTuple=(123, "abc")
  val (number, string) = myTuple

  //for 표현식에서 패턴 사용하기.
  for ((country, city) <- capitals)
    println("The capital of "+ country +" is "+city)

  val results = List(Some("apple"), None, Some("orange"))
  for (Some(fruit) <- results) println(fruit)
}