‘데이터와 행위를 묶는게 캡슐화다’ → 아님

---

## 07 캡슐화

모듈을 분리하는 가장 중요한 기준은, 필요없는 정보는 드러내지 않는 것, 노출되어야 할 부분만 정확하게 노출하는 것. → 이러한 방법은 **캡슐화**를 통해서 가능

**캡슐화**

- 레코드 캡슐화 하기(7.1)
- 컬렉션 캡슐화 하기(7.2)
- 기본형 데이터 구조도 기본형을 객체로 바꾸기(7.3)

**클래스는 본래 정보를 숨기는 용도로 설계됨**

- 여러 함수를 클래스로 묶기(6.9)
- 클래스 추출하기(7.5)
- 클래스 인라인하기(7.6, 필요없는 클래스를 지우는 방법)
- 클래스는 내부 정보 뿐 아니라 연결 관계를 숨기는데도 유용한데 이를 위한 방법으로는 위임 숨기기(7.7)
- 위임 숨기기를 통해 필요없는 중개자가 너무 많아지면 중개자 제거하기(7.8)도 필요함

가장 큰 캡슐화 단위는 클래스와 모듈이지만 함수도 캡슐화가 가능함

- 함수 추출하기(6.1)로 추출한 후 알고리즘 교체하기(7.9)를 적용하면 됨

---

## 7.1 레코드 캡슐화하기

### 배경

```json
organization = {
  "name" : "애크미 구스베리",
  "country" : "GB"
}
```

- 레코드, 계산해서 얻을 수 있는 값과 그렇지 않은 값은 구별해야 한다는 점
- 레코드 대신 클래스를 이용하는 것을 선호함.
- 클래스를 이용하면 어떠한 데이터를 제공해주는지 메서드를 보면 바로 알 수 있음.
- 데이터가 불변 데이터인 경우에는 값만 제공해주면 되므로 굳이 클래스는 쓰지 않음. 그냥 모든 필드를 레코드에서 모두 제공해주도록 함
- 레코드 구조는 크게 두 가지로 구분하는게 가능
    - 하나는 필드 이름을 노출
    - 해시맵과 같은 라이브러리를 통해서 감싸는 형태
- 라이브러리를 이용하면 필드를 보기 위해 처음 그 라이브러리를 처음 생성해서 쓰는 곳을 참조해야함. 이게 불편하면 클래스를 쓰는 것도 추천 → 어떠한 데이터를 제공해주는지 알 수 있음
- JSON이나 XML같은 포맷으로 직렬화하는 경우가 많음. 이런 구조는 캡슐화 하는게 좋음. 출력하는 형식이 바뀔 수 있음.

### 절차

1. 레코드를 담은 변수를 캡슐화(6.6) 함
2. 레코드를 감싼 단순한 클래스로 해당 변수의 내용을 교체함
3. 테스트
4. 원본 레코드 대신 새로 정의한 클래스 타입의 객체를 반환하는 함수들을 새로 만듦
5. 레코드를 반환하는 예전 함수를 새로 만든 함수로 바꿈
6. 한 부분을 바꿀 때마다 테스트함

---

## 7.2 컬렉션 캡슐화하기

### 배경

- 가변 데이터는 모두 캡슐화를 하는 편
- 데이터들이 언제 어떻게 수정되는지 추적하기가 좋음. (데이터 변경 자체는 추적이 힘듦) === 데이터를 캡슐화(함수로 접근하게 하기)
- 컬렉션 내부의 요소를 변경하는 작업이 필요한 것은 클래스 메서드로 따로 만들어 두는 편. ex) add(), remove()..
    - 컬렉션 자체를 반환하는 것을 막는다는 것은 아님

      컬렉션 자체 반환을 막도록 하면 컬렉션에서 사용가능한 다채로운 인터페이스를 사용하는데 제한이 걸리기 때문

    - 그래서 **컬렉션 변경과 같은 작업은 클래스 메서드를 통해서 이뤄지도록** 하고 **컬렉션을 반환**하는 getter 함수는 컬렉션 자체를 반환하는 것이 아니라 **복사본을 반환**하도록 하자. 그리고 컬렉션을 통째로 변경할 수 있는 세터는 없애도록 하자. 없앨 수 없으면 인수로 전달받은 복제본으로 가져와서 원본에 영향이 안가도록 하자.
    - 물론 원본에 영향이 안가는게 이상하다고 느낄 순 있지만 대부분의 프로그래머는 이 패턴을 이용. 성능적으로도 크게 문제가 없을 것임
    - 코드 베이스에서 일관성을 주는 것.

