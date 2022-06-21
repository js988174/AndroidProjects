fun main() {

    val obj1:SubClass1 = SubClass1()
    println("obj1.subA1 : ${obj1.subA1}")
    obj1.subMethod1()

    println("obj1.superA1 : ${obj1.superA1}")

    println("------------------------------------")

    val obj2:SuperClass1 = obj1

    println("obj2.superA1 : ${obj2.superA1}")
    obj1.subMethod1()

    println("obj2.subA1 : ${obj2.subA1}")
    obj2.superMethod1()
}

open class SuperClass1 {

    var superA1 = 100

    fun superMethod1() {
        println("SuperClass1의 superMethod1 입니다")
    }
}

class SubClass1 : SuperClass1() {

    var subA1 = 200

    fun subMethod1() {

        println("subClass1의 subMethod1 입니다")
    }

    open class SuperClass2 {

        open fun superMethod2() {
            println("SuperClass2의 superMethod2")
        }
    }
    
    class SubClass2 : SuperClass2() {

        override fun superMethod2() {
            println("SubClass2의 superMethod2")
        }
    }
}