package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import utils.PropertyReader;

import static java.lang.Thread.sleep;

public class TiempoPage extends BasePage {

    private final By diaBy = By.linkText("Hoy");
    private final By proyectoBy = By.id("project");
    private final By opcionBy = By.xpath("//option[. = 'TATF-202208']");
    private final By inicioBy = By.id("start");
    private final By finBy = By.id("finish");
    private final By notaBy = By.id("note");
    private final By btnSubmitBy = By.id("btn_submit");

    public TiempoPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void agregarHora() {

        get(PropertyReader.getEnvironmentProperty("sitioHora"));

        //Seleccionar fecha de hoy en el calendario
        click(diaBy);

        //Seleccionar el proyecto TATF-202208
        click(proyectoBy);
//        sleep(2000);
        WebElement dropdown = findElement(proyectoBy);
//        sleep(2000);
        dropdown.findElement(opcionBy).click();

        //Agregar hora de inicio
        click(inicioBy);
        sendKeys(inicioBy, PropertyReader.getValuesProperty("inicio"));

        //Agregar hora de fin
        click(finBy);
        sendKeys(finBy, PropertyReader.getValuesProperty("fin"));

        //Agregar nota o comentario
        click(notaBy);
        sendKeys(notaBy, PropertyReader.getValuesProperty("nota"));

        //Presionar el botón Enviar
        click(btnSubmitBy);
    }
}