### 절차

1. 아직 컬렉션을 캡슐화하지 않았다면 변수 캡슐화하기 (6.6)부터 함
2. 컬렉션에 원소를 추가/제거 하는 함수를 만듦(컬렉션을 통째로 변경하는 세터를 모두 제거하고 인수로 받은 컬렉션은 복제본을 사용하도록)
3. 정적 검사 수행
4. 컬렉션을 참조하는 부분을 찾고 하나씩 클래스로 바꾸기. 하나씩 수정할 때마다 `테스트`
5. 컬렉션 getter를 통해 원본 내용을 수정할 수 없는 읽기 전용 프록시나 복제본을 반환하도록 함
6. `테스트`

### 예시

수업(course) 목록을 필드로 지니고 있는 Person 클래스를 예.

```java
// 수정 전
public class Person {
    protected String name; 
    protected List<Course> courses = new ArrayList<>();

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }
}

public class Course {
    protected String name; 
    protected boolean isAdvanced;

    public String getName() {
        return name;
    }

    public boolean isAdvanced() {
        return isAdvanced;
    }
}
```

개발자가 캡슐화를 하면 되었다고 생각할 수 있겠지만 문제가 있음

setter가 있는점과 컬렉션을 제어하는 메서드가 없음. (todo: 반영 필요)

- 여기에선 컬렉션 안의 요소를 제거시 에러가 발생하는 경우를 잡도록 해놓음. 상황에 맞게 호출자가 대응할 여지를 남겨두기 위함.
- 이렇게 바꾼 후 컬렉션을 직접 다루던 코드가 있다면 방금 추가한 클래스와 메서드를 이용하도록 바꾸면 됨
- 그 다음 getter에서 원본을 그대로 노출시키는게 아니라 복사본을 주도록 바꾸기

```java
// 수정 후
public class Person {
    protected String name;
    protected List<Course> courses = new ArrayList<>();

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

		// 복사본 노출
    public List<Course> getCourses() {
        return new ArrayList<>(courses);
    }

    public void addCourse(Course course) {
        this.courses.add(course);
    }

    public void removeCourse(Course course) {
        this.courses.remove(course);
    }

		// 컬렉션 내부 요소 제거시 에러 catch
    public void removeCourse(int index) {
        try {
            this.courses.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw e;
        }
    }
}
```

---

## 7.3 기본형을 객체로 바꾸기

### 배경

- 개발 초기에는 단순한 정보나 문자를 표현했던 데이터들이 프로그램 규모가 커질수록 간단해지지 않음.
- ex) 전화번호 같은 문자열 데이터가 나중에 포매팅이나 지역 코드 추출등과 같은 특별한 동작이 필요해질 수 있음
- 저자: 데이터가 단순히 출력 이상의 기능이 필요해진다면 클래스로 바꾼다.

  이렇게 바꿈으로써 나중에 특별한 동작이 필요해지면 이 클래스에 추가하면 되므로 유용함


### 절차

1. 아직 변수를 캡슐화하지 않았다면 캡슐화 (6.6절)부터 함
2. 단순한 값 클래스(Value Class)를 만듦. 생성자는 기존 값을 인수로 받아서 저장하고 이 값을 반환하는 Getter를 추가함
3. 정적 검사 수행
4. 값 클래스의 인스턴스의 setter와 getter의 수정이 필요하면 변경함
5. 테스트

### 예시

주문(Order)클래스, 우선순위(Priority)라는 항목 존재.

```java
public class Order {
    protected String priority;

    public Order(String priority) {
        this.priority = priority;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }
}
```

