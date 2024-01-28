package me.pixelperfect.vitalcontrol.commands;

import me.pixelperfect.vitalcontrol.VitalControl;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class HealCommand implements CommandExecutor {

    public VitalControl plugin;
    public HealCommand(VitalControl plugin) {this.plugin = plugin;}

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("heal")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can execute this command!");
                return true;
            }

            Player player = (Player) sender;
            if (!(player.hasPermission("vital.control.heal"))) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&cYou don't have permission to execute this command!"));
                return true;
            }
            if (args.length == 0) {
                if (player.getHealth() < 20) {
                    player.setHealth(20);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&aYou've been healed!"));
                } else {player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&7No healing required!"));}
            }
            if (args.length > 0) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&cThis player is not online or doesn't exist!"));
                    return true;
                }
                if (target.getName().equals(player.getName())) {
                    if (player.getHealth() < 20) {
                        player.setHealth(20);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&aYou've been healed!"));
                    } else {player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&7No healing required!"));}
                    return true;
                }
                if (target.getHealth() < 20) {
                    target.setHealth(20);
                    target.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&aYou've been healed by &e" + player.getName()));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully healed &e" + target.getName()));
                } else {player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&7This player doesn't need healing!"));}
            }
            return true;
        }

        if (label.equalsIgnoreCase("feed")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can execute this command!");
                return true;
            }

            Player player = (Player) sender;
            if (!(player.hasPermission("vital.control.feed"))) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&cYou don't have permission to execute this command!"));
                return true;
            }
            if (args.length == 0) {
                if (player.getFoodLevel() < 20) {
                    player.setFoodLevel(20);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&aYou've been fed!"));
                } else {player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&7You're not hungry!"));}
            }
            if (args.length > 0) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&cThis player is not online or doesn't exist!"));
                    return true;
                }
                if (target.getName().equals(player.getName())) {
                    if (player.getFoodLevel() < 20) {
                        player.setFoodLevel(20);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&aYou've been fed!"));
                    } else {player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&7You're not hungry!"));}
                    return true;
                }
                if (target.getFoodLevel() < 20) {
                    target.setFoodLevel(20);
                    target.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&aYou've been fed by &e" + player.getName()));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully fed &e" + target.getName()));
                } else {player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&7This player is not hungry!"));}
            }
            return true;
        }

        if (label.equalsIgnoreCase("revive") || label.equalsIgnoreCase("rev")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can execute this command!");
                return true;
            }

            Player player = (Player) sender;
            if (!(player.hasPermission("vital.control.feed"))) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&cYou don't have permission to execute this command!"));
                return true;
            }
            if (args.length == 0) {
                if (player.getFoodLevel() == 20 && player.getHealth() == 20) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&7You're not in need of revival!"));
                    return true;
                }
                if (player.getHealth() < 20) {
                    player.setHealth(20);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&aYou've been healed!"));
                }
                if (player.getFoodLevel() < 20) {
                    player.setFoodLevel(20);
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&aYou've been fed!"));
                }
            }
            if (args.length > 0) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&cThis player is not online or doesn't exist!"));
                    return true;
                }
                if (target.getName().equals(player.getName())) {
                    if (player.getFoodLevel() == 20 && player.getHealth() == 20) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&7You're not in need of revival!"));
                        return true;
                    }
                    if (player.getHealth() < 20) {
                        player.setHealth(20);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&aYou've been healed!"));
                    }
                    if (player.getFoodLevel() < 20) {
                        player.setFoodLevel(20);
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&aYou've been fed!"));
                    }
                    return true;
                }
                if (target.getFoodLevel() == 20 && target.getHealth() == 20) {
                    target.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&7This player is not in need of revival!"));
                    return true;
                }
                if (target.getHealth() < 20) {
                    target.setHealth(20);
                }
                if (target.getFoodLevel() < 20) {
                    target.setFoodLevel(20);
                }
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aSuccessfully revived &e" + target.getName()));
            }
            return true;
        }

        return false;
    }
}
