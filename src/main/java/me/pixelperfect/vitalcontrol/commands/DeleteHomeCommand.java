package me.pixelperfect.vitalcontrol.commands;

import me.pixelperfect.vitalcontrol.VitalControl;
import me.pixelperfect.vitalcontrol.files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class DeleteHomeCommand implements CommandExecutor {

    public DataManager data;
    public VitalControl plugin;
    public DeleteHomeCommand(VitalControl plugin, DataManager data) {this.plugin = plugin; this.data = data;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("delhome") || label.equalsIgnoreCase("dhome")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can execute this command!");
                return true;
            }
            Player player = (Player) sender;
            if (!(player.hasPermission("vital.control.delhome"))) {
                player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', plugin.prefix + "&cYou don't have permission to delete homes!"));
                return true;
            }
            ConfigurationSection homes = data.getConfig().getConfigurationSection("players." + player.getUniqueId() + ".homes");

            if (args.length == 0) {
                if (!(data.getConfig().contains("players." + player.getUniqueId() + ".homes.home"))) {
                    if (homes != null && homes.getKeys(false).size() == 0) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&cNo home to delete!"));
                        return true;
                    }
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&cPlease specify a home!"));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l> &b/delhome &3<&bhome-name&b>"));
                } else {
                    // REMINDER: Try deleting home from config by just deleting main parent-path
                    data.getConfig().set("players." + player.getUniqueId() + ".homes.home.world", null);
                    data.getConfig().set("players." + player.getUniqueId() + ".homes.home.x", null);
                    data.getConfig().set("players." + player.getUniqueId() + ".homes.home.y", null);
                    data.getConfig().set("players." + player.getUniqueId() + ".homes.home.z", null);
                    data.getConfig().set("players." + player.getUniqueId() + ".homes.home.pitch", null);
                    data.getConfig().set("players." + player.getUniqueId() + ".homes.home.yaw", null);
                    data.getConfig().set("players." + player.getUniqueId() + ".homes.home", null);
                    data.saveConfig();
                    data.reloadConfig();
                    player.sendMessage(ChatColor.translateAlternateColorCodes(
                            '&', plugin.prefix + "&ehome &awas deleted successfully!"));
                    return true;
                }
            }
            if (args.length > 0) {
                String home = args[0];
                if (!(data.getConfig().contains("players." + player.getUniqueId() + ".homes." + home))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes(
                            '&', plugin.prefix + "&cCouldn't find a home named &e" + home + "&c!"));
                    return true;
                }
                data.getConfig().set("players." + player.getUniqueId() + ".homes." + home + ".world", null);
                data.getConfig().set("players." + player.getUniqueId() + ".homes." + home + ".x", null);
                data.getConfig().set("players." + player.getUniqueId() + ".homes." + home + ".y", null);
                data.getConfig().set("players." + player.getUniqueId() + ".homes." + home + ".z", null);
                data.getConfig().set("players." + player.getUniqueId() + ".homes." + home + ".pitch", null);
                data.getConfig().set("players." + player.getUniqueId() + ".homes." + home + ".yaw", null);
                data.getConfig().set("players." + player.getUniqueId() + ".homes." + home, null);
                data.saveConfig();
                data.reloadConfig();
                if (data.getConfig().contains("players." + player.getUniqueId() + ".homes." + home)) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes(
                            '&', plugin.prefix + "&cDeletion of home: &e" + home + " &cwas unsuccessful!"));
                    return true;
                } else {
                    player.sendMessage(ChatColor.translateAlternateColorCodes(
                            '&', plugin.prefix + "&e" + home + " &awas deleted successfully!"));
                }
            }
            return true;
        }

        return false;
    }
}
