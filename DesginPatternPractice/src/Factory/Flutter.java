package Factory;

public class Flutter {
    private SupportedPlatforms supportedPlatforms;
    public Flutter(SupportedPlatforms supportedPlatforms){
        this.supportedPlatforms = supportedPlatforms;
    }

    public void displayTheme(){
        System.out.println("Displaying theme");
    }

    public void changeRefreshRate(){
        System.out.println("Changing Refresh Rate");
    }


    public UiFactory getFactoryForPlatform(){
        return UiFactoryFactory.getUiFactoryForSupported(supportedPlatforms);
    }
}
