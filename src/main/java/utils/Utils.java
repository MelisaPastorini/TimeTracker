package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.ObjectUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.util.Objects;

public class Utils {
    public static WebDriver configurarDriver() {

        WebDriver webDriver = null;

        //Configuracion de web driver
        String navegador = PropertyReader.getValuesProperty("navegador");
        if (Objects.equals(navegador, "chrome")) {
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("start-maximized");
            chromeOptions.addArguments("--ignore-certificate-errors");
            webDriver = WebDriverManager.chromedriver().capabilities(chromeOptions).create();
        }
        if (Objects.equals(navegador, "edge")) {
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("start-maximized");
            edgeOptions.addArguments("--ignore-certificate-errors");
            webDriver = WebDriverManager.edgedriver().capabilities(edgeOptions).create();
        }
        if (Objects.equals(navegador, "firefox")){
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.addArguments("start-maximized");
            firefoxOptions.addArguments("--ignore-certificate-errors");
            webDriver = WebDriverManager.firefoxdriver().capabilities(firefoxOptions).create();
        }

        return webDriver;
    }

}

