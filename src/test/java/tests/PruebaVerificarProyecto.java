package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.ProyectosPage;
import utils.PropertyReader;
import utils.Utils;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class PruebaVerificarProyecto {
    //WebDriver para configurar y manipular pagina
    protected static WebDriver driver;

    //Valores para utilizar en tests
    protected static String usuario, password, proyecto, inicio, fin, nota;

    //Valores para utilizar en los asserts
    protected static String tituloPaginaProyectos, mensajeError, nombreUsuarioRol;

    //Page Objects
    protected LoginPage loginPage;
    protected ProyectosPage proyectosPage;

    @BeforeAll
    public void setUp() {

        //Valores para utilizar en tests
        usuario = PropertyReader.getValuesProperty("user");
        password = PropertyReader.getValuesProperty("password");
        proyecto = PropertyReader.getValuesProperty("proyecto");

        //Valores para utilizar en los asserts
        tituloPaginaProyectos = PropertyReader.getValuesProperty("tituloPaginaProyectos");
        mensajeError = PropertyReader.getValuesProperty("mensajeError");
        nombreUsuarioRol = PropertyReader.getValuesProperty("nombreUsuarioRol");

        //Configurar el WebDriver
        driver = Utils.configurarDriver();
        loginPage = new LoginPage(driver);
        loginPage.signInWith(usuario, password);
    }

    @Test
    void VerificarProyecto() throws InterruptedException {

        //Entrar en el menú Proyectos
        proyectosPage = new ProyectosPage(driver);
        proyectosPage.IrAMenuProyectos();

        sleep(2000);

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
