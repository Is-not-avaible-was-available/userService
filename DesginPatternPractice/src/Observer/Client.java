package Observer;

public class Client {
    public static void main(String[] args) {
        Flipkart flipkart = Flipkart.createInstance();
        EmailService emailService = new EmailService();
        AnalyticsService analyticsService = new AnalyticsService();
        LogisticsService logisticsService = new LogisticsService();

        flipkart.onOrderPlaced();
    }
}
