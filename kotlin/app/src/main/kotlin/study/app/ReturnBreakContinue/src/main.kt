fun main(args : Array<String>) {
    f1(5)
    f1(10)

    var a1 = 0

    while (a1 < 10) {
        a1++
        if (a1 > 5) {
            break
        }
        println("a1 : ${a1}")
    }

    println("--------------------------")
    a1 = 0

    while (a1 < 10) {
        a1++b
        if (a1 % 2 == 0) {
            continue
        }
        println("a1 : ${a1}")
    }
}

fun f1(a1 : Int) {

        if(a1 == 10) {
            return
        }
    println("이 부분이 수행이 될까요?")
    }


