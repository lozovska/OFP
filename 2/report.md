% Лабораторная работа № 2 «Введение в объектно-ориентированное программирование на языке Scala»
% 5 апреля 2023 г.
% Карина Лозовска, ИУ9И-64Б

# Цель работы
Целью данной работы является изучение базовых объектно-ориентированных возможностей языка Scala.

# Индивидуальный вариант
Мультимножество строк с операциями объединения («+»), пересечения («*») и вычитания («-»). 
В мультимножестве одна строка может содержаться в нескольких экземплярах.

# Реализация
Код использует иммутабельные структуры данных и функциональный стиль программирования. Класс
`MultisetString` имеет конструктор по умолчанию, который создает пустое мультимножество, и
конструктор, который принимает карту элементов и их количества.
Метод `add` добавляет элемент в мультимножество. Он создает новый экземпляр `MultisetString`,
который содержит все элементы из текущего мультимножества и новый элемент с увеличенным
количеством.
Метод `remove` удаляет элемент из мультимножества. Он создает новый экземпляр `MultisetString`,
который содержит все элементы из текущего мультимножества, кроме удаленного элемента. Если
элемент встречается в мультимножестве более одного раза, то метод уменьшает его количество на
единицу.
Метод `+` выполняет операцию объединения двух мультимножеств. Он создает новый экземпляр
`MultisetString`, который содержит все элементы из обоих мультимножеств, суммируя количество
элементов, если они встречаются в обоих мультимножествах.
Метод `*` выполняет операцию пересечения двух мультимножеств. Он создает новый экземпляр
`MultisetString`, который содержит только те элементы, которые встречаются в обоих
мультимножествах, с количеством, равным минимальному количеству элемента в обоих
мультимножествах.
Метод `-` выполняет операцию вычитания одного мультимножества из другого. Он создает новый
экземпляр `MultisetString`, который содержит только те элементы, которые встречаются в первом
мультимножестве, но не во втором, с количеством, равным разности количества элемента в первом и
втором мультимножествах.
Метод `toString` возвращает строковое представление мультимножества.

```scala
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
```

# Тестирование
```scala
// Create some multisets
val set1 = new MultisetString().add("a").add("b").add("c").add("a").add("c")
val set2 = new MultisetString().add("b").add("b").add("c").add("d")

// Perform operations on the multisets
println(set1 + set2)        // вывод: [a, a, b, b, b, c, c, c, d]
println(set1 * set2)        // вывод: [b, c]
println(set1 - set2)        // вывод: [a, a, c]
```

# Вывод
В ходе выполнения данной лабораторной работы было проведено более детальное ознакомление с
базовыми объектно-ориентированными возможностями языка программирования Scala. Были изучены
основные концепции объектно-ориентированного программирования, такие как классы и объекты.
Был получен опыт разработки классов на языке Scala, в том числе создание конструкторов и
методов классов. Кроме того, были изучены возможности контейнерных классов, доступных в
стандартной библиотеке языка Scala, такие как Map. Были рассмотрен пример использования этих
классов для хранения и обработки данных.
В результате выполнения данной лабораторной работы были получены навыки работы с
объектно-ориентированным языком программирования Scala, а также понимание основных принципов
объектно-ориентированного программирования и использования контейнерных классов для хранения и
обработки данных.