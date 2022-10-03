package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import pages.EliminarTiempoPage;
import pages.LoginPage;
import pages.TiempoPage;
import utils.PropertyReader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PruebaEliminarHora extends BaseTest{

    //Valores para utilizar en los asserts
    protected String proyecto, inicio, fin, nota;
    protected String tituloPaginaEliminar, mensajeError, nombreUsuarioRol;
    protected String tituloPaginaTiempo;

    //Page Objects
    protected LoginPage loginPage;
    protected TiempoPage tiempoPage;
    protected EliminarTiempoPage eliminarTiempoPage;

    @BeforeEach
    public void configuracionAntesDelTestEliminarHora() {

        //Valores para utilizar en los asserts
        proyecto = PropertyReader.getValuesProperty("proyecto");
        inicio = PropertyReader.getValuesProperty("inicio");
        fin = PropertyReader.getValuesProperty("fin");
        nota = PropertyReader.getValuesProperty("nota");
        tituloPaginaEliminar = PropertyReader.getValuesProperty("tituloPaginaEliminar");
        tituloPaginaTiempo = PropertyReader.getValuesProperty("tituloPaginaTiempo");
        mensajeError = PropertyReader.getValuesProperty("mensajeError");
        nombreUsuarioRol = PropertyReader.getValuesProperty("nombreUsuarioRol");

        //Configurar el WebDriver
        loginPage = new LoginPage(driver);
        loginPage.signInWith();
    }

    @Test
    void eliminarUnicaHoraCargada() {

        //Agregar Hora
        tiempoPage = new TiempoPage(driver);
        tiempoPage.agregarHora();

        //Presionar el ícono de eliminar hora para pasar a la pantalla de eliminar hora
        eliminarTiempoPage = new EliminarTiempoPage(driver);
        eliminarTiempoPage.eliminarHora();

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
        eliminarTiempoPage.eliminarDefinitivoHora();

        //Verificar titulo de la pagina "Time Tracker - Tiempo"
        assertEquals(tituloPaginaTiempo, driver.getTitle());

        //Verificar que la grilla no está presente y que el mensaje de excepcion es:
        //"no such element: Unable to locate element: {\"method\":\"css selector\",\"selector\":\".record-list\"}",
        NoSuchElementException noSuchElementException = Assertions.assertThrows(NoSuchElementException.class, () -> {
            driver.findElement(By.cssSelector(".record-list"));
        });

        assertTrue(noSuchElementException.getMessage().contains("no such element"));
    }
}
