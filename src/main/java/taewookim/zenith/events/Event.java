package taewookim.zenith.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import taewookim.zenith.ZenithPlugin;
import taewookim.zenith.skills.Skill;
import taewookim.zenith.weapons.Weapon;

import java.lang.reflect.Method;

public class Event implements Listener {

    public void ActiveSkill(Player p, Weapon type) {
        if(!p.hasCooldown(Material.DIAMOND_SWORD)) {
            Class<? extends Skill> skillclass = type.getClasses();
            p.setCooldown(Material.DIAMOND_SWORD, type.getCool());
            try {
                Skill skill = skillclass.getDeclaredConstructor(new Class[]{Player.class}).newInstance(p);
                ZenithPlugin.AddSkill(skill);
            }catch(Exception e) {
            }

        }
    }

    @EventHandler
    public void Inter(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if((e.getAction().equals(Action.LEFT_CLICK_AIR)||e.getAction().equals(Action.LEFT_CLICK_BLOCK))&&p.getItemInHand()!=null&&p.getItemInHand().hasItemMeta()) {
            PersistentDataContainer container = p.getItemInHand().getItemMeta().getPersistentDataContainer();
            if(container.has(ZenithPlugin.skillkey, PersistentDataType.STRING)) {
                ActiveSkill(p, Weapon.valueOf(container.get(ZenithPlugin.skillkey, PersistentDataType.STRING)));
            }
        }
    }

    @EventHandler
    public void Damage(EntityDamageByEntityEvent e) {
        if(!e.getCause().equals(EntityDamageEvent.DamageCause.CUSTOM)) {
            return;
        }
        if(e.getDamager() instanceof Player p&&p.getItemInHand()!=null&&p.getItemInHand().hasItemMeta()) {
            PersistentDataContainer container = p.getItemInHand().getItemMeta().getPersistentDataContainer();
            if(container.has(ZenithPlugin.skillkey, PersistentDataType.STRING)) {
                ActiveSkill(p, Weapon.valueOf(container.get(ZenithPlugin.skillkey, PersistentDataType.STRING)));
            }
        }
    }

}
