//chapter16 리스트

object ch16_note {
  //다양한 리스트 형태.
  val fruit = List("apples", "oranges", "perars")
  val nums = List(1, 2, 3, 4)
  val diag3 =
    List(
      List(1, 0, 0),
      List(0, 1, 0),
      List(0, 0, 1)
    )
  val empty = List()

  //배열과의 차이점.
  //1. 리스트는 변경 불가능.
  //2. 리스트의 구조는 재귀적이지만, 배열은 평면적이다.

  //리스트 타입.
  //리스트는 균일, 즉 어떤 리스트에 속한 모든 원소의 타입은 같다.
  //List[T]
  //공변적: S 타입이 T 타입의 서브타입이면 List[S]도 List[T]의 서브타입.

  //리스트 생성.
  //Nil과 ::
  val fruit = "apples" :: ("oranges" :: ("pears" :: Nil))
  val nums = 1 :: (2 :: (3 :: (4 :: Nil)))
  val diag3 = (1 :: (0 :: (0 :: Nil))) ::
    (0 :: (1 :: (0 :: Nil))) ::
    (0 :: (0 :: (1 :: Nil))) :: Nil
  val empty = Nil

  //리스트 기본 연산.
  //head: 어떤 리스트의 첫 번째 원소 반환.
  //tail: 어떤 리스트의 첫 번째 원소를 제외한 나머지 원소로 이뤄진 리스트 반환
  //isEmpty: 비었는지
  //삽입정렬.
  def isort(xs: List[Int]): List[Int] =
    if (xs.isEmpty) Nil
    else insert(xs.head, isort(xs.tail))
  def insert(x: Int, xs: List[Int]): List[Int] =
    if (xs.isEmpty || x <= xs.head) x :: xs
    else xs.head :: insert(x, xs.tail)

  //리스트 패턴.
  val List(a, b, c) = fruit
  val a :: b :: rest = fruit

  def isort(xs: List[Int]): List[Int] = xs match {
    case List()   => List()
    case x :: xs1 => insert(x, isort(xs.tail))
  }
  def insert(x: Int, xs: List[Int]): List[Int] = xs match {
    case List() => x :: Nil
    case y :: ys =>
      if (x <= y) x :: xs
      else y :: insert(x, ys)
  }

  //리스트 연결.
  List(1, 2) ::: List(3, 4, 5)

  //::: 구현.
  //append[T]: 템플릿
  def append[T](xs: List[T], ys: List[T]): List[T] =
    xs match {
      case Nil        => ys
      case head :: tl => head :: append(tl, ys)
    }

  //length: 리스트 길이 구하기.
  //비싼 연산.
  List(1, 2, 3).length

  //init, last <-> tail, head
  //비싼 연산.
  val abcde = List('a', 'b', 'c', 'd', 'e')
  abcde.last //e
  abcde.init //List(a,b,c,d,e)

  //reverse: 리스트 뒤집기.
  //비싼 연산.
  abcde.reverse //List(e,d,c,b,a)

  //drop, take, splitAt
  //drop: 처음부터 n번째 원소까지 반환
  //take: n+1부터 마지막 원소까지 반환
  //splitAt: n번째에서 분할, 리스트 2개의 튜플 반환.
  abcde take 2 //List(a, b)
  abcde drop 2 //List(c,d,e)
  abcde splitAt 2 //(List(a,b), List(c,d,e))

  //apply, indices: 원소 선택.
  //리스트에서는 많이 사용 안 함
  abcde apply 2 // c
  abcde.indices //Range(0, 1, 2, 3, 4)

  //flatten: 리스트들을 하나의 리스트로.
  List(List(1, 2), List(3), List(), List(4, 5)).flatten // List(1, 2, 3, 4, 5)
  fruit
    .map(_.toCharArray)
    .flatten //List(a, p, p, l, e, s, o, r, a, n, g, e, s, p, e, a, r, s)

  //zip: 두 리스트->순서쌍 리스트. 남는건 버린다.
  //unzip: 순서쌍 리스트 -> 두 리스트.
  abcde.indices zip abcde // Vector((0,a), (1,b), (2,c), (3,d), (4,e))
  abcde.zipWithIndex // List((a,0), (b,1), (c,2), (d,3), (e,4))
  val zipped = abcde zip List(1, 2, 3)
  zipped.unzip //(List(a, b, c),List(1, 2, 3))

  //리스트 출력하기: toString, mkString
  //String
  abcde.toString // List(a, b, c, d, e)
  abcde mkString ("[", ",", "]") // [a, b, c, d, e]
  abcde mkString "" // abcde

  //리스트 변환하기: iterator, toArray, copyToArray
  val arr = abcde.toArray // Array(a, b, c, d, e)
  arr.toList //List(a, b, c, d, e)
  val arr2 = new Array[Int](10)
  List(1, 2, 3) copyToArray (arr2, 3) //Array(0, 0, 0, 1, 2, 3, 0, 0, 0, 0)
  val it = abcde.iterator
  it.next //a
  it.next //b

