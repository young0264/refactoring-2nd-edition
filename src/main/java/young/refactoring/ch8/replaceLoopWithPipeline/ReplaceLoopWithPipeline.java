package young.refactoring.ch8.replaceLoopWithPipeline;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ReplaceLoopWithPipeline {
    public List<Office> acquireData(String input) {
        String[] lines = input.split("\n");
        List<Office> result = new ArrayList<>();

        String[] loop = lines;

        // TO-BE
        Arrays.stream(loop)
                .skip(1)
                .filter(line -> !line.trim().equals(""))
        ;

        // AS-IS
        for (String line : lines) {
            String[] record = line.split(",");
            if (record[1].trim().equals("India")){
                //city, phone
                result.add(new Office(record[0], record[2]));
            }
        }
        return result;
    }
}
