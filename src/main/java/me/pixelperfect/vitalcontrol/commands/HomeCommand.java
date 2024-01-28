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

public class HomeCommand implements CommandExecutor {

    public DataManager data;
    public VitalControl plugin;
    public HomeCommand(VitalControl plugin, DataManager data) {this.plugin = plugin; this.data = data;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        // GOAL: Make a /homes or /listhomes command
        if (label.equalsIgnoreCase("home")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can execute this command!");
                return true;
            }
            Player player = (Player) sender;
            if (!(player.hasPermission("vital.control.home"))) {
                player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', plugin.prefix + "&cYou don't have permission to use homes!"));
                return true;
            }
            ConfigurationSection homes = data.getConfig().getConfigurationSection("players." + player.getUniqueId() + ".homes");

            if (args.length == 0) {
                if (!(data.getConfig().contains("players." + player.getUniqueId() + ".homes.home"))) {
                    if (homes != null && homes.getKeys(false).size() == 0) {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&cNo homes set!"));
                        return true;
                    }
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&cPlease specify a home!"));
                    player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l> &b/home &3<&bhome-name&b>"));
                    return true;
                }
                double locX = data.getConfig().getDouble("players." + player.getUniqueId() + ".homes.home.x");
                double locY = data.getConfig().getDouble("players." + player.getUniqueId() + ".homes.home.y");
                double locZ = data.getConfig().getDouble("players." + player.getUniqueId() + ".homes.home.z");
                double yaw = (float) data.getConfig().getDouble("players." + player.getUniqueId() + ".homes.home.yaw");
                double pitch = (float) data.getConfig().getDouble("players." + player.getUniqueId() + ".homes.home.pitch");
                World world = Bukkit.getServer().getWorld(data.getConfig().getString("players." + player.getUniqueId() + ".homes.home.world"));
                Location location = new Location(world, locX, locY, locZ, (float) yaw, (float) pitch);
                player.teleport(location);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Teleported you to &ehome&7!"));
            }
            if (args.length > 0) {
                String home = args[0];
                if (!(data.getConfig().contains("players." + player.getUniqueId() + ".homes." + home))) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes(
                            '&', plugin.prefix + "&cCouldn't find a home named &e" + home + "&c!"));
                    return true;
                }
                double locX = data.getConfig().getDouble("players." + player.getUniqueId() + ".homes." + home + ".x");
                double locY = data.getConfig().getDouble("players." + player.getUniqueId() + ".homes." + home + ".y");
                double locZ = data.getConfig().getDouble("players." + player.getUniqueId() + ".homes." + home + ".z");
                double yaw = data.getConfig().getDouble("players." + player.getUniqueId() + ".homes." + home + ".yaw");
                double pitch = data.getConfig().getDouble("players." + player.getUniqueId() + ".homes." + home + ".pitch");
                World world = Bukkit.getServer().getWorld(data.getConfig().getString("players." + player.getUniqueId() + ".homes." + home + ".world"));
                Location location = new Location(world, locX, locY, locZ, (float) yaw, (float) pitch);
                player.teleport(location);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&7Teleported you to &e" + home + "&7!"));
            }
            return true;
        }

        return false;
    }
}
