package it.stortoh.bedwars.settings;

import it.stortoh.bedwars.Bedwars;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class LocationsConfig {

    private File file;
    private FileConfiguration config;

    private static LocationsConfig data;

    public LocationsConfig(String fileName) {
        file = new File(Bedwars.getInstance().getDataFolder(), fileName);
        try {
            if (!file.exists() && !file.createNewFile()) throw new IOException();
        } catch (IOException e) {
            throw new RuntimeException("[WildMC-BedWars] Impossibile creare il file di configurazione: " + fileName);
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public void save() {
        try {
            config.save(file);
        } catch (IOException e) {
            throw new RuntimeException("[WildMC-BedWars] Impossibile salvare il file di configurazione: " + file.getName());
        }
    }

    public static LocationsConfig getData() {
        if (data == null) data = new LocationsConfig("locations.yml");
        return data;
    }

    public static void setData(LocationsConfig data) {
        LocationsConfig.data = data;
    }
}
