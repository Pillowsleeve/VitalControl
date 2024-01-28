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

public class ListHomesCommand implements CommandExecutor {

    public DataManager data;
    public VitalControl plugin;
    public ListHomesCommand(VitalControl plugin, DataManager data) {this.plugin = plugin; this.data = data;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("listhomes")
                || label.equalsIgnoreCase("homes") || label.equalsIgnoreCase("homelist")) {

            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can execute this command!");
                return true;
            }
            Player player = (Player) sender;
            if (!(player.hasPermission("vital.control.listhomes"))) {
                player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', plugin.prefix + "&cYou don't have permission to use homes!"));
                return true;
            }
            ConfigurationSection homes = data.getConfig().getConfigurationSection("players." + player.getUniqueId() + ".homes");
            if (homes != null && homes.getKeys(false).size() == 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&cYou haven't set any homes!"));
                return true;
            }
            if (homes != null && homes.getKeys(false).size() > 0) {
                int homeAmount = homes.getKeys(false).size();
                player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', plugin.prefix + "&7You have a total of &e" + homeAmount + " &7homes."));
                final Set<String> list = homes.getKeys(false);
                player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', "&e&l> &eHomes: &f" + list));
            }
            return true;
        }
        return false;
    }
}
