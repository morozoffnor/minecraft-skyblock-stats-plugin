package ml.igoryan.skyblockstats;

import org.bukkit.plugin.java.JavaPlugin;

import ml.igoryan.skyblockstats.Managers.PropertiesManager;

import java.util.Properties;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
    PropertiesManager props = new PropertiesManager();
    Properties config = props.getConfig();
    System.out.println(config.entrySet());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
