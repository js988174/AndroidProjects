fun main(args : Array<String>) {

   var t1 = TestClass1()
    println("t1 : ${t1}")

    var t2 = TestClass2()
    var t3 = TestClass2()
    println("t2.a1 : " + t2.a1)
    println("t2.a2 : " + t2.a2)

    println("t3.a1 : " + t3.a1)
    println("t3.a2 : " + t3.a2)

    t2.a1 = 100
    t2.a2 = 200

    println("t2.a1 : " + t2.a1)
    println("t2.a2 : " + t2.a2)

    println("t3.a1 : " + t3.a1)
    println("t3.a2 : " + t3.a2)

    var t4 = TestClass3()
    t4.f1()
}

class TestClass1 {

}

class TestClass2 {
    var a1 = 0
    var a2 = 0
}

class TestClass3 {
    fun f1 () {
        println("f1 메서드가 호출되었습니다")
    }
}