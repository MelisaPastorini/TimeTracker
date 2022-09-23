package utils;

import lombok.SneakyThrows;
import java.io.FileReader;
import java.util.Properties;

public class PropertyReader {

    private PropertyReader() {
    }

    /**
     * Obtenemos valores de 'environment.properties' dado una key.
     *
     * @param key Clave para obtener valor.
     * @return Valor dado una key.
     */
    @SneakyThrows
    public static String getEnvironmentProperty(String key) {
        Properties properties = new Properties();
        try (FileReader fileReader = new FileReader("src/main/resources/environment.properties")) {
            properties.load(fileReader);
        }
        return properties.getProperty(key);
    }

    @SneakyThrows
    public static String getValuesProperty(String key) {
        Properties properties = new Properties();
        try (FileReader fileReader = new FileReader("src/main/resources/values.properties")) {
            properties.load(fileReader);
        }
        return properties.getProperty(key);
    }
}

