## 06 기본적인 리팩터링

추출 = 이름짓기

- 코드 이해도가 높아지다 보면 이름을 바꿔야 할 때가 많음(완전)
- 함수 선언 바꾸기(6.5절)은 함수의 이름을 변경할 때, 함수의 인수를 추가하거나 제거할 때 많이 쓰임
- 바꿀 대상이 변수라면 **변수 이름 바꾸기(6.7절)** 기법을 사용하고 이는 변수 캡슐화하기 (6.8절)과 관련이 깊음
- 매개변수 객체 만들기(6.8절) 기법을 적용해서 객체를 하나로 묶으면 편리할 때가 많음

  이름을 짓거나 바꾸는 것은 기본적인 리팩터링임

- 이 다음으로는  함수들을 모듈로 묶는 **여러 함수를 클래스로 묶기(6.9절)**을 이용할 수도 있음
- 또 다른 함수를 묶는 방법, **여러 함수를 변환 함수로 묶기 (6.10절)**, 읽기 전용 데이터를 다룰 때 좋음
- 모듈끼리 단계를 구분짓는 **단계 쪼개기(6.11절) 기법**을 적용하는 것도 가능

→ 추출, 이름짓기, 좋은 코드 생성, 들을 묶어 모듈로 만들기

---

## 6.1 함수 추출하기

독립된 함수로 추출하고 목적에 맞는 이름을 붙이는 것

언제 적용 할까?

- 중복을 제거, 재사용성을 위해서 함수 추출을 함
- 목적과 구현을 분리하는 방식
    - 함수 하나에 여러가지 일을 하면 구현이 여러개가 담김. 그러면 그때부터 이해하기 어려워지기 시작함. 이러한 구현들을 추출해서 무엇을 하는지 이름을 지어주자. 목적을 정해주자.
- 함수를 짧게 만들면 함수 호출이 많아져서 성능이 느려질까 걱정 → 컴파일러가 캐싱 등 잘해주고 현대 컴퓨터에서 걱정할 일은 아님.
- [성능 최적화]
    - 함수 추출하기는 이름을 잘 지어야 리팩터링의 효과가 발휘됨
    - 지금 당장 할 필요가 없으면 굳이 리팩터링을 지금 당장에 하지는 마라.
- 함수가 무엇을 하는지 드러나야함.
- 필요한 변수들을 다 넘겨줘야함.

  파라미터들을 객체로 합쳐서 객체를 통채로 넘기는 기법도 있음.

- 값을 반환할 변수가 여러개일 수 있는데, 이 경우에 처리하는 방법
    - 반환하는 함수 값 자체를 여러개로 만들려고 한다면 합치기
    - 임시 변수를 질의 함수로 바꾸거나(7.4) 변수를 쪼개는 방식(9.1)을 사용

---

## 6.2 함수 인라인하기

### 배경

함수 자체가 짧은걸 권장, but 가끔 함수 본문이 함수 이름만큼 명확한 경우가 있다면 함수를 제거하는게 좋을 때도 있음

```java
// 불필요한 함수
public int doubleValue(int x) {
    return x * 2; // 함수 본문이 함수 이름과 같은 수준으로 명확함
}
```

```java
// 함수를 제거한 개선 코드
int result = x * 2; // 함수를 호출할 필요 없이 직접 사용
```

- 잘못 추출한 함수들이 있다면 이를 제거하기 위해 인라인
- 간접 호출을 너무 과하게 쓰는 경우가 있다면, 인라인
- 즉, **필요없는 함수 호출을 제거**하기 위해 있음

### 절차

이 기법을 적용하는 순서

1. 다형 메서드인지 확인
2. 인라인 할 함수를 호출하는 곳을 모두 찾아서 교체.
3. 인라인하기 까다로운 부분이 있다면 이를 남겨놓고 가볍게 바꿀 수 있는 부분부터 바꿈
4. 함수 선언부를 제거함
- 이런 경우는 컴파일 에러가 안나는 선에서 한 문장씩 옮기는게 좋음. 그러면서 테스트 진행. 즉, 짧은 스텝으로 가져가는게 중요함.

---

## 6.3 변수 추출하기

### 배경

