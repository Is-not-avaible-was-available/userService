package Decorator;

public class Client {
    public static void main(String[] args) {
        IceCream iceCream = new ChocoSyrup(
                                 new ChocoScoop(
                                         new VanillaScoop(
                                                 new ChocoSyrup(
                                                         new ChocoCone(
                                                                 new OrangeCone()
                                                         )
                                                 )
                                         )
                                 )
        );

        System.out.println(iceCream.getDescription());
        System.out.println(iceCream.getPrice());
    }
}
