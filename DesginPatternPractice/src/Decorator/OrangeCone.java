package Decorator;

public class OrangeCone implements IceCream{

    private IceCream iceCream;

    public OrangeCone(IceCream iceCream){
        this.iceCream =  iceCream;
    }

    public OrangeCone(){}
    @Override
    public int getPrice() {
        if(iceCream==null){
            return 20;
        }
        return iceCream.getPrice()+ 20;
    }

    @Override
    public String getDescription() {
        if(iceCream == null){
            return " OrangeCone";
        }
        return iceCream.getDescription() + " OrangeCone";
    }
}
