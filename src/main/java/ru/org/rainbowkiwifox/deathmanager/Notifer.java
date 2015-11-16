package ru.org.rainbowkiwifox.deathmanager;

import static org.bukkit.Bukkit.getLogger;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Notifer {
    private Player v;
    private String dm;
    private String l;
    private boolean kv;
    private boolean kf;
    
    public void notify(Player victim, String deathMessage, String location, boolean kv, boolean kf) {
        this.v = victim;
        this.dm = deathMessage;
        this.l = location;
        this.kv = kv;
        this.kf = kf;
        
        getLogger().info("[DeathLogger] " + dm + " " + l + ", vanish: " + kv + ", fly: " + kf + ".");
        
        if(v.hasPermission("deathlogger.notice")) {
            v.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[DeathLogger] &c" + dm + " &b" + l + "."));
        }   
    }
}