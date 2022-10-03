package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.PropertyReader;

public class ReportesPage extends BasePage{

    private final By periodBy = By.id("period");
    private final By opcionMesBy = By.xpath("//option[. = 'este mes']");
    private final By proyectoBy = By.id("project");
    private final By opcionBy = By.xpath("//option[. = 'TATF-202208']");
    private final By agrupamientoBy = By.id("group_by1");
    private final By opcionFechaBy = By.xpath("//option[. = 'fecha']");
    private final By btnGenerar = By.cssSelector(".button-set > #btn_generate");

    public ReportesPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void irAMenuReportes() {

        get(PropertyReader.getEnvironmentProperty("sitioReportes"));
    }

    public void opcionesReporte() {

        //Seleccionar el periodo "este mes"
        click(periodBy);
        {
            WebElement dropdown = findElement(periodBy);
            dropdown.findElement(opcionMesBy).click();
        }

        //Seleccionar el proyecto "TATF-202208"
        click(proyectoBy);
        {
            WebElement dropdown = findElement(proyectoBy);
            dropdown.findElement(opcionBy).click();
        }

        //Seleccionar agrupamiento por fecha
        click(agrupamientoBy);
        {
            WebElement dropdown = findElement(agrupamientoBy);
            dropdown.findElement(opcionFechaBy).click();
        }

        //Presionar el botón "Generar"
        click(btnGenerar);
    }
}