- 표현식이 너무 복잡하다면 일부를 지역 변수로 추출해서 관리하면 이해하기가 더 쉬워짐
- 단계별 이름을 붙혀서 보다 코드의 목적을 드러내기 쉽기 때문
- 변수를 추출하기로 결정했다면 그것이 적용할 문맥이 어디까지인지 살펴봐야함
- 이 함수 안에서만 필요한지, 다른 함수에서도 이 표현식의 단계가 필요한지 생각해봐야함.
- 함수 안에서만 필요하면 **지역변수**로 쓰면 되지만, 다른 함수에서도 필요하다면 이 표현식을 **함수**로 추출하는게 나을 수 있음.
- 이렇게 맥락을 고려해서 작성하면 중복이 줄어듬.

### 절차

- 추출하려는 표현식에 부작용이 없는지 확인
- 불변 변수를 선언, 표현식의 복제본 대입
- 원본 표현식을 새로 만든 변수로 교체
- 테스트
- 여러 곳에서 사용한다면 각각 교체(새로운 함수/변수를 만들어 전체에 적용)

### 예시

```java
// 원본
public class Before {
    public double price(Order order) {
        return order.quantity * order.itemPrice - 
                Math.max(0, order.quantity - 500) * order.itemPrice * 0.05 + 
                Math.min(order.quantity * order.itemPrice * 0.1 , 100);
    }
}
```

```java
// 지역 변수로 추출
public class After {
    public double price(Order order) {
        double basePrice = order.quantity * order.itemPrice;
        double quantityDiscount = Math.max(0, order.quantity - 500) * order.itemPrice * 0.05;
        double shipping = Math.min(order.quantity * order.itemPrice * 0.1 , 100); 
        return basePrice - quantityDiscount + shipping;
    }
}
```

**클래스 내에서 중요한 개념(로직)은 변수로 감추지 말고 메서드로 정의**

```java
// Order 클래스 내에서 적용시

public class Order {
    protected int quantity;
    protected int itemPrice;

    public Order(int quantity, int itemPrice) {
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    public double price() {
        return this.quantity * this.itemPrice -
                Math.max(0, this.quantity - 500) * this.itemPrice * 0.05 +
                Math.min(this.quantity * this.itemPrice * 0.1 , 100);
    }
}
```

```java
public class OrderAfter {
    protected int quantity;
    protected int itemPrice;

    public OrderAfter(int quantity, int itemPrice) {
        this.quantity = quantity;
        this.itemPrice = itemPrice;
    }

    public double price() {
        return basePrice() - quantityDiscount() + shipping();
    }

    private double shipping() {
        return Math.min(this.quantity * this.itemPrice * 0.1, 100);
    }

    private double quantityDiscount() {
        return Math.max(0, this.quantity - 500) * this.itemPrice * 0.05;
    }

    private int basePrice() {
        return this.quantity * this.itemPrice;
    }
}
```

---

## 6.4 변수 인라인하기

### **배경**

변수는 함수 안에서 특정한 의미를 가져서 코드 이해를 도와줌

### **절차**

1. 대입문의 우변(표현식)에서 부작용이 생기지 않는지 확인하기
2. 변수가 불변으로 선언되지 않았다면 불변으로 만든 후 테스트 하기
3. 이 변수를 가장 처음 사용하는 코드를 찾아서 대입문 우변의 코드로 바꿈
4. `테스트`
5. 변수를 사용하는 모든 부분을 교체하고 `테스트`
6. 변수 선언문과 대입문을 지움
7. `테스트`

### **예시**

```java
// before
public boolean method(Order order) {
    int basePrice = order.basePrice;
    return basePrice > 1000; 
}
```

```java
// after
public boolean method(Order order) {
    return order.basePrice > 1000; 
}
```

---

## 6.5 함수 선언 바꾸기

### 배경

- 함수 이름이 좋으면 이름만 보고도 무슨 일을 하는지 파악, but 나쁜 이름은 혼란을 일으킴
- 잘못된 함수 이름 → 무조건 바꾸기 (한번에 잘 지으면 좋겠지만 힘듦.)
- 함수의 목적을 주석으로 설명해보기. 그러다 보면 주석이 멋진 이름으로 바뀌어 올 때가 있음. 실제 구현 사항에 집중해서 함수의 이름을 짓기 보다는 해줘야 하는 역할에 좀 더 집중하면 됨
- 매개변수는 함수와 어울려서 함수의 문맥을 정해줌(함수 + 매개변수 set)
    - 함수를 읽을때 매개변수까지 같이 말이 되도록 읽기
