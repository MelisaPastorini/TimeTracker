package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.LoginPage;
import pages.ReportesPage;
import utils.PropertyReader;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PruebaGenerarReporte extends BaseTest{

    //Valores para utilizar en los asserts
    protected String tituloPaginaReportes, mensajeError, sitioReporte, nombreUsuarioRol;

    //Page Objects
    protected LoginPage loginPage;
    protected ReportesPage reportesPage;

    @BeforeEach
    public void configuracionAntesDelTestGenerarReporte() {

        //Valores para utilizar en los asserts
        tituloPaginaReportes = PropertyReader.getValuesProperty("tituloPaginaReportes");
        mensajeError = PropertyReader.getValuesProperty("mensajeError");
        sitioReporte = PropertyReader.getValuesProperty("sitioReporte");
        nombreUsuarioRol = PropertyReader.getValuesProperty("nombreUsuarioRol");

        //Configurar el WebDriver
        loginPage = new LoginPage(driver);
        loginPage.signInWith();
    }

    @Test
    void generarReporte() {

        //Entrar en el menú Reportes
        reportesPage = new ReportesPage(driver);
        reportesPage.irAMenuReportes();

        //Verificar título de la página Time Tracker - Reportes
        assertEquals(tituloPaginaReportes, driver.getTitle(), mensajeError);

        //Verificar que el título en la página es "Reportes"
        assertEquals("Reportes", driver.findElement(By.cssSelector(".page-title")).getText());

        //Verificar que el usuario es el adecuado
        assertEquals(nombreUsuarioRol, driver.findElement(By.cssSelector(".user-details")).getText());

        reportesPage.opcionesReporte();

        //Verificar que esté presente la grilla en pantalla
        {
            List<WebElement> elements = driver.findElements(By.name("reportViewForm"));
            assert (elements.size() > 0);
        }

        //Verificar URL del rerpote generado
        var currentUrl = driver.getCurrentUrl();
        assertTrue(currentUrl.contains(sitioReporte));

        //Verificar que está el botón "Enviar por correo"
        {
            List<WebElement> elements = driver.findElements(By.cssSelector("input"));
            assert(elements.size() > 0);
        }
    }
}
