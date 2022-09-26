package tests;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.EliminarTiempoPage;
import pages.LoginPage;
import pages.TiempoPage;
import utils.PropertyReader;
import utils.Utils;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

public class PruebaEliminarHora {
    //WebDriver para configurar y manipular pagina
    protected static WebDriver driver;

    //Valores para utilizar en tests
    protected static String usuario, password, proyecto, inicio, fin, nota;

    //Valores para utilizar en los asserts
    protected static String tituloPaginaEliminar, mensajeError, nombreUsuarioRol;
    protected static String tituloPaginaTiempo;

    //Page Objects
    protected LoginPage loginPage;
    protected TiempoPage tiempoPage;
    protected EliminarTiempoPage eliminarTiempoPage;

    @BeforeAll
    public void setUp() {

        //Valores para utilizar en tests
        usuario = PropertyReader.getValuesProperty("user");
        password = PropertyReader.getValuesProperty("password");
        proyecto = PropertyReader.getValuesProperty("proyecto");
        inicio = PropertyReader.getValuesProperty("inicio");
        fin = PropertyReader.getValuesProperty("fin");
        nota = PropertyReader.getValuesProperty("nota");

        //Valores para utilizar en los asserts
        tituloPaginaEliminar = PropertyReader.getValuesProperty("tituloPaginaEliminar");
        tituloPaginaTiempo = PropertyReader.getValuesProperty("tituloPaginaTiempo");
        mensajeError = PropertyReader.getValuesProperty("mensajeError");
        nombreUsuarioRol = PropertyReader.getValuesProperty("nombreUsuarioRol");

        //Configurar el WebDriver
        driver = Utils.configurarDriver();
        loginPage = new LoginPage(driver);
        loginPage.signInWith(usuario, password);
    }

    @Test
    void EliminarHora() throws InterruptedException {

        //Agregar Hora
        tiempoPage = new TiempoPage(driver);
        tiempoPage.AgregarHora();

        //Presionar el ícono de eliminar hora para pasar a la pantalla de eliminar hora
        eliminarTiempoPage = new EliminarTiempoPage(driver);
        eliminarTiempoPage.EliminarHora();

        //Esperar para que se cargue la nueva pagina
        sleep(2000);

        //Verificar que el título de la página es: Time Tracker - Eliminando el historial de tiempo |
        assertEquals(tituloPaginaEliminar, driver.getTitle(), mensajeError);

        //Verificar usuario activo
        assertEquals(nombreUsuarioRol, driver.findElement(By.cssSelector(".user-details")).getText());

        //Verificar tarea cargada
        //Verificar que está el texto con el nombre del proyecto
        assertEquals(proyecto, driver.findElement(By.cssSelector(".text-cell:nth-child(1)")).getText());

        //Verificar que está el texto con la hora de inicio
        assertEquals(inicio, driver.findElement(By.cssSelector(".time-cell:nth-child(2)")).getText());

        //Verificar que está el texto con la hora de fin
        assertEquals(fin, driver.findElement(By.cssSelector(".time-cell:nth-child(3)")).getText());

        //Verificar que está el texto con la nota
        assertEquals(nota, driver.findElement(By.cssSelector(".text-cell:nth-child(5)")).getText());


        //Presionar el botón Eliminar para eliminar definitivamente la hora
        eliminarTiempoPage.EliminarDefinitivoHora();

        //Esperar para que se cargue la nueva pagina
        sleep(2000);

        // Verificar titulo de la pagina "Time Tracker - Tiempo"
        assertEquals(tituloPaginaTiempo, driver.getTitle());
    }
}
