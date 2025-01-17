package young.refactoring.ch1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import young.refactoring.ch1.enums.PlayType;
import young.refactoring.ch1.model.Invoice;
import young.refactoring.ch1.model.Performance;
import young.refactoring.ch1.model.Play;
import young.refactoring.ch1.model.Plays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StatementTest {

    @Test
    void statementRenderPlainText() throws Exception {
        Statement statement = new Statement();

        List<Performance> performances = getPerformancesForTest();

        // Invoice 데이터 생성
        Invoice invoice = new Invoice("손님1", performances);

        HashMap<String, Play> playMap = getStringPlayHashMapForTest();

        //playMap 데이터 생성
        Plays plays = new Plays(playMap);

        String statementResult = statement.statementForPlainText(invoice, plays);

        String expectedAnswer = """
                청구내역 (고객명: 손님1)
                hamelet: 600 50석
                young: 1100 100석
                hyun: 2100 200석
                총액: 3800
                적립 포인트: 280점
                """;
        Assertions.assertEquals(statementResult, expectedAnswer);
    }

    @Test
    void statementRenderHtml() throws Exception {
        Statement statement = new Statement();

        // performances 데이터 작성
        List<Performance> performances = getPerformancesForTest();

        // Invoice 데이터 생성
        Invoice invoice = new Invoice("손님1", performances);

        // playMap 데이터 작성
        HashMap<String, Play> playMap = getStringPlayHashMapForTest();

        //playMap 데이터 생성
        Plays plays = new Plays(playMap);

        String statementResult = statement.statementForHtml(invoice, plays);

        String expectedAnswer = """
                <h1> 청구내역 (고객명: 손님1)
                </h1><table>\s
                <tr><th> 연극 </th> <th>좌석 수</th> <th>금액</th><tr><td> hamelet: </td> <td> $600 </td> <td> 50석 </td></tr>
                <tr><td> young: </td> <td> $1100 </td> <td> 100석 </td></tr>
                <tr><td> hyun: </td> <td> $2100 </td> <td> 200석 </td></tr>
                </table>
                총액: $3800
                적립 포인트: 280점""";

        Assertions.assertEquals(statementResult, expectedAnswer);

    }

    private static HashMap<String, Play> getStringPlayHashMapForTest() {
        // playMap 데이터 작성
        HashMap<String, Play> playMap = new HashMap<>();
        playMap.put("hamlet", new Play("hamelet", PlayType.TRAGEDY));
        playMap.put("young", new Play("young", PlayType.COMEDY));
        playMap.put("hyun", new Play("hyun", PlayType.TRAGEDY));
        return playMap;
    }

    private static List<Performance> getPerformancesForTest() {
        // performances 데이터 작성
        List<Performance> performances = new ArrayList<>();
        performances.add(new Performance("hamlet", 50));
        performances.add(new Performance("young", 100));
        ;
        performances.add(new Performance("hyun", 200));
        return performances;
    }

}
