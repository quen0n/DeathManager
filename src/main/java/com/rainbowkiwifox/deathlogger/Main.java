package com.rainbowkiwifox.deathlogger;

import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    public static final Logger _log = Logger.getLogger("Minecraft");
    
    ConsoleCommandSender console = Bukkit.getConsoleSender();
    
    @Override
    public void onEnable() {
        console.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[DeathLogger] &aПлагин запущен!"));
        Bukkit.getPluginManager().registerEvents(this, this);
    }
    @Override
    public void onDisable() {
        console.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[DeathLogger] &aПлагин выключен."));
    }
    
    @EventHandler
    public void handleDeath(PlayerDeathEvent e) {
        Player v = e.getEntity();
        Player a = v.getKiller();
        
        Location vl = v.getLocation();
        int vlX = vl.getBlockX();
        int vlY = vl.getBlockY();
        int vlZ = vl.getBlockZ();
        
        String vle = " (" + vl.getWorld().getName()+ " " + vlX +"," + vlY + "," + vlZ + ")";
        String deathMessage = v.getDisplayName() + " был убит " + a.getDisplayName() + vle;
        
        console.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[DeathLogger] &c" + deathMessage));
        
        if(v.hasPermission("deathlogger.notice")) {
            v.sendMessage("&6[DeathLogger] &fВы были убиты игроком " + a.getDisplayName() + " " + vle);
       }
    }
}
