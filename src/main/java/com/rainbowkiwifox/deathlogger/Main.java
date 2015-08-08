package com.rainbowkiwifox.deathlogger;

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
    public void handlerDeaths(PlayerDeathEvent e) {
        Player victim = e.getEntity();
        Player agressor = victim.getKiller();
        Location victimLocation = victim.getLocation();
        
        int vlX = victimLocation.getBlockX();
        int vlY = victimLocation.getBlockY(); 
        int vlZ = victimLocation.getBlockZ();
        String vlW = victimLocation.getWorld().getName();
        
        String vLastLocation = "(" + vlW + " " + vlX + "," + vlY + "," + vlZ + ")";
        
        Notifer n = new Notifer();
        try {
        n.notify(victim, agressor.getDisplayName(), vLastLocation);
        } catch (Exception ex) {
        String none = "&7не руками человека";
        n.notify(victim, none, vLastLocation);
        }
    }
}
