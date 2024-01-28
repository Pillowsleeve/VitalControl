package me.pixelperfect.vitalcontrol.commands;

import me.pixelperfect.vitalcontrol.VitalControl;
import me.pixelperfect.vitalcontrol.files.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class VanishCommand implements CommandExecutor {

    public DataManager data;
    public VitalControl plugin;
    public VanishCommand(VitalControl plugin, DataManager data) {this.plugin = plugin; this.data = data;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("vanish")) {

            if (!(sender instanceof Player)) {
                sender.sendMessage("You have to be a player to execute this command!");
                return true;
            }
            Player player = (Player) sender;
            if (!(player.hasPermission("vital.control.vanished"))) {
                player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', plugin.prefix + "&c&lNo Permission! &7You don't have permission to execute this command!"));
                return true;
            }

            if (plugin.vanished.contains(player)){
                for (Player online : Bukkit.getOnlinePlayers()) {
                    plugin.vanished.remove(player);
                    online.showPlayer(plugin, player);
                    player.setPlayerListName(player.getDisplayName());
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&cYou're no longer hidden!"));
                }
            } else {
                for (Player online : Bukkit.getOnlinePlayers()) {
                    plugin.vanished.add(player);
                    online.hidePlayer(plugin, player);
                    player.setPlayerListName(ChatColor.translateAlternateColorCodes('&', "&7&o" + player.getName()));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&aYou've been hidden!"));
                }
            }
        }

        return false;
    }
}
