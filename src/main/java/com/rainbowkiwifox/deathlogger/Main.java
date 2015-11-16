package com.rainbowkiwifox.deathlogger;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
    public static Main instance = null;
    protected FileConfiguration config;
    
    @Override
    public void onEnable() {
        instance = this;
        new EventListener().register();
        config = getConfig();
        
        if (getServer().getPluginManager().getPlugin("Essentials") != null && getServer().getPluginManager().getPlugin("Essentials").isEnabled()) {
            getLogger().info("Successfully hooked into Essentials!");
            getLogger().info("Plugin has been enabled!");    
        }
        else {
            getLogger().warning("Failed to hook into Essentials, disabling plugin!");
            getPluginLoader().disablePlugin(this);
        }
    }
    @Override
    public void onDisable() {
        getLogger().info("Plugin has been diasabled.");
    }
}

