package Observer;

public class EmailService implements OrderPlacedSubscriber, OrderCancelledSubscriber{
    private Flipkart flipkart;

    public EmailService(){
        this.flipkart = Flipkart.createInstance();
        flipkart.registerSubscriber(this);
    }

    @Override
    public void onOrderPlaced() {

        sendingEmails();
    }

    @Override
    public void onOrderCancelled() {

    }

    public void sendingEmails(){
        System.out.println("Sending email to user.");
    }
}
