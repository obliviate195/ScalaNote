object ch15_ex1{
  abstract class Expr
  case class Var(name: String) extends Expr
  case class Number(num: Double) extends Expr
  case class UnOp(operator: String, arg: Expr) extends Expr
  case class BinOp(operator: String, left: Expr, right: Expr) extends Expr

  def simplifyTop(expr: Expr):Expr=expr match{
    case UnOp("-", UnOp("-", e))=>e   // 부호를 두 번 반전
    case BinOp("+", e, Number(0))=>e  // 0을 더함
    case BinOp("*", e, Number(1))=>e  // 1을 곱함
    case _=>expr
  }

  def describe(x: Any)= x match {
    case 5 => "five"
    case true=>"truth"
    case "hello" => "hi!"
    case Nil => "the empty list"
    case _ => "something else"
  }

}