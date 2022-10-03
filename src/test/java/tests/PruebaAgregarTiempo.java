package tests;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.LoginPage;
import pages.TiempoPage;
import utils.PropertyReader;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PruebaAgregarTiempo extends BaseTest{

    //Valores para utilizar en tests
    protected String nombreUsuarioRol, proyectos, proyecto, inicio, fin, nota;

    //Page Objects
    protected LoginPage loginPage;
    protected TiempoPage tiempoPage;

    @BeforeEach
    public void configuracionAntesDelTestAgregarTiempo() {

        //Valores para utilizar en asserts
        nombreUsuarioRol = PropertyReader.getValuesProperty("nombreUsuarioRol");
        proyectos = PropertyReader.getValuesProperty("proyectos");
        proyecto = PropertyReader.getValuesProperty("proyecto");
        inicio = PropertyReader.getValuesProperty("inicio");
        fin = PropertyReader.getValuesProperty("fin");
        nota = PropertyReader.getValuesProperty("nota");


        //Configurar el WebDriver
        loginPage = new LoginPage(driver);
        loginPage.signInWith();
    }

    @Test
    void agregarTiempo() {

        tiempoPage = new TiempoPage(driver);
        tiempoPage.agregarHora();

        //Verificar que el usuario es el adecuado
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


    }
}