- 이렇게 하면 활용 범위가 넓어질 뿐 아니라, 다른 모듈과의 결합(coupling)도 줄어듬
- 객체를 넘길지 요소 하나만을 넘길지에 대한 것은 장단점이 있다. 그런것을 적합한 쪽으로 할 수 있는 능력을 갖추도록 하자.

### 절차

- 리팩터링을 적용하는 절차, 한가지
- 보통 간단한 절차만으로 충분할 때가 많지만, 더 세분화된 마이그레이션 절차가 훨씬 적합한 경우가 많음
- 함수 선언 바꾸기는 사정이 좀 다름

**[간단한 절차]**

1. 매개변수를 제거하기 전에 먼저 함수 본문에서 매개변수를 참조하는 곳이 없는지 확인
2. 메서드 선언을 원하는 형태로 바꾸기
3. 기존 메서드 선언을 참조하는 부분을 모두 찾아서 바꾸기
4. 테스트

```java
// 전
public double circum(double radius) {
    return 2 * Math.PI * radius;
}
```

```java
// 후
public double circumference(double radius) {
    return 2 * Math.PI * radius;
}
```

**[마이그레이션 절차]**

1. 이어지는 추출 단계를 수월하게 만들어야 한다면 함수 본문을 적절히 리팩터링 함
2. 함수 본문을 새로운 함수로 추출함
3. 추출한 함수에 매개변수를 추가해야 한다면 추가함
4. 테스트
5. 기존 함수를 인라인
6. 함수 이름을 바꿈
7. 테스트

```java
// 전
public double circum(double radius) {
    return 2 * Math.PI * radius;
}
```

```java
// 후
public double circum(double radius) {
    return circumference(radius);
}

private double circumference(double radius) {
    return 2 * Math.PI * radius;
}
```

다형성을 구현한 클래스나 상속 구조에서는 메서드 변경이 어렵기 때문에
새롭게 원하는 함수를 만들고 원래 함수를 호출하는 메서드로 사용해서 변경하면 편함

**예시: 매개변수 추가하기**

- 새로운 요구사항 : 우선순위 큐를 지원하라는 기능
    - 이 기능을 지원하기 위해서 매개변수로 일반 큐를 사용할지 우선순위 큐를 사용할지 여부를 추가해야함
    - 한번에 변경하기 힘드므로 마이그레이션 절차로 진행

```java
// 예약자 추가 기능
public void addReservation(Customer customer) {
    this.reservations.add(customer); 
}
```

```java
// 마이그레이션 기법 적용
public void addReservation(Customer customer) {
    priorityAddReservation(customer);
}

private void priorityAddReservation(Customer customer) {
    this.reservations.add(customer);
}
```

```java
// 우선순위 예약여부 판단할 수 있는 구조
// 파라미터 넣기
public void addReservation(Customer customer) {
        priorityAddReservation(customer, false);
    }

private void priorityAddReservation(Customer customer, boolean isPriority) {
    this.reservations.add(customer);
}
```

**리팩터링 과정에서 기존 메서드를 보존하면서 새로운 메서드를 도입하고, 최종적으로 기존 메서드를 제거**

---

## 6.6 변수 캡슐화하기

### 배경

- 함수는 데이터보다 다루기 수월함
- 함수는 대체로 호출 하는식으로 동작되며 함수를 바꿀 때는 함수가 다른 함수를 호출하도록 변경해주면 쉽게 바꾸는게 가능
- 데이터는 데이터를 사용하는 모든 부분을 바꿔줘야함
- 짧은 함수 안의 임시 변수처럼 유효 범위가 아주 좁은 데이터는 문제가 되지 않지만 이러한 이유로 전역 데이터는 골칫거리가 될 수 있음
- 데이터의 접근을 독점하는 함수를 만드는게 가장 좋음(데이터에 직접적인 접근보다 함수를 통해 접근하는게 통제성이 더 좋다란 말)
- 데이터 캡슐화를 하면 데이터 변경 전이나 변경 후 추가 로직을 쉽게 넣는게 간단함

  유효범위가 함수 하나보다 넓은 가변 데이터는 모두 이런식으로 캡슐화를 함.

