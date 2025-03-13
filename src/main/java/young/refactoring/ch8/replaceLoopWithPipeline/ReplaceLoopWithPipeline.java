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
                .skip(1)                              // 1번 스킵하고
                .filter(line -> !line.trim().equals("")) // ""가 아닌 데이터만 가져오고
                .map(line -> line.split(","))
                .filter(record -> record[1].trim().equals("India"))
                .forEach(record -> result.add(new Office(record[0], record[2]))); //Performs an action for each element of this stream.
        return result;
    }
}
