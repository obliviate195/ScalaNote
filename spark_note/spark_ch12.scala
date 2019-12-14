object spark_ch12 {
  spark.range(500).rdd

  spark.range(10).toDF().rdd.map(rowObject => rowObject.getLong(0))

  spark.range(10).rdd.toDF

  val myCollection =
    "Spark The Definitive Guide : Big Data Processing Made Simple".split(" ")
  val words = spark.sparkContext.parallelize(myCollection, 2)

  words.setName("myWords")
  words.name

  spark.sparkContext.textFile("some/path/withTextFiles")
  spark.sparkContext.wholeTextFiles("some/path/withTextFiles")

  words.distinct().count()

  def startsWithS(individual: String) = {
    individual.startsWith("S")
  }
  words.filter(word => startsWithS(word)).collect

  val words2 = words.map(word => (word, word(0), word.startsWith("S")))
  words2.filter(record => record._3).take(5)
  words2.filter(record => record._3).take(5).foreach(println)

  words.flatMap(word => word.toSeq).take(5)

  words.sortBy(word => word.length * -1).collect().foreach(println)
  /* Definitive
  Processing
  Simple
  Spark
  Guide
  Data
  Made
  The
  Big
  :
   */

  spark.sparkContext.parallelize(1 to 20).reduce(_ + _)

  def wordLengthReducer(leftWord: String, rightWord: String): String = {
    if (leftWord.length > rightWord.length)
      return leftWord
    else
      return rightWord
  }
  words.reduce(wordLengthReducer)

  words.count()

  val confidence = 0.95
  val timeoutMilliseconds = 400
  words.countApprox(timeoutMilliseconds, confidence)

  words.first()

  //sc.parallelize() : 데이터세트를 넘겨주고 RDD를 생성
  //1 to 20: 20 포함하는 range
  spark.sparkContext.parallelize(1 to 20).min()
  spark.sparkContext.parallelize(1 to 20).max()

  words.take(5)
  words.takeOrdered(5)
  words.top(5)
  val withReplacement = true
  val numberToTake = 6
  val randomSeed = 100L
  words.takeSample(withReplacement, numberToTake, randomSeed)

  words.saveAsTextFile("file:/tmp/bookTitle")
  words.saveAsObjectFile("tmp/my/sequenceFilePath")

  //모든 파티션에 1값을 생성 후 합산
  words.mapPartitions(part => Iterator[Int](1)).sum()

  def indexedFunc(partitionIndex: Int, withinPartIterator: Iterator[String]) = {
    withinPartIterator.toList.map(
      value=>s"Partition: $partitionIndex=>$value").iterator
  }
  words.mapPartitionsWithIndex(indexedFunc).collect()

  words.foreachPartition{ iter=>
    import java.io._
    import scala.util.Random

    val randomFileName=new Random().nextInt()
    val pw=new PrintWriter(new File(s"\tmp\random-file-${randomFileName}.txt"))

    while (iter.hasNext){
      pw.write(iter.next)
    }
    pw.close()
  }
  //데이터셋의 모든 파티션을 배열로 변환
  spark.sparkContext.parallelize(Seq("Hello", "World"),2).glom().collect()
}