```java
// 우선순위 값을 표현하는 클래스
public class Priority {
    protected String value;

    public Priority(String value) {
        this.value = value;
    }
    
    public String toString() {
        return value; 
    }
}

//Priority를 사용하도록 객체 변경
public class Order {
    protected Priority priority;

    public Order(String priority) {
        this.priority = new Priority(priority);
    }

		// getPriority() 메서드도 String을 붙이는게 자연스럽기에 수정
    public String getPriorityString() {
        return priority.toString();
    }

    public void setPriority(String priority) {
        this.priority = new Priority(priority);
    }
}
```

이후 우선순위를 비교하는 작업 등이 요구사항으로 들어올 수 있음. 그런 경우 Priority 클래스에 동작으로 추가해주면 됨

---

## 7.4 임시 변수를 질의 함수로 바꾸기

### 배경

함수 안에서 어떤 코드의 결과값을 뒤에서 다시 참조할 목적으로 임시 변수를 사용함. 임시 변수는 계산된 결과를 반복적으로 계산하지 않기 위해서 사용하는데 이는 함수로 만들어두는게 유용한 경우가 있음.(여러곳에서 똑같은 방식으로 계산되는 변수를 보면 이를 함수로 추출해 놓음)

주로 함수를 추출할 때 임시 변수가 문제가 됨. 함수의 파라미터 수를 줄이는데 기여함.

이 기법을 사용시 주의할 점은 변수를 스냅샷 처럼 사용하는 경우. 즉, 여러번의 대입을 하는 경우에는 사용하면 안된다는 점

### 절차

1. 변수가 사용되기 전에 값이 확실히 결정되는지, 즉 매번 다른 결과를 내지 않는지 확인
2. 읽기전용으로 만들 수 있는 변수는 읽기전용으로 만듦
3. 테스트
4. 변수 대입문을 함수로 추출
5. 테스트
6. 변수 인라인하기(6.4)로 임시 변수를 제거

### 예시

```java
// Order 클래스
public class Order {
    protected int quantity;
    protected Item item;

    public Order(int quantity, Item item) {
        this.quantity = quantity;
        this.item = item;
    }
    
    public double getPrice() {
		    // 1. 리팩터링 대상(임시변수 -> 메서드)
        int basePrice = quantity * item.price;
        
		    // 2. 리팩터링 대상(임시변수 -> 메서드)
        double discountFactor = 0.98; 
        
        if (basePrice > 1000) discountFactor -= 0.03; 
        return basePrice * discountFactor;
    }
}
```

```java
// 1번의 경우에는 읽기 전용으로 금방 빼낼 수 있음
    public double getPrice() {
        double discountFactor = 0.98;

        if (getBasePrice() > 1000) discountFactor -= 0.03;
        return getBasePrice() * discountFactor;
    }

    private int getBasePrice() {
        return quantity * item.price;
    }
```

```java
// 2번의 경우에는 대입하는 경우가 있으므로 고려해서 함수 추출해야함.
    public double getPrice() {
        return getBasePrice() * getDiscountFactor();
    }

    private int getBasePrice() {
        return quantity * item.price;
    }

    private double getDiscountFactor() {
        double discountFactor = 0.98;
        if (getBasePrice() > 1000) discountFactor -= 0.03;
        return discountFactor;
    }
```

---

## 7.5 클래스 추출하기

### 배경

클래스는 반드시 명확하게 추상화하고 주어진 소수의 역할만 수행해야함

하지만 실무에서는 주어진 클래스에 데이터와 동작이 계속해서 추가되면서 커지는 경우가 많음

역할이 많아지고 데이터와 메서드가 많은 클래스는 이해하기 어려움

그러므로 메서드와 데이터를 따로 묶을 수 있다면 클래스를 분리하라는 신호임

또 함께 변경되는 일이 많거나 서로 의존하는 데이터가 많다면 이도 분리할 수 있다는 신호.

### 절차

