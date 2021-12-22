package ml.igoryan.skyblockstats;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;


import java.io.File;

public final class Main extends JavaPlugin {

    private File configFile = new File(getDataFolder(), "config.yml");
    private FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

    private File messagesFile = new File(getDataFolder(), "messages.yml");

    @Override
    public void onEnable() {
        // Plugin startup logic

        // create config and messages file if there is none
        if(!configFile.exists()) {
            saveResource("config.yml", false);
        }
        if(!messagesFile.exists()) {
            saveResource("messages.yml", false);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
