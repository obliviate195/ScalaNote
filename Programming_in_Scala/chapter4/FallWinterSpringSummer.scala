import ChecksumAccumulator.calculate
object FallWinterSpringSummer extends App{
  for(season<-List("fall", "winter", "sprint"))
    println(season+": "+calculate(season))
}