1. 클래스의 역할을 분리하는 방법을 정함
2. 분리될 역할을 담당할 클래스를 새로 만듦
3. 원래 클래스의 생성자에서 새로운 클래스의 인스턴스를 생성하여 필드에 저장
4. 분리될 역할에 필요할 필드들을 새 클래스로 옮김. 하나씩 옮길 때마다 테스트
5. 메서드들도 새 클래스로 옮김. 하나씩 옮길 때마다 테스트
6. 양쪽 클래스의 인터페이스를 살펴보면서 메서드를 제거하고 이름도 새로운 환경에 맞게 바꿈
7. 새 클래스를 외부로 노출할 지 정함

### 예시

```java
public class Person {
    protected String name;
    protected String officeAreaCode;
    protected String officeNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOfficeAreaCode() {
        return officeAreaCode;
    }

    public void setOfficeAreaCode(String officeAreaCode) {
        this.officeAreaCode = officeAreaCode;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }
}
```

1. TelephoneNumber 클래스 정의

```java
// 전화번호를 별도의 클래스로 뽑기
// officeAreaCode 필드 먼저 옮기기
public class TelephoneNumber {
}
```

1. TelephoneNumber 로 필드를 하나씩 옮기기
2. Person 클래스에 TelephoneNumber 필드를 추가하고 여기에 officeAreaCode 필드 옮기기

```java
public class Person {
    protected String name;
    protected String officeNumber;
    protected TelephoneNumber telephoneNumber;

    public Person() {
        telephoneNumber = new TelephoneNumber();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public String getOfficeAreaCode() {
        return telephoneNumber.officeAreaCode;
    }

    public void setOfficeAreaCode(String officeAreaCode) {
        this.telephoneNumber.officeAreaCode = officeAreaCode;
    }

    public String getOfficeNumber() {
        return officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.officeNumber = officeNumber;
    }
}
```

```java
public class TelephoneNumber {
    protected String officeAreaCode;
    protected String officeNumber;
}
```

```java
public class Person {
    protected String name;
    protected TelephoneNumber telephoneNumber;

    public Person() {
        telephoneNumber = new TelephoneNumber();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getOfficeAreaCode() {
        return telephoneNumber.officeAreaCode;
    }

    public void setOfficeAreaCode(String officeAreaCode) {
        this.telephoneNumber.officeAreaCode = officeAreaCode;
    }

    public String getOfficeNumber() {
        return this.telephoneNumber.officeNumber;
    }

    public void setOfficeNumber(String officeNumber) {
        this.telephoneNumber.officeNumber = officeNumber;
    }
}
```

- [클래스로 추출] 이제는 메서드 이름을 적절하게 바꿔야 할 때
    - TelephoneNumber 이라는 클래스를 맥락으로 주고 있으므로 굳이 office라는 단어를 사용할 필요는 없음
- 이후 전화번호와 관련된 동작이 필요하다면 이곳에 추가하면 됨

```java
public class TelephoneNumber {
    protected String areaCode;
    protected String number;
}
// + getter
```

---

## 7.6 클래스 인라인하기

### 배경

클래스 인라인 ↔ 클래스 추출하기(반대되는 리팩터링 기법)

- 저자 : 제 역할을 하지 못하는 클래스가 있다면 인라인 해버림
- 주로 역할을 옮기는 리팩터링 이후 남은 역할이 거의 없을 때 이 클래스를 가장 많이 사용하는 클래스로 옮김
- 두 클래스의 기능을 다시 배분하고 싶을 때 인라인하는 기법을 사용하기도 함
- 애매한 역할을 하는 두 클래스가 있다면 그것들을 합쳐서 새로운 클래스를 추출(7.5)하는게 더 나을 수 있음

### 절차

1. 소스 클래스(인라인 대상 클래스)의 public 메서드들을 타겟 클래스(되돌리려는 클래스)에 생성
2. 소스 클래스의 메서드를 사용하는 코드를 모두 타겟 클래스의 위임 메서드를 사용하도록 바꿈. 하나씩 바꿀 때마다 테스트 시행
3. 소스 클래스의 메서드와 필드를 모두 타겟 클래스로 옮김. 하나씩 옮길 때마다 테스트함
4. 소스 클래스를 삭제함

### 예시

