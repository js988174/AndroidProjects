fun main(args : Array<String>) {
    var t1 = TestClass1()
    var t2 = TestClass2(100 , 200)

    println("t2.a1 : ${t2.a1}")
    println("t2.a2 : ${t2.a2}")

    var t3 = TestClass3(100)
    println("t3.a1 : ${t3.a1}")
    println("t3.a2 : ${t3.a2}")
    println("t3.a3 : ${t3.a3}")

    var t4 = TestClass3(100, 200)
    println("t4.a1 : ${t4.a1}")
    println("t4.a2 : ${t4.a2}")
    println("t4.a3 : ${t4.a3}")

    var t5 = TestClass3(100, 200, 300)
    println("t5.a1 : ${t5.a1}")
    println("t5.a2 : ${t5.a2}")
    println("t5.a3 : ${t5.a3}")
}

class TestClass1 {
    init {
        println("객체가 생성되면 자동으로 통하는 부분입니다.")
    }
}

    class TestClass2 constructor(a1 : Int, a2 : Int) {
        var a1 = a1
        var a2 = a2
    }

class TestClass3 constructor(a1 : Int) {
    var a1 = a1
    var a2 = 0
    var a3 = 0

    constructor(a1 : Int, a2: Int) :this(a1) {
        this.a2 = a2
    }

    constructor(a1 : Int, a2: Int, a3 : Int) :this(a1) {
        this.a2 = a2
        this.a3 = a3
    }
}




