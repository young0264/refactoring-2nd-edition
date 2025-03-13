## 08 기능 이동

- 지금까지는 프로그램 요소 생성, 제거, 이름 변경 등의 리팩터링 진행
- 이제부터 다른 컨택스트로 옮겨서 적절한 모듈을 만들어주는 방향의 리팩터링 진행

---

- (8.1) 함수 옮기기
- (8.2) 필드 옮기기
- (8.3) 문장을 함수로 옮기기
- (8.4) 문장을 호출한 곳으로 옮기기
- (8.5) 인라인 코드를 함수 호출로 바꾸기
- (8.6) 문장 슬라이스
- (8.7) 반복문 쪼개기
- (8.8) 반복문을 파이프라인으로 바꾸기
- (8.9) 죽은 코드 제거하기

---

## 8.1 함수 옮기기(Move Function)

### 배경

리팩터링이 필요한 악취가 날 때?

1. 뒤엉킨 변경
2. 산탄총 수술
3. 기능 편애
4. 임시 필드
5. 메시지 체인
6. 내부자 거래
7. 서로 다른 인터페이스와 대안들
8. 데이터 클래스

---

- 소프트웨어의 핵심? 모듈성(modularity)

  모듈성이 좋다의 기준? → 서로 연관성이 있는 요소, 함수들이 모여있고 이들의 연관 관계를 파악하기 쉽다라는 점

- 모듈화가 잘되어 있다면 어떤 점이 좋을지?
    - 모듈끼리의 불필요한 결합이 줄어듬.
    - 느슨한 결합을 지향(느슨한 결합은 대체하기가 쉽고 코드의 수정이 쉬움)
    - 캡슐화를 통해 불필요한 부분을 드러내지 않아도 됨. 필요한 부분만 보면되므로 코드의 이해가 쉬워짐
- 어떤 기준으로 함수 옮기기의 기준을 적용할 수 있을까?
    - 대상 함수의 현재 컨택스트와 후보 캔택스트를 둘러보며 비교.

### 절차

### 예시

**중첩 함수를 최상위로 옮기기**

**다른 클래스로 옮기기**

- **위임 전**

```java
public class Account {
    private int dayOverdrawn;
    private AccountType type;

    /** 은행 이자 계산 */
    public double bankCharge(){
        double result = 4.5;
        if (this.dayOverdrawn > 0) {
            result += this.overdraftCharge();
        }
        return result;
    }

    /** 초과 인출 이자 계산 */
    public double overdraftCharge() {
        if (this.type.isPremium) {
            int baseCharge = 10;
            if (this.dayOverdrawn <= 7) {
                return baseCharge;
            } else {
                return baseCharge * (this.dayOverdrawn - 7) * 0.85;
            }
        } else {
            return this.dayOverdrawn * 1.75;
        }
    }
}
```

- **위임 후**

```java
public class AccountType {

    public boolean isPremium;

    public double overdraftCharge(int daysOverdrawn) {
        if (this.isPremium) {
            int baseCharge = 10;
            if (daysOverdrawn <= 7) {
                return baseCharge;
            } else {
                return baseCharge + (daysOverdrawn - 7) * 0.85;
            }
        } else {
            return daysOverdrawn * 1.75;
        }
    }
}
```

```java
public class Account {
    private int dayOverdrawn; //(계좌별로 달라지는 변수), 특정날 초과인출?
    private AccountType type;

    /** 은행 이자 계산 */
    public double bankCharge(){
        double result = 4.5;
        if (this.dayOverdrawn > 0) {
            result += this.overdraftCharge();
        }
        return result;
    }

    /** 초과 인출 이자 계산 */
    public double overdraftCharge() { // 위임 메서드
        return this.type.overdraftCharge(this.dayOverdrawn);
    }
}
```

---

## 8.2 필드 옮기기(Move Field)

### 배경

잘 짜여진 데이터 구조는 직고나적으로 어떠한 동작을 수행하는지 이해하기 쉽고 짜기 쉬움.

