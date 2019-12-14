object ch2_note{
  println("Hello, world!")
  val msg="Hello, world!"
  val ms3: String="Hello yet again, world!"
  println(msg)
  var greeting="Goodbye cruel world!"

  def max(x:Int, y:Int):Int={
    if(x>y) x
      else y
  }
  def max2(x: Int, y: Int)=if(x>y) x else y
  
  def greet()=println("hello, world!")
}