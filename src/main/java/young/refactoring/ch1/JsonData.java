package young.refactoring.ch1;

public class JsonData {

    private final String playJson = """
            {
              "hamlet": {"name": "Hamlet", "type": "tragedy"},
              "as-like": {"name": "As You Like It", "type": "comedy"},
              "othello": {"name": "Othello", "type": "tragedy"}
            }
            """;

    private final String invoicesJson = """
            [
              {
                "customer": "BigCo",
                "performance": [
                  {
                    "playID": "hamlet",
                    "audience": 55
                  },
                  {
                    "playID": "as-like",
                    "audience": 35
                  },
                  {
                    "playID": "othello",
                    "audience": 40
                  }
                ]
              }
            ]
            """;


}
