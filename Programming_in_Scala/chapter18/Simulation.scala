package org.stairwaybook.simulation
abstract class Simulation {
  //Action 타입 지정.
  type Action = () => Unit
  //지정 시간에 실행할 필요가 있는 액션.
  case class WorkItem(time: Int, action: Action)
  private var curtime = 0
  def currentTime: Int = curtime
  //실행하지 않은 잔여 작업 항목.
  private var agenda: List[WorkItem] = List()
  private def insert(ag: List[WorkItem], item: WorkItem): List[WorkItem] = {
    if (ag.isEmpty || item.time < ag.head.time) item :: ag
    else ag.head :: insert(ag.tail, item)
  }
  def afterDelay(delay: Int) (block: => Unit) { // (=> Unit)이 type->이름에 의한 호출.
    val item = WorkItem(currentTime + delay, () => block)
    agenda = insert(agenda, item)
  }
  private def next() {
    //@unchecked: Nil인 경우를 빠뜨렸다는 경고가 생성되지 않음.
    (agenda: @unchecked) match {
      case item :: rest =>
        agenda = rest
        curtime = item.time
        item.action()
    }
  }
  def run() {
    afterDelay(0) {
      println("*** simulation started, time = "+
        currentTime +" ***")
    }
    while (!agenda.isEmpty) next()
  }
}
