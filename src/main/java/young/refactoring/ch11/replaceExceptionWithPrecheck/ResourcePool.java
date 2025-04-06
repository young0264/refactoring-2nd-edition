package young.refactoring.ch11.replaceExceptionWithPrecheck;

import java.util.*;

public class ResourcePool {
    Deque<Resource> available = new ArrayDeque<>();
    List<Resource> allocated = new ArrayList<>();

    public Resource get() {
        Resource result;

        if (available.isEmpty()) {
            result = Resource.create();
            allocated.add(result);
        } else {
            result = available.pop();
            allocated.add(result);
        }
        return result;
    }
}