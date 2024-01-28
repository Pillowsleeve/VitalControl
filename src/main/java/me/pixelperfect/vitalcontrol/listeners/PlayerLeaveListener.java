package me.pixelperfect.vitalcontrol.listeners;

import me.pixelperfect.vitalcontrol.VitalControl;
import me.pixelperfect.vitalcontrol.files.DataManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerLeaveListener implements Listener {

    public DataManager data;
    public VitalControl plugin;
    public PlayerLeaveListener(VitalControl plugin, DataManager data) {this.plugin = plugin; this.data = data;}

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        if (!(plugin.getConfig().getBoolean("handle-join-messages"))) {
            return;
        }
        Player player = event.getPlayer();
        event.setQuitMessage(ChatColor.translateAlternateColorCodes(
                '&', plugin.getConfig().getString("leave-message") + "")
                .replace("{player}", player.getName()));
    }
}
