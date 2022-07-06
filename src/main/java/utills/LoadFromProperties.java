package utills;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class LoadFromProperties {

    public static final String path = "src/main/resources/api_urls.properties";
    public static final String pathData = "src/main/resources/config.properties";

    public static String getProperties(String property) {

        Properties properties = new Properties();

        try (FileInputStream fileProperties = new FileInputStream(path)) {
            properties = new Properties();
            properties.load(fileProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties.getProperty(property);
    }

    public static String getConfigProperties(String property) {

        Properties properties = new Properties();

        try (FileInputStream fileProperties = new FileInputStream(pathData)) {
            properties = new Properties();
            properties.load(fileProperties);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties.getProperty(property);
    }
}