package dhbw.fowler2.theatre;

import dhbw.fowler2.theatre.Play;
import dhbw.fowler2.theatre.Performance;
import dhbw.fowler2.theatre.StatementPrinter;
import dhbw.fowler2.theatre.Invoice;
import java.util.List;
import java.util.Map;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class StatementPrinterTest {
    private static Invoice invoice;
    private static Map<String, Play> plays;
    @BeforeClass
    public static void setUp(){
        invoice = new Invoice("BigCo", List.of(new Performance("hamlet", 55),
                new Performance("as-like", 35),
                new Performance("othello", 40)));

        plays = Map.of("hamlet", new Play("Hamlet", "tragedy"),
                "as-like", new Play("As You Like It", "comedy"),
                "othello", new Play("Othello", "tragedy"));
    }

    @Test
    public void printsStatements() {
        StatementPrinter statementPrinter = new StatementPrinter();
        var result = statementPrinter.print(invoice, plays);

        Assert.assertEquals("Statement print mismatch", "Statement for BigCo\n" +
                "  Hamlet: $650.00 (55 seats)\n" +
                "  As You Like It: $580.00 (35 seats)\n" +
                "  Othello: $500.00 (40 seats)\n" +
                "Amount owed is $1,730.00\n" +
                "You earned 47 credits\n", result);
    }

    @Test
    public void printsHTMLStatements(){
        StatementPrinter statementPrinter = new HTMLStatementPrinter();
        String result = statementPrinter.print(invoice, plays);

        Assert.assertEquals("Statement print mismatch",
                "<h1>Statement for BigCo</h1>\n" +
                "<ul>\n" +
                "  <li>Hamlet: $650.00 (55 seats)</li>\n" +
                "  <li>As You Like It: $580.00 (35 seats)</li>\n" +
                "  <li>Othello: $500.00 (40 seats)</li>\n" +
                "</ul>\n"+
                "<p>Amount owed is $1,730.00</p>\n" +
                "<p>You earned 47 credits</p>\n", result);
    }
}
