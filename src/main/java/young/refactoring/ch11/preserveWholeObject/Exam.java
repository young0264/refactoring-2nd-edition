package young.refactoring.ch11.preserveWholeObject;

public class Exam {
    Room room;
    HeatingPlan heatingPlan;

    public void client() throws Exception {
        Range range = new Range(room.daysTempRange.low, room.daysTempRange.high);

        if (!heatingPlan.nexWithinRange(range)) {
            throw new Exception("방 온도가 지정 범위를 벗어났습니다.");
        }
    }

}
