package Observer;

public class AnalyticsService implements OrderPlacedSubscriber{

    private Flipkart flipkart;
    public AnalyticsService(){
        this.flipkart = Flipkart.createInstance();
        flipkart.registerSubscriber(this);
    }
    @Override
    public void onOrderPlaced() {

        doingSomeAnalytics();
    }

    public void doingSomeAnalytics(){
        System.out.println("Blah Blah Blah!");
    }
}
