package com.rainbowkiwifox.deathlogger;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener {
    ConsoleCommandSender console = Bukkit.getConsoleSender();
    Notifer n = new Notifer();
    
    public void register() {
        Bukkit.getPluginManager().registerEvents(this, Main.instance);
    }
    
    //убираем ненужные сообщения о входе и выходе на сервер
    @EventHandler(priority = EventPriority.HIGHEST)
    public void handleJoin(PlayerJoinEvent e) {
        e.setJoinMessage(null);
    }
    @EventHandler(priority = EventPriority.HIGHEST)
    public void handleQuit(PlayerQuitEvent e) {
        e.setQuitMessage(null);
    }

    //определяем местоположение убитого и отправляем сообщение о смерти в лог
    @EventHandler
    public void handlerDeaths(PlayerDeathEvent e) {
        Player victim = e.getEntity(); //получаем убитого
        Location victimLocation = victim.getLocation(); //нужно для получение местоположения
        
        int vlX = victimLocation.getBlockX();//получаем X убитого
        int vlY = victimLocation.getBlockY();//получаем Y убитого 
        int vlZ = victimLocation.getBlockZ();//получаем Z убитого
        String vlW = victimLocation.getWorld().getName();//получаем мир убитого
        
        String vLastLocation = "(" + vlW + " " + vlX + "," + vlY + "," + vlZ + ")";//итоговое местоположение убитого
        String deathMessage = e.getDeathMessage();//получаем сообщение о смерти
        
        n.notify(victim, deathMessage, vLastLocation); //оповещаем, собстна
        
        e.setDeathMessage(null); //убираем сообщение о смерти
    }
}
