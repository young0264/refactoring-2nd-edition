package young.refactoring.ch1.model;

import java.util.List;

public class Plays {

    List<Play> playList;

    public Play get(String playID) {
        for (Play play : playList) {
            if (playID.equals(play.getName())) {
                return play;
            }
        }
        return new Play("Unknown", "Unknown");
    }

}
