// A comment!
/* Another comment */
/** A documentation comment */
object MyModule {
  def abs(n: Int ): Int =
    if (n < 0) -n
    else n

  def factorial(n: Int): Int = {
    def go(n: Int, acc: Int): Int =
      if (n <= 0) acc
      else go(n-1, n*acc)

    go(n, 1)
  }

  def fibonacci(n: Int): Int = {
    @annotation.tailrec
    def fibHelper(n: Int, a: Int, b: Int): Int =
      if (n == 0) a
      else if (n == 1) b
      else fibHelper(n-1, b, (a+b))
    fibHelper(n, 0, 1)
  }

  def formatResult(name: String, n: Int, f: Int => Int) = {
    val msg = "The %s of %d is %d."
    msg.format(name, n, f(n))
  }

  def main(args: Array[String]): Unit = {
    println(formatResult("absolute value", -42, abs))
    println(formatResult("factorial", 7, factorial))
    println(formatResult("fibonacci", 7, fibonacci))
    println(formatResult("increment", 7, (x: Int) => x + 1))
    println(formatResult("increment2", 7, (x) => x + 1))
    println(formatResult("increment3", 7, x => x + 1))
    println(formatResult("increment4", 7, _ + 1))
    println(formatResult("increment5", 7, x => { val r = x + 1; r}))
  }

  def binSearch[A](as: Array[A], key: A, gt: (A,A) => Boolean): Int = {
    @annotation.tailrec
    def go(low: Int, mid: Int, high: Int): Int = {
      if (low > high) -mid - 1
      else {
        val mid2 = (low + high) / 2

        val d = as(mid2)
        val greaterThan = gt(d, key)
        if (!greaterThan && !gt(key, d)) mid2
        else if (greaterThan) go(low, mid2, mid2-1)
        else go(mid2 + 1, mid2, high)
      }
    }
    go(0, 0, as.length - 1)
  }

  def isSorted[A](as: Array[A], gt: (A,A) => Boolean): Boolean = {
    @annotation.tailrec
    def go(i: Int, prev: A): Boolean = {
      if (i == as.length) true
      else if (gt(as(i), prev)) go(i + 1, as(i))
      else false
    }
    if (as.length == 0) true
    else go(1, as(0))
  }

  def partial1[A,B,C](a: A, f: (A,B) => C): B => C = {
    b => f(a,b)
  }

  def curry[A,B,C](f: (A,B) => C): A => (B => C) = {
    a => b => f(a,b)
  }

  def uncurry[A,B,C](f: A => B => C): (A,B) => C = {
    (a, b) => f(a)(b)
  }

  def compose[A,B,C](f: B => C, g: A => B): A => C = {
    a => f(g(a))
  }
}