처음부터 데이터 구조를 올바르게 짜기 어려움.

도메인 지식이 점차 쌓이면서 더 적합한 데이터 구조가 보일 수 있음.

필드 옮기기 : 더 큰 리팩터링을 하기 위한 수단으로 사용됨.

필드 하나를 옮기면 그 ㄱ필드를 사용하던 함수들도 같이 옮길 수 있기 때문.

- 산탄총 수술, 내부자 거래 악취 → **필드 옮기기** 리팩터링 적용

### 예시

[TODO]

TO-BE : discountRate 필드를 CustomerContract 클래스로 옮기기

- 옮길 필드를 캡슐화 해놓기

```java
public class Customer {
    protected String name;
    protected CustomerContract customerContract;

    public Customer(String name, double discountRate) {
        this.name = name;
        this.customerContract = new CustomerContract(LocalDateTime.now(), discountRate);
    }

    private void setDiscountRate(double discountRate) {
        this.customerContract.discountRate = discountRate;
    }

    public double getDiscountRate() {
        return customerContract.discountRate;
    }

    public void becomePreferred() {
        setDiscountRate(getDiscountRate() + 0.3);
        // 다른 멋진 일들
    }

    public int applyAmount(int amount) {
        return Math.subtractExact(amount, (int) (amount * getDiscountRate()));
    }

}
```

```java
public class CustomerContract {
    protected LocalDateTime startDate;
    protected double discountRate;

    public CustomerContract(LocalDateTime startDate, double discountRate) {
        this.startDate = startDate;
        this.discountRate = discountRate;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }
}
```

---

## 8.3 문장을 함수로 옮기기

### 배경

- 중복 제거는 코드를 건강하게 관리하는 가장 효과적인 방법 중 하나
- 중복 코드를 피호출 함수로 합치면, 추후 반복되는 부분에서 수정이 필요할 때 한번만 수정하면 됨
- 그 문장들이 피호출 함수의 일부라는 확신이 있어야함.

### 절차

1. ‘문장 슬라이드’로 근처로 이동
2. 코드를 잘라 피호출 함수로 복사하고 테스트
3. 호출자가 둘 이상이면, 함수로 추출. 기억하기 쉬운 임시 이름을 지어줌
4. 다른 호출자 모두가 방금 추출한 함수를 사용하도록 수정함. 하나씩 수정할 때마다 테스트함.
5. 새로운 함수를 사용하게 되면. 원래 함수를 새로운 함수 안으로 인라인한 후 원래 함수를 제거.
6. 새로운 함수 이름을 원래 함수의 이름으로 바꿔줌(함수 이름 바꾸기)

### 예시

```java
// 사진 관련 데이터를 HTML로 내보내는 코드.
public class Camera {

    public String renderPerson(OutputStream outputStream, Person person) {
        StringBuilder result = new StringBuilder();
        result.append(String.format("<p> %s </p>", person.name));
        result.append(renderPhoto(person.photo));
        result.append(emitPhotoData(person.photo));

        // OutputStream에 문자열을 바이트로 변환하여 출력
//        outputStream.write(result.toString().getBytes(StandardCharsets.UTF_8));
//        outputStream.flush(); // 버퍼를 비우고 즉시 출력

        return result.toString();
    }

    private String renderPhoto(Photo photo) {
        return null;
    }

    public String photoDiv(Photo photo) {
        StringBuilder result = new StringBuilder();
        result.append("<div>");
        result.append(emitPhotoData(photo));
        result.append("</div>");
        return result.toString();
    }

    public String emitPhotoData(Photo photo) {
        StringBuilder result = new StringBuilder();
        result.append(String.format("<p> 제목: %s </p>", photo.title));
        result.append(String.format("<p> 위치: %s </p>", photo.location));
        result.append(String.format("<p> 날짜: %s </p>", photo.date.toString()));
        return result.toString();
    }

}
```

```java
public class Person {
    String name;
    Photo photo;
}
```

```java
public class Photo {
    String title;
    String location;
    LocalDateTime date;
}
```