- 그래야 자주 사용하는 데이터에 대한 결합도가 높아지는 일을 막을 수 있음(데이터 그 자체로 바로 접근해서 사용하는 경우는 문제가 많았음)
- 객체 지향에서 데이터를 항상 private으로 유지해야 한다고 강조하는 이유.
    - 데이터 자체로 접근을 한다면 변경에 어려움이 있음.
- public 필드 → private 변경 및 캡슐화 진행
- self-encapsulation을 주장 → 이정도면 클래스를 쪼개는게 맞음 → 여기서 불변 데이터의 경우에는 가변 데잉터보다 캡슐화할 이유가 적음

  데이터가 변경될 일이 없어서 갱신 검증이나 추가 로직이 있을 필요가 없기 때문. 데이터를 변형시킬 일이 없으니 딱히 걱정 X


### 절차

1. 변수로의 접근과 갱신을 전담하는 캡슐화 함수를 만듦
2. 정적 검사를 수행
3. 변수를 직접 참조하던 부분을 모두 적절한 캡슐화 함수 호출로 바꿈. 하나씩 바꿀 때마다 테스트 진행
4. 변수의 접근 범위를 제한함
5. 테스트 진행
6. 변수 값이 레코드라면 레코드 캡슐화하기(7.1) 적용 고려

### 예시

- 전역변수의 데이터에 접근하기 위해 getX(), setX() 메서드를 만듦
- 참조하는 부분을 Getter 메서드로 변경 진행
- 하나씩 변경할 때마다 테스트를 해주고 모두 테스트가 완료되면 기존 변수에 접근하지 못하도록 접근 제어자를 바꾸기
- Getter로 변경 진행
- 하나씩 변경할 때마다 테스트를 해주고 모두 테스트가 완료되면 기존 변수에 접근하지 못하도록 접근 제어자를 바꾸자
- 여기서 추가로 Getter로 가져간 데이터의 변경이 **원본에 영향**을 주지 않도록 하려면 **clone() 메서드**를 통해 **복제본**을 던지도록 하면됨. 아니면 클래스를 통해 매번 **새로운 객체**를 만들어 주는 방법도 있음.

---

## 6.7 변수 이름 바꾸기

### 배경

- 이름 짓기와 관련된 리팩터링 기법
- 이름 변경, 사용자의 요구사항이 변경되어 그에 맞게 이름을 변경해야 하는 경우에 사용

### 절차

- 이름을 변경할 때 사용 범위도 고려해보기. 폭 넓게 사용되는 변수라면 변수 캡슐화 하기(6.8절 고려)
- 이름을 바꿀 변수를 참조하는 곳을 모두 찾아서 하나씩 변경하기(변수 값이 변하지 않으면 복제본을 이용해 하나씩 점진적으로 변경해나가기)
- 테스트하기

### 예시: 변수 캡슐화 하기

- 변수 이름 바꾸기는 간단함
- 변수를 참조하는 곳이 있다면 바꾸면 됨

```java
// 선언된 변수
String tpHd = "untitled"; 
String result = String.format("<h1> %s </h1> ", tphd); 
```

```java
// 변수의 값 수정하는 부분

//전
tphd = obj.getArticleTitle(); 

//후
setTitle(obj.getArticleTitle()); 
```

```java
// 변수의 값을 조회하는 부분

//전
String result = String.format("<h1> %s </h1> ", tphd);
 
//후
String result = String.format("<h1> %s </h1> ", getTitle());  
```

### 예시: 상수 이름 바꾸기

변경되지 않는 상수값은 캡슐화 하지 않고 복제 방식(Copying Style)으로도 쉽게 변수 이름을 바꿀 수 있음

- AS-IS : cpNm
- TO-BE : companyName

```java
String cpNm = "애크미 구수베리"; 
```

```java
String companyName = "애크미 구수베리"; 
String cpNm = companyName; 
```

- 이렇게 복사본을 만드는 이유
    - ‘companyName’ 복제몬을 만든 후,
      하나씩 cpNm → companyName 으로 변경하면서 테스트를 통해 영향도를 확인 가능

      즉, 점진적으로 리팩토링하며 정상 동작을 보장할 수 있음

    - 코드가 많이 참조되고 있는 변수라면, 직접 변경하는 것은 위험할 수 있음.
    - 대규모 코드 변경이 필요한 경우, 한번에 바꾸기보다는 점진적으로 변경하는 것이 안전함
    - cpNm을 유지한 상태에서 companyName을 도입하면, 문제가 생겨도 다시 cpNm으로 돌아가는게 쉬움

