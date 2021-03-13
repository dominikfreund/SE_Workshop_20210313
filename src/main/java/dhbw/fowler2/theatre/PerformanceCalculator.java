package dhbw.fowler2.theatre;

public class PerformanceCalculator {
    private int totalAmount;
    public PerformanceCalculator(){
        totalAmount = 0;
    }

    public int addAmount(Play play, int perfAudience){
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
        totalAmount += thisAmount;
        return thisAmount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }
}
