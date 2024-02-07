package Adapter;

public class AnotherAdapter implements Target{

    private AnotherAdaptee adaptee;
    public AnotherAdapter(AnotherAdaptee adaptee){
        this.adaptee = adaptee;
    }
    @Override
    public void doSomething() {
        adaptee.adapteeDoingSomething();
    }

    @Override
    public void someMethod() {
        adaptee.someAdapteeMethod();

    }
}
