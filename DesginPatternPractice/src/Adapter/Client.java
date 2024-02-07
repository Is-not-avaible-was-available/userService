package Adapter;

public class Client {
    private Target target;

    public Client(Target target){
        this.target = target;
    }

    public void doSomethingMore(){
        target.doSomething();
    }

    public void someOtherMethods(){
        target.someMethod();
    }
}
