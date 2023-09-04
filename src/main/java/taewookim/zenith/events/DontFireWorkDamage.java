package taewookim.zenith.events;

import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DontFireWorkDamage implements Listener {

    @EventHandler
    public void Fire(EntityDamageByEntityEvent e) {
        if(e.getDamager() instanceof Firework) {
            e.setCancelled(true);
        }
    }
}
