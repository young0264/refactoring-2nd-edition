- Building Tests → 언어로 예제 만들고, 테스트도 만들고. → 리팩토링 코드 작성해보기
- 테스트에 관련된 얘기들 나눠보면 좋을 것 같음

---

## 04 테스트 구축하기

- 리팩터링을 제대로 하기 위해서는 테스트 케이스가 필요함.(리팩터링을 하더라도 제대로 기능이 동작하고 있다는 테스트.)
- 리팩터링을 하지 않더라도 이런 테스트를 작성하는 일은 개발 효율을 높여줌

  개발에 시간을 빼앗기는데 효율이 높아지는 상황.


---

## 4.1 자가 테스트 코드의 가치

- 프로그래머들이 시간을 쏟는 비중
    - 실제 코드를 짜는 비중은 그렇게 높지 않음
    - 현재 상황 파악, 설계등을 하는데에 시간을 씀
    - 하지만 대부분의 시간은 버그를 찾기 위한 디버깅을 하는데 시간을 씀
- 코드를 짜면서 테스트를 작성하면서 개발을 진행했는데, 테스트 코드가 실패한다면 방금 작성한 코드에 문제가 있을 가능성이 높음
    - 문제가 생겼을 때, 찾아야하는 코드의 양을 확연히 줄여주는 측면에서 테스트 코드는 디버깅의 시간을 많이 줄여줌.
    - **즉, 테스트 코드는 강력한 버그 검출 도구 버그를 찾는 시간을 대폭 줄여줌**
- 테스트를 작성하기 좋은 시점은 실제 프로그래밍을 하기 전, 기능을 추가하기 전에 테스트를 작성하는 것.

  얼핏 순서가 뒤바뀐 듯 들리지만 전혀 그렇지 않음

- 테스트를 작성하다 보면 원하는 기능을 추가하기 전에 무엇이 필요한지 고민하게 됨.
- 실제 구현에 집착하는게 아니라 인터페이스에 집착하게됨.
- 테스트를 먼저 작성함을 통해 코딩이 완료되는 시점을 명확하게 정할 수 있음(테스트가 모두 통과하는 시점)
- 켄트 백은 이처럼 테스트부터 작성하는 습관을 통해서 테스트 주도 개발(Test Driven Development)를 개발함.
- TDD에서는 테스트를 먼저 작성하고 테스트를 통과하기 위해 코드를 작성하고 통과한 다음에 리팩터링을 진행하는 과정으로 이뤄져있음.
- 이번장. 테스트 코드 작성 방법 소개, 깊게는 아니지만 어떤 효과를 누리는지 소개.

---

## 4.2 테스트할 샘플 코드

```
지역: Asia
수요: 30
가격: 20
생산자 수: 3

- Byzantium: 비용: 10, 생산량: 9, 수익: 90
- Attalia: 비용: 12, 생산량: 10, 수익: 120
- Sinope: 비용: 10, 생산량: 6, 수익: 60

총 합: 생산량 25,
부족분: 5, 
총수익: 230
*총 판매액(500, 180+200+120) - 구매액(270, 90+120+60) = 230
```

생산계획 : 지역의 수요와 가격으로 구성됨

- 지역(province)
    - 수요(demand)
    - 가격(price)
- 지역에 위치한 생산자들은 각기 제품을 특정 가격으로 특정 수량만큼 생산 가능
- 생산자별로 제품을 모두 판매했을 경우 얻을 수 있는 수익(full revenue)을 보여줌
- 부족분(shortfall)은 수요에서 총 생산량을 뺀 값이고 여기서는 총수익(profit)도 볼 수 있음
- 사용자는 여기서 수요, 가격, 생산량(production) 비용(cost)을 조정해가며, 그에 따른 생산 부족분과 총 수익을 확인할 수 있음.

[수익과 생산 부족성을 계산할 수 있어야함)

비즈니스 로직 클래스는 크게 두가지로 구성됨. 지역을 나타내는 Province와 생산자인 Producer.

---

## 4.3 첫 번째 테스트

- **given**: 테스트에 필요한 객체와 데이터 설정

  **when**: 실제 코드를 수행해보고 then에서 검증을 해보는 과정으로 구성

  **then**: assert에 대한 검증 실행

- 실무에서 테스트를 굉장히 자주 수행을 함. 왜냐하면 방금 추가한 코드에 문제가 있는지 없는지를 체크하기 위함.

---

## 4.4 테스트 추가하기

계속해서 테스트를 추가해보자.

테스트 추가는 public 메서드로 되어있는 모든 메서드를 테스트 하는게 아님.

**위험 요인을 기준으로 테스트를 작성해야함.**

적은 수의 테스트로도 충분한 효과를 누를 수 있어야함.

테스트의 목적은 향후 버그를 찾기 위함임. 이를 인지하자.

그러므로 get(), set()처럼 단순한 메서드들은 테스트를 할 필요가 없음.

다음으로 수익을 계산하는 부분을 테스트로 추가해보자.

```java
@Test
void profitTest(){
    //given
    Province sampleProvince = SampleProvinceFactory.getSampleProvince();
    int answer = 230;
    //when
    int result = sampleProvince.profit();
    //then
    assertEquals(answer, result);
}
```

생산 부족분을 계산하는 코드와 유사한 부분이 있음.

- `Province sampleProvince = SampleProvinceFactory.getSampleProvince();`

  이 부분인데 테스트 할 대상을 결정하는 부분이다.

  이런 중복은 제거하는게 좋음. 근데 중복을 제거할 땐 다음과 같이 하는건 비추.


