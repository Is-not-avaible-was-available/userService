package Decorator;

public class ChocoScoop implements IceCream{
    private IceCream iceCream;
    public ChocoScoop(IceCream iceCream){
        this.iceCream = iceCream;
    }

    @Override
    public int getPrice() {
        return iceCream.getPrice() + 20;
    }

    @Override
    public String getDescription() {
        return iceCream.getDescription() + " ChocoScoop";
    }
}
