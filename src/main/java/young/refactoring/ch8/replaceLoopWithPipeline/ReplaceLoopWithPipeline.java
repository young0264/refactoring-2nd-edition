package young.refactoring.ch8.replaceLoopWithPipeline;

import java.util.ArrayList;
import java.util.List;

public class ReplaceLoopWithPipeline {
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
}
