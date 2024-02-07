package Factory;

import Factory.Component.Button.Button;
import Factory.Component.DropDown.DropDown;
import Factory.Component.Menu.Menu;

public interface UiFactory {

    public Button createButton();
    public DropDown createDropDown();
    public Menu createMenu();
}
