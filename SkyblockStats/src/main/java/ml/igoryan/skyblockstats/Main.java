package ml.igoryan.skyblockstats;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import ml.igoryan.skyblockstats.Handlers.DatabaseHandler;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class Main extends JavaPlugin implements Listener {

    private File configFile = new File(getDataFolder(), "config.yml");
    public FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

    private File messagesFile = new File(getDataFolder(), "messages-en.yml");

    Connection db = null;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(this, this);
        // create config and messages file if there is none
        if(!configFile.exists()) {
            saveResource("config.yml", false);
        }
        if(!messagesFile.exists()) {
            saveResource("messages-en.yml", false);
        }
        // then try to connect or create a database
        try {
            if(!configFile.exists()) {
                saveResource("config.yml", false);
            }
           db = DriverManager.getConnection("jdbc:sqlite:plugins/SkyblockStats/database.db");
           getLogger().info("Opened local database successfully!");
           DatabaseHandler.createTable(db, config.getString("Database.table"));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.getLogger().info(config.getString("Database.db"));
        try {
            DatabaseHandler.getPlayer(db, "Igor");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) throws SQLException {

        // TODO: insert a player into a database only if they aren't there already

        String playerName = event.getPlayer().getName();
        DatabaseHandler handler = new DatabaseHandler();
        handler.addPlayer(db, playerName);
    }

}
