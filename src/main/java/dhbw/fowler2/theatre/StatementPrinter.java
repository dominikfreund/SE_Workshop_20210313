package dhbw.fowler2.theatre;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.Map;

public class StatementPrinter {
    private PerformanceCalculator perfCalc;
    private NumberFormat format = NumberFormat.getCurrencyInstance(Locale.US);

    public String print(Invoice invoice, Map<String, Play> plays) {
        perfCalc = new PerformanceCalculator();

        var result = String.format("Statement for %s\n", invoice.customer);

        for (var perf : invoice.performances) {
            var play = plays.get(perf.playID);

            // add volume credit
            // add extra credit for every ten comedy attendees
            perfCalc.addVolumeCredits(perf.audience, play.type);

            // print line for this order
            result += String.format("  %s: %s (%s seats)\n", play.name, format.format(perfCalc.addAmount(play, perf.audience) / 100), perf.audience);
        }
        result += String.format("Amount owed is %s\n", format.format(perfCalc.getTotalAmount() / 100));
        result += String.format("You earned %s credits\n", perfCalc.getVolumeCredits());
        return result;
    }

    public String printHTML(Invoice invoice, Map<String, Play> plays){
        perfCalc = new PerformanceCalculator();

        String htmlResult = String.format("<h1>Statement for "+invoice.customer+"</h1>\n");
        htmlResult += "<ul>\n";

        for(var perf : invoice.performances){
            var play = plays.get(perf.playID);

            // add volume credit
            // add extra credit for every ten comedy attendees
            perfCalc.addVolumeCredits(perf.audience, play.type);

            // print line for this order
            htmlResult += String.format("  <li>%s: %s (%s seats)</li>\n", play.name, format.format(perfCalc.addAmount(play, perf.audience) / 100), perf.audience);
        }
        htmlResult += "</ul>\n";
        htmlResult += String.format("<p>Amount owed is %s</p>\n", format.format(perfCalc.getTotalAmount() / 100));
        htmlResult += String.format("<p>You earned %s credits</p>\n", perfCalc.getVolumeCredits());

        return htmlResult;
    }

}
