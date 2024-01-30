package me.pixelperfect.vitalcontrol.commands;

import me.pixelperfect.vitalcontrol.VitalControl;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class CatBow implements CommandExecutor {

    public VitalControl plugin;
    public CatBow(VitalControl plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("beebow") || label.equalsIgnoreCase("bb")) {

            if (!(sender instanceof Player)) {
                sender.sendMessage("Only players can execute this command!");
                return true;
            }
            Player player = (Player) sender;
            if (!player.hasPermission("vital.control.beebow")) {
                player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', plugin.prefix + "&cYou don't have permission to execute this command!"));
                return true;
            }

            player.getInventory().addItem(getBeeBow());
            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix + "&aYou received a special bow!"));

            return true;
        }

        return false;
    }

    private ItemStack getBeeBow() {
        ItemStack beeBow = new ItemStack(Material.BOW);
        ItemMeta meta = beeBow.getItemMeta();
        meta.setDisplayName(ChatColor.LIGHT_PURPLE + "Beebow");
        beeBow.setItemMeta(meta);
        return beeBow;
    }
}
