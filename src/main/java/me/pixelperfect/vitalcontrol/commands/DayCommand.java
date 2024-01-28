package me.pixelperfect.vitalcontrol.commands;

import me.pixelperfect.vitalcontrol.VitalControl;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DayCommand implements CommandExecutor {

    public VitalControl plugin;
    public DayCommand(VitalControl plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("day")) {

            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can execute this command!");
                return true;
            }
            Player player = (Player) sender;
            if (!(player.hasPermission("vital.control.day"))) {
                player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', plugin.prefix + "&cYou don't have permission to use this command!"));
                return true;
            }

            World world = Bukkit.getServer().getWorld(player.getWorld().getName());
            if (world != null) {
                world.setTime(1000);
            }
            return true;
        }

        if (label.equalsIgnoreCase("noon")) {

            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can execute this command!");
                return true;
            }
            Player player = (Player) sender;
            if (!(player.hasPermission("vital.control.noon"))) {
                player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', plugin.prefix + "&cYou don't have permission to use this command!"));
                return true;
            }

            World world = Bukkit.getServer().getWorld(player.getWorld().getName());
            if (world != null) {
                world.setTime(6000);
            }
            return true;
        }

        if (label.equalsIgnoreCase("night")) {

            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can execute this command!");
                return true;
            }
            Player player = (Player) sender;
            if (!(player.hasPermission("vital.control.night"))) {
                player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', plugin.prefix + "&cYou don't have permission to use this command!"));
                return true;
            }

            World world = Bukkit.getServer().getWorld(player.getWorld().getName());
            if (world != null) {
                world.setTime(13000);
            }
            return true;
        }

        if (label.equalsIgnoreCase("midnight")) {

            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can execute this command!");
                return true;
            }
            Player player = (Player) sender;
            if (!(player.hasPermission("vital.control.midnight"))) {
                player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', plugin.prefix + "&cYou don't have permission to use this command!"));
                return true;
            }

            World world = Bukkit.getServer().getWorld(player.getWorld().getName());
            if (world != null) {
                world.setTime(18000);
            }
            return true;
        }

        return false;
    }
}
