package Observer;

public class LogisticsService implements OrderPlacedSubscriber{

    private Flipkart flipkart;
    public LogisticsService(){
        this.flipkart = Flipkart.createInstance();
        flipkart.registerSubscriber(this);
    }
    @Override
    public void onOrderPlaced() {

        doingSomething();
    }

    public void doingSomething(){
        System.out.println("Logistics Service updating the inventory");
    }
}
