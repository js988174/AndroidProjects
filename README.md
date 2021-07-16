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

     - var a1 = 100;
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

     - fun main(args : Array<String>) {
       f1()
       f2(100, 55.55)
       f3(100, 55.55)
       f3(100)
       f3(a1 = 200, a2 = 66.66)
       f3(a2 = 77.77, a1= 300)
       f3(a2 = 88.88)
        }

       fun f1() {
          println("f1함수가 호출되었습니다")
        }

       fun f2(a1 : Int, a2 : Double) {
          println("f2가 호출되었습니다.")
          println("a1 : ${a1}")
          println("a2 : ${a2}")
         }

       fun f3(a1 : Int = 0, a2 : Double = 0.0) {
          println("f3이 호출되었습니다")
          println("a1 : ${a1}")
          println("a2 : ${a2}")
         }

            fun f4() : Unit {
            println("f4가 호출되었습니다.")
         }

       fun f5() : Int {
            return 100
         }

       fun f6() {
          println("매개 변수가 없는 f6")
         }

       fun f6(a1 : Int) {
            println("정수 값 한개를 받는 f6 : ${a1}")
         }

       fun f6(a1 : Double) {
            println("실수 값 한개를 받는 f6 : ${a1}")
         }

       fun f6(a1 : Int, a2 : Int) {
            println("정수 값 두개를 받는 f6 : ${a1}, ${a2}")
         }

       fun f7() {
            fun f8() {
                println("f8 함수가 호출되었습니다")
            }

            println("f7 함수가 호출되었습니다")
            f8()
         }

   - 람다

         fun main(args : Array<String>) {

          f1(a1 = {
            println("f1이 전달한 함수가 호출되었습니다")
          })

          f2(a1 = {x:Int, y:Int ->
            println("f2가 전달한 함수가 호출되었습니다.")
            println("x : ${x}")
            println("y : ${y}")
            });
         }

         f3(a1 = {x:Int, y:Int ->
              x+y
          })
          f3(a1 = {x:Int, y:Int ->
              x-y
            });
         }

         fun f1(a1 : () -> Unit) {
           a1()
         }

         fun f2(a1 : (Int, Int) -> Unit) {
           a1(100, 200)
         }

         fun f3(a1 : (Int, Int) -> Int) {
          var a2 = a1(100, 200)
          println("a2 : ${a2}")
         }
         