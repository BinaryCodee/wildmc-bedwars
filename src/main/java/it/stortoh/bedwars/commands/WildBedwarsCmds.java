package it.stortoh.bedwars.commands;

import it.stortoh.bedwars.Bedwars;
import it.stortoh.bedwars.arena.Arena;
import it.stortoh.bedwars.arena.ArenaManager;
import it.stortoh.bedwars.managers.ChatManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;


public class WildBedwarsCmds implements CommandExecutor {

    private ArenaManager arenaManager;

    public WildBedwarsCmds() {
        this.arenaManager = Bedwars.getInstance().getArenaManager();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Player player = (Player) sender;
        if (args.length == 0) {
            sender.sendMessage("§cUsa /wildbw help per vedere i comandi disponibili.");
            return true;
        } else if (args[0].equalsIgnoreCase("help")) {
            sender.sendMessage("§2§lWILD§f§lMC §a§lBEDWARS");
            sender.sendMessage("§f");
            sender.sendMessage("§8* §e/wildbw setlobby - Imposta il lobby spawn.");
            sender.sendMessage("§8* §e/wildbw arena - Lista comandi arena.");
            sender.sendMessage("");
            return true;
        } else if (args[0].equalsIgnoreCase("setlobby")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("§cDevi essere un giocatore per eseguire questo comando.");
                return true;
            }

            File configFile = new File(Bedwars.getInstance().getDataFolder(), "data.yml");
            FileConfiguration config = YamlConfiguration.loadConfiguration(configFile);

            config.set("lobby.world", player.getWorld().getName());
            config.set("lobby.x", player.getLocation().getX());
            config.set("lobby.y", player.getLocation().getY());
            config.set("lobby.z", player.getLocation().getZ());
            config.set("lobby.yaw", player.getLocation().getYaw());
            config.set("lobby.pitch", player.getLocation().getPitch());

            try {
                config.save(configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }

            player.sendMessage(ChatManager.prefix + "§aHai impostato il punto di spawn.");
            return true;
        } else if (args[0].equalsIgnoreCase("arena")) {
            if (args.length == 1) {
                sender.sendMessage("§cUsa /wildbw arena help per vedere i comandi disponibili.");
                return true;
            } else if (args[1].equalsIgnoreCase("help")) {
                sender.sendMessage("§2§lWILD§f§lMC §a§lBEDWARS");
                sender.sendMessage("§f");
                sender.sendMessage("§e§lARENA");
                sender.sendMessage("§8* §e/wildbw arena create <nome> - Crea un'arena.");
                sender.sendMessage("§8* §e/wildbw arena delete <nome> - Elimina un'arena.");
                sender.sendMessage("§8* §e/wildbw arena setwaiting <arena> - Imposta lo spawn dell'arena.");
                sender.sendMessage("§8* §e/wildbw arena setspectator <arena> - Imposta il lobby dell'arena.");
                sender.sendMessage("§8* §e/wildbw arena setmaxplayers <arena> <numero> - Imposta il numero massimo di giocatori.");
                sender.sendMessage("§8* §e/wildbw arena setminplayers <arena> <numero> - Imposta il numero minimo di giocatori.");
                sender.sendMessage("§8* §e/wildbw arena setmode <arena> <solo/double/trio/squad> - Imposta la modalità di gioco.");
                sender.sendMessage("§8* §e/wildbw arena setgenerator <arena> <emerald/diamond> - Imposta il generatore dell'arena.");
                sender.sendMessage("§f");
                sender.sendMessage("§e§lTEAMS");
                sender.sendMessage("§8* §e/wildbw arena createteam <arena> <team> <blue/red/green/yellow/gray/white/pink/cyan> - Crea un team.");
                sender.sendMessage("§8* §e/wildbw arena setteamspawn <arena> <team> - Imposta lo spawn del team.");
                sender.sendMessage("§8* §e/wildbw arena setteambed <arena> <team> - Imposta il letto del team.");
                sender.sendMessage("§8* §e/wildbw arena setteammaxplayers <arena> <team> <numero> - Imposta il numero massimo di giocatori del team.");
                sender.sendMessage("§8* §e/wildbw arena setteamminplayers <arena> <team> <numero> - Imposta il numero minimo di giocatori del team.");
                sender.sendMessage("§8* §e/wildbw arena setteamshop <arena> <team> <upgrade/items> - Imposta lo shop se items o upgrades del team.");
                sender.sendMessage("§8* §e/wildbw arena setteamgenerator <arena> <team> - Imposta il generatore del team.");
                sender.sendMessage("§f");
                sender.sendMessage("§e§lALTRO");
                sender.sendMessage("§8* §e/wildbw arena list - Lista delle arene.");
                sender.sendMessage("§8* §e/wildbw arena save <arena> - Salva l'arena.");
                sender.sendMessage("§8* §e/wildbw arena modify <arena> - Modifica un'arena.");
                sender.sendMessage("§8* §e/wildbw arena enable <arena> - Abilita un'arena.");
                sender.sendMessage("§8* §e/wildbw arena disable <arena> - Disabilita un'arena.");
                sender.sendMessage("§f");

                return true;
            } else if (args[1].equalsIgnoreCase("create")) {
                if (args.length == 2) {
                    if (args.length > 1) {
                        String arenaName = args[1];

                        arenaManager.createArena(arenaName, player.getLocation());
                        player.sendMessage(ChatManager.prefix + "l'arena " + arenaName + " è stata creata con successo&a!");
                    } else {
                        sender.sendMessage("§cUsa /wildbw arena create <nome>.");
                    }
                    return true;
                }
                return true;
            } else if (args[1].equalsIgnoreCase("delete")) {
                if (args.length == 2) {
                    if (args.length > 1) {
                        String arenaName = args[1];

                        if (arenaManager.deleteArena(arenaName)) {
                            player.sendMessage(ChatManager.prefix + "l'arena " + arenaName + " è stata eliminata con successo&a!");
                        } else {
                            player.sendMessage(ChatManager.prefix + "l'arena " + arenaName + " non esiste&a!");
                        }
                    } else {
                        sender.sendMessage("§cUsa /wildbw arena delete <nome>.");
                    }
                    return true;
                }
                sender.sendMessage("§cComando non ancora implementato.");
                return true;
            } else if (args[1].equalsIgnoreCase("setwaiting")) {
                if (args.length == 2) {
                    sender.sendMessage("§cUsa /wildbw arena setwaiting <arena>.");
                    return true;
                }
                sender.sendMessage("§cComando non ancora implementato.");
                return true;
            } else if (args[1].equalsIgnoreCase("setspectator")) {
                if (args.length == 2) {
                    sender.sendMessage("§cUsa /wildbw arena setspectator <arena>.");
                    return true;
                }
                sender.sendMessage("§cComando non ancora implementato.");
                return true;
            } else if (args[1].equalsIgnoreCase("setmaxplayers")) {
                if (args.length == 2) {
                    sender.sendMessage("§cUsa /wildbw arena setmaxplayers <arena> <numero>.");
                    return true;
                }
                sender.sendMessage("§cComando non ancora implementato.");
                return true;
            } else if (args[1].equalsIgnoreCase("setminplayers")) {
                if (args.length == 2) {
                    sender.sendMessage("§cUsa /wildbw arena setminplayers <arena> <numero>.");
                    return true;
                }
                sender.sendMessage("§cComando non ancora implementato.");
                return true;
            } else if (args[1].equalsIgnoreCase("setmode")) {
                if (args.length == 2) {
                    sender.sendMessage("§cUsa /wildbw arena setmode <arena> <solo/double/triple/squad>.");
                    return true;
                }
                sender.sendMessage("§cComando non ancora implementato.");
                return true;
            } else if (args[1].equalsIgnoreCase("setgenerator")) {
                if (args.length == 2 || args.length == 3) {
                    sender.sendMessage("§cUsa /wildbw arena setgenerator <arena> <emerald/diamond>.");
                    return true;
                }
            } else if (args[1].equalsIgnoreCase("createteam")) {
                if (args.length == 2 || args.length == 3 || args.length == 4) {
                    sender.sendMessage("§cUsa /wildbw arena createteam <arena> <team> <blue/red/green/yellow/gray/white/pink/cyan>.");
                    return true;
                }
                sender.sendMessage("§cComando non ancora implementato.");
                return true;
            } else if (args[1].equalsIgnoreCase("setteamspawn")) {
                if (args.length == 2 || args.length == 3) {
                    sender.sendMessage("§cUsa /wildbw arena setteamspawn <arena> <team>.");
                    return true;
                }
                sender.sendMessage("§cComando non ancora implementato.");
                return true;
            } else if (args[1].equalsIgnoreCase("setteambed")) {
                if (args.length == 2 || args.length == 3) {
                    sender.sendMessage("§cUsa /wildbw arena setteambed <arena> <team>.");
                    return true;
                }
                sender.sendMessage("§cComando non ancora implementato.");
                return true;
            } else if (args[1].equalsIgnoreCase("setteammaxplayers")) {
                if (args.length == 2 || args.length == 3 || args.length == 4) {
                    sender.sendMessage("§cUsa /wildbw arena setteammaxplayers <arena> <team> <numero>.");
                    return true;
                }
                sender.sendMessage("§cComando non ancora implementato.");
                return true;
            } else if (args[1].equalsIgnoreCase("setteamminplayers")) {
                if (args.length == 2 || args.length == 3 || args.length == 4) {
                    sender.sendMessage("§cUsa /wildbw arena setteamminplayers <arena> <team> <numero>.");
                    return true;
                }
                sender.sendMessage("§cComando non ancora implementato.");
                return true;
            } else if (args[1].equalsIgnoreCase("setteamshop")) {
                if (args.length == 2 || args.length == 3 || args.length == 4) {
                    sender.sendMessage("§cUsa /wildbw arena setteamshop <arena> <team> <upgrade/items>.");
                    return true;
                }
                sender.sendMessage(" §cComando non ancora implementato.");
            } else if (args[1].equalsIgnoreCase("setteamgenerator")) {
                if (args.length == 2 || args.length == 3) {
                    sender.sendMessage("§cUsa /wildbw arena setteamgenerator <arena> <team>.");
                    return true;
                }
                sender.sendMessage("§cComando non ancora implementato.");
                return true;
            } else if (args[1].equalsIgnoreCase("save")) {
                if (args.length == 2) {
                    sender.sendMessage("§cUsa /wildbw arena save <arena>.");
                    return true;
                }
                sender.sendMessage("§cComando non ancora implementato.");
                return true;
            } else if (args[1].equalsIgnoreCase("enable")) {
                if (args.length == 2) {
                    sender.sendMessage("§cUsa /wildbw arena enable <arena>.");
                    return true;
                }
                sender.sendMessage("§cComando non ancora implementato.");
                return true;
            } else if (args[1].equalsIgnoreCase("disable")) {
                if (args.length == 2) {
                    sender.sendMessage("§cUsa /wildbw arena disable <arena>.");
                    return true;
                }
                sender.sendMessage("§cComando non ancora implementato.");
                return true;
            } else if (args[1].equalsIgnoreCase("list")) {
                player.sendMessage("§e§lLista Arene:");
                for (String name : arenaManager.getArenas().keySet()) {
                    Arena arena = arenaManager.getArenas().get(name);
                    String status = arena.isEnabled() ? "§aAbilitata" : "§cDisabilitata";

                    player.sendMessage("§8* §e" + name + " - " + status);
                }
            }
        }
        return false;
    }
}
