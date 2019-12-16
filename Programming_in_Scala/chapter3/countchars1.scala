import scala.io.Source

if(args.length>0){
  for(lines<-Source.fromFile(args(0)).getLines())
    println(line.length+" "+line)

}
else
  Console.err.println("Please enter filename")
