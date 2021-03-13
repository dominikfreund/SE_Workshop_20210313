package dhbw.fowler2.theatre;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {

    public String print(Invoice invoice, Map<String, Play> plays) {
        var totalAmount = 0;
        var volumeCredits = 0;
        var result = String.format("Statement for %s\n", invoice.customer);

        NumberFormat frmt = NumberFormat.getCurrencyInstance(Locale.US);

        for (var perf : invoice.performances) {
            var play = plays.get(perf.playID);
            var thisAmount = 0;

            thisAmount = calculateAmount(play, perf.audience);

            // add volume credit
            volumeCredits += Math.max(perf.audience - 30, 0);
            // add extra credit for every ten comedy attendees
            if ("comedy" == play.type) volumeCredits += Math.floor(perf.audience / 5);

            // print line for this order
            result += String.format("  %s: %s (%s seats)\n", play.name, frmt.format(thisAmount / 100), perf.audience);
            totalAmount += thisAmount;
        }
        result += String.format("Amount owed is %s\n", frmt.format(totalAmount / 100));
        result += String.format("You earned %s credits\n", volumeCredits);
        return result;
    }

    private int calculateAmount(Play play, int perfAudience) {
        int thisAmount = 0;
        switch (play.type) {
            case "tragedy":
                thisAmount = 40000;
                if (perfAudience > 30) {
                    thisAmount += 1000 * (perfAudience - 30);
                }
                break;
            case "comedy":
                thisAmount = 30000;
                if (perfAudience > 20) {
                    thisAmount += 10000 + 500 * (perfAudience - 20);
                }
                thisAmount += 300 * perfAudience;
                break;
            default:
                throw new Error("unknown type: ${play.type}");
        }

        return thisAmount;
    }


}
