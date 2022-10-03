package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.PropertyReader;

public class LoginPage extends BasePage {

    private final By userBy = By.id("login");
    private final By passwordBy = By.name("password");
    private final By btnLoginBy = By.id("btn_login");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void signInWith() {

        get(PropertyReader.getEnvironmentProperty("sitioWeb"));
        sendKeys(userBy, PropertyReader.getValuesProperty("user"));
        sendKeys(passwordBy, PropertyReader.getValuesProperty("password"));
        click(btnLoginBy);
    }
}

