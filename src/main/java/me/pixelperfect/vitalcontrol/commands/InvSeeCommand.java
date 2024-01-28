package me.pixelperfect.vitalcontrol.commands;

import me.pixelperfect.vitalcontrol.VitalControl;
import me.pixelperfect.vitalcontrol.files.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class InvSeeCommand implements CommandExecutor {

    public DataManager data;
    public VitalControl plugin;
    public InvSeeCommand(VitalControl plugin, DataManager data) {this.plugin = plugin; this.data = data;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("invsee")) {
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
            if (args.length == 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&cPlease specify a player!"));
            }
            Player target = Bukkit.getPlayer(args[0]);
            if (target != null & args[0].equalsIgnoreCase(target.getName())) {
                Inventory targetInv = target.getInventory();
                player.openInventory(targetInv);
            } else {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cThis player is not online or doesn't exist!"));
            }
            return true;
        }
        return false;
    }
}
