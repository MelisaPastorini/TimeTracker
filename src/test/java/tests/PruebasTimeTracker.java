package tests;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.*;
import utils.PropertyReader;
import utils.Utils;

import java.util.List;

import static java.lang.Thread.sleep;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)


public class PruebasTimeTracker {

    //WebDriver para configurar y manipular pagina
    protected static WebDriver driver;

    //Valores para utilizar en tests
    protected static String usuario, password, proyecto, inicio, fin, nota;

    //Valores para utilizar en asserts
    protected static String tituloPaginaLogin, tituloLogin, tituloPaginaTiempo;
    protected static String tituloPaginaEliminar, mensajeError, nombreUsuarioRol;
    protected static String tituloPaginaReportes, sitioReporte, tituloPaginaProyectos, tituloPaginaPersonas;

    //Page Objects
    protected LoginPage loginPage;
    protected TiempoPage tiempoPage;
    protected EliminarTiempoPage eliminarTiempoPage;
    protected ReportesPage reportesPage;
    protected ProyectosPage proyectosPage;
    protected PersonasPage personasPage;

    @BeforeAll
    public void setUp() {

        //Valores para utilizar en tests
        usuario = PropertyReader.getValuesProperty("user");
        password = PropertyReader.getValuesProperty("password");
        proyecto = PropertyReader.getValuesProperty("proyecto");
        inicio = PropertyReader.getValuesProperty("inicio");
        fin = PropertyReader.getValuesProperty("fin");
        nota = PropertyReader.getValuesProperty("nota");

        //Valores para asserts
        tituloPaginaLogin = PropertyReader.getValuesProperty("tituloPaginaLogin");
        tituloLogin = PropertyReader.getValuesProperty("tituloLogin");
        tituloPaginaTiempo = PropertyReader.getValuesProperty("tituloPaginaTiempo");
        tituloPaginaEliminar = PropertyReader.getValuesProperty("tituloPaginaEliminar");
        tituloPaginaReportes = PropertyReader.getValuesProperty("tituloPaginaReportes");
        tituloPaginaProyectos = PropertyReader.getValuesProperty("tituloPaginaProyectos");
        tituloPaginaPersonas = PropertyReader.getValuesProperty("tituloPaginaPersonas");
        nombreUsuarioRol = PropertyReader.getValuesProperty("nombreUsuarioRol");
        mensajeError = PropertyReader.getValuesProperty("mensajeError");
        sitioReporte = PropertyReader.getValuesProperty("sitioReporte");

        driver = Utils.configurarDriver();
        loginPage = new LoginPage(driver);
        loginPage.signInWith(usuario, password);
    }

    @AfterAll
    public static void tearDown() {

        //Cerrar la pagina
        driver.quit();
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