---

## 8.4 문장을 호출한 곳으로 옮기기

→ 함수를 하나 만들어 안전하게 작업 진행

### 배경

- 함수는 프로그래머가 쌓아 올리는 추상화의 기본 빌딩 블록임.
- 코드베이스의 기능 범위가 달라지면 추상화의 경계도 움직임.
- 함수 관점에서 생각해보면, 초기에는 응집도 높고 한가지 일만 수행하던 함수가 어느새 둘 이상의 다른 일을 수행하게 바뀔 수가 있음.

### 절차

1. 호출자가 한개, 피호출 함수도 간단 → 피호출자의 함수를 추출해서 호출자에게(원래대로) 넣고 테스트 → 성공하면 리팩터링 끝.
2. 이동하면 안되는 코드를 함수로 추출, 검색하기 쉬운 이름으로 지어줌.
3. 함수 인라인
4. 추출된 함수의 이름을 원래 함수의 이름으로 변경

### 예시

[AS-IS]: 날짜 출력만 따로 빼고 싶은 상황.

```java
public class Camera {

    public void renderPeron(OutputStream outputStream, Person person) throws IOException {
        outputStream.write(String.format("<p> %s </p>", person.name).getBytes());
        renderPhoto(outputStream, person.photo);
        emitPhotoData(outputStream, person.photo);
    }

    public void listRecentPhotos(OutputStream outputStream, List<Photo> photos) {
        photos.stream()
                .filter(p -> p.date.isAfter(recentDateCutOff()))
                .forEach(p -> {
                    try {
                        outputStream.write("<div> \n".getBytes());
                        emitPhotoData(outputStream, p);
                        outputStream.write("</div>\n".getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    private void emitPhotoData(OutputStream outputStream, Photo photo) throws IOException {
        outputStream.write(String.format("<p> 제목: %s </p>", photo.title).getBytes());
        outputStream.write(String.format("<p> 위치: %s </p>", photo.location).getBytes());
        outputStream.write(String.format("<p> 날짜: %s </p>", p.date.toString()).getBytes());
    }

    public String renderPhoto(OutputStream outputStream, Photo photo) {
        return null;
    }

    private LocalDateTime recentDateCutOff() {
        return null;
    }

}
```

[TO-BE] : zzNew라는 눈에띄는 별개 함수를 만들어 날짜 출력문을 제외한 함수를 만듦. 이동작업.

```java
public class Camera {

    public void renderPeron(OutputStream outputStream, Person person) throws IOException {
        outputStream.write(String.format("<p> %s </p>", person.name).getBytes());
        renderPhoto(outputStream, person.photo);
        emitPhotoData(outputStream, person.photo);
        outputStream.write(String.format("<p> 날짜: %s </p>", person.photo.date.toString()).getBytes());
    }

    public void listRecentPhotos(OutputStream outputStream, List<Photo> photos) {
        photos.stream()
                .filter(p -> p.date.isAfter(recentDateCutOff()))
                .forEach(p -> {
                    try {
                        outputStream.write("<div> \n".getBytes());
                        emitPhotoData(outputStream, p);
                        outputStream.write(String.format("<p> 날짜: %s </p>", p.date.toString()).getBytes());
                        outputStream.write("</div>\n".getBytes());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    public String renderPhoto(OutputStream outputStream, Photo photo) {
        return null;
    }

    private LocalDateTime recentDateCutOff() {
        return null;
    }

    private void emitPhotoData(OutputStream outputStream, Photo photo) throws IOException {
        outputStream.write(String.format("<p> 제목: %s </p>", photo.title).getBytes());
        outputStream.write(String.format("<p> 위치: %s </p>", photo.location).getBytes());
    }

}
```

---

## 8.5 인라인 코드를 함수 호출로 바꾸기

→중복 제거를 위해 함수로 추출하여 인라인 작업하기

### 배경

- 함수는 여러 동작을 하나로 묶어줌

  함수를 활용하면 이해하기 더 쉬워짐.

  중복을 없애는데 사용함

