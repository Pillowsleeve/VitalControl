package me.pixelperfect.vitalcontrol.commands;

import me.pixelperfect.vitalcontrol.VitalControl;
import me.pixelperfect.vitalcontrol.files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetHomeCommand implements CommandExecutor {

    public DataManager data;
    public VitalControl plugin;
    public SetHomeCommand(VitalControl plugin, DataManager data) {this.plugin = plugin; this.data = data;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        // GOAL: Check for amount of homes/create maximum allowed governed by permissions
        if (label.equalsIgnoreCase("sethome")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can execute this command!");
                return true;
            }
            Player player = (Player) sender;
            if (!(player.hasPermission("vital.control.sethome"))) {
                player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', plugin.prefix + "&cYou don't have permission to set a home!"));
                return true;
            }
            double locX = player.getLocation().getX();
            double locY = player.getLocation().getY();
            double locZ = player.getLocation().getZ();
            float yaw = player.getLocation().getYaw();
            float pitch = player.getLocation().getPitch();
            String world = player.getWorld().getName();
            if (args.length == 0) {
                if (data.getConfig().contains("players." + player.getUniqueId() + ".homes.home")) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes(
                            '&', plugin.prefix + "&cPlease specify a name!"));
                    player.sendMessage(ChatColor.translateAlternateColorCodes(
                            '&', "&e&l> &b/sethome &3<&bhome-name&b>"));
                    return true;
                }
                data.getConfig().set("players." + player.getUniqueId() + ".homes.home.world", world);
                data.getConfig().set("players." + player.getUniqueId() + ".homes.home.x", locX);
                data.getConfig().set("players." + player.getUniqueId() + ".homes.home.y", locY);
                data.getConfig().set("players." + player.getUniqueId() + ".homes.home.z", locZ);
                data.getConfig().set("players." + player.getUniqueId() + ".homes.home.pitch", pitch);
                data.getConfig().set("players." + player.getUniqueId() + ".homes.home.yaw", yaw);
                data.saveConfig();
                data.reloadConfig();
                player.sendMessage("");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&aHome set to current location!"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l> &7Name: &fhome"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l> &7World: &f" + world));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l> &b/Home &7to teleport to this home!"));
                player.sendMessage("");
            }
            if (args.length > 0) {
                String name = args[0];
                if (data.getConfig().contains("players." + player.getUniqueId() + ".homes." + name)) {
                    player.sendMessage(ChatColor.translateAlternateColorCodes(
                            '&', plugin.prefix + "&cA home with this name already exists!"));
                    return true;
                }
                data.getConfig().set("players." + player.getUniqueId() + ".homes." + name + ".world", world);
                data.getConfig().set("players." + player.getUniqueId() + ".homes." + name + ".x", locX);
                data.getConfig().set("players." + player.getUniqueId() + ".homes." + name + ".y", locY);
                data.getConfig().set("players." + player.getUniqueId() + ".homes." + name + ".z", locZ);
                data.getConfig().set("players." + player.getUniqueId() + ".homes." + name + ".pitch", pitch);
                data.getConfig().set("players." + player.getUniqueId() + ".homes." + name + ".yaw", yaw);
                data.saveConfig();
                data.reloadConfig();
                player.sendMessage("");
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&aHome set to current location!"));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l> &7Name: &f" + name));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l> &7World: &f" + world));
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&e&l> &b/Home &3" + name + " &7to teleport to this home!"));
                player.sendMessage("");
            }
            return true;
        }

        return false;
    }
}