---

## 6.8 매개변수 객체 만들기

### 배경

- 데이터 항목 여러개가 이 함수로 저 함수로 같이 몰려다니는 경우를 자주 볼 수 있음

  이런 데이터 무리를 하나의 데이터 구조로 모아줌.

  (데이터 사이의 관계가 명확해져서 하나의 책임을 가질 수 있도록 하기 위함, 또 파라미터 개수를 줄여줄 수 있으니까 함수의 이해가 쉬워짐)

- 코드를 더 근본적으로 바꿔준다는데 있음

### 절차

1. 적당한 데이터 구조가 없다면 새로 만듦(클래스로 만드는 것을 추천)
2. `테스트`
3. 함수 선언 바꾸기(6.8) 새 데이터 구조를 매개변수로 활용함
4. `테스트`
5. 함수 호출 시 새로운 데이터 구조 인스턴스를 넘기도록 수정함. 그리고 하나씩 `테스트`
6. 기존 매개변수를 사용하던 코드를 새 데이터 구조의 원소를 사용하도록 바꿈
7. 다 바꿨다면 기존 매개변수를 사용하는 함수는 제거하고 `테스트`

### 예시

온도 측정값(reading)에서 정상 작동 범위를 벗어나는 코드가 있는지 검사하는 코드

```java
public List<Reading> readingsOutsideRange(Station station, int min, int max) {
    return station.readings
            .stream()
            .filter(r -> r.temp < min || r.temp > max)
            .collect(Collectors.toList()); 
}
```

```java
// 리팩터링 여지가 있는 부분
List<Reading> alerts = readingsOutsideRange(station, 
    operationPlan.temperatureFloor, // 최저 온도 
    operationPlan.temperatureCeiling) //최고 온도
```

- 범위(최저, 최고)라는 개념은 객체 하나로 충분히 묶을 수 있음

```java
public class NumberRange {
    protected int min;
    protected int max;

    public NumberRange(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
}
```

```java
public List<Reading> readingsOutsideRange(
Station station
, int min, int max
, NumberRange numberRange) {
    return station.readings
            .stream()
            .filter(r -> r.temp < min || r.temp > max)
            .collect(Collectors.toList());
}
```

- 이 함수를 호출하는 곳에서는 매개변수로 null만 넣으면 컴파일 오류는 생기지 않음.
  **컴파일 오류가 생기지 않는 선에서 점진적으로 문제를 개선할 수 있도록 하는게 중요**함.

```java
// max 대신 numberRange.getMax()를 사용
// min 대신 numberRange.getMin()를 사용
public List<Reading> readingsOutsideRange(Station station, int min, int max, NumberRange numberRange) {
    return station.readings
            .stream()
            .filter(r -> r.temp < numberRange.getMax() || r.temp > numberRange.getMax())
            .collect(Collectors.toList());
}
```

테스트가 통과되면 매개변수를 제거하고 테스트

---

## 6.9 여러 함수를 클래스로 묶기

### 배경

- 공통 데이터를 사용하는 함수가 여럿 있다면 이들을 하나의 클래스로 묶으려는 생각.

  하나의 새로운 책임을 가진 클래스, 함수의 파라미터를 줄여서 이해를 높일 수 있다는 측면

- 클래스로 묵으면 이 함수들이 공유하는 공통 환경을 더 명확하게 표현하는게 가능해짐
- 각 함수에 전달되는 인수를 줄여 함수 호출이 간결해짐
- 기존의 함수들을 재구성할 때와 새로만든 클래스와 관련해 놓친 연산을 찾아서 새클래스의 메서드로 뽑아내도 좋음
- 함수를 한데 묶는 또 다른 방법으로는 여러 함수를 변환 함수로 묶기(6.10) 기법. 이건 맥락에 따라 잘 결정해야함

### 절차

1. 함수들이 공유하는 공통 데이터 레코드를 캡슐화(7.1)함
2. 공통 레코드를 사용하는 함수 각각을 새 클래스로 옮김(함수 옮기기, 8.1)
3. 데이터를 조작하는 로직들은 함수로 추출(6.1)해서 새 클래스로 옮김

