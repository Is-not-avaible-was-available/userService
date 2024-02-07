package Decorator;

public class ChocoCone implements IceCream{
    private IceCream iceCream;

    public ChocoCone(IceCream iceCream){
        this.iceCream = iceCream;
    }

    @Override
    public String getDescription() {
        if(iceCream==null){
            return " ChocolateCone";
        }
        return iceCream.getDescription() + " ChocolateCone";
    }

    @Override
    public int getPrice() {
        if(iceCream==null){
            return 25;
        }
        return iceCream.getPrice() + 25;
    }
}
