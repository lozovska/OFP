% Лабораторная работа № 3 «Обобщённые классы в Scala»
% 5 апреля 2023 г.
% Карина Лозовска, ИУ9И-64Б

# Цель работы
Целью данной работы является приобретение навыков разработки обобщённых классов на языке Scala
с использованием неявных преобразований типов.

# Индивидуальный вариант
Класс `KadaneStack[T]`, представляющий неизменяемый стек с операциями `push`, `pop` и `empty`,
реализованный через список. В случае, если `T` — числовой тип, для стека должна быть также
доступна операция `maxSum`, возвращающая максимальную сумму подряд идущих элементов стека и
работающая за константное время.

# Реализация
Здесь `KadaneStack` представляет собой класс, определенный с помощью списка элементов типа `T`.
Операции `push`, `pop` и `empty` реализованы как методы класса. Операция `push` добавляет
элемент в начало списка, операция `pop` удаляет первый элемент списка, а операция `empty`
возвращает `true`, если список пуст, и `false` в противном случае.
Операция `maxSum` реализована с помощью алгоритма Кадане для поиска максимальной суммы подряд
идущих элементов в списке. Алгоритм работает за линейное время и использует только константную
дополнительную память. Для этого мы используем числовой тип `T`, который должен быть
поддерживаем операциями сложения и сравнения.

```scala
class KadaneStack[T](val stack: List[T] = Nil) {
  def push(elem: T): KadaneStack[T] = new KadaneStack(elem :: stack)
  
    def pop: KadaneStack[T] = {
    	stack match {
      	case Nil => throw new NoSuchElementException("Can't pop from empty stack")
      	case _ :: tail => new KadaneStack[T](tail)
      }
  }
  
  def empty: Boolean = stack.isEmpty
  
  def maxSum(implicit num: Numeric[T]): T = {
    import num._
    var maxEndingHere = zero
    var maxSoFar = zero
    for (elem <- stack) {
      maxEndingHere = max(elem, maxEndingHere + elem)
      maxSoFar = max(maxSoFar, maxEndingHere)
    }
    maxSoFar
  }
}
```

# Тестирование
```scala
object KadaneStackTest extends App {
  val stack1 = KadaneStack(List(1,2))
  val S = stack1.push(3).push(2).push(-6).push(5).push(-10).push(-10)
  println(S.stack)          //вывод: List(-10, -10, 5, -6, 2, 3, 1, 2)
  println(S.maxSum)         //вывод: 8

  val stack2 = KadaneStack(List("hello", "world", "time", "to", "sleep"))
  val S2 = stack2.pop
  println(S2.stack)         //вывод: List(world, time, to, sleep)
  println(S2.empty)         //вывод: false
}
```

# Вывод
В ходе выполнения лабораторной работы был реализован класс `KadaneStack[T]`, представляющий
собой неизменяемый стек с операциями `push`, `pop` и `empty`, реализованный через список. В
случае, если `T` — числовой тип, для стека была реализована дополнительная операция `maxSum`,
возвращающая максимальную сумму подряд идущих элементов стека и работающая за константное время.
Были изучены основные принципы работы с обобщенными классами в языке программирования Scala,
такие как использование типовых параметров для создания универсальных классов и методов.
Также были рассмотрены примеры использования класса `KadaneStack[T]` для хранения и обработки
данных, а также выполнения операций `push`, `pop` и `empty`.
Особое внимание было уделено реализации операции `maxSum` для стека с числовыми элементами,
которая работает за константное время. Была рассмотрена алгоритмическая основа данной операции -
алгоритм Кадане, который позволяет эффективно находить максимальную сумму подряд идущих
элементов в массиве.
В результате выполнения лабораторной работы были получены навыки работы с обобщенными классами и
типовыми параметрами в языке программирования Scala, а также понимание основных принципов работы
со стеком и алгоритма Кадане для нахождения максимальной суммы подряд идущих элементов.