### 예시

```json
reading = {
  "customer": "ivan", 
  "quantity": 10,
  "month": 5,
  "year": 2017
}
```

```java
// client1
Reading reading = acquireReading(); 
double baseCharge = baseRate(reading.month, reading.year) * reading.quantity; 

// client2
Reading reading = acquireReading(); 
double base = (baseRate(reading.month, reading.year) * reading.quantity); 
double taxableCharge = Math.max(0, base - taxThreshold(reading.year)); 
```

```java
Reading reading = acquireReading(); 
double base = calculateBaseCharge(reading)

public double calculateBaseCharge(Reading reading) {
    return (baseRate(reading.month, reading.year) * reading.quantity); 
}
```

```java
// 알고보니 이런게 있었음
Reading reading = acquireReading(); 
double base = calculateBaseCharge(reading)

public double calculateBaseCharge(Reading reading) {
    return (baseRate(reading.month, reading.year) * reading.quantity); 
}
```

- 중복을 위해 메서드를 빼놔도 데이터와의 거리는 가깝지 않으니까 못보고 사용.
- 데이터와 그것의 동작은 가까운 거리에 있는게 좋으므로 클래스로 만들어서 처리할 수 있음
- calculateBaseCharge() 메서드도 이제 클래스로 옮기면 됨

---

## 6.10 여러 함수를 변환 함수로 묶기

### 배경

- 소프트웨어는 데이터를 입력받아서 여러가지 정보를 도출함
    - 이렇게 도출된 정보를 바탕으로 비슷한 도출 로직이 또 일어나는 경우가 있음
    - 이런 도출 작업들을 한 곳으로 모으는 것을 추천함
    - 모아두면 검색과 갱신을 일관적으로 적용하는게 가능해짐
- 변환 함수(transform)을 적용 가능
    - 원본 데이터를 받아서 필요한 정보를 도출하고 출력 데이터를 만들어서 이를 반환하는 방법
    - 이 변환 함수의 특징은 여러곳에서 도출하는게 아니라 변환 함수만 바라보도록 하는 것이 특징
    - 이 방법 대신 ‘여러 함수를 클래스로 묶기(6.9)’ 로 처리해도 좋음
    - 한군데로 모은다는 점이 특징
- 다만 원본 데이터가 코드 안에서 갱신되는 경우라면 클래스로 문제를 해결하는 것이 나음

  (그 데이터 변경은 자기가 담당하는게 나음. 데이터와 그 데이터를 다루는 메서드는 가까이 있는게 좋음)

- 변환 함수로 묶는다면 원본 데이터가 수정되면 일관성이 깨질 수 있음

    ```java
    // 변환 함수로 묶기 전 (원본 데이터 직접 사용)
    // 이 상태에서는  Order 객체를 직접 사용하므로 원본 데이터가 그대로 유지됨
    class Order {
        private String customer;
        private double totalPrice;
    
        public Order(String customer, double totalPrice) {
            this.customer = customer;
            this.totalPrice = totalPrice;
        }
    
        public String getCustomer() {
            return customer;
        }
    
        public double getTotalPrice() {
            return totalPrice;
        }
    }
    
    public class OrderService {
        public double getDiscountedPrice(Order order) {
            return order.getTotalPrice() * 0.9;
        }
    
        public String getCustomerName(Order order) {
            return order.getCustomer().toUpperCase();
        }
    }
    ```

    ```java
    // 변환 함수로 묶은 후, 원본 데이터가 수정되면서 일관성이 깨지는 경우
    
    class OrderDto {
        private String customer;
        private double totalPrice;
    
        public OrderDto(String customer, double totalPrice) {
            this.customer = customer;
            this.totalPrice = totalPrice;
        }
    
        public String getCustomer() {
            return customer;
        }
    
        public void setCustomer(String customer) {
            this.customer = customer;
        }
    
        public double getTotalPrice() {
            return totalPrice;
        }
    
        public void setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
        }
    }
    
    public class OrderConverter {
        public static OrderDto convert(Order order) {
            return new OrderDto(order.getCustomer(), order.getTotalPrice());
        }
    }
    
    public class OrderService {
        public void processOrder(Order order) {
            OrderDto dto = OrderConverter.convert(order);
            
            // 변환된 데이터를 조작
            dto.setCustomer("MODIFIED " + dto.getCustomer());
            dto.setTotalPrice(dto.getTotalPrice() * 0.9);
    
            System.out.println("Discounted Price: " + dto.getTotalPrice());
            System.out.println("Modified Customer: " + dto.getCustomer());
        }
    }
    ```

  ### 해결 방법

    ```java
    // 변환 후 변경할 수 없는 값이므로 일관성이 깨지는 문제를 방지할 수 있음.
    class OrderDto {
        private final String customer;
        private final double totalPrice;
    
        public OrderDto(String customer, double totalPrice) {
            this.customer = customer;
            this.totalPrice = totalPrice;
        }
    
        public String getCustomer() {
            return customer;
        }
    
        public double getTotalPrice() {
            return totalPrice;
        }
    }
    ```

  ### 결론

  변환 함수로 묶을 때 변환된 객체가 수정되면 원본 데이터와 불일치가 발생할 수 있음

  [해결]

    - 변환된 객체가 수정될 가능성이 있다면 불변(Immutable) 객체로 생성
    - 원본 데이터를 수정해야 한다면 변환이 아니라 업데이트 메서드 제공
    - 변환된 데이터와 원본 데이터의 역할을 명확하게 구분

    ---


