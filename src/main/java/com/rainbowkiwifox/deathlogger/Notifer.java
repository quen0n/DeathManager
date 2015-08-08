package com.rainbowkiwifox.deathlogger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class Notifer {
    ConsoleCommandSender console = Bukkit.getConsoleSender();
    private Player v;
    private String a;
    private String l;
    
    public void notify(Player victim, String agressor, String location) {
        this.v = victim;
        this.a = agressor;
        this.l = location;
        
        console.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[DeathLogger] &c" + v.getDisplayName() + " был убит " + a + " &b" + l));
        
        if(v.hasPermission("deathlogger.notice")) {
            v.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[DeathLogger] &fВы были убиты " + a + " &b" + l));
        }   
    }
}