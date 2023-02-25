val mymerge: (List[Int], List[Int]) => List[Int] = {
  case(Nil, Nil) => Nil
  case(list1, Nil) => list1
  case(Nil, list2) => list2
  case (list1, list2) =>
  	val list1_Head :: list1_Tail = list1
  	val list2_Head :: list2_Tail = list2
  	if (list1_Head < list2_Head) list1_Head :: mymerge(list1_Tail, list2)
  	else list2_Head :: mymerge(list1, list2_Tail)
}

object Main {
  def main(args: Array[String]) = {
    val list1 = List(1, 2, 9, 15, 20)
    val list2 = List(3, 6, 10, 12, 19)
    val list = mymerge(list1, list2)
    println(list)
  }
}
