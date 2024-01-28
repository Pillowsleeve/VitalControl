package me.pixelperfect.vitalcontrol.commands;

import me.pixelperfect.vitalcontrol.VitalControl;
import me.pixelperfect.vitalcontrol.files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

import java.util.Set;

public class ListWarpsCommand implements CommandExecutor {

    public DataManager data;
    public VitalControl plugin;
    public ListWarpsCommand(VitalControl plugin, DataManager data) {this.plugin = plugin; this.data = data;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("listwarps")
                || label.equalsIgnoreCase("warps") || label.equalsIgnoreCase("warplist")) {

            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can execute this command!");
                return true;
            }
            Player player = (Player) sender;
            if (!(player.hasPermission("vital.control.listwarps"))) {
                player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', plugin.prefix + "&cYou don't have permission to view available warps!"));
                return true;
            }
            ConfigurationSection warps = data.getConfig().getConfigurationSection("warps");
            if (warps != null && warps.getKeys(false).size() == 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&cNo warps have been set yet!"));
                return true;
            }
            if (warps != null && warps.getKeys(false).size() > 0) {
                int warpAmount = warps.getKeys(false).size();
                player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', plugin.prefix + "&7There are a total of &e" + warpAmount + " &7warps."));
                final Set<String> list = warps.getKeys(false);
                player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', "&e&l> &eWarps: &f" + list));
            }
            return true;
        }
        return false;
    }
}
