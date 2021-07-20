fun main() {
    val obj1 = TestClass1()
    val obj2 = TestClass2()

    testFun1()
    testFun2()
}

open abstract class AbstractClass1 {
    open abstract fun abstractMethod1()
}

open abstract class AbstractClass2 {
    open abstract fun abstractMethod2()
}

fun testFun1(obj1:AbstractClass1) {
    obj1.abstractMethod1()
}

fun testFun2(obj2:AbstractClass2) {
    obj2.abstractMethod2()
}

class TestClass1 : AbstractClass1() {
    override fun abstractMethod1() {
        println("TestClass1의 abstractMethod1")
    }
}

class TestClass2 : AbstractClass2() {
    override fun abstractMethod2() {
        println("TestClass2의 abstractMethod2")
    }
}