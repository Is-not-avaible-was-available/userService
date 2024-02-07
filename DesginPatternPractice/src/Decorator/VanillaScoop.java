package Decorator;

public class VanillaScoop implements IceCream{
    private IceCream iceCream;
    public VanillaScoop(IceCream iceCream){
        this.iceCream = iceCream;
    }

    @Override
    public String getDescription() {
        return iceCream.getDescription() + " VanillaScoop";
    }

    @Override
    public int getPrice() {
        return iceCream.getPrice() + 20;
    }
}
