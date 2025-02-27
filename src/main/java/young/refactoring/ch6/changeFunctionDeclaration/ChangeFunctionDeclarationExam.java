package young.refactoring.ch6.changeFunctionDeclaration;

public class ChangeFunctionDeclarationExam {

    public double circum(double radius){
        return circumference(radius);
    }

    public double circumference(double radius){
        return 2* Math.PI * radius;
    }

}
