package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

public class EliminarTiempoPage extends BasePage {

    private final By btnEliminarBy = By.cssSelector("tr:nth-child(2) > td:nth-child(7) .table_icon");
    private final By btnDeleteBy = By.id("delete_button");


    public EliminarTiempoPage(WebDriver webDriver) {
        super(webDriver);
    }

    public void eliminarHora() {

//        sleep(2000);

        //Presionar el ícono de eliminar hora
        click(btnEliminarBy);

//        sleep(2000);
    }

    public void eliminarDefinitivoHora() {

//        sleep(2000);

        //Presionar el botón Eliminar
        click(btnDeleteBy);

//        sleep(2000);
    }
}

