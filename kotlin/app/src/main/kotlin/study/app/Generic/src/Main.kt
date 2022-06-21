fun main()  {
    val t1 =TestClass1<Int>()
    t1.testMethod1(100)

    val t2 = TestClass1<String>()
    t2.testMethod1("문자열")

    val t3 = TestClass2<Int>(100)
    t3.testMethod2(200)

    val t4 = TestClass2<String>("문자열1")
    t4.testMethod2("문자열2")

    println("----------------------------")
}

class TestClass1<T> {

    fun testMethod1(a1:T) {
        println("a1 : $a1")
    }
}

class TestClass2<T>(var a1: T) {
    fun testMethod2(a2: T) {
        println("a1 : $a1")
        println("a2 : $a2")
    }
    }

    class TestClass3<A, B> {

        fun testMethod(a1:A, a2:B) {
            println("a1 : ${a1}")
            println("a2 : ${a2}")
        }
}