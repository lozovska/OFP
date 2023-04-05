class MultisetString(private val elements: Map[String, Int]) {
  def this() = this(Map.empty[String, Int])
  
  // Add an element to the multiset
  def add(element: String): MultisetString = {
    val count = elements.getOrElse(element, 0)
    new MultisetString(elements + (element -> (count + 1)))
  }
  
  // Remove an element from the multiset
  def remove(element: String): MultisetString = {
    val count = elements.getOrElse(element, 0)
    if (count > 1) {
      new MultisetString(elements + (element -> (count - 1)))
    } else {
      new MultisetString(elements - element)
    }
  }
  
  // Union operation
  def +(other: MultisetString): MultisetString = {
    new MultisetString(elements ++ other.elements.map {
      case (element, count) => element -> (count + elements.getOrElse(element, 0))
    })
  }
  
  // Intersection operation
  def *(other: MultisetString): MultisetString = {
    new MultisetString(elements.flatMap {
      case (element, count) =>
        val otherCount = other.elements.getOrElse(element, 0)
        val res = count.min(otherCount)
        if (res > 0) {
          Some(element -> res)
        } else {
          None
        }
    })
  }
  
  // Subtraction operation
  def -(other: MultisetString): MultisetString = {
    new MultisetString(elements.flatMap {
      case (element, count) =>
        val otherCount = other.elements.getOrElse(element, 0)
        if (count > otherCount) {
          Some(element -> (count - otherCount))
        } else {
          None
        }
    })
  }
  
  override def toString(): String = {
    "[" + elements.toSeq.flatMap {
      case (element, count) => Seq.fill(count)(element)
    }.mkString(", ") + "]"
  }
}

// Create some multisets
val set1 = new MultisetString().add("a").add("b").add("c").add("a").add("c")
val set2 = new MultisetString().add("b").add("b").add("c").add("d")

// Perform operations on the multisets
println(set1 + set2)
println(set1 * set2)
println(set1 - set2)