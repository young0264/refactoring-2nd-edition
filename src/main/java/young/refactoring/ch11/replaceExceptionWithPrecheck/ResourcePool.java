package young.refactoring.ch11.replaceExceptionWithPrecheck;

import java.util.*;

public class ResourcePool {
    Deque<Resource> available = new ArrayDeque<>();
    List<Resource> allocated = new ArrayList<>();

    public Resource get() {
        Resource result;
        try {
            result = available.pop();
            allocated.add(result);
        } catch (NoSuchElementException e) {
            result = Resource.create();
            allocated.add(result);
        }
        return result;
    }
}