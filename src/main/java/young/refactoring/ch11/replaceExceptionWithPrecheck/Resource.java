package young.refactoring.ch11.replaceExceptionWithPrecheck;

public class Resource {
    public static Resource create() {
        return new Resource();
    }
}