- 배송 추적 정보를 표현하는 TrackingInformation 클래스

```java
public class TrackingInformation {
    protected String shippingCompany;
    protected String trackingNumber;

    public String display() {
        return String.format("%s: %s", shippingCompany, trackingNumber);     
    }
    
    public String getShippingCompany() {
        return shippingCompany;
    }

    public void setShippingCompany(String shippingCompany) {
        this.shippingCompany = shippingCompany;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
}
```

- TrackingInformation는 배송 Shipment 클래스의 일부로 사용됨

```java
public class Shipment {
    protected TrackingInformation trackingInformation; 
    
    public String trackingInfo() {
        return trackingInformation.display(); 
    }

    public TrackingInformation getTrackingInformation() {
        return trackingInformation;
    }

    public void setTrackingInformation(TrackingInformation trackingInformation) {
        this.trackingInformation = trackingInformation;
    }
}
```

- TrackingInformation 클래스가 제 역할을 하지 못한다고 판단해 인라인
    1. TrackingInformation에서 사용하는 메서드들을 모두 Shipment로 옮김
    2. TrackingInformation의 모든 요소를 옮김
    3. 메서드 인라인하기

```java
public class Shipment {
    protected TrackingInformation trackingInformation;
    protected String shippingCompany;
    protected String trackingNumber;

		// trackingInformation 내 display()를 인라인 필요
		// AS-IS
    public String display() {
        return String.format("%s: %s", shippingCompany, trackingNumber);
    }
    
    public String trackingInfo() {
		    // AS-IS
        // return trackingInformation.display();
        
        //TO-BE
        return String.format("%s: %s", shippingCompany, trackingNumber);
    }

    public TrackingInformation getTrackingInformation() {
        return trackingInformation;
    }

    public void setTrackingInformation(TrackingInformation trackingInformation) {
        this.trackingInformation = trackingInformation;
    }

    public String getShippingCompany() {
        return shippingCompany; 
    }

    public void setShippingCompany(String shippingCompany) {
        this.shippingCompany = shippingCompany; 
    }

    public String getTrackingNumber() {
        return trackingNumber; 
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber; 
    }
}
```

---

## 7.7 위임 숨기기

### 배경

- 모듈화 설계를 제대로 하는 핵심은 **캡슐화**
- 캡슐화는 모듈이 노출하는 요소를 제한해서 꼭 필요한 부분을 위주로 협력하게 해줌
- 캡슐화가 잘 되어있다면 무언가를 변경할 때 함께 고려해야 할 모듈수가 적어져 코드를 변경하시 쉬워짐
- 예로 객체가 다른 객체의 메서드를 호출하려면 그 객체를 알아야함. 근데 호출 당하는 객체의 인터페이스가 변경되면 그 객체를 알고있는 모든 객체가 변경이 필요해짐
- 이런 경우가 발생할 수 있으니 그 객체를 노출시키지 않으면 됨. 숨겨. 그러면 영향을 받지 않음
- **이렇게 객체가 다른 객체를 알면 안되는 경우. 즉 객체와 다른 객체가 결합하면 안되는 경우에 이 기법을 사용하면 좋음.**

  (그럼 그냥 위임숨기기는 캡슐화의 일종아닌가?)


### 절차

1. 위임 객체의 각 메서드에 해당하는 위임 메서드를 서버 객체에 생성함(서버 객체가 대신 호출)
2. 클라이언트가 위임 객체 대신 서버를 호출하도록 수정. 하나씩 바꿀 때마다 테스트함.
3. 모두 수정했다면, 서버로부터 위임 객체를 얻는 접근자를 제거함
4. 테스트

### 예시

사람(Person)과 사람이 속한 부서(Department)를 다음과 같이 정의

```java
// Person(사람) 클래스
public class Person {
    protected String name;
    protected Department department; 
    
    public Person(String name) {
        this.name = name;
    }

		// AS-IS, todo: department 참조 메서드 지우기
    public Department getDepartment() {
        return department;
    }
    
    // TO-BE
    public Person getManager() {
		    return department.getManager();
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
```