### 절차

1. 변환할 레코드를 입력받아서 값을 그대로 반환하는 변환 함수를 만듦
2. 묶을 함수 중 하나를 골라서 본문 코드를 변환 함수로 옮기고 레코드에 새 필드로 기록. 그런 다음 클라이언트가 이 필드를 사용하도록 수정함
3. 테스트
4. 나머지 묶을 함수들도 반복해서 처리

### 예시

---

## 6.11 단계 쪼개기

### 배경

- 서로 다른 두 대상을 한꺼번에 다루는 코드를 발견하면 각각을 별개의 모듈로 나누는 방법을 찾음
- 두 대상을 한번에 생각하는 것이 아니라 한 대상씩 생각하기 위함
- 모듈이 잘 분리되어 있다면 다른 모듈의 상세 내요은 전혀 기억하지 않아도 된다는 장점
  (모듈간 결합도를 낮추고 응집도 올리기)
- 이렇게 하기 위한 가장 간편한 방법이 단계 쪼개기.
  (한 모듈 수정 후 다른 모듈 수정 .. )
- 로직을 순차적인 단계들로 분리. 중요한건 각 단계는 서로 확연히 다른 일을 수행함
- 이런 과정은 **컴파일러와 유사**함

  컴파일러는 기본적으로 어떤 텍스트를 입력받아서 실행 가능한 형태로 변환함.

  지속적으로 발전하면서 여러 단계로 구성되는게 좋다고 판단되었는데 과정은 다음과 같음

    - 텍스트를 토큰화 하기
    - 토큰을 파싱해서 구문 트리 만들기
    - 구문 트리 변환해서 목적 코드 만들기
- 각 단계는 자신의 목적만 집중하기 때문에 나머지 단계를 몰라도 됨

  자신의 문제만 해결하면 됨!

- 이렇게 단계를 쪼개는 기법은 주로 덩치 큰 소프트웨어에 적용됨
- 여러 단계로 분리하면 좋을만한 코드를 발견할 때마다 기본적으로 단계 쪼개기 리팩터링을 함

  코드 영역들이 마침 서로 다른 데이터와 함수를 사용한다면 이는 단계 쪼개기에 적합하다는 뜻.

  이렇게 별개의 모듈로 분리하면 코드를 분명하게 드러낼 수 있음.


### 절차

1. 두 번째 단계에 해당하는 코드를 독립 함수로 추출
2. 테스트
3. **중간 데이터 구조를 만들고** 앞에서 추출한 함수의 인수로 추가
4. 테스트
5. 추출한 두 번째 단계 함수의 매개변수를 하나씩 검토.

   첫번째 단계 → 중간 데이터 구조로 옮김,

   하나씩 옮길 때마다 테스트

   모듈과 모듈 사이에는 데이터 공유가 겹치면 안됨. 두번째 단계에서 사용하면 안되는 매개변수가 있는데 이는 중간 데이터 구조로 옮기고 이 필드를 설정하는 문장을 호출한 곳으로 옮김

6. 첫 번째 단계 코드를 함수로 추출하면서 중간 데이터 구조를 반환하도록 만듦