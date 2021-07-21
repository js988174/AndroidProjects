# Kotlin

## 2021-07-16, 금

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
      - while문

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

    - main

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
                a1++
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



    - 객체지향 프로그래밍(OOP)

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


## 2021-07-17, 토

- 객체지향 프로그래밍

     - 생성자

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

     - 상속

        fun main() {
                var s1 =SubClass1()
                println("s1.subMember1 : ${s1.subMember1}")
                s1.subMember1()

                println("s1.superMember1 : ${(s1.subMember1)}")
                s1.superMethod1()
            }

            open class SuperClass1 {
                var superMember1 = 100

                fun superMethod1() {
                    println("superClass1의 메서드 입니다")
                }
            }

            class SubClass1 : SuperClass1() {
                var subMember1 = 200

                fun subMember1() {
                    println("SubClass1의 메서드 입니다.")
                }
            }

            open class SuperClass2(val a1:Int)

            class SubClass2 : SuperClass2(100)

            class SubClass3 : SuperClass2 {

                constructor() : super(100)
            }

     - Property

        - var(variable), val(value)는 보조 생성자에는 붙일 수 없다.

        - get set 호출

                    fun main() {

                val obj1 = TestClass1(100,200)
                println("obj1.a1 : ${obj1.a1}")
                println("obj1.a2 : ${obj1.a2}")

                obj1.a1 = 1000
                println("obj1.a1: ${obj1.a1}")

                println("----------------------------")

                val obj2 = TestClass2()

                obj2.v1 = 100
                // obj2.v2 = 200
                println("obj2.v1 : ${obj2.v1}")
                println("obj2.v2 : ${obj2.v2}")

                obj2.v3 = 5000
                println("obj2.v3 : ${obj2.v3}")
            }

            class TestClass1 constructor(var a1:Int, val a2:Int)


            class TestClass2 {
                var v1:Int = 0
                val v2:Int = 0
                var v3:Int = 100
                // get() = field
                get() {
                    println("get 호출")
                    return field
                }
                set(value) {
                    println("set 호출")
                    field = value
                }
            }

     - 지연초기화

        - lateinit : var로 선언된 변수의 초기화를 뒤로 미룰 수 있다.
          lazy : val로 선언된 변수의 값을 초기화 한다는 의미
          val => lateinit으로 지연 초기화 불가능
          var => lateinit으로 지연 초기화 가능
          주의할 점 : lateinit은 변수의 값을 사용하기 전에 반드시 초기화가
                      이러어져야 한다.


          fun main() {
                val obj1 = TestClass1()
                println("obj1.a1 : ${obj1.a1}")
                println("obj1.a2 : ${obj1.a2}")
                //println("obj1.a3 : ${obj1.a3}")

                obj1.testMethod1()
            }

            class TestClass1 {
                var a1: Int = 100
                var a2: Int
                lateinit var a3:String

                  val a4: String by lazy {
                        println("a4 init")
                        "문자열2"
                    }

                init {
                    a2 = 200
                }

                fun testMethod1() {
                    if(::a3.isInitialized == false) {
                        a3 = "문자열"
                    }
                    println("a3 : $a3")
                }
            }
    
    ## 2021-07-19, 월


     - Companion

          - companion 멤버는 객체 생성 없이 클래스의 이름을 통해 접근해 사용한다.
        
     - Data Class 

          - Data Class 는 개발 편리성을 위해 equals, hashCode, copy, toString, componentN을 
            자동으로 구현해준다. 
            ex) data class Person(var name: String, var age:Int, var gende: String)

     - 제네릭 
         -> 객체를 생성할 때 타입을 결정하는 개념을 Generic이라고 부른다.

       - 가변성

           - 불변성 : 제네릭이 설정된 객체의 주소 값을 같은 타입의 제네릭이 설정된 변수에만 담을 수 있다.
           - 공변성 : 제네릭이 설정된 객체의 주소 값을 부모 클래스 타입의 제네릭이 설정된
                     변수에도 담을 수 있다. (out)
           - 반 공변성 : 제네릭이 설정된 객체의 주소 값을 자식 클래스  타입의 제네릭이 설정된 변수에도
                       담을 수 있다. (in)


     - 중첩 클래스

       - 클래스안에 클래스를 정의하는 것을 중첩 클래스라고 한다.

        -익명 중첩 클래스

            - 이름이 없는 클래스 
            - 인터페이스나 추상클래스를 사용할 때 이용한다.
            - 이름이 없으므로 객채 생성x
            - 인터페이스를 구현한 클래스나 추상 클래스를 상속받은 클래스를 만듬과 동시에 메서드를 
              Overriding하고 객체를 생성하는 것 까지 수행된다.

     - Null 처리 

       - !! 연산자

             - null을 허용하지 않는 형태의 값으로 변환하는 연산자이다.
             - null을 허용하는 변수에 담긴 값을 null을 허용하지 않는 형태의 변수에 담을 경우 사용
             - 변환 과정에서 null 값이 들어 있을 경우 오류 발생

       - ?: 연산자
             - 참조변수에 null 이 들어있으면 지정된 기본값을 반환한다.


       - ?. 연산자
             - 참조변수를 통해 메서드를 호출하거나 멤버 변수를 사용할 떄 참조변수에 객체의
               주소값이 들어 있다면 객체에 접근해서 메서드나 변수를 사용한다.

    
     - 형변환

       - 변수에 담긴 값이나 객체를 다른 형태로 변환하는 것을 의미한다.
       - 다른 클래스 타입의 객체로 변환하는 것을 의미한다.


            - is 연산자

               - 형 변환이 가능하면 변환을 하고 true를 반환한다
               - if 문으로 구성하여 사용하며 if 문 내에서만 변환된 타입을 사용하고 if 문을 나가게 되면
                 변환되기 전의 타입으로 다시 변경된다.

            - Any 타입
               
               - Kotlin은 모든 클래스가 직접 혹은 간접적으로 Any 클래스를 상속받는다.
               - 모든 객체의 주소 값은 Any 타입 참조 변수에 담을 수 있다.
        

    ## 2021-07-21, 수

        - 열거형 

            - 특정 값을 의미하는 상수들을 모아 관리하는 개념 

        -  Sealed Class

            - 객체를 모아 관리하는 개념
            - 객체가 가지고 있는 변수에 값을 설정하여 지정된 값을
              변경하는 것이 가능하다.
            - 열거형 사용시 상수가 의미하는 값을 수시로 변경하거나
              다양한 형태로 사용하고자 할 떄 사용한다.

        - 리플렉션

             - 프로그램 실행 중에 프로그램의 구조를 분석하는 기법
             - 객체의 클래스 정보, 생성자 정보, 프로퍼티 정보를 실행
               중에 파악한다.


        -   
             