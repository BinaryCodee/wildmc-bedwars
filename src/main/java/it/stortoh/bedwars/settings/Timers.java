package it.stortoh.bedwars.settings;

import it.stortoh.bedwars.Bedwars;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import it.stortoh.bedwars.enums.BedwarsState;
import it.stortoh.bedwars.managers.ChatManager;

public class Timers {

    public void Timers(Player player) {
        if (Bedwars.getInstance().getState() == BedwarsState.WAITING) {
            startGame();
        }
    }

    public void startGame() {
        new BukkitRunnable() {
            int time = 30;
            Player player;

            @Override
            public void run() {
                if (time > 0) {
                    if (time == 30) {
                        Bedwars.getInstance().setGameState(BedwarsState.WAITING);
                        Bukkit.broadcastMessage(new ChatManager().prefix + "La partita iniziera' tra %time% secondi.".replaceAll("%time%", String.valueOf(time == 30)));
                        player.sendTitle("§c%time% secondi.".replaceAll("%time%", String.valueOf(time == 30)), "");
                    }
                    if (time == 20) {
                        Bukkit.broadcastMessage(new ChatManager().prefix + "La partita iniziera' tra %time% secondi.".replaceAll("%time%", String.valueOf(time == 20)));
                        player.sendTitle("§c%time% secondi.".replaceAll("%time%", String.valueOf(time == 20)), "");
                    }
                    if (time == 10) {
                        Bukkit.broadcastMessage(new ChatManager().prefix + "La partita iniziera' tra %time% secondi.".replaceAll("%time%", String.valueOf(time == 10)));
                        player.sendTitle("§c%time% secondi.".replaceAll("%time%", String.valueOf(time == 10)), "");
                    }
                    if (time == 5) {
                        Bukkit.broadcastMessage(new ChatManager().prefix + "La partita iniziera' tra %time% secondi.".replaceAll("%time%", String.valueOf(time == 5)));
                        player.sendTitle("§c%time% secondi.".replaceAll("%time%", String.valueOf(time == 5)), "");
                    }
                    if (time == 4) {
                        Bukkit.broadcastMessage(new ChatManager().prefix + "La partita iniziera' tra %time% secondi.".replaceAll("%time%", String.valueOf(time == 4)));
                        player.sendTitle("§c%time% secondi.".replaceAll("%time%", String.valueOf(time == 4)), "");
                    }
                    if (time == 3) {
                        Bukkit.broadcastMessage(new ChatManager().prefix + "La partita iniziera' tra %time% secondi.".replaceAll("%time%", String.valueOf(time == 3)));
                        player.sendTitle("§c%time% secondi.".replaceAll("%time%", String.valueOf(time == 3)), "");
                    }
                    if (time == 2) {
                        Bukkit.broadcastMessage(new ChatManager().prefix + "La partita iniziera' tra %time% secondi.".replaceAll("%time%", String.valueOf(time == 2)));
                        player.sendTitle("§c%time% secondi.".replaceAll("%time%", String.valueOf(time == 2)), "");
                    }
                    if (time == 1) {
                        Bukkit.broadcastMessage(new ChatManager().prefix + "La partita iniziera' tra %time% secondi.".replaceAll("%time%", String.valueOf(time == 1)));
                        player.sendTitle("§c%time% secondi.".replaceAll("%time%", String.valueOf(time == 1)), "");
                    }
                    time--;
                } else {
                    Bedwars.getInstance().setGameState(BedwarsState.START);
                    Bukkit.broadcastMessage(new ChatManager().prefix + "La partita e' iniziata!");
                    player.sendTitle("La partita e' iniziata!", "Buona fortuna!");
                    cancel();
                }
            }
        }.runTaskTimer(Bedwars.getInstance(),20L, 20L);
    }
}
