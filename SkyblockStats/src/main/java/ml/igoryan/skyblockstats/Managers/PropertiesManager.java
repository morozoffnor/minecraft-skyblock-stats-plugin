package ml.igoryan.skyblockstats.Managers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {

    Properties props = new Properties();
    String file = "plugins/skyblock-stats/plugin.properties";
    InputStream is = null;

    private void readfile() {
        try {
            is = new FileInputStream(file);
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        }
        try {
            // load default
            props.setProperty("base_api_url", "");
            props.setProperty("token", "");
            // load user props
            props.load(is);
            System.out.print("Your settings in plugins/skyblock-stats/plugin.properties - " + props.entrySet());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public Properties getConfig() {
        readfile();
        return props;
    }
}
