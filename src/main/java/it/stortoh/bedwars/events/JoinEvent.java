package it.stortoh.bedwars.events;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import it.stortoh.bedwars.Bedwars;
import it.stortoh.bedwars.enums.BedwarsState;
import it.stortoh.bedwars.managers.BWScoreboardManager;

import java.io.File;

public class JoinEvent implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        BedwarsState state = Bedwars.getInstance().getState();

        if (state == BedwarsState.LOBBY) {
            BWScoreboardManager.getInstance().addLobbyScoreboard(player);
            File configFile = new File(Bedwars.getInstance().getDataFolder(), "data.yml");
            if (configFile.exists()) {
                FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);
                if (config.contains("lobby.world") && config.contains("lobby.x") && config.contains("lobby.y") &&
                        config.contains("lobby.z") && config.contains("lobby.yaw") && config.contains("lobby.pitch")) {
                    String worldName = config.getString("lobby.world");
                    double x = config.getDouble("lobby.x");
                    double y = config.getDouble("lobby.y");
                    double z = config.getDouble("lobby.z");
                    float yaw = (float) config.getDouble("lobby.yaw");
                    float pitch = (float) config.getDouble("lobby.pitch");

                    Location lobbyLocation = new Location(Bukkit.getWorld(worldName), x, y, z, yaw, pitch);
                    player.teleport(lobbyLocation);
                } else {
                    player.sendMessage("§cErrore, nei file di sistema non esiste lo spawn. Usa: /wildbw help per maggiori informazioni!");
                }
            }
        } else {
            BWScoreboardManager.getInstance().removeLobbyScoreboard(player);
        }

        if (player.hasPermission("wildbw.donator")) {
            player.setAllowFlight(true);
            player.setFlying(true);
        } else {
            player.setFlying(false);
            player.setAllowFlight(false);
        }
    }

}
