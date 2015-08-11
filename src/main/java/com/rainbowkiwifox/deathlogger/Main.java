package com.rainbowkiwifox.deathlogger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    ConsoleCommandSender console = Bukkit.getConsoleSender();
    public static Main instance = null;
    
    @Override
    public void onEnable() {
        console.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[DeathLogger] &aПлагин запущен!"));
        instance = this;
        new EventListener().register();
    }
    @Override
    public void onDisable() {
        console.sendMessage(ChatColor.translateAlternateColorCodes('&', "&6[DeathLogger] &aПлагин выключен."));
    }
}
