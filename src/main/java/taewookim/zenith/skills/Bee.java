package taewookim.zenith.skills;

import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BoundingBox;

public class Bee extends Skill {
    public Bee(Player p) {
        super(p);
    }

    @Override
    public void Update() {
        p.getWorld().spawnParticle(Particle.SMOKE_NORMAL, p.getLocation(), 100, 4, 4, 4, 0);
        for(Entity en : p.getWorld().getNearbyEntities(BoundingBox.of(p.getLocation(), 8, 8, 8))) {
            if(!en.equals(p)&&en instanceof LivingEntity le) {
                le.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 2));
            }
        }
        isEnd = true;
    }
}
