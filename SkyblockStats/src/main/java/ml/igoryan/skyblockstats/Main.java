package ml.igoryan.skyblockstats;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import ml.igoryan.skyblockstats.Handlers.DatabaseHandler;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
        // then try to connect or create a database
        try {
           Connection db = DriverManager.getConnection("jdbc:sqlite:plugins/SkyblockStats/database.db");
           System.out.println("Opened local database successfully!");
           DatabaseHandler.createTable(db);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
