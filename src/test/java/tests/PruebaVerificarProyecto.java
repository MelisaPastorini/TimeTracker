package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.LoginPage;
import pages.ProyectosPage;
import utils.PropertyReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PruebaVerificarProyecto extends BaseTest{

    //Valores para utilizar en tests
    protected String proyecto, inicio, fin, nota;

    //Valores para utilizar en los asserts
    protected String tituloPaginaProyectos, mensajeError, nombreUsuarioRol;

    //Page Objects
    protected LoginPage loginPage;
    protected ProyectosPage proyectosPage;

    @BeforeEach
    public void configuracionAntedDelTestVerificarProyecto() {

        //Valores para utilizar en tests
        proyecto = PropertyReader.getValuesProperty("proyecto");

        //Valores para utilizar en los asserts
        tituloPaginaProyectos = PropertyReader.getValuesProperty("tituloPaginaProyectos");
        mensajeError = PropertyReader.getValuesProperty("mensajeError");
        nombreUsuarioRol = PropertyReader.getValuesProperty("nombreUsuarioRol");

        //Configurar el WebDriver
        loginPage = new LoginPage(driver);
        loginPage.signInWith();
    }

    @Test
    void verificarProyecto() {

        //Entrar en el menú Proyectos
        proyectosPage = new ProyectosPage(driver);
        proyectosPage.irAMenuProyectos();

        //Verificar título de la página Time Tracker - Proyectos
        assertEquals(tituloPaginaProyectos, driver.getTitle(), mensajeError);

        //Verificar que el título en la página es "Proyectos"
        assertEquals("Proyectos", driver.findElement(By.cssSelector(".page-title")).getText());

        //Verificar que el usuario es el adecuado
        assertEquals(nombreUsuarioRol, driver.findElement(By.cssSelector(".user-details")).getText());

        //Verificar que está el texto con el nombre del proyecto
        assertEquals(proyecto, driver.findElement(By.cssSelector(".text-cell:nth-child(1)")).getText());
    }

}
