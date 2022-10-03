package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.time.Duration;

public class Utils {
    public static WebDriver configurarDriver() {

        WebDriver webDriver = null;

        //Configuracion de web driver
        String navegador = PropertyReader.getValuesProperty("navegador");
        switch (navegador) {
            case "chrome" -> {
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("start-maximized");
                chromeOptions.addArguments("--ignore-certificate-errors");
                webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
            }
            case "edge" -> {
                EdgeOptions edgeOptions = new EdgeOptions();
                edgeOptions.addArguments("start-maximized");
                edgeOptions.addArguments("--ignore-certificate-errors");
                webDriver = WebDriverManager.edgedriver().capabilities(edgeOptions).create();
            }
            case "firefox" -> {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("start-maximized");
                firefoxOptions.addArguments("--ignore-certificate-errors");
                webDriver = WebDriverManager.firefoxdriver().capabilities(firefoxOptions).create();
            }
            default -> System.out.println("Los navegadores soportados son chrome, edge o firefox");
        }

        assert webDriver != null;
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        return webDriver;
    }
}

