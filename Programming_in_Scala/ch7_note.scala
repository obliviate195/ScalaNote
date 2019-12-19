//chapter7 내장 제어 구문
object ch7_note{
  //if-else 구문은 값이다.
  val filename=
    if (!args.isEmpty) args(0)
    else "default.txt"
  
  println(if (!args.isEmpty) args(0) else "default.txt")
  
  def gcdLoop(x: Long, y: Long): Long={
    var a=x
    var b=y
    while (a!=0){
      val temp=a
      a=b%a
      b=temp
    }
    b
  }
  var line=""
  do {
    line=readLine()
    println("Read: "+line)
  } while(line!="")

  def gcd(x:Long, y:Long):Long=
    if (y==0) x else gcd(y, x%y)

  //for: 컬렉션 순회
  val filesHere=(new java.io.File(".")).listFiles() //File 객체의 배열
  for (file <- filesHere)
    println(file)

  for (i <- 1 to 4)
    println("Iteration "+i)
  for (i <-1 until 4)
    println("Iteration "+i)
  //필터링
  for (file <- filesHere if file.getName.endsWith(".scala"))
    println(file)

  for (
    file <- filesHere
    if file.isFile()
    if file.getName.endsWith(".scala")
  ) println(file)

  def fileLines(file: java.io.File)=
    scala.io.Source.fromFile(file).getLines().toList
  def grep(pattern: String)=
    for{
      file<-filesHere
      if file.getName.endsWith(".scala");
      line <- fileLines(file)
      trimmed=line.trim()
      if trimmed.matches(pattern)
    } println(file +": "+ line.trim)
  grep(".*gcd.*")

  //yield: for 순회할때마다 반환->컬렉션 생성
  def scalaFiles=
    for{
      file<-filesHere
      if file.getName.endsWith(".scala")

    } yield file

  val forLineLengths=
    for{
      file <- filesHere
      if file.getName.endsWith(".scala")
      line<-fileLines(file)
      trimmed=line.trim()
      if trimmed.matches(".*for.*")
    } yield trimmed.length

  //throw는 결과 타입이 있는 표현식
  val half=
    if(n%2==0)
      n/2
    else
      throw new RuntimeException("n must be even")

  import java.io.FileReader
  import java.io.FileNotFoundException
  import java.io.IOException

  try{
    val f=new FileReader("input.txt")
  
  }catch{
    case ex: FileNotFoundException=>
    case ex: IOException=>
  }

  val file=new FileReader("input.txt")
  try{

  }finally{
    file.close()
  }

  import java.net.URL
  import java.net.MalformedURLException

  def urlFor(path: String) = 
    try{
      new URL(path)
    } catch{
      case e: MalformedURLException=>
        new URL("http://www.scala-lang.org")
    }

  val firstArg = if(args.length>0) args(0) else ""
  firstArg match {
    case "salt"=>println("pepper")
    case "chips"=>println("salsa")
    case "eggs"=>println("bacon")
    case _ =>println("huh?")
  }
  //match 표현식=>값
  val friend=
    firstArg match{
      case "salt"=>"pepper"
      case "chips"=>"salsa"
      case "eggs"=>"bacon"
      case _=>"huh?"
    }
  println(friend)

  //break, continue는 사용하지 말자
  def searchFrom(i: Int): Int=
    if(i>=args.length) -1
    else if (args(i).startsWith("-")) searchFrom(i+1)
    else if (args(i).endsWith(".scala")) i
    else searchFrom(i+1)
  val i=searchFrom(0)

  def makeRowSeq(row: Int)=
    for(col <- 1 to 10) yield{
      val prod=(row*col).toString
      val padding=" " * (4-prod.length())
      padding + prod
    }

  def makeRow(row: Int)=makeRowSeq(row).mkString
  def multiTable()={
    val tableSeq=
      for (row<- 1 to 10)
        yield makeRow(row)
    tableSeq.mkString("\n")
  }

}