fun main() {

    val obj1 = TestClass1()
    obj1.testMethod1()
}

class TestClass1 {

  var a1 = 100

    fun testMethod1() {

        var a1 = 200

        println("a1 : $a1")
        println("this.a1 : ${this.a1}")

        fun testMethod2() {
            println("testMethod1 내부의 testMethod2")
        }

        testMethod2()
        this.testMethod2()
    }

    fun testMethod2() {
        println("testMethod2")
    }
}

