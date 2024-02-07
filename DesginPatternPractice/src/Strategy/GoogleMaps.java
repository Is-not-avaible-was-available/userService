package Strategy;

public class GoogleMaps {

    private PathCalculator pathCalculator;

    public GoogleMaps(PathCalculator pathCalculator){
        this.pathCalculator = pathCalculator;
    }
    public void getPath(String source,  String destination, Mode mode){

        pathCalculator = PathCalculatorFactory.getPathCalculatorForMode(mode);

        pathCalculator.calculatePath(source, destination);
    }
}
