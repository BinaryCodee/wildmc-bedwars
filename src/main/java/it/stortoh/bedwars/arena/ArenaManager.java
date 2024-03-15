package it.stortoh.bedwars.arena;

import it.stortoh.bedwars.Bedwars;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class ArenaManager {

    private Bedwars plugin;
    private Map<String, Arena> arenas;
    private File arenasFile;
    private FileConfiguration arenasConfig;

    public ArenaManager(Bedwars plugin) {
        arenasFile = new File(plugin.getDataFolder(), "arenas.yml");
        if (!arenasFile.exists()) {
            try {
                arenasFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        arenasConfig = YamlConfiguration.loadConfiguration(arenasFile);
    }

    public void loadArenas() {
        if (arenasConfig.contains("arenas")) {
            for (String arenaName : arenasConfig.getConfigurationSection("arenas").getKeys(false)) {
                Arena arena = Arena.loadFromConfig(arenaName, arenasConfig);
                arenas.put(arenaName, arena);
            }
        }
    }

    public void saveArenas() {
        for (Arena arena : arenas.values()) {
            arena.saveToConfig(arenasConfig);
        }
        try {
            arenasConfig.save(arenasFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createArena(String name, Location spawn) {
        arenas.put(name, new Arena(name, spawn));
        saveArenas();
    }

    public boolean deleteArena(String name) {
        arenas.remove(name);
        saveArenas();
        return false;
    }

    public void modifyArena(String name, Location spawn) {
        Arena arena = arenas.get(name);
        if (arena != null) {
            arena.saveToConfig(arenasConfig);
            saveArenas();
        }
    }

    public void saveArena(String name) {
        Arena arena = arenas.get(name);
        if (arena != null) {
            arena.saveToConfig(arenasConfig);
            saveArenas();
        }
    }

    public void enableArena(String name) {
        Arena arena = arenas.get(name);
        if (arena != null) {
            arena.setEnabled(true);
            saveArenas();
        }
    }

    public void disableArena(String name) {
        Arena arena = arenas.get(name);
        if (arena != null) {
            arena.setEnabled(false);
            saveArenas();
        }
    }

    public Map<String, Arena> getArenas() {
        return arenas;
    }

}
