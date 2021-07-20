fun main(args : Array<String>){
    var a1 = 100

    if (a1 > 50) {
        println("a1은 50보다 큽니다")
    }

    if (a1 < 50) {
        println("a1은 50보다 작습니다")
    }

    if (a1 > 50) {
        println("a1은 50보다 큽니다")
    } else {
        println("a1은 50보다 크지 않습니다")
    }

    if (a1 < 50) {
        println("a1은 50보다 작습니다")
    } else {
        println("a1은 50보다 작지 않습니다")
    }

    if (a1 == 20) {
        print("a1은 20입니다")
    } else if (a1 == 50) {
        println("a1은 50입니다")
    } else if (a1 == 100) {
        println("a1은 100입니다")
    } else {
        println("a1은 20, 50, 100이 아닙니다")
    }
}
