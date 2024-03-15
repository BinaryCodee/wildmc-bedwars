package it.stortoh.bedwars.settings;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;

import java.util.ArrayList;
import java.util.List;

public class Util {

    private static List<Thread> threads = new ArrayList<>();
    public static World defaultMap = Bukkit.createWorld(new WorldCreator("BwLobby-1"));

    public static List<Thread> getThreads() {
        return threads;
    }
}
