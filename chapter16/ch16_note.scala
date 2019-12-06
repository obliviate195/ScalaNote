object ch16_note{
  def isort(xs: List[Int]): List[Int]=
    if (xs.isEmpty) Nil
    else insert(xs.head, isort(xs.tail))
  
  def insert(x: Int, xs: List[Int]): List[Int]=
    if (xs.isEmpty || x<=xs.head) x::xs
    else xs.head :: insert(x, xs.tail)

  val fruit="apples"::"oranges"::"pears"::Nil
  val nums=1::2::3::4::Nil
  val diag3=(1::0::0::Nil)::
            (0::1::0::Nil)::
            (0::0::1::Nil)::Nil
  val empty=Nil

  //x.head, x.tail : 리스트의 첫 값, 첫 값 뺀 나머지 리스트
  val List(a,b,c)=fruit //패턴매치

  //List(1, 2):::List(3,4,5) : 리스트 결합
  def append[T](xs:List[T], ys:List[T]): List[T]=
    xs match{
      case List() => ys
      case x::xs1 => x::append(xs1, ys) //x::xs1 : List의미, xs1이 tail
    }
  
  //List(1,2,3).length: 전체 리스트를 순환하기 때문에 비싼 연산
  //x.init <-> x.tail, x.last <-> x.head init, last는 전체 리스트 순회
  //reverse: 리스트 뒤집기
  def rev[T](xs:List[T]): List[T]=xs match{
    case List()=>xs
    case x::xs1=>rev(xs1):::List(x)
  }
  //xs splitAt n==(xs taken, xs drop n, xs splitAt n은 한 번만 순회
  //xs apply n==(xs drop n).head
  //indices : 리스트에서 유효한 모든 인덱스의 리스트 반환
  //flatten : 리스트의 리스트를 인자로 받아 하나의 리스트로
  //fruit.map(_.toCharArray).flatten
  //res3: List[Char] = List(a, p, p, l, e, s, o, r, a, n, g, e, s, p, e, a, r, s)

  //abcde.indices zip abcde : 원소들끼리 1대1 매칭. 남은건 버림
  //res5: scala.collection.immutable.IndexedSeq[(Int, Any)] = Vector((0,a), (1,b), (2,c), (3,d), (4,'e))
  //abcde.zipWithIndex : 인덱스와 묶기
  //List[(Char, Int)] = List((a,0), (b,1), (c,2), (d,3), (e,4))
  //unzip: 튜플의 리스트를 리스트의 튜플로

  //merge sort: 양 원소 하나씩 비교해서 둘 중 하나 선택하면서 정렬
  def msort[T] (less: (T, T)=>Boolean)
    (xs: List[T]):List[T]={ //커링
      def merge(xs: List[T], ys: List[T]): List[T]=
        (xs, ys) match{
          case (Nil, _)=>ys
          case (_, Nil)=>xs
          case(x::xs1, y::ys1)=>
            if (less(x,y)) x::merge(xs1, ys)
            else y::merge(xs, ys1)
        }
      val n=xs.length/2
      if(n==0) xs
      else {
        val (ys, zs)=xs splitAt n
        merge(msort(less)(ys), msort(less)(zs))
      }
    }
    
    def hasZeroRow(m: List[List[Int]])=
      m.exists(row=>row forall(_==0))
    def sum(xs: List[Int]): Int=(0/:xs)(_+_)
    def product(xs: List[Int]): Int=(1/:xs)(_*_)
    //(z/: List(a,b,c))(op) == op(op(op(z,a),b),c) //deprecated => foldLeft
    //(List(a,b,c):/z)(op) == op(a, op(b, op(c,z))) //deprecated => foldRight
}