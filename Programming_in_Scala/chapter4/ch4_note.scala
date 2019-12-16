object ch4_note{
  //new ChecksumAccumulator로 생성
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
    def checksum(): Int=~(sum&0xFF)+1
  }
  val acc=new ChecksumAccumulator
  val csa=new ChecksumAccumulator

  //ChecksumAccumulator 내의 sum 재할당 가능(sum이 public일 때)
  acc.sum=0
}