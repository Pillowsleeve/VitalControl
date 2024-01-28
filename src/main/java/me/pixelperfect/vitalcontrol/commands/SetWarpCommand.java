package me.pixelperfect.vitalcontrol.commands;

import me.pixelperfect.vitalcontrol.VitalControl;
import me.pixelperfect.vitalcontrol.files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class SetWarpCommand implements CommandExecutor {

    public DataManager data;
    public VitalControl plugin;
    public SetWarpCommand(VitalControl plugin, DataManager data) {this.plugin = plugin; this.data = data;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        // GOAL: Check for amount of homes/create maximum allowed governed by permissions
        if (label.equalsIgnoreCase("setwarp")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can execute this command!");
                return true;
            }
            Player player = (Player) sender;
            if (!(player.hasPermission("vital.control.setwarp"))) {
                player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', plugin.prefix + "&cYou don't have permission to set a warp!"));
                return true;
            }
            double locX = player.getLocation().getX();
            double locY = player.getLocation().getY();
            double locZ = player.getLocation().getZ();
            float yaw = player.getLocation().getYaw();
            float pitch = player.getLocation().getPitch();
            String world = player.getWorld().getName();
            ConfigurationSection warps = data.getConfig().getConfigurationSection("warps");
            if (args.length == 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', plugin.prefix + "&cPlease specify a name!"));
                player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', "&e&l> &b/setwarp &3<&bwarp-name&b>"));
            }
            if (args.length > 0) {
                String name = args[0];
                if (data.getConfig().contains("warps." + name)) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes(
                            '&', plugin.prefix + "&cA warp with this name already exists!"));
                    return true;
                }
                data.getConfig().set("warps." + name + ".world", world);
                data.getConfig().set("warps." + name + ".x", locX);
                data.getConfig().set("warps." + name + ".y", locY);
                data.getConfig().set("warps." + name + ".z", locZ);
                data.getConfig().set("warps." + name + ".pitch", pitch);
                data.getConfig().set("warps." + name + ".yaw", yaw);
                data.saveConfig();
                data.reloadConfig();
                player.sendMessage("");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&aWarp set to current location!"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l> &7Name: &f" + name));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l> &7World: &f" + world));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l> &b/Warp &3" + name + " &7to teleport to this warp point!"));
                player.sendMessage("");
            }
            return true;
        }

        return false;
    }
}
