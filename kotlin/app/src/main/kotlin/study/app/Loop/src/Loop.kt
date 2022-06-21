fun main(args : Array<String>) {

    var a1 = 1..10

    for(item in a1) {
        println("a1 : ${item}")
    }
    println("---------------------------")

    for((idx, item) in a1.withIndex()) {
        println("${idx} : ${item}")
    }

    println("-----------------------------")

    var a2 = 0

    while (a2 < 10) {
        println("a2 : ${a2}")
        a2++;
    }

    var a3 = 10;

    do {
        println("a3 : ${a3}")
        a3++;
    } while (a3 < 10)
}