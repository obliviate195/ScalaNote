//chapter13 패키지와 임포트

object ch13_note{
  def showFruit(fruit: Fruit){
    import fruit._
    println(name+"s are "+color)
  }

  import java.util.regex
  class AStarB{
    //java.util.regex.Pattern 접근하기.
    val pat=regex.Pattern.compile("a*b")
  }

  import Fruits.{Apple, Orange}
  //객체 이름 변경.
  import Fruits.{Apple=>McIntosh, Orange}
  //제외하고 멤버 불러오기
  import Fruits.{Pear=>_, _}

  //암시적 임포트
  //스칼라가 모든 프로그램에 먼저 추가
  //나중에 임포트한 패키지가 먼저 임포트한 패키지를 가린다.
  import java.lang._
  import scala._
  import Predef._

  class Outer{
    class Inner{
      private def f(){println("f")}
      class InnerMost{
        f() //가능.
      }
    }
    //(new Inner).f() 오류.
  }

  //클래스는 동반 객체의 비공개 멤버에 모두 접근 가능. 역도 성립.
  class Rocket{
    import Rocket.fuel
    private def canGoHomeAgain=fuel>20
  }
  object Rocket{
    private def fuel = 10
    def chooseStrategy(rocket: Rocket){
      if(rocket.canGoHomeAgain)
        goHome()
      else
        pickAStar()
    }
    def goHome(){}
    def pickAStar() {}
  }
  
}

package p{
  class Super{
    protected def f() {println("f")}
  }
  class Sub extends Super{
    f()
  }
  class Other{
    //(new Super).f() 오류: 
  }
}

package bobsrockets{
  package navigation{
    //bobsrockets 패키지 내부에 있는 모든 객체와 클래스에서
    //Navigator에 접근 가능.
    private[bobsrockets] class Navigator {
      //Navigator의 모든 서브클래스에서 접근 가능.
      //Navigator 클래스를 둘러싼 패키지 중 navigation에 있는 모든 코드 안에서 접근 가능
      protected[navigation] def useStartChart() {}
      class LegOfJourney{
        private[Navigator] val distance = 100

      }
      //객체 전용: 그 정의를 포함하는 객체 내부에서만 접근 가능.
      private[this] var speed=200
    }
  }
  package launch{
    import navigation._
    object Vehicle{
      private[launch] val guide = new Navigator
    }
  }
}