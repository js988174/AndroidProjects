# Kotlin

## 2021-07-18, 금

- 코틀린 기본 문법

  - 메인함수 
     fun main(args : Array<String>) {
        println("Hello World")  
     }

     - 주석, 출력은 자바와 동일하다.

     - 기본적으로 문장의 마지막에 세미클론을 사용하지 않는다.
    하지만 세미클론을 쓰는것도 허용한다. 

     - 변수의 값을 출력할 때는 ${변수명}을 사용한다.  

  - DataType

     - println("정수 : ${100}")
       println("정수 (Long) : ${100L} ")
       println("실수 : ${55.55}")
       println("실수 (Float): ${55.55f}")
       println(1000000000)
       println(1_000_000_000) <- 숫자 구분을 위해 언더바 허용
       println("문자열입니다")
       println('a')
     
     - 변수
        var a1 = 100;
       println("a1 : ${a1}")
       // a1 = 55.55
       var a2 = "안녕하세요"
       println("a2 : ${a2}")
       var a3 : Int = 100
       println("a3 : ${a3}")

       // var a4 : String = 100

       var a4 = 100
       println("a4 : ${a4}")

       a4 = 200
       println("a4 : ${a4}")

       val a5 = 100
       println("a5 : ${a5}")

       // a5 = 200
       
     - 함수
