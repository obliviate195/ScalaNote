//chapter8 함수와 클로저
object ch8_note{
  var increase=(x:Int) => x+1

  increase=(x:Int)=>{
    println("asdf")
    x+1
  }

  val someNumbers=List(-11, -10, -5, 0, 5, 10)
  someNumbers.foreach((x:Int)=>println(x))
  someNumbers.filter((x:Int)=>x>0)
  someNumbers.filter(x=>x>0)
  someNumbers.filter(_>0)

  val f=(_:Int)+(_:Int)

  someNumbers.foreach(println _)

  def sum(a: Int, b: Int, c: Int) = a+b+c
  //a.apply가 sum(1,2,3) 호출
  val a = sum _
  a(1,2,3)
  val b= sum(1, _:Int, 3)

  //함수가 필요한 위치라는게 명확하면 _ 생략 가능
  someNumbers.foreach(println)

  //클로저
  var more=1
  val addMore=(x:Int) => x+more
  addMore(10)
  more=9999
  addMore(10)

  var sum=0
  someNumbers.foreach(sum+= _)

  def makeIncreaser(more: Int) = (x:Int) => x+more
  val inc1=makeIncreaser(1)
  val inc9999=makeIncreaser(9999)
  
  //반복 파라미터: *
  //실제론 Array[String]
  def echo(args: String*) = 
    for (arg<-args) println(arg)
  echo()
  echo("one")
  echo("hello","world")

  val arr=Array("asdf","ddd")
  echo(arr:_*)

  def speed(distance: Float, time: Float): Float = 
    distance / time
  
  speed(time = 10, distance = 100)

  def printTime(out: java.io.PrintStream = Console.out) = 
    out.println("time = "+ System.currentTimeMillis())

  def printTime2(out: java.io.PrintStream = Console.out,
                  divisor: Int=1) =
    out.println("time = "+ System.currentTimeMillis()/divisor)

  //꼬리재귀는 자동으로 최적화
  def approximate(guess: Double): Double =
    if (isGoodEnough(guess)) guess
    else approximate(improve(guess))
  
  //꼬리재귀 x
  def boom(x: Int): Int =
    if (x==0) throw new Exception("b")
    else boom(x-1) +1
  
  //꼬리재귀
  def bang(x: Int): Int =
    if (x==0) throw new Exception("b")
    else bang(x-1)

    
}