package com.rainbowkiwifox.deathlogger;

import com.earth2me.essentials.Essentials;
import org.bukkit.Bukkit;
import static org.bukkit.Bukkit.getLogger;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import static org.bukkit.entity.EntityType.PLAYER;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class EventListener implements Listener {
    Notifer n = new Notifer();
    Essentials ess = (Essentials) Bukkit.getPluginManager().getPlugin("Essentials");
    
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
        Player killer; //убийца
        EntityType ktype;//тип убийцы
        boolean kv = false; //состояние ваниша убийцы
        boolean kf = false; //состояние полёта убийцы
        Location victimLocation = victim.getLocation(); //нужно для получения местоположения
                        
        int vlX = victimLocation.getBlockX();//получаем X убитого
        int vlY = victimLocation.getBlockY();//получаем Y убитого 
        int vlZ = victimLocation.getBlockZ();//получаем Z убитого
        String vlW = victimLocation.getWorld().getName();//получаем мир убитого
        
        String vLastLocation = "(" + vlW + " " + vlX + "," + vlY + "," + vlZ + ")";//итоговое местоположение убитого
        String deathMessage = e.getDeathMessage();//получаем сообщение о смерти
        
        try {
            ktype = victim.getLastDamageCause().getEntityType();//получаем тип убийцы;
            killer = victim.getKiller();  //не всегда убийцей может быть игрок
        } catch(Exception ex) {           //и чтобы не получить эксепшен
            killer = null;                //нужен этот костыль
            ktype = null;
        }
        
                            
        if (killer != null & ktype == PLAYER) { //если убийца игрок, получаем:
            kv = ess.getUser(killer).isVanished();//состояние ваниша убийцы из Essentials
            kf = ess.getUser(killer).getBase().getAllowFlight();//состояние полёта убийцы из Essentials
        }
        
        //оповещаем, собстна
        if (deathMessage != null) {
            n.notify(victim, deathMessage, vLastLocation, kv, kf);
        }
        else {
            getLogger().info("[DeathLogger] Сообщение о смерти игрока " + victim.getName() + " было убрано, т.к. оно было пустым.");
        }
        
        e.setDeathMessage(null); //убираем сообщение о смерти
    }
}
