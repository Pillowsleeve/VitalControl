package me.pixelperfect.vitalcontrol.commands;

import me.pixelperfect.vitalcontrol.VitalControl;
import me.pixelperfect.vitalcontrol.files.DataManager;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class GameModeCommands implements CommandExecutor {

    public DataManager data;
    public VitalControl plugin;
    public GameModeCommands(VitalControl plugin, DataManager data) {this.plugin = plugin; this.data = data;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        GameMode gamemode;
        String mode;

        if (label.equalsIgnoreCase("creative") || label.equalsIgnoreCase("c")) {
            gamemode = GameMode.CREATIVE;
            mode = "&eCreative";
            if (!(sender instanceof Player)) {
                sender.sendMessage("You have to be a player to execute this command!");
                return true;
            }
            Player player = (Player) sender;
            if (!(player.hasPermission("vital.control.creative"))) {
                player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', plugin.prefix + "&c&lNo Permission! &7You don't have permission to execute this command!"));
                return true;
            }
            if (args.length == 0) {
                if (!(player.getGameMode().equals(gamemode))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes(
                            '&', plugin.prefix + "&7Your game-mode was set to " + mode + "&7!"));
                }
                player.setGameMode(gamemode);
            }
            if (args.length > 0) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null && args[0].equalsIgnoreCase(target.getName())) {
                    if (!(target.getGameMode().equals(gamemode))) {
                        target.sendMessage(ChatColor.translateAlternateColorCodes(
                                '&', "&3" + player.getName() + "&7 set your game-mode to " + mode + "&7!"));
                    }
                    target.setGameMode(gamemode);
                } else player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', plugin.prefix + "&cThis player doesn't exist or is not online!")); return true;
            }
            return true;
        }

        if (label.equalsIgnoreCase("survival") || label.equalsIgnoreCase("s")) {
            gamemode = GameMode.SURVIVAL;
            mode = "&eSurvival";
            if (!(sender instanceof Player)) {
                sender.sendMessage("You have to be a player to execute this command!");
                return true;
            }
            Player player = (Player) sender;
            if (!(player.hasPermission("vital.control.survival"))) {
                player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', plugin.prefix + "&c&lNo Permission! &7You don't have permission to execute this command!"));
                return true;
            }
            if (args.length == 0) {
                if (!(player.getGameMode().equals(gamemode))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes(
                            '&', plugin.prefix + "&7Your game-mode was set to " + mode + "&7!"));
                }
                player.setGameMode(gamemode);
            }
            if (args.length > 0) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null && args[0].equalsIgnoreCase(target.getName())) {
                    if (!(target.getGameMode().equals(gamemode))) {
                        target.sendMessage(ChatColor.translateAlternateColorCodes(
                                '&', "&3" + player.getName() + "&7 set your game-mode to " + mode + "&7!"));
                    }
                    target.setGameMode(gamemode);
                } else player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', plugin.prefix + "&cThis player doesn't exist or is not online!")); return true;
            }
            return true;
        }

        if (label.equalsIgnoreCase("adventure") || label.equalsIgnoreCase("a")) {
            gamemode = GameMode.ADVENTURE;
            mode = "&eAdventure";
            if (!(sender instanceof Player)) {
                sender.sendMessage("You have to be a player to execute this command!");
                return true;
            }
            Player player = (Player) sender;
            if (!(player.hasPermission("vital.control.adventure"))) {
                player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', plugin.prefix + "&c&lNo Permission! &7You don't have permission to execute this command!"));
                return true;
            }
            if (args.length == 0) {
                if (!(player.getGameMode().equals(gamemode))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes(
                            '&', plugin.prefix + "&7Your game-mode was set to " + mode + "&7!"));
                }
                player.setGameMode(gamemode);
            }
            if (args.length > 0) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null && args[0].equalsIgnoreCase(target.getName())) {
                    if (!(target.getGameMode().equals(gamemode))) {
                        target.sendMessage(ChatColor.translateAlternateColorCodes(
                                '&', "&3" + player.getName() + "&7 set your game-mode to " + mode + "&7!"));
                    }
                    target.setGameMode(gamemode);
                } else player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', plugin.prefix + "&cThis player doesn't exist or is not online!")); return true;
            }
            return true;
        }

        if (label.equalsIgnoreCase("spectator") || label.equalsIgnoreCase("sp")) {
            gamemode = GameMode.SPECTATOR;
            mode = "&eSpectator";
            if (!(sender instanceof Player)) {
                sender.sendMessage("You have to be a player to execute this command!");
                return true;
            }
            Player player = (Player) sender;
            if (!(player.hasPermission("vital.control.spectator"))) {
                player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', plugin.prefix + "&c&lNo Permission! &7You don't have permission to execute this command!"));
                return true;
            }
            if (args.length == 0) {
                if (!(player.getGameMode().equals(gamemode))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes(
                            '&', plugin.prefix + "&7Your game-mode was set to " + mode + "&7!"));
                }
                player.setGameMode(gamemode);
            }
            if (args.length > 0) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target != null && args[0].equalsIgnoreCase(target.getName())) {
                    if (!(target.getGameMode().equals(gamemode))) {
                        target.sendMessage(ChatColor.translateAlternateColorCodes(
                                '&', "&3" + player.getName() + "&7 set your game-mode to " + mode + "&7!"));
                    }
                    target.setGameMode(gamemode);
                } else player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', plugin.prefix + "&cThis player doesn't exist or is not online!")); return true;
            }
            return true;
        }

        return false;
    }
}
