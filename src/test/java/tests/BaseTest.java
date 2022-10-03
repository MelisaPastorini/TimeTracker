package tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.WebDriver;
import utils.Utils;

public class BaseTest {
    //WebDriver para configurar y manipular pagina
    protected static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        //Configurar el WebDriver
        driver = Utils.configurarDriver();
    }

    @AfterAll
    public static void tearDown() {
        driver.quit();
    }
 }
