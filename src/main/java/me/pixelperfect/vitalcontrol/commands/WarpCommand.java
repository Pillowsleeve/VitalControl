package me.pixelperfect.vitalcontrol.commands;

import me.pixelperfect.vitalcontrol.VitalControl;
import me.pixelperfect.vitalcontrol.files.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;

public class WarpCommand implements CommandExecutor {

    public DataManager data;
    public VitalControl plugin;
    public WarpCommand(VitalControl plugin, DataManager data) {this.plugin = plugin; this.data = data;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        // GOAL: Make a /homes or /listhomes command
        if (label.equalsIgnoreCase("warp")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can execute this command!");
                return true;
            }
            Player player = (Player) sender;
            if (!(player.hasPermission("vital.control.warp"))) {
                player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', plugin.prefix + "&cYou don't have permission to set a warp!"));
                return true;
            }
            ConfigurationSection warps = data.getConfig().getConfigurationSection("warps");

            if (args.length == 0) {
                if (!(data.getConfig().contains("warps"))) {
                    if (warps != null && warps.getKeys(false).size() == 0) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&cNo warps set!"));
                        return true;
                    }
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&cPlease specify a warp!"));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l> &b/warp &3<&bwarp-name&b>"));
                    return true;
                }
            }
            if (args.length > 0) {
                String warp = args[0];
                if (!(data.getConfig().contains("warps." + warp))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes(
                            '&', plugin.prefix + "&cCouldn't find a warp named &e" + warp + "&c!"));
                    return true;
                }
                double locX = data.getConfig().getDouble("warps." + warp + ".x");
                double locY = data.getConfig().getDouble("warps." + warp + ".y");
                double locZ = data.getConfig().getDouble("warps." + warp + ".z");
                double yaw = data.getConfig().getDouble("warps." + warp + ".yaw");
                double pitch = data.getConfig().getDouble("warps." + warp + ".pitch");
                World world = Bukkit.getServer().getWorld(data.getConfig().getString("warps." + warp + ".world"));
                Location location = new Location(world, locX, locY, locZ, (float) yaw, (float) pitch);
                player.teleport(location);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Teleported you to &e" + warp + "&7!"));
            }
            return true;
        }

        return false;
    }
}
