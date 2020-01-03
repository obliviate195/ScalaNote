import java.io.PrintWriter
import java.io.File

object FileMatcher {
  private def filesHere = (new java.io.File(".")).listFiles
  
  //String을 입력 받고 Boolean을 반환하는 함수를 인자로 받음
  private def filesMatching(matcher: String => Boolean) =
    for (file <- filesHere; if matcher(file.getName))
      yield file
    
  //(fileName: String, query: String) => fileName.endsWith(query)
  //matcher
  //_.endsWith(query): 하나의 바운드 변수와 query라는 이름의 자유 변수 1개, 클로저
  def filesEnding(query: String) =
    filesMatching(_.endsWith(query))
  def filesContaining(query: String) =
    filesMatching(_.contains(query))
  //matches: 문자열에서 원하는 패턴이 있는지 알아보는 메소드. 정규표현식
  def filesRegex(query: String) =
    filesMatching(_.matches(query))

}
