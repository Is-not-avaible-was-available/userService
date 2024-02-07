package Factory;

import Factory.Component.Button.Button;
import Factory.Component.Button.IosButton;
import Factory.Component.DropDown.DropDown;
import Factory.Component.DropDown.IosDropDown;
import Factory.Component.Menu.IosMenu;
import Factory.Component.Menu.Menu;

public class IosFactory implements UiFactory{
    @Override
    public DropDown createDropDown() {
        return new IosDropDown();
    }

    @Override
    public Button createButton() {
        return new IosButton();
    }

    @Override
    public Menu createMenu() {
      return  new IosMenu();
    }
}
