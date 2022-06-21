fun main() {
    var obj1 = TestClass1(100, 200)
    var obj2 = TestClass2(100, 200)

    println("obj1.a1 : ${obj1.a1}")
    println("obj1.a2 : ${obj1.a2}")

    println("obj2.a1 : ${obj2.a1}")
    println("obj2.a1 : ${obj2.a2}")

    var obj3 = TestClass1(100 , 200 , 300)
    var obj4 = TestClass1(100 , 200 , 300)

    println("obj3.a1 : ${obj3.a1}")
    println("obj3.a2 : ${obj3.a2}")
    println("obj3.a3 : ${obj3.a3}")

    println("obj4.a1 : ${obj4.a1}")
    println("obj4.a2 : ${obj4.a2}")
    println("obj4.a3 : ${obj4.a3}")
}

class TestClass1(var a1:Int, var a2:Int ) {

    var a3:Int = 0

    init {
        println("TestClass1의 init")
    }

    constructor(a1:Int, a2:Int, a3:Int) : this(a1, a2) {
        this.a3 = a3
    }
}
data class TestClass2(var a1:Int, var a2:Int) {

    var a3:Int = 0

    init {
        println("TestClass2의 init")
    }

    constructor(a1:Int, a2:Int, a3:Int) : this(a1, a2) {
        this.a3 = a3
    }

    fun testMethod2() {
        println("TestClass2의 testMethod2입니다")
    }
}
