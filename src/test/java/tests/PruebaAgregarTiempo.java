package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.TiempoPage;
import utils.PropertyReader;
import utils.Utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class PruebaAgregarTiempo {
    //WebDriver para configurar y manipular pagina
    protected static WebDriver driver;

    //Valores para utilizar en tests
    protected static String usuario, password, proyecto, inicio, fin, nota;

    //Page Objects
    protected LoginPage loginPage;
    protected TiempoPage tiempoPage;

    @BeforeAll
    public void setUp() {

        //Valores para utilizar en tests
        usuario = PropertyReader.getValuesProperty("user");
        password = PropertyReader.getValuesProperty("password");
        proyecto = PropertyReader.getValuesProperty("proyecto");
        inicio = PropertyReader.getValuesProperty("inicio");
        fin = PropertyReader.getValuesProperty("fin");
        nota = PropertyReader.getValuesProperty("nota");

        //Configurar el WebDriver
        driver = Utils.configurarDriver();
        loginPage = new LoginPage(driver);
        loginPage.signInWith(usuario, password);
    }

    @Test
    void AgregarTiempo() throws InterruptedException {

        tiempoPage = new TiempoPage(driver);
        tiempoPage.AgregarHora();

        //Verificar tarea cargada
        //Verificar que está el texto con el nombre del proyecto
        assertEquals(proyecto, driver.findElement(By.cssSelector(".text-cell:nth-child(1)")).getText());

        //Verificar que está el texto con la hora de inicio
        assertEquals(inicio, driver.findElement(By.cssSelector(".time-cell:nth-child(2)")).getText());

        //Verificar que está el texto con la hora de fin
        assertEquals(fin, driver.findElement(By.cssSelector(".time-cell:nth-child(3)")).getText());

        //Verificar que está el texto con la nota
        assertEquals(nota, driver.findElement(By.cssSelector(".text-cell:nth-child(5)")).getText());

    }
}
