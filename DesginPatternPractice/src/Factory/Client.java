package Factory;

import Factory.Component.Button.Button;
import Factory.Component.DropDown.DropDown;
import Factory.Component.Menu.Menu;

public class Client {
    public static void main(String[] args) {
        Flutter flutter = new Flutter(SupportedPlatforms.ANDROID);
        UiFactory uiFactory = flutter.getFactoryForPlatform();
        Button button = uiFactory.createButton();
        Menu menu = uiFactory.createMenu();
        DropDown dropDown = uiFactory.createDropDown();
    }
}
