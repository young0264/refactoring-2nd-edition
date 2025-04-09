package young.refactoring.ch12.pullUpConstrucBody;

public class Manager extends Employee {
    Grade grade;

    public Manager(String name, Grade grade, boolean isPrivileged) {
        super(name);
        this.grade = grade;

        // finishConstruction는 부모 메서드 호출.
        //  -> 메서드 내 isPrivileged()는 재정의 된 자식 메서드 호출
        // 개인적으로는 좀 불편한 구조라고 생각되는데 어떻게 해야 이해하기 쉬울까?(트래킹이 힘들다)
        finishConstruction(); // 공통로직 올리기.
    }

    @Override
    public boolean isPrivileged() {
        return grade.val > 4;
    }
}
