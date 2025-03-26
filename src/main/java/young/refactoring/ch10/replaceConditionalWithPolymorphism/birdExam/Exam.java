package young.refactoring.ch10.replaceConditionalWithPolymorphism.birdExam;

public class Exam {
    public String plumage(Bird bird) {
        return bird.plumage();
    }

    public int airSpeedVelocity(Bird bird) {
        return bird.airSpeedVelocity();
    }

    // 주어진 타입에 따라 해당하는 서브클래스(Bird의 하위 클래스) 인스턴스를 생성하여 반환
    // 반환 타입은 부모 클래스인 Bird로 선언하여 다형성을 활용
    public Bird createBird(String type) {
        switch (type) {
            case "유럽 제비":
                return new EuropeanSwallow();
            case "아프리카 제비":
                return new AfricanSwallow();
            case "노르웨이 파랑 앵무":
                return new NorwegianBlueParrot();
            default:
                return new Bird();
        }
    }

}