```java
// 비추
// todo: 예제 수정
Province sampleProvince = SampleProvinceFactory.getSampleProvince();
    
    @Test
    void shortfallTest(){
        //given
        int answer = 5;
        //when
        int result = sampleProvince.shortFall();
        //then
        assertEquals(answer, result);
    }

    @Test
    void profitTest(){
        //given
        int answer = 230;
        //when
        int result = sampleProvince.profit();
        //then
        assertEquals(answer, result);
    }
```

- → 테스트가 객체를 공유하도록 설정하면 한 테스트 할 객체를 고립시키지 못하므로 한 테스트가 변경하면 다른 테스트에서도 영향이 갈 수 있기 때문

```java
// todo: 예제 수정
class ProvinceTest {

    Province sampleProvince;

    @BeforeEach
    void setUp() {
        sampleProvince = SampleProvinceFactory.getSampleProvince();
    }

    @Test
    void shortfallTest(){
        //given
        int answer = 5;
        //when
        int result = sampleProvince.shortFall();
        //then
        assertEquals(answer, result);
    }

    @Test
    void profitTest(){
        //given
        int answer = 230;
        //when
        int result = sampleProvince.profit();
        //then
        assertEquals(answer, result);
    }
}
```

- 공유 픽스처를 사용하지않고, 불변 객체를 가지고 테스트를 진행

---

## 4.5 픽스처 수정하기

- 지금까진 고정된 fixture를 사용해서 테스트를 진행했음
- 하지만 실전에선 이런 fixture가 setter 메서드로 인해 변경될 가능성이 있음.

  이런 변경 가능성을 염두해두고 테스트를 짜야함.

- Producer 클래스의 경우에는 setProduction() 같은 메서드는 좀 특이함

  그러므로 이 부분을 테스트 해봐야함


```java
// todo: 예제 수정
@Test
void changeProductionTest(){
    //given
    int shortFall = -6;
    int profit = 292;
    //when
    sampleProvince.producers.get(0).setProduction(20);
    int actualShortFall = sampleProvince.shortFall();
    int actualProfit = sampleProvince.profit();
    //then
    assertEquals(shortFall, actualShortFall);
    assertEquals(profit, actualProfit)  ;
}
```

여기서는 검증을 하는 부분이 두 assertEquals()로 나뉘는데 일반적으로 한 테스트에서는 한개의 assertEquals()만 있는게 좋음.

왜냐면 한개의 assertEquals()가 실패하면 뒤에 있는건 검사하지 않아도 되기 때문

그러므로 유용한 정보를 놓칠 수도 있기 때문에 웬만하면 한개의 assertEquals()로 두는게 좋음

---

## 4.6 경계 조건 검사하기(실패 테스트!)

지금까지 작성한 모든 경우는 정상적인 데이터를 가지고 검사했음. 일명 꽃길(Happy Path)상황만을 염두해 둔 것.

그런데 이 **범위를 벗어나는 경계 지점에서 문제가 생기면 어떤 일이 일어나느지 확인하는게 좋음**

그 중 하나로 컬렉션을 쓰는 데이터가 있다면 이 값이 비어있을 때 무슨일이 일어날지. 다음과 같이 테스트를 만들어 보면 됨.(실패테스트!)

// todo: 예제 수정

```java
@Test
void noProducersTest(){
    //given
    Province province = new Province("No Producers", new ArrayList<>(), 30, 20);
    int shortFall = 30;
    int profit = 0;
    //when
    int actualShortFall = province.shortFall();
    int actualProfit = province.profit();
    //then
    assertEquals(shortFall, actualShortFall);
    assertEquals(profit, actualProfit);
}
```

- 일반적인 데이터의 경우엔 맞지 않는 특이한 데이터를 넣어보면 됨.
- ex)
    - 수요는 음수가 될 수 없음
    - 수요의 최솟값은 0이어야 함
    - 이와 같은 문제가 생길 수 있는 경계 조건을 생각해보고 그 부분을 집중적으로 테스트해보기.
- 이러한 유효성 검사는 너무 많으면 중복 체크를 하게 될 가능성이 많으므로 문제가 될 수 있음
- 하지만 외부 시스템으로부터 받는 JSON과 같은 데이터는 유효성 체크를 항상 하는게 좋음
- **테스트는 어느 수준까지** 작성하는게 좋을까?
    - 테스트가 개발 속도를 높여준다는 말은 있지만 테스트에 너무 집착하다 보면 기능을 추가하는 의욕이 떨어질 수 있음.
    - 테스트는 위험한 부분을 위주로 작성하는게 좋음
    - 또 코드가 복잡한 부분을 찾아보자.! 이는 틀릴 여지가 많기 때문에 테스트를 추가해서 검증하는게 좋음

---

## 4.7 끝나지 않은 여정

이 책의 핵심은 리팩터링이기 때문에 테스트에 대해서 많은 내용을 설명하지는 않겠음.

- 이 장에서 보여준 테스트는 단위 테스트(Unit Test)임.
- **단위 테스트**는 코드의 작은 영역만 집중적으로 테스트하되 컴포넌트의 상호작용까지 테스트를 하지는 않는 테스트를 말함
- 모든 자가 검증의 테스트는 단위 테스트로부터 나옴
- 이외에도 물론 테스트는 다양하게 많음
- 컴포넌트의 상호작용을 테스트하는 통합 테스트, 성능 테스트 등등….
- 테스트를 할 때 명심해야 하는 생각이 항상 처음부터 완벽한 테스트 케이스를 모두 갖출 순 없음. 그러므로 버그 리포트를 만날 때 마다 해당 테스트 케이스부터 작성해두자.
- **테스트 할 때 어느 정도까지 해야하는 지를 궁금해함**
    - 테스트 커버리지? 는 코드에서 테스트 하지 않는 부분을 찾는 것이지, 코드 자체를 모두 테스트 한 것은 아님
    - 그러므로 커버리지가 높은 것이 모든 경우를 테스트 한 것은 아니다.