package bobsdelights
abstract class Fruit(
  val name: String,
  val color: String
)
object Fruits {
  object Apple extends Fruit("apple", "red")
  object Orange extends Fruit("orange", "orange")
  object Pear extends Fruit("pear", "yellowish")
  val menu=List(Apple, Orange, Pear)
}

//Fruit에 간단하게 접근.
import bobsdelights.Fruit
//bobsdelights의 모든 멤버에 간단하게 접근.
import bobsdelights._
//Fruits의 모든 멤버에 간단하게 접근
import bobsdelights.Fruits._