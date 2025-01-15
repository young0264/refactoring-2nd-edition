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
    void statement() throws Exception {
        Statement statement = new Statement();

        // performances 데이터 작성
        List<Performance> performances = new ArrayList<>();
        performances.add(new Performance("hamlet", 50));
        performances.add(new Performance("young", 100));;
        performances.add(new Performance("hyun", 200));

        // Invoice 데이터 생성
        Invoice invoice = new Invoice("손님1", performances);

        // playMap 데이터 작성
        HashMap<String, Play> playMap = new HashMap<>();
        playMap.put("hamlet", new Play("hamelet", PlayType.TRAGEDY));
        playMap.put("young", new Play("young", PlayType.COMEDY));
        playMap.put("hyun", new Play("hyun", PlayType.TRAGEDY));

        //playMap 데이터 생성
        Plays plays = new Plays(playMap);

        String statementResult = statement.statement(invoice, plays);

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
}