```java
// Department 클래스
public class Department {
    protected int chargeCode;
    protected Person manager; // 관리자

    public int getChargeCode() {
        return chargeCode;
    }

    public void setChargeCode(int chargeCode) {
        this.chargeCode = chargeCode;
    }

    public Person getManager() {
        return manager;
    }

    public void setManager(Person manager) {
        this.manager = manager;
    }rr
}
```

클라이언트에서 ‘**어떤 사람이 속한 부서의 관리자**’를 알고 싶은 경우

부서 객체를 얻어야함.

```java
person.department.manager
```

항상 부서 클래스를 통해서 매니저를 조회함. 이런 의존성을 줄이고 싶으면 사람 클래스에 간단히 위임 메서드 생성 가능

```java
// Person 클래스
public Person manager() {
		return department.getManager();
}
```

그리고 Person 객체에서 부서를 조회하는 메서드 제거.

---

## 7.8 중개자 제거하기

### 배경

- 위임 숨기기의 반대되는 리팩터링
    - 위임숨기기: 접근하려는 객체를 제한하는 캡슐화를 제공 → 불필요한 결합이나 의존성을 제거
- 만약 클래스에 위임이 너무 많다면 그냥 접근을 허용하도록 내버려 두는게 나을 수 있음
- 결합을 해야하는 구조라면 결합을 하는게 나을 수 있음
- 객체가 단순히 중개자(middle man) 역할만 해준다면 이 리팩터링 기법을 고려해보자

### 절차

1. 위임 객체를 얻는 getter 생성
2. 위임 메서드로 호출하는 클라이언트가 이 getter를 거치도록 수정
3. 하나씩 바꿀 때마다 테스트를 진행
4. 모두 수정했다면 위임 메서드를 삭제

### 예시

자신이 속한 부서(Department)의 관리자(Manager)를 찾는 사람 (Person) 클래스가 있는 상황

```java
public class Person {
    protected String name;
    protected Department department;

    public Person(String name) {
        this.name = name;
    }
    
    public void setDepartment(Department department) {
        this.department = department;
    }

    public Person manager() {
        return department.getManager();
    }
}
```

즉 Person 객체에서 department를 거치지 않고 Manager를 조회하는 위임 메서드가 있음.

중개자를 제거해보자

Person 객체에서 department를 조회하는 getter를 만들어야함.

```java
public class Person {
    protected String name;
    protected Department department;

    public Person(String name) {
        this.name = name;
    }

		// TO-BE
		// 이 메서드를 통해 Department 객체에 접근 가능해짐
    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

		// AS-IS
		// 불필요한 결합이나 의존성 제거
    public Person manager() {
        return department.getManager();
    }
}
```

---

## 7.9 알고리즘 교체하기

### 배경

어떤 목적을 달성하는 방법은 여러가지가 있음. 더 나은 방법 찾기.

- 더 나은 방법을 찾으면 복잡한 기존의 방법을 걷어내고 코드를 간결하게 고침.
- 리팩터링을 하면 복잡한 대상을 단순한 단위로 나누는게 가능해짐. 하지만 때로는 알고리즘 전체를 걷어내고 훨씬 간결한 알고리즘으로 바꿔야 할 때가 있음.
- 일부분(조금)만 바꾸고 싶을 때도, 통째로 바꾼 후에 처리하면 더 간단하게 처리 가능해짐
- 이 방법을 하기전에는 반드시 메서드를 잘게 나눴는지 확인하자. 거대하고 복잡한 알고리즘은 교체하기 어려움

### 절차

1. 교체할 코드를 함수 하나에 모음
2. 이 함수만을 이용해 동작을 검증하는 테스트를 마련
3. 대체할 알고리즘을 준비
4. 정적 검사를 수행함
5. 기존 알고리즘과 새 알고리즘의 결과를 비교하는 테스트 수행.
    1. 두 결과가 같다면 리팩터링 종료.
    2. 그렇지 않다면 기존 알고리즘을 참고해서 새 알고리즘을 테스트하고 디버깅함.

       (잠시동안 두 가지의 알고리즘이 있는 것)