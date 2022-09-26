package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.PersonasPage;
import pages.ProyectosPage;
import utils.PropertyReader;
import utils.Utils;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class PruebaVerificarUsuario {
    //WebDriver para configurar y manipular pagina
    protected static WebDriver driver;

    //Valores para utilizar en tests
    protected static String usuario, password, proyecto;

    //Valores para utilizar en los asserts
    protected static String tituloPaginaPersonas, mensajeError, nombreUsuarioRol;

    //Page Objects
    protected LoginPage loginPage;
    protected PersonasPage personasPage;

    @BeforeAll
    public void setUp() {

        //Valores para utilizar en tests
        usuario = PropertyReader.getValuesProperty("user");
        password = PropertyReader.getValuesProperty("password");
        proyecto = PropertyReader.getValuesProperty("proyecto");

        //Valores para utilizar en los asserts
        tituloPaginaPersonas = PropertyReader.getValuesProperty("tituloPaginaPersonas");
        mensajeError = PropertyReader.getValuesProperty("mensajeError");
        nombreUsuarioRol = PropertyReader.getValuesProperty("nombreUsuarioRol");

        //Configurar el WebDriver
        driver = Utils.configurarDriver();
        loginPage = new LoginPage(driver);
        loginPage.signInWith(usuario, password);
    }

    @Test
    void VerificarUsuario() throws InterruptedException {

        //Entrar en el menú Personas
        personasPage = new PersonasPage(driver);
        personasPage.IrAMenuPersonas();

        sleep(2000);

        //Verificar título de la página Time Tracker - Personas
        assertEquals(tituloPaginaPersonas, driver.getTitle(), mensajeError);

        //Verificar que el título en la página es "Personas"
        assertEquals("Personas", driver.findElement(By.cssSelector(".page-title")).getText());

        //Verificar que el usuario es el adecuado
        assertEquals(nombreUsuarioRol, driver.findElement(By.cssSelector(".user-details")).getText());

        //Verificar que está el login del usuario
        assertEquals(usuario, driver.findElement(By.cssSelector("tr:nth-child(26) > .text-cell:nth-child(2)")).getText());
    }

}
