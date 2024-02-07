package Strategy;

public class PathCalculatorFactory {

    public static PathCalculator getPathCalculatorForMode(Mode mode){
        if(mode == Mode.CAR){
            return new CarPathCalculator();
        }else if(mode == Mode.WALK){
            return new WalkPathCalculator();
        }

        return null;
    }
}
