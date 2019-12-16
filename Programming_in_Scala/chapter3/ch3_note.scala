object ch3_note{
  val big=new java.math.BigInteger("12345")

  //타입을 각괄호 안으로 ex)Array[String], 인수는 3으로
  //val로 선언하면 그 변수를 재할당 할 수는 없지만 그 변수가 나타내는 객체는 변경 가능
  val greetStrings: Array[String] =new Array[String](3)
  //인덱스 참조는 []아닌 ()로
  //배열의 원소에 접근하는 것은 일반적은 메소드 호출과 같다.
  greetStrings(0)="Hello"
  greetStrings(1)=", "
  greetStrings(2)="world!\n"
  //0 to 2==(0).to(2)
  for (i<-0 to 2)
    print(greetStrings(i))

  val greetStrings=new Array[String](3)
  greetStrings.update(0, "hello")
  greetStrings.update(1, ", ")
  greetStrings.update(2, "world!\n")
  for (i<-0.to(2))
    print(greetStrings.apply(i))
  //Array: 변경 가능 시퀀스
  val numNames=Array("zero", "one", "two")//Array.apply("zero", "one", "two")
  //List: 변경 불가능 시퀀스
  val oneTwoThree=List(1,2,3)
  //::: : 두 리스트를 이어붙임
  val oneTwo=List(1,2)
  val threeFour=List(3,4)
  val oneTowThreeFour=oneTwo:::threeFour
  println(oneTwo+" and "+threeFour+" were not mutated.")
  println("Thus, "+oneTowThreeFour+" is a new list.")

  //:: : 새 원소를 기존 리스트의 맨 앞에 추가한 리스트 반환
  //리스트 뒤에 새 원소를 추가하면 리스트의 길이에 비례한 시간이 걸림
  val twoThree=List(2,3)
  val oneTwoThree=1::twoThree
  println(oneTwoThree)

  //Nil : 빈 리스트
  val oneTwoThree=1::2::3::Nil
  println(oneTwoThree)

  //튜플: 각기 다른 타입의 원소를 넣을 수 있다.
  //튜플의 각 원소들의 타입이 다를 수 있기 때문에 pair(0)이 아닌 pair._1로 접근
  val pair=(99, "Luftballons")
  println(pair._1)
  println(pair._2)

  //set
  var jetSet=Set("Boeing", "Airbus")
  jetSet+="Lear"
  println(jetSet.contains("Cessna"))

  import scala.collection.mutable.Set
  val movieSet=Set("Hitch", "Poltergeist")
  movieSet+="Shrek"
  println(movieSet)

  //map
  import scala.collection.mutable.Map
  val treasureMap=Map[Int, String]()//팩토리 메소드
  treasureMap+=(1->"Go to island.")
  treasureMap+=(2->"Find big X on ground.")
  treasureMap+=(3->"Dig.")
  println(treasureMap(2))

  val romanNumeral=Map(
    1->"I", 2->"II", 3->"III", 4->"IV", 5->"V"
  )
  println(romanNumeral(4))

  //명령형 스타일-var
  def printArgs(args: Array[String]):Unit={
    var i=0
    while(i<args.length){
      println(args(i))
      i+=1
    }
  }

  //함수형 스타일로-var 없이
  def printARgs(args: Array[String]): Unit={
    for(arg<=args)
      println(arg)
  }

  def printArgs(args: Array[String]): Unit={
    args.foreach(println)
  }

  //var나 부수 효과가 없는 함수
  //mkString: iterable 컬렉션(배열, 리스트, 집합, 맵 등)에서 사용, 각 원소 사이에 인자로 넘긴 문자열을
  //끼워 넣은 문자열 반환
  def formatArgs(args: Array[String]) = args.mkString("\n")

  val res=formatArgs(Array("zero", "one", "two"))
  assert(res=="zero\none\ntwo")

  
}