- 비슷한 코드를 일일이 찾아 수정하는 대신 함수 하나만 수정하면 된다는 장점이 있음.
- 이미 존재하는 함수와 똑같은 일을 하는게 있다면 이 코드를 인라인으로 바꾸자.
- 이름을 잘 지어서 목적이 잘 드러나도록.!
- 인라인 할 코드가 보이면 함수로 바꿔서 인라인 작업하자.

---

## 8.6 문장 슬라이드하기

→ 코드 옮겨서 한곳에 모아두기

### 배경

관련된 코드들이 서로 가까이 모여있다면 이해하기 더 쉬움

문장 슬라이드하기 리팩터링으로 이런 코드들을 한군데에 모아둠

- 변수를 함수 첫머리가 아닌 사용 시점에 선언하는 스타일을 선호함.
- 이런 문장 슬라이드를 통해 관련 코드끼리 모으는 작업은 리팩터링의 준비단계에 자주 수행함.

  (관련있는 코드를 한눈에 들어오게 모은 후 함수 추출)


### 절차

1. 코드(문장들)를 보며 이동할 위치를 찾음.

   이동하면 동작이 달라지는 코드가 있는지도 체크함. 문제가 있으면 이 방식은 포기(이동 포기)

2. 코드조각을 잘라낸 후 목표 위치에 붙여넣음
3. 테스트

### 예시

```java
1. PricingPlan pricingPlan = retrievePricingPlan();
2. Order order = retrieveOrder();
3. int baseCharge = pricingPlan.base;
4. int charge;
5. int chargePerUnit = pricingPlan.unit;
6. int units = order.units;
7. int discount;
8. charge = baseCharge + units * chargePerUnit;
9. int discountableUnits = Math.max(units - pricingPlan.discountThreshold, 0);
10. discount = (int) (discountableUnits * pricingPlan.discountFactor);
11. if (order.isRepeat) discount += 20; 
12. charge = charge - discount; 
13. chargeOrder(charge);
```

```java
    public void slideStatements() {
        PricingPlan pricingPlan = retrievePricingPlan();
        Order order = retrieveOrder();
        int baseCharge = pricingPlan.base;
        int charge;
        int chargePerUnit = pricingPlan.unit;
        int units = order.units;
        int discount;
        charge = baseCharge + units * chargePerUnit;
        int discountableUnits = Math.max(units - pricingPlan.discountThreshold, 0);
        discount = (int) (discountableUnits * pricingPlan.discountFactor);
        if (order.isRepeat) discount += 20;
        charge = charge - discount;
        chargeOrder(charge);
    }
```

---

## 8.7 반복문 쪼개기

`todo: 실용성이 얼마나 있을까?. 실용적일지가 의문임`

### 배경

- 반복문 하나에서 두가지 일을 수행하는 경우
  (한번에 모두 처리하는게 성능적으로 좋지 않을까 하는 생각에 기인)
- 반복문안에서 어떠한 일을 수행하고 있는지 제대로 파악해야함
- 반복문을 분리하면 사용하기도 쉬워짐
- 반면 여러 일을 수행하는 반복문이라면 구조체를 반환하거나 지역 변수를 활용해야함
- 반복문 쪼개기는 서로 다른 일들이 한 함수에서 이뤄지고 있는 신호일 수 있음.

  반복문 쪼개기와 함수 추출하기는 연이어서 수행하는 일이 잦음


### 절차

1. 반복문을 복제하여 두 개로 만듦
2. 반복문이 중복되어 생기는 부수효과를 파악해서 제거함
3. 테스트함
4. 완료됐으면 각 반복문을 함수로 추출할지 고민함

### 예시

- 전체 급여와 가장 어린 나이를 계산하는 코드

```java
int youngest = peopleList.isEmpty() ? Integer.MAX_VALUE : peopleList.get(0).age;
int totalSalary = 0;
for (People p : peopleList) {
    if (p.age < youngest) youngest = p.age; 
    totalSalary += p.salary;
}
```

