package it.stortoh.bedwars.settings;

import it.stortoh.bedwars.Bedwars;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Configs {

    private Bedwars plugin;
    private File configFile;
    private FileConfiguration config;

    public Configs(Bedwars plugin) {
        this.plugin = plugin;
    }

    public void setup() {
        if (!plugin.getDataFolder().exists()) {
            plugin.getDataFolder().mkdir();
        }

        configFile = new File(plugin.getDataFolder(), "config.yml");

        if (!configFile.exists()) {
            plugin.saveResource("config.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public FileConfiguration getConfig() {
        return config;
    }
}
