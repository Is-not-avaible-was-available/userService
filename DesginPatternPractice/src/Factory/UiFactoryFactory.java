package Factory;

public class UiFactoryFactory {

    public static UiFactory getUiFactoryForSupported(SupportedPlatforms supportedPlatforms){
        if(supportedPlatforms == SupportedPlatforms.ANDROID){
            return new AndroidFactory();
        }else if(supportedPlatforms == SupportedPlatforms.IOS){
            return new IosFactory();
        }
        return null;
    }
}
