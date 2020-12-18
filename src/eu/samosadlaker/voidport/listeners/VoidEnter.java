package eu.samosadlaker.voidport.listeners;

import eu.samosadlaker.voidport.core.Colors;
import eu.samosadlaker.voidport.core.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerTeleportEvent;

public class VoidEnter implements Listener {

    private static Main plugin = Main.getPlugin();
    FileConfiguration config = plugin.getConfig();
    String prefix = config.getString("prefix") + " &f» &3";

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onVoidEnter(EntityDamageEvent e){
        if(!(e.getEntity() instanceof Player)){
            return;
        }
        Player p = (Player) e.getEntity();

        if(!(p.getWorld().equals(Bukkit.getWorld(config.getString("world"))))){
            return;
        }

        if(e.getCause().equals(EntityDamageEvent.DamageCause.VOID)){
            e.setCancelled(true);
            p.setFallDistance(0);
            if(e.getCause().equals(EntityDamageEvent.DamageCause.FALL)){
                e.setCancelled(true);
            }
            p.teleport(Bukkit.getWorld(config.getString("world")).getSpawnLocation(), PlayerTeleportEvent.TeleportCause.UNKNOWN);
            p.sendMessage(Colors.formatColor(prefix + config.getString("teleport-message")));

        }


    }
}
