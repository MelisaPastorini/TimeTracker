package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.PropertyReader;

public class ProyectosPage extends BasePage{

    protected static String sitioProyectos;

    public ProyectosPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void irAMenuProyectos() {

        get(PropertyReader.getEnvironmentProperty("sitioProyectos"));
    }
}

