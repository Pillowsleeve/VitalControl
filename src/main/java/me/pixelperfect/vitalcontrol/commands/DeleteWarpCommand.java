package me.pixelperfect.vitalcontrol.commands;

import me.pixelperfect.vitalcontrol.VitalControl;
import me.pixelperfect.vitalcontrol.files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class DeleteWarpCommand implements CommandExecutor {

    public DataManager data;
    public VitalControl plugin;
    public DeleteWarpCommand(VitalControl plugin, DataManager data) {this.plugin = plugin; this.data = data;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("delwarp")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can execute this command!");
                return true;
            }
            Player player = (Player) sender;
            if (!(player.hasPermission("vital.control.delwarp"))) {
                player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', plugin.prefix + "&cYou don't have permission to delete a warp!"));
                return true;
            }
            ConfigurationSection warps = data.getConfig().getConfigurationSection("warps");

            if (warps != null && warps.getKeys(false).size() == 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&cNo warps to delete!"));
                return true;
            }

            if (args.length == 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&cPlease specify a home!"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l> &b/delhome &3<&bhome-name&b>"));
            }
            if (args.length > 0) {
                String warp = args[0];
                if (!(data.getConfig().contains("warps." + warp))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes(
                            '&', plugin.prefix + "&cCouldn't find a home named &e" + warp + "&c!"));
                    return true;
                }
                data.getConfig().set("warps." + warp + ".world", null);
                data.getConfig().set("warps." + warp + ".x", null);
                data.getConfig().set("warps." + warp + ".y", null);
                data.getConfig().set("warps." + warp + ".z", null);
                data.getConfig().set("warps." + warp + ".pitch", null);
                data.getConfig().set("warps." + warp + ".yaw", null);
                data.getConfig().set("warps." + warp, null);
                data.saveConfig();
                data.reloadConfig();
                if (data.getConfig().contains("warps." + warp)) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes(
                            '&', plugin.prefix + "&cDeletion of warp: &e" + warp + " &cwas unsuccessful!"));
                    return true;
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes(
                            '&', plugin.prefix + "&e" + warp + " &awas deleted successfully!"));
                }
            }
            return true;
        }

        return false;
    }
}
