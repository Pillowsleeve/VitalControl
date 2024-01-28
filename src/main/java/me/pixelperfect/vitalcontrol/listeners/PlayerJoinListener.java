package me.pixelperfect.vitalcontrol.listeners;

import me.pixelperfect.vitalcontrol.VitalControl;
import me.pixelperfect.vitalcontrol.files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    public VitalControl plugin;
    public DataManager data;
    public PlayerJoinListener(VitalControl plugin, DataManager data) {this.plugin = plugin; this.data = data;}

    public boolean isFirstJoin(Player player) {
        if (data.getConfig().contains("players." + player.getUniqueId())) {
            return false;
        }
        return true;
    }

    @EventHandler
    public void onJoinVanishCheck(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        for (int i = 0; i < plugin.vanished.size(); i++) {
            player.hidePlayer(plugin, plugin.vanished.get(i));
        }
    }

    @EventHandler
    public void onPlayerJoinMessage(PlayerJoinEvent event) {
        if (!(plugin.getConfig().getBoolean("handle-join-messages"))) {
            return;
        }
        Player player = event.getPlayer();
        if (isFirstJoin(player)) {
            data.getConfig().set("players." + player.getUniqueId() + ".name", player.getName());
            data.saveConfig();
            event.setJoinMessage(ChatColor.translateAlternateColorCodes(
                    '&', plugin.prefix + plugin.getConfig().getString("first-join-message")).replace("{player}", player.getName()));
        } else {
            event.setJoinMessage(ChatColor.translateAlternateColorCodes(
                    '&', plugin.getConfig().getString("join-message") + "")
                    .replace("{player}", player.getName()));
        }
    }

}
