package common.framework;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class BaseConfiguration {
    private Properties configFile;

    public BaseConfiguration() {
        configFile = new Properties();

        try {
            configFile.load(this.getClass().getClassLoader().getResourceAsStream("base.properties"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public String getProperty(String key) {
        return this.configFile.getProperty(key);
    }

    public void setProperty(String key, String value) {
        this.configFile.setProperty(key, value);

        try {
            configFile.store(new FileOutputStream("base.properties"), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
