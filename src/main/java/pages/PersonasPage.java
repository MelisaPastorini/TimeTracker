package pages;

import org.openqa.selenium.WebDriver;
import utils.PropertyReader;

public class PersonasPage extends BasePage{

    protected static String sitioPersonas;

    public PersonasPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void IrAMenuPersonas() {

        get(PropertyReader.getEnvironmentProperty("sitioPersonas"));
    }
}