  def msort[T](less: (T, T) => Boolean)(xs: List[T]): List[T] = { //currying
    def merge(xs: List[T], ys: List[T]): List[T] = (xs, ys) match {
      case (Nil, _) => ys
      case (_, Nil) => xs
      case (x :: xs1, y :: ys1) =>
        if (less(x, y)) x :: merge(xs1, ys)
        else y :: merge(xs, ys1)
    }
    val n = xs.length / 2
    if (n == 0) xs
    else {
      val (ys, zs) = xs splitAt n
      merge(msort(less)(ys), msort(less)(zs))
    }
  }

  msort((x: Int, y: Int) => x < y)(List(2, 1, 3, 5, 4)) //  List[Int] = List(1, 2, 3, 4, 5)
  val reverseIntSort = msort((x: Int, y: Int) => x > y) _

  //리스트 매핑: map, flatMap, foreach
  List(1, 2, 3) map (_ + 1) //List(2,3,4)
  val words = List("the", "quick", "brown", "fox")
  words.map(_.length()) // List(3, 5, 5, 3)
  //flatMap: 단일 리스트 반환.
  words.map(_.toList) //  List(List(t, h, e), List(q, u, i, c, k), List(b, r, o, w, n), List(f, o, x))
  words.flatMap(_.toList) //List(t, h, e, q, u, i, c, k, b, r, o, w, n, f, o, x)
  //foreach: 모든 원소에 프로시저 실행.
  var sum = 0
  List(1, 2, 3, 4, 5) foreach (sum += _) //15

  //리스트 걸러내기: filter, partition, find, takeWhile, dropWhile, span
  List(1, 2, 3, 4, 5) filter (_ % 2 == 0) //List(2, 4)
  words filter (_.length == 3) //List(the, fox)
  List(1, 2, 3, 4, 5) partition (_ % 2 ==0) // (List(2, 4), List(1, 3,5))
  //find: 조건을 만족하는 첫 번째 원소만 반환.
  List(1, 2, 3, 4, 5) find (_ % 2 ==0) // Some(2)
  List(1, 2, 3, 4, 5) find (_ <= 0) // None
  //takeWhile, dropWhile, span: 조건을 만족하는 가장 긴 접두사 반환, 제거, 둘 같이.
  List(1, 2, 3, -4, 5) takeWhile (_ > 0) // List(1, 2, 3)
  words dropWhile (_ startsWith("t")) //List(quick, brown, fox)
  List(1, 2, 3, -4, 5) span (_ > 0) // (List(1, 2, 3),List(-4, 5))

  //리스트 전체에 대한 술어: forall, exists
  //forall: 전체가 참이면 참.
  //exists: 하나라도 참이면 참.
  def hasZeroRow(m: List[List[Int]]) =
    m.exists((row: List[Int]) => row.forall((x: Int) => x == 0))
    //m exists (row => row forall (_ == 0))

  //리스트 폴드: /:, :\
  // sum(List(a, b, c)) = 0 + a + b + c
  def sum(xs: List[Int]): Int = (0 /: xs) (_ + _)
  def product(xs: List[Int]): Int = (1 /: xs) (_ * _)
  ("" /: words) (_ + " " + _) //the quick brown fox
  words.foldLeft("")(_ + " " + _) // 첫 시작, 사이 연산
  def flattenRight[T](xss: List[List[T]]) =
    (xss :\ List[T]()) (_ ::: _)

  //reverse 함수
  def reverseLeft[T] (xs: List[T]) =
    (List[T]() /: xs) {(ys, y) => y :: ys}

  //리스트 정렬: sortWith
  List(1, -3, 4, 2, 6) sortWith (_ < _) // List(-3, 1, 2, 4, 6)

  //List 객체의 메소드
  //원소로부터 리스트 만들기: List.apply
  List.apply(1, 2, 3)

  //수의 범위를 리스트로 만들기: List.range
  List.range(1,5) // List(1, 2, 3, 4)
  List.range(1, 9, 2) // List(1, 3, 5, 7)

  //균일한 리스트 생성: List.fill
  List.fill(5)('a') // List(a, a, a, a, a)
  List.fill(2, 3)('b') // List(List(b, b, b), List(b, b, b))

  //함수 도표화: List.tabulate
  val squares = List.tabulate(5) (n => n*n) //List(0, 1, 4, 9, 16)
  val multiplication = List.tabulate(5, 5) (_ * _)
    //List(List(0, 0, 0, 0, 0), List(0, 1, 2, 3, 4), List(0, 2, 4, 6, 8), List(0, 3, 6, 9, 12), List(0, 4, 8, 12, 16))
  
  //여러 리스트 연결하기: List.concat
  List.concat(List('a', 'b'), List('c')) // List(a, b, c)

  (List(10, 20), List(3, 4, 5)).zipped.map(_ * _) // List(30, 80)
  (List("abc", "de"), List(3, 2)).zipped.forall(_.length == _) //true
  (List("abc", "de"), List(3, 2)).zipped.exists(_.length != _) //false

}
