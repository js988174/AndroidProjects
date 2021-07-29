# Android-App


   ## Log 

        - 로그란 ? 
          - 로그를 사용하여 해당 코드의 흐름을 알기 위해서

            - Log.d("로그 검색용", "출력문 = 로그 내용")
            - v : verbose -> 상세한 로그 내용 출력
            - d : debug -> 개발에 필요한 내용 출력
            - i : information -> 일반적인 메시지 전달
            - w : warning -> 경고성 메시지 전달 
            - e : error -> 에러 메시지를 출력

    


  ## 변수 


        - 변수란 ?
            - 값을 임시로 메모리에 저장하고 그 저장 공간에 이름을 부여

              - var 변수명(이름) = 값 
              - var 변수명 : 타입  변수명 = 값  (값 변경 가능)
            
              - val 변수명(이름) = 값   (값 변경 불가능)


  ## 상수 

        - 상수란 ?
            - 변하지 않는 값을 입력해둘 때 사용하며, 읽기 전용 변수인 val
              앞에 붙음(Int, Long 같은 기본 문자열인 String만 입력 가능)

            - const val PI = 3.141592

  ## 조건문 

        - if
           - 특정 조건에 따라 실행을 달리하고자 할 때 사용

           - if (조건식) {
              조건식이 참일경우 실행
           } else if {조건식} {
              조건식이 참일 경우 실행
           } else {
             조건식이 거짓일 경우 실행
           }


        - when 
          - when (파라미터)
              비교값1 -> {
                // 변수값이 비교값1과 같다면 실행
              }
              비교값2 -> {
                // 변수값이 비교값2와 같다면 이 영역이 실행됩니다.
              } else -> {
                // 변수값이 앞에서 비교한 값들과 다르면 이 영역에서 실행
              }


        - 배열과 컬렉션
          - Array : 개수 특정
          - Coleection : 개수 특정 없음 무한 입력 가능

          - 배열에 있는 값 꺼내기
            - 배열명[인덱스]  ex) var seventhValue = intArray[6]
            - 배열명.get(인덱스) ex) var tenthValue = intArray.get(9)

          - 컬렉션 종류 : List, Map , Set 이 있으면 거의 앞에 Mutable을 붙여 사용한다


          - while : for문이 특정 범위라면 while문은 특정 조건이 만족할떄까지 반복하기 위한 구조 

          - while do : 최초 값이 조건식을 만족하지 않았을 경우 실행 코드가 달라지는 것입니다.


        - 스코프 함수

          - this로 사용 되는 함수 : run, apply , with 
           -> 호출 되는 대상이 null 일경우 with 보다는 apply나 run을 사용하느것이 효율적이다
              이유는 with가 확장 함수가 아니기 때문이다.
          - it으로 사용되는 함수 : let, also


## 화면 구성

        - 디자인 요소
          - 말줄입 표시하기 : elipsize 
          - 전광판처럼 텍스트 만들기 : 'auto', focusablen TouchMode true로 설정하기
                                   -> .. 화면    전광판..면         전광판화..
          - 클릭하면 사라지는 미리보기 : hint
          - 키보드 모양 설정 : InputType
          - 이미지 크기 설정 : scaleType
          - 이미지 영역에 색채우기 : tint
          - binding 사용법 : 1 ) build.gradle에 buildFeatures { viewBinding true} 추가 해주기
                             2 ) main에 val binding by lazy {ActivityMainBinding.inflate(layoutInflater)}
          - radio 는 1개만 선택 가능 checkbox는 여러개 선택가능 
          - 프로그래스바 : 진행 상태를 나타내는 위젯 1. 시간이 일정하지 않은 작업  2. 시간이 일정할때 % 형식
          - 레이팅바 : 현재 별점이 텍스트뷰에 표시되도록 하겠습니다.

       - 리소스
          - 이미지 폴더를 hdpi, mdpi, xhdpi, xxhdpi, xxxhdpi 최소 5개 해상도로 만들어서 사용

       - 액티비티 
            - onCreate() : 액티비티 생성 -> 프래그먼트 자원을 초기화할 때 사용
            - onStart() : 화면에 보이기 시작함 -> 화면 생성 후에 화면에 입력된 값을 초기화
            - onResume() : 실제 액티비티 실행 -> onStart()와 같은 용도이고 다른 점은 start를 거치지 않고 Resume이 바로 호출됨
            - onPause() : 화면의 일부가 다른 액티비에 가려짐 -> 플레이어 일시정지 할 때 사용
            - onStop() : 다른 액티비티가 실행되면서 완전히 가려짐 -> 동영상 일시정지가 아닌 정지를 하는 용도
            - onDestroy() : 종료됩니다.

       - 백스택 
            : 액티비티 또는 화면 컴포넌트를 담는 안드로이드의 저장 공간

            - 플래그 
                - FLAG_ACTIVITY_CLEAR_TOP : 액티비티 A를 호출하면 스택에 있던 B/C를 삭제하여 A를 화면에 출력
                - FLAG_ACTIVITY_NEW_TASK : 새로운 테스크를 생성하여 안에 액티비티를 추가할 때 사용합니다.
                - FLAG_ACTIVITY__MULTIPLE_TASK : 호출되는 액티비티를 메인으로 하는 새로운 태스크 생성
                _ FLAG_ACTIVITY_SINGLE_TOP : 호출되는 액티비티가 TOP에 있으면 다시 생성하지 않고 존재하던 액티비 사용

      - 프래그먼트

           - 액티비티 조건마다 화면 구성을 다르게 할 수 있는것

           - View: 화면 보이는 모든 요소의 최상위 클래스 
           - onDraw() 메서드: 텍스트 출력하거나 그림을 그릴 때 호출하는 메서드입니다.
           - CanVas: onDraw() 메서드를 통해 전달되는 그리기 도구
           - paint: 화면에 그려지는 색상,스타일,굵기 정보 정의하는 클래스
  ## 권한

      - 권한 명세: 해당 데이터나 기능의 사용 여부를 설정
      - 기능 명세: 해당 기능이 있는 폰만 받도록 설정

      - 일반 권한의 종류 : ACCESS_NETWORK_STATE(네트워크 연결), ACCESS_WIFI_STATE(WIFI), BLUETOOTH(블루투스)
                          INTERNET(네트워크 및 인터넷), NFC(기기간 근거리 통신), SET_ALARM(알람 설정), VIBRATE(진동 설정)
      
      - 위험 권한 : CALENDAR, CAMERA, CONTACTS, LOCATION, MICROPHONE, PHONE, SENSORS, SMS, STORAGE

      - 권한 그룹 : ex) 기능 읽기 쓰기가 있다면 읽기만 허용해도 쓰기도 허용

      -