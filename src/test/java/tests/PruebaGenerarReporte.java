package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.LoginPage;
import pages.ReportesPage;
import utils.PropertyReader;
import utils.Utils;

import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class PruebaGenerarReporte {

    //WebDriver para configurar y manipular pagina
    protected static WebDriver driver;

    //Valores para utilizar en tests
    protected static String usuario, password;

    //Valores para utilizar en los asserts
    protected static String tituloPaginaReportes, mensajeError, sitioReporte;

    //Page Objects
    protected LoginPage loginPage;
    protected ReportesPage reportesPage;

    @BeforeAll
    public void setUp() {

        //Valores para utilizar en tests
        sitioReporte = PropertyReader.getValuesProperty("sitioReporte");

        //Valores para utilizar en los asserts
        tituloPaginaReportes = PropertyReader.getValuesProperty("tituloPaginaReportes");
        mensajeError = PropertyReader.getValuesProperty("mensajeError");

        //Configurar el WebDriver
        driver = Utils.configurarDriver();
        loginPage = new LoginPage(driver);
        loginPage.signInWith(usuario, password);
    }

    @Test
    void GenerarReporte() throws InterruptedException {

        //Entrar en el menú Reportes
        reportesPage = new ReportesPage(driver);
        reportesPage.IrAMenuReportes();

        sleep(2000);

        //Verificar título de la página Time Tracker - Reportes
        assertEquals(tituloPaginaReportes, driver.getTitle(), mensajeError);

        //Verificar que el título en la página es "Reportes"
        assertEquals("Reportes", driver.findElement(By.cssSelector(".page-title")).getText());

        reportesPage.OpcionesReporte();

        sleep(2000);

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
