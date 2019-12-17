import scala.collection.mutable.Map

object ChecksumAccumulator{
  private val cache=Map[String, Int]()
  def calculate(s: String): Int=
    if (cache.contains(s))
      cache(s)
    else{
      val acc=new ChecksumAccumulator
      for (c<-s)  //문자열의 모든 문자를 이터레이션
        acc.add(c.toByte)
      val cs=acc.checksum()
      cache+=(s->cs)  //map 저장
      cs
    }
}
class ChecksumAccumulator{
  private var sum=0
  /* def add(b: Byte): Unit={
    //메소드 파라미터는 val
    //b=1 불가능
    sum+=b
  } */
  //프로시저 : 오직 부수 효과를 얻기 위해서만 사용하는 메소드
  //프로시저는 메소드 본체를 중괄호로 감싸자-Unit 결과타입
  // def add(b: Byte): Unit=sum+=b
  def add(b: Byte){sum+=b}
  /* def checksum(): Int={
    return ~(sum&0xFF)+1
  } */
  def checksum(): Int = ~(sum & 0xFF) + 1
}