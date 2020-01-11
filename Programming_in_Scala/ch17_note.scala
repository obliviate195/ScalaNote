//chapter17 컬렉션

object ch17_note {
  //리스트(List)
  //앞부분에 빠르게 원소를 삭제하거나 추가 가능
  //임의의 위치에 접근할 때는 빠르지 않다.

  //배열(Array)
  //임의의 위치에 있는 원소를 효율적으로 접근 가능.
  val fiveInts = new Array[Int](5) // Array(0, 0, 0, 0, 0)
  val finveToOne = Array(5, 4, 3, 2, 1)
  //각 괄호가 아닌 괄호로 인덱스 접근.
  finveInts(0) = fiveToOne(4)

  //리스트 버퍼(ListBuffer)
  //원소 추가가 쉬운 리스트.
  //작업을 끝내고 toList
  import scala.collection.mutable.ListBuffer
  val buf = new ListBuffer[Int]
  buf += 1
  buf += 2
  3 +=: buf
  buf.toList

  //배열 버퍼(ArrayBuffer)
  //끝, 시작 부분에 원소를 추가하거나 삭제 가능. 그 외에는 배열과 동일
  import scala.collection.mutable.ArrayBuffer
  val buf = new ArrayBuffer[Int]()
  buf += 12
  buf += 15
  buf(0)
  buf.length

  //문자열(StringOps를 통해)
  //String을 StringOps로 암시적 변환
  def hasUpperCase(s: String) = s.exists(_.isUpper)
  hasUpperCase("sdfsdEEsdfdsfsdaW") // true

  //집합(Set): 특정 객체가 최대 하나만 들어가도록 보장.
  import scala.collection.mutable
  val text = "See Spot run. Run, Spot. Run!"
  val wordsArray = text.split("[ !,.]+")
  val words = mutable.Set.empty[String]
  for (word <- wordsArray)
    words += word.toLowerCase()
  words // Set(see, run, spot)

  //맵(Map)
  val map = mutable.Map.empty[String, Int]
  //key, value
  map("hello")=1
  map("there")=2
  map //  Map(hello -> 1, there -> 2)

  def countWords(text: String) = {
    val counts=mutable.Map.empty[String, Int]
    for (rawWord <- text.split("[ ,!.]+")) {
      val word = rawWord.toLowerCase()
      val oldCount =
        if (counts.contains(word))
          counts(word)
        else
          0
        counts += (word -> (oldCount + 1))
    }
    counts
  }
  
  //정렬된 집합과 맵.
  import scala.collection.immutable.TreeSet
  val ts=TreeSet(9, 3, 1, 8, 0, 2, 7, 4, 6, 5)

  import scala.collection.immutable.TreeMap
  val tm=TreeMap(3 -> 'x', 1 -> 'x', 4->'x')

  //컬렉션 초기화.
  //초기 원소를 컬렉션 동반 객체의 팩토리 메소드에 넘긴다.
  //컴파일러는 그 호출을 동반 객체에 있는 apply 메소드 호출로 변환한다.
  List(1,2,3)
  Set('a', 'b', 'c')

  import scala.collection.mutable
  val stuff=mutable.Set[Any](42)

  val colors=List("blue", "yello", "red", "green")
  val treeSet=TreeSet[String]()++colors

  treeSet.toList
  treeSet.toArray

  import scala.collection.mutable
  //immutable to mutable set
  val mutaSet = mutable.Set.empty ++= treeSet
  //mutable to immutable
  val immutaSet=Set.empty ++ mutaSet

  //Tuple: 다른 타입의 원소를 묶을 수 있다.
  (1, "hello", Console)

  //메소드에서 여러값 반환.
  def longestWord(words: Array[String]) = {
    var word=words(0)
    var idx =0
    for (i <- 1 until words.length)
      if (words(i).length > word.length()) {
        word=words(i)
        idx=i
      }
    (word, idx)
  }
  val longest=longestWord("The quick brown fox".split(" "))
  longest._1 // quick
  longest._2 // 1
  val (word, idx) = longest // word = quick, idx = 1
  val word, idx = longest // word=idx=(quick, 1)
  
}
