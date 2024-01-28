package me.pixelperfect.vitalcontrol.commands;

import me.pixelperfect.vitalcontrol.VitalControl;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TpHereCommand implements CommandExecutor {

    public VitalControl plugin;
    public TpHereCommand(VitalControl plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("tphere")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("You have to be a player to execute this command!");
                return true;
            }
            Player player = (Player) sender;
            if (!(player.hasPermission("vital.control.tphere"))) {
                player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', plugin.prefix + "&c&lNo Permission! &7You don't have permission to execute this command!"));
                return true;
            }

            if (args.length == 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&cPlease specify a player!"));
            }
            if (args.length > 0) {
                Player target = Bukkit.getPlayer(args[0]);
                if (target == null) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes(
                            '&', plugin.prefix + "&cThis player is not online or doesn't exist!"));
                } else {
                    Location playerLoc = player.getLocation();
                    target.teleport(playerLoc);
                    target.sendMessage(ChatColor.translateAlternateColorCodes(
                            '&', plugin.prefix + "&e" + player.getName() + " &7Teleported you to their location!"));
                }
            }
            return true;
        }
        return false;
    }
}
