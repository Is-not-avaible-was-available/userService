package Decorator;

public class ChocoSyrup implements IceCream{

    private IceCream iceCream;
    public ChocoSyrup(IceCream iceCream){
        this.iceCream = iceCream;
    }

    @Override
    public String getDescription() {
        return iceCream.getDescription() + " ChocoSyrup";
    }

    @Override
    public int getPrice() {
        return iceCream.getPrice() + 10;
    }
}
