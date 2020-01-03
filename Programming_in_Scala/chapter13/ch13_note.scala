//chapter13 패키지와 임포트

package bobsrockets.navigation
class Navigator

package bobsrockets.navigation{
  class Navigator
}

package bobsrockets{
  package navigation{
    class Navigator{
      val map=new StarMap
    }
    class StarMap
  }
  class Ship{
    val nav=new navigation.Navigator
  }
  package fleets{
    class Fleet{
      def addShip(){new Ship}
    }
  }
}