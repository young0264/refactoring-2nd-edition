package young.refactoring.ch9.changeReferenceTovalue;

public class Person {
    TelephoneNumber telephoneNumber;

    public Person(String areaCode, String number) {
        telephoneNumber = new TelephoneNumber(areaCode, number);        // refactor: 전체 생성자
    }

    public String getOfficeAreaCode() {
        return telephoneNumber.areaCode;
    }

    public void setOfficeAreaCode(String areaCode) {
        telephoneNumber = new TelephoneNumber(areaCode, telephoneNumber.number);    // refactor: 새로운 객체 대입(불변유지)
    }

    public String getOfficeNumber() {
        return telephoneNumber.number;
    }

    public void setOfficeNumber(String number) {
        telephoneNumber = new TelephoneNumber(telephoneNumber.areaCode, number);    // refactor: 새로운 객체 대입(불변유지)
    }
}
