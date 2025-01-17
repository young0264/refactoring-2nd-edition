package young.refactoring.ch1;

import young.refactoring.ch1.enums.PlayType;
import young.refactoring.ch1.model.*;

public class Statement {

    public String statementForPlainText(Invoice invoice, Plays plays) throws Exception {
        return renderPlainText(new StatementData(invoice, plays));
    }

    public String statementForHtml(Invoice invoice, Plays plays) throws Exception {
        return renderHtml(new StatementData(invoice, plays));
    }

    private String renderPlainText(StatementData statementData) throws Exception {
        StringBuilder result = new StringBuilder(String.format("청구내역 (고객명: %s)\n", statementData.getInvoice().getCustomer()));

        for (Performance performance : statementData.getInvoice().getPerformanceList()) {
            result.append(String.format("%s: %d %d석\n"
                    , statementData.playFor(performance).getName(), statementData.amountFor(performance) / 100, performance.getAudience()));
        }
        result.append(String.format("총액: %d\n", statementData.totalAmount(statementData)));
        result.append(String.format("적립 포인트: %d점\n", statementData.totalVolumeCredits(statementData)));
        return result.toString();
    }

    private String renderHtml(StatementData statementData) throws Exception {
        StringBuilder result = new StringBuilder(String.format("<h1> 청구내역 (고객명: %s)\n</h1>", statementData.getCustomer()));
        result.append("<table> \n");
        result.append("<tr><th> 연극 </th> <th>좌석 수</th> <th>금액</th>");
        for (Performance performance : statementData.getPerformances()) {
            result.append(String.format("<tr><td> %s: </td> <td> $%d </td> <td> %d석 </td></tr>\n",statementData.playFor(performance).getName(), statementData.amountFor(performance) / 100, performance.getAudience()));
        }
        result.append("</table>\n");

        result.append(String.format("총액: $%d\n", statementData.totalAmount(statementData)));
        result.append(String.format("적립 포인트: %d점", statementData.totalVolumeCredits(statementData)));
        return result.toString();
    }

}
