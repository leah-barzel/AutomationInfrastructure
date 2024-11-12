package Config;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * ConfigReader reads configuration properties from a specified properties file.
 * It provides methods to retrieve browser type and URL from the properties.
 */
public class ConfigReader {

    private Properties properties = new Properties();
    private final String browserKey = "BROWSER"; // Key for the browser property
    private final String urlKey = "URL"; // Key for the URL property

    /**
     * Constructs a ConfigReader and loads properties from the specified file.
     *
     * @param filePath the path to the properties file
     * @throws RuntimeException if there is an error loading the properties file
     */
    public ConfigReader(String filePath) {
        try (FileInputStream inputStream = new FileInputStream(filePath)) {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load configuration properties.", e);
        }
    }

    /**
     * Retrieves the browser type specified in the properties.
     *
     * @return the browser type as a String
     */
    public String getBrowser() {
        return properties.getProperty(browserKey);
    }

    /**
     * Retrieves the URL specified in the properties.
     *
     * @return the URL as a String
     */
    public String getUrl() {
        return properties.getProperty(urlKey);
    }
}
