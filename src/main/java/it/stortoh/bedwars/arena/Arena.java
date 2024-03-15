package it.stortoh.bedwars.arena;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;

public class Arena {
    
    private String name;
    private Location spawnLocation;
    private boolean enabled;
    
    public Arena(String name, Location spawnLocation) {
        this.name = name;
        this.spawnLocation = spawnLocation;
        this.enabled = false;
    }

    public String getName() {
        return name;
    }

    public Location getSpawnLocation() {
        return spawnLocation;
    }

    public void setSpawnLocation(Location spawnLocation) {
        this.spawnLocation = spawnLocation;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void saveToConfig(FileConfiguration config) {
        String basePath = "arenas." + name;
        config.set(basePath + ".world", spawnLocation.getWorld().getName());
        config.set(basePath + ".x", spawnLocation.getX());
        config.set(basePath + ".y", spawnLocation.getY());
        config.set(basePath + ".z", spawnLocation.getZ());
        config.set(basePath + ".yaw", spawnLocation.getYaw());
        config.set(basePath + ".pitch", spawnLocation.getPitch());
        config.set(basePath + ".enabled", enabled);
    }

    public static Arena loadFromConfig(String name, FileConfiguration config) {
        String basePath = "arenas." + name;
        World world = Bukkit.getWorld(config.getString(basePath + ".world"));
        double x = config.getDouble(basePath + ".x");
        double y = config.getDouble(basePath + ".y");
        double z = config.getDouble(basePath + ".z");
        float yaw = (float) config.getDouble(basePath + ".yaw");
        float pitch = (float) config.getDouble(basePath + ".pitch");
        Location spawnLocation = new Location(world, x, y, z, yaw, pitch);
        Arena arena = new Arena(name, spawnLocation);
        arena.setEnabled(config.getBoolean(basePath + ".enabled"));
        return arena;
    }
}
