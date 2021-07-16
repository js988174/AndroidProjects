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

       println("정수 : ${100}")
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

        fun main(args : Array<String>) {
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
         
    - 연산자

         fun main(args : Array<String>) {
          var a1 = 10
          var a2 = -a1
          println("a2 : ${a2}")

          a2 = a1.unaryMinus()
          println("a2 : ${a2}")

          a2 = +a1
          println("a2 : ${a2}")

          a2 = a1.unaryPlus()

          var a3 = true
          var a4 = !a3
          println("a4 : ${a4}")

          a4 = a3.not()
          println("a4 : ${a4}")

          var a5 = 10
          var a6 = 10
          var a7 = a5++        // a5 = a5 + 1
          var a8 = a6--        // a6 = a6 - 1
          println("a5, a7 : ${a5}, ${a7}")  // 11, 10
          println("a6, a8 : ${a6}, ${a8}")  // 9, 10

          a5 = 10
          a6 = 10
          a7 = ++a5
          a8 = --a6
          println("a5, a7 : ${a5}, ${a7}")  // 11, 11
          println("a6, a8 : ${a6}, ${a8}")  // 9, 9

          a5 = 10
          a6 = 10
          a7 = a5.inc()
          a8 = a6.inc()
          println("a5, a7 : ${a5}, ${a7}")
          println("a6, a8 : ${a6}, ${a8}")

          a5 = 10
          a6 = 3
          a7 = a5 + a6
          a8 = a5.plus(a6)
          println("a7 : ${a7}")
          println("a8 : ${a8}")

          a7 = a5 - a6
          a8 = a5.minus(a6)
          println("a7 : ${a7}")
          println("a8 : ${a8}")

          a7 = a5 * a6
          a8 = a5.times(a6)
          println("a7 : ${a7}")
          println("a8 : ${a8}")

          a7 = a5 / a6
          a8 = a5.div(a6)
          println("a7 : ${a7}")
          println("a8 : ${a8}")

          var a10 = a6..a5
          var a11 = a6.rangeTo(a5)
          println("a10 : ${a10}")
          println("a11 : ${a11}")

          // 코틀린은 대입 연산자를 사용하지 않는다.

        - 비교 연산자

          var a13 = 10
          var a14 = a13 == 10
          println("a14 : ${a14}") // true

          a14 = a13.equals(10)
          println("a14 : ${a14}") // true

          a14 = a13 != 10
          println("a14 : ${a14}") // false

          a14 = !(a13.equals(10))
          println("a14 : ${a14}") // false

          a14 = a13 > 20
          println("a14 : ${a14}") // false

          a14 = a13.compareTo(20) > 0
          println("a14 : ${a14}") // false

          a14 = a13 < 20
          println("a14 : ${a14}") // true

          a14 = a13.compareTo(20) < 0
          println("a14 : ${a14}") // true

          a14 = a13 >= 10
          println("a14 : ${a14}") // true

          a14 = a13.compareTo(10) >= 0
          println("a14 : ${a14}") // true

          a14 = a13 <= 10
          println("a14 : ${a14}") // true

          a14 = a13.compareTo(10) <= 10
          println("a14 : ${a14}") // true
    
    - if문

         fun main(args : Array<String>) {

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

    - when문
          
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


