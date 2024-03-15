package it.stortoh.bedwars;

import it.stortoh.bedwars.arena.ArenaManager;
import it.stortoh.bedwars.commands.WildBedwarsCmds;
import it.stortoh.bedwars.events.JoinEvent;
import it.stortoh.bedwars.events.QuitEvent;
import it.stortoh.bedwars.settings.Configs;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import it.stortoh.bedwars.enums.BedwarsState;

public final class Bedwars extends JavaPlugin {

    static Bedwars instance;
    BedwarsState state;
    public BedwarsState getState() {
        return state;
    }
    public static Bedwars getInstance() {
        return instance;
    }
    public void setGameState(BedwarsState state) {
        this.state = state;
    }
    private ArenaManager arenaManager;
    private Configs configs;

    @Override
    public void onEnable() {
        instance = this;
        setGameState(BedwarsState.LOBBY);
        arenaManager = new ArenaManager(this);
        configs = new Configs(this);

        getCommand("wildbw").setExecutor(new WildBedwarsCmds());

        Bukkit.getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new QuitEvent(), this);

        configs.setup();
    }

    @Override
    public void onDisable() {
        instance = null;
    }

    public ArenaManager getArenaManager() {
        return arenaManager;
    }

    public Configs getConfigs() {
        return configs;
    }
}
