package Observer;

import java.util.ArrayList;
import java.util.List;

public class Flipkart {

    private static Flipkart flipkart;
    private List<OrderPlacedSubscriber> orderPlacedSubscribers = new ArrayList<>();
    private List<OrderCancelledSubscriber> orderCancelledSubscribers = new ArrayList<>();

    private Flipkart(){}

    public static Flipkart createInstance(){
        if(flipkart==null){
            flipkart = new Flipkart();
        }
        return flipkart;
    }

    public void registerSubscriber(OrderPlacedSubscriber orderPlacedSubscriber){
        orderPlacedSubscribers.add(orderPlacedSubscriber);
    }

    public void onOrderPlaced(){
        for (OrderPlacedSubscriber orderPlacedSubscriber: orderPlacedSubscribers){
            orderPlacedSubscriber.onOrderPlaced();
        }
    }


}
