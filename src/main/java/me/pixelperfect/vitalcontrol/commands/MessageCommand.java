package me.pixelperfect.vitalcontrol.commands;

import me.pixelperfect.vitalcontrol.VitalControl;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MessageCommand implements CommandExecutor {

    public VitalControl plugin;
    public MessageCommand(VitalControl plugin) {this.plugin = plugin;}

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (label.equalsIgnoreCase("message") || label.equalsIgnoreCase("msg")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("You have to be a player to execute this command!");
                return true;
            }
            Player player = (Player) sender;
            if (!(player.hasPermission("vital.control.message"))) {
                player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', plugin.prefix + "&c&lNo Permission! &7You don't have permission to execute this command!"));
                return true;
            }
            if (args.length == 0) {
                player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', plugin.prefix + "&cPlease specify a player and message!"));
            }

            if (args.length == 1) {
                player.sendMessage(ChatColor.translateAlternateColorCodes(
                        '&', plugin.prefix + "&cPlease specify a message!"));
            }

            // Step 1: /msg [Player] "message"
            StringBuilder msg = new StringBuilder();
            for (int i = 1; i < args.length; i++) { //Build message
                msg.append(args[i]).append(" ");
            }

            Player target = Bukkit.getPlayer(args[0]);
            if (target != null && args[0].equalsIgnoreCase(target.getName())) {
                if (target.isOnline()) {
                    if (args.length > 1) { //Send Message
                        int maxmsg = plugin.getConfig().getInt("max-private-message-length");
                        if (!(args.length > maxmsg)) {
                            player.sendMessage(ChatColor.translateAlternateColorCodes(
                                    '&', plugin.prefix + "&a&lSent! &7A message was sent to: &f" + target.getName()));
                            target.sendMessage("");
                            target.sendMessage(ChatColor.translateAlternateColorCodes(
                                    '&', "&e&l! &7You received a private message from: &e" + player.getName()));
                            target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lMessage:&r"));
                            target.sendMessage(msg.toString());
                            target.sendMessage("");
                            if (target.hasPermission("vital.control.message")) {
                                target.sendMessage(ChatColor.translateAlternateColorCodes('&',
                                        "&7You can respond with: &b/message &e" + player.getName() + "&b [&fMessage&b]"));
                            } else {target.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l! &7You don't have permission to respond...")); }
                            target.sendMessage("");
                        } else {
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lSorry! &7This message is too long!"));
                            player.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.prefix +
                                    "&7Messages are not allowed to be longer than &e" + maxmsg + "&7 arguments!"));
                        }
                    } else {
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&l! &7Please provide a &emessage&7!"));
                        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&b&lUsage: &7/message [Player] [&eMessage&7]"));
                    }
                } else {player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&c&lSorry! &7This player is not online!"));}
            }

            return true;
        }

        return false;
    }
}
