package me.pixelperfect.vitalcontrol;

import me.pixelperfect.vitalcontrol.commands.*;
import me.pixelperfect.vitalcontrol.files.DataManager;
import me.pixelperfect.vitalcontrol.listeners.PlayerJoinListener;
import me.pixelperfect.vitalcontrol.listeners.PlayerLeaveListener;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class VitalControl extends JavaPlugin {

    public DataManager data = new DataManager(this);
    public String prefix = ChatColor.translateAlternateColorCodes('&', "&3&lVital&8:&bControl &8&l> &r");

    public ArrayList<Player> vanished = new ArrayList<>(); //For Vanish command (If on list, player is vanished)

    @Override
    public void onEnable() {
        // Plugin startup logic
        this.saveDefaultConfig();
        data.saveConfig();
        registerListeners();
        registerCommands();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void registerCommands() {
        this.getCommand("creative").setExecutor(new GameModeCommands(this, data));
        this.getCommand("survival").setExecutor(new GameModeCommands(this, data));
        this.getCommand("spectator").setExecutor(new GameModeCommands(this, data));
        this.getCommand("adventure").setExecutor(new GameModeCommands(this, data));
        this.getCommand("sethome").setExecutor(new SetHomeCommand(this, data));
        this.getCommand("home").setExecutor(new HomeCommand(this, data));
        this.getCommand("delhome").setExecutor(new DeleteHomeCommand(this, data));
        this.getCommand("listhomes").setExecutor(new ListHomesCommand(this, data));
        this.getCommand("vanish").setExecutor(new VanishCommand(this, data));
        this.getCommand("invsee").setExecutor(new InvSeeCommand(this, data));
        this.getCommand("setwarp").setExecutor(new SetWarpCommand(this, data));
        this.getCommand("warp").setExecutor(new WarpCommand(this, data));
        this.getCommand("delwarp").setExecutor(new DeleteWarpCommand(this, data));
        this.getCommand("listwarps").setExecutor(new ListWarpsCommand(this, data));
        this.getCommand("day").setExecutor(new DayCommand(this));
        this.getCommand("noon").setExecutor(new DayCommand(this));
        this.getCommand("night").setExecutor(new DayCommand(this));
        this.getCommand("midnight").setExecutor(new DayCommand(this));
        this.getCommand("tpto").setExecutor(new TpToCommand(this));
        this.getCommand("tphere").setExecutor(new TpHereCommand(this));
        this.getCommand("message").setExecutor(new MessageCommand(this));
        this.getCommand("heal").setExecutor(new HealCommand(this));
        this.getCommand("feed").setExecutor(new HealCommand(this));
        this.getCommand("revive").setExecutor(new HealCommand(this));
    }
    public void registerListeners() {
        this.getServer().getPluginManager().registerEvents(new PlayerJoinListener(this, data), this);
        this.getServer().getPluginManager().registerEvents(new PlayerLeaveListener(this, data), this);
    }
}
