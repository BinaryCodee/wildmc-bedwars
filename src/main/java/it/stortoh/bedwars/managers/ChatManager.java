package it.stortoh.bedwars.managers;

import org.bukkit.ChatColor;

public class ChatManager {


    public static String format(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public String permission = format("&cNessun permesso!");
    public static String prefix = format("&2&lWILD&f&lMC &e");

}
