package Factory;

import Factory.Component.Button.AndriodButton;
import Factory.Component.Button.Button;
import Factory.Component.DropDown.AndroidDropDown;
import Factory.Component.DropDown.DropDown;
import Factory.Component.Menu.AndroidMenu;
import Factory.Component.Menu.Menu;

public class AndroidFactory implements UiFactory{
    @Override
    public Menu createMenu() {
        return new AndroidMenu();
    }

    @Override
    public DropDown createDropDown() {
        return new AndroidDropDown();
    }

    @Override
    public Button createButton() {
        return new AndriodButton();
    }
}
