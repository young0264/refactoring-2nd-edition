package young.refactoring.ch1.model;

import java.util.Map;

public class Plays {

    Map<String, Play> plays;

    public Plays(Map<String, Play> plays) {
        this.plays = plays;
    }

    public Map<String, Play> getPlays() {
        return plays;
    }

    public Play get(String playName) {
        return plays.get(playName);
    }

}