- 관련 없는 두 가지 계산을 수행 → 반복문 쪼개기 적용

```java
int youngest = peopleList.isEmpty() ? Integer.MAX_VALUE : peopleList.get(0).age;
int totalSalary = 0;

// #1
for (People p : peopleList) {
    if (p.age < youngest) youngest = p.age;         
}

// #2
for (People p : peopleList) { 
    totalSalary += p.salary;
}
```

- 반복문 쪼개기의 묘미는 이 자체가 아닌 그 다음 단계로 가는 디딤돌 역할에 있음.

  이 리팩터링을 할 때는 나뉜 각 반복문을 **각각의 함수로 추출하면** 어떨지까지 한 묶음으로 고민하자.


---

## 8.8 반복문을 파이프라인으로 바꾸기

### 배경

- 반복문 순회 시 반복문을 사용하라고 배웠겠지만..

  언어는 계속해서 발전하고 더 나은 구조를 제공해줌.

- Collection Pipeline을 이용하면 **처리 과정을 일련의 연산으로 표현**할 수 있음.

  이때 각 연산은 컬렉션을 입력받아 다른 컬렉션을 뱉음.

  대표적으로 map과 filter. map은 함수를 사용해 입력 컬렉션의 각 원소를 변환. filter는 또 다른 함수를 사용해 입력 컬렉션을 필터링해 부분집합을 만듦

- 파이프라인은 다음 단계를 위해서 컬렉션을 뱉으므로 논리를 파이프라인으로 표현하면 이해하기 쉬움.

### 절차

1. 반복문에서 사용하는 컬렉션을 가리키는 변수를 만듦
2. 반복문의 첫줄부터 시작해서 각각의 단위 행위를 적절한 컬렉션 파이프라인 연산으로 대체.
   이전에 만든 변수부터 시작해서 연쇄적으로 수행. 하나씩 대체할 때마다 테스트함
3. 반복문의 모든 동작을 대체했다면 반복문 자체를 지움

### 예시

```
office, country, telephone
Chicago, USA, +1 312 373 1000
Beijing, China, +86 4008 900 505
Bangalor, India, +91 80 4064 9570

... (더 많은 데이터)
```

```java
// AS-IS
public List<Office> acquireData(String input) {
    String[] lines = input.split("\n");
    boolean firstLine = true;
    List<Office> result = new ArrayList<>();
    for (String line : lines) {
        if (firstLine) {
            firstLine = false;
            continue;
        }
        if (line.trim().equals("")) continue;

        String[] record = line.split(",");
        if (record[1].trim().equals("India")){
            //city, phone
            result.add(new Office(record[0], record[2]));
        }
    }
    return result;
}
```

```java
// TO-BE
public List<Office> acquireData(String input) {
        String[] lines = input.split("\n");
        List<Office> result = new ArrayList<>();

        String[] loop = lines;

        // TO-BE
        Arrays.stream(loop)
                .skip(1)                              // 1번 스킵하고
                .filter(line -> !line.trim().equals("")) // ""가 아닌 데이터만 가져오고
                .map(line -> line.split(","))
                .filter(record -> record[1].trim().equals("India"))
                .forEach(record -> result.add(new Office(record[0], record[2]))); //Performs an action for each element of this stream.
        return result;
    }
```

---

## 8.9 죽은 코드 제거하기

### 배경

소프트웨어에서 사용되지 않은 코드가 있다면 그 소프트웨어의 동작을 이해하는데 커다란 어려움을 줄 수 있음.

‘이 코드들은 절대 호출되지 않으니 무시해도 된다!’ 라는 신호를 주지 않기 때문임.**(호출이 되지 않더라도 다른 개발자가 의도적으로 남겨놓았을 수도 있기 때문에 사용되지 않는다면 삭제하자)**

코드가 더 이상 삭제되지 않게 됐다면 지워야함. 혹시라도 다시 필요해질 것이라는 불안감 때문에 남겨놓는다면 VCS(Version Control System)을 이용하자.