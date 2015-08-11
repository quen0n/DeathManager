package com.rainbowkiwifox.deathlogger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Notifer {
    ConsoleCommandSender console = Bukkit.getConsoleSender();
    private Player v;
    private String dm;
    private String l;
    
    public void notify(Player victim, String deathMessage, String location) {
        this.v = victim;
        this.dm = deathMessage;
        this.l = location;
        
        console.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[DeathLogger] &c" + dm + " &b" + l));
        
        if(v.hasPermission("deathlogger.notice")) {
            v.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[DeathLogger] &c" + dm + " &b" + l));
        }   
    }
}