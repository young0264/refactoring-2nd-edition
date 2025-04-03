package young.refactoring.ch11.preserveWholeObject;

public class Exam {
    Room room;
    HeatingPlan heatingPlan;

    public void client() throws Exception {
        int low = room.daysTempRange.low;
        int high = room.daysTempRange.high;
        if (!heatingPlan.withinRange(low, high)) {
            throw new Exception("방 온도가 지정 범위를 벗어났습니다.");
        }
    }

}
