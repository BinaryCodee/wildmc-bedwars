package it.stortoh.bedwars.events;

import it.stortoh.bedwars.managers.BWScoreboardManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitEvent implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();

        BWScoreboardManager.getInstance().removeLobbyScoreboard(player);
        player.setAllowFlight(false);
        player.setFlying(false);
    }

}
