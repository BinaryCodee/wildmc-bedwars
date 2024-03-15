package it.stortoh.bedwars.managers;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BWScoreboardManager {
    private static BWScoreboardManager instance;
    private ScoreboardManager scoreboardManager;

    private BWScoreboardManager() {
        scoreboardManager = Bukkit.getScoreboardManager();
    }

    public static BWScoreboardManager getInstance() {
        if (instance == null) {
            instance = new BWScoreboardManager();
        }
        return instance;
    }

    public void addLobbyScoreboard(Player player) {
        Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("Lobby", "dummy");
        objective.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&2&lWILD&f&lMC"));
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String date = dateFormat.format(new Date());

        objective.getScore(ChatColor.translateAlternateColorCodes('&', "&7" + date)).setScore(10);
        objective.getScore(" ").setScore(9);
        objective.getScore(ChatColor.translateAlternateColorCodes('&', "&fNome: &a" + player.getName())).setScore(8);
        objective.getScore(ChatColor.translateAlternateColorCodes('&', "&fLivello: &aSoon")).setScore(7);
        objective.getScore("  ").setScore(6);
        objective.getScore(ChatColor.translateAlternateColorCodes('&', "&fClan: &aSoon")).setScore(5);
        objective.getScore(ChatColor.translateAlternateColorCodes('&', "&fOnline: &a%online%").replaceAll("%online%", String.valueOf(Bukkit.getOnlinePlayers().size()))).setScore(4);
        objective.getScore(ChatColor.translateAlternateColorCodes('&', "&fServer: &a" + player.getWorld().getName())).setScore(3);
        objective.getScore("   ").setScore(2);
        objective.getScore(ChatColor.translateAlternateColorCodes('&', "&eplay.wildmc.it")).setScore(1);

        player.setScoreboard(scoreboard);
    }

    public void removeLobbyScoreboard(Player player) {
        player.setScoreboard(scoreboardManager.getNewScoreboard());
    }
}
