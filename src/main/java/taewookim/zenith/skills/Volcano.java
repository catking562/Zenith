package taewookim.zenith.skills;

import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.BoundingBox;
import taewookim.zenith.weapons.Weapon;

public class Volcano extends Skill {
    public Volcano(Player p) {
        super(p);
    }

    @Override
    public void Update() {
        p.getWorld().spawnParticle(Particle.FLAME, p.getLocation(), 100, 0, 0, 0 , 1);
        for(Entity en : p.getWorld().getNearbyEntities(BoundingBox.of(p.getLocation(), 3, 3, 3))) {
            if(!en.equals(p)&&en instanceof LivingEntity le) {
                le.damage(Weapon.VOLCANO.getDamge(), p);
                le.setFireTicks(100);
            }
        }
        isEnd = true;
    }
}
