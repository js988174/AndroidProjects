fun main(args : Array<String>) {

    var a1 = 2

    when (a1) {
        1 -> {
            println("a1은 1입니다")
        }
        2 -> {
            println("a1은 2입니다")
        }
        3 -> {
            println("a1은 3입니다")
        }
        else -> {
            println("a1은 ?입니다")
        }
    }
    when (a1) {
        0, 1 -> {
            println("a1은 0이거나 1입니다")
        }
        2, 3 -> {
            println("a1은 2이거나 3입니다")
        }
        else -> {
            println("a1은 ?입니다")
        }
    }

    var a2 = 55.55
    when (a2) {
        33.33 -> {
            println("a2는 33.33 입니다")
        }
        55.55 -> {
            println("a2는 55.55 입니다")
        }
        else -> {
            println("a2는 33.33, 55.55가 아닙니다")
        }
    }
    // 자바와는 다르게 모든 타입이 가능하다
    var a3 = "문자열1"
    when(a3) {
        "문자열1" -> {
            println("a3은 문자열1 입니다")
        }
        "문자열2" -> {
            println("a3은 문자열2 입니다")
        }
        else -> {
            println("a3은 문자열1, 문자열2가 아닙니다")
        }
    }

    var a4 = 5
    when(a4) {
        in 1..3 -> {
            println("a6은 1 ~ 3사이 숫자입니다")
        }
        in 4..6 -> {
            println("a6은 4 ~ 6사이 숫자입니다")
        }
        !in 1..6 -> {
            println("a6은 1 ~ 6사이 숫자입니다")
        }
        else -> {
            println("a6은 위 모든 조건을 만족하지 않습니다")
        }
    }

    var a7 = f1(1)
    var a8 = f1(2)
    var a9 = f1(3)
    println("a7 : ${a7}")
    println("a8 : ${a8}")
    println("a9 : ${a9}")
}

    fun f1(a1 : Int) = when(a1) {
        1 -> {
            100
        }
        2 -> {
            200
        }
        else -> {
            300
        }
    }
