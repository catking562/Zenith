package taewookim.zenith.events;

import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import taewookim.zenith.weapons.Weapon;

import java.util.Random;

public class EntityDeath implements Listener {

    Random r = new Random();

    @EventHandler
    public void EntityDeath(EntityDeathEvent e) {
        if(e.getEntity() instanceof Bee&&r.nextDouble()<0.1) {
            e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), Weapon.BEE_KEEPER.getItem());
        }
        if(e.getEntity() instanceof Phantom&&r.nextDouble()<0.1) {
            e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), Weapon.STARFURY.getItem());
        }
        if(e.getEntity() instanceof Enderman&&r.nextDouble()<0.1) {
            e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), Weapon.INFLUX_WAVER.getItem());
        }
        if(e.getEntity() instanceof EnderDragon) {
            switch(r.nextInt(2)) {
                case 0:
                    e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), Weapon.STAR_WRATH.getItem());
                    break;
                case 1:
                    e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), Weapon.MEOWMERE.getItem());
                    break;
            }

        }
        if(e.getEntity() instanceof Bat &&r.nextDouble()<0.1) {
            e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), Weapon.BROKEN_HERO_SWORD.getItem());
        }
        if(e.getEntity() instanceof Evoker &&r.nextDouble()<0.1) {
            e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), Weapon.MURAMASA.getItem());
        }
        if(e.getEntity() instanceof Horse &&r.nextDouble()<0.05) {
            e.getEntity().getWorld().dropItem(e.getEntity().getLocation(), Weapon.THE_HORSEMANS_BLADE.getItem());
        }
    }
}
