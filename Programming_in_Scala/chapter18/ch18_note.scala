//chapter18 상태가 있는 객체

object ch_18_note {
  class BankAccount {
    //var: 변경 가능.
    private var bal: Int = 0
    def balance: Int = bal
    def deposit(amount: Int) {
      require(amount > 0)
      bal += amount
    }
    def withdraw(amount: Int): Boolean =
      if (amount > bal) false
      else {
        bal -= amount
        true
      }
  }

  //var은 자동으로 getter, setter를 정의해줌.
  class Time {
    var hour = 12
    var minuter = 0
  }
  class Time {
    private[this] var h= 12
    private[this] var m = 0
    def hour: Int = h
    def hour_=(x: Int) { h= x} //setter
    def minute: Int = m
    def minute_=(x: Int) {m=x} //setter
  }

  class Thermometer {
    var celsius: Float = _  //=_ : 디폴트 값 할당(수 타입: 0, Boolean: false, reference: null)
    def fahrenheit = celsius*9/5 + 32
    def fahrenheit_= (f: Float) {
      celsius=(f-32)*5/9
    }
    override def toString = fahrenheit + "F/"+ celsius+"C"
  }

  //Digital Circuit
  val a, b, c=new Wire
  def inverter(input: Wire, output: Wire)
  def andGate(a1: Wire, a2: Wire, output: Wire)
  def orGate(o1: Wire, o2: Wire, output: Wire)

  def halfAdder(a: Wire, b: Wire, s: Wire, c: Wire) {
    val d, e = new Wire
    orGate(a, b, d)
    andGate(a, b, c)
    inverter(c, e)
    andGate(d, e, s)
  }

  def fullAdder(a: Wire, b: Wire, cin: wire, sum: Wire, cout: Wire){
    val s, c1, c2 = new Wire
    halfAdder(a, cin, s, c1)
    halfAdder(b, s, sum, c2)
    orGate(c1, c2, cout)
  }
}
