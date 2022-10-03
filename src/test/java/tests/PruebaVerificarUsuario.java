package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.LoginPage;
import pages.PersonasPage;
import utils.PropertyReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PruebaVerificarUsuario extends BaseTest{

    //Valores para utilizar en tests
    protected String usuario, proyecto;

    //Valores para utilizar en los asserts
    protected String tituloPaginaPersonas, mensajeError, nombreUsuarioRol;

    //Page Objects
    protected LoginPage loginPage;
    protected PersonasPage personasPage;

    @BeforeEach
    public void configuracionAntesDelTestVerificarUsuario() {

        //Valores para utilizar en tests
        usuario = PropertyReader.getValuesProperty("user");
        proyecto = PropertyReader.getValuesProperty("proyecto");

        //Valores para utilizar en los asserts
        tituloPaginaPersonas = PropertyReader.getValuesProperty("tituloPaginaPersonas");
        mensajeError = PropertyReader.getValuesProperty("mensajeError");
        nombreUsuarioRol = PropertyReader.getValuesProperty("nombreUsuarioRol");

        //Configurar el WebDriver
        loginPage = new LoginPage(driver);
        loginPage.signInWith();
    }

    @Test
    void verificarUsuario() {

        //Entrar en el menú Personas
        personasPage = new PersonasPage(driver);
        personasPage.irAMenuPersonas();

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
