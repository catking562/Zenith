package taewookim.zenith.skills;

import org.bukkit.Color;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BoundingBox;
import taewookim.zenith.weapons.Weapon;

public class Blood extends Skill {
    public Blood(Player p) {
        super(p);
    }

    @Override
    public void Update() {
        p.getWorld().spawnParticle(Particle.REDSTONE, p.getLocation(), 20, 2.5, 2.5, 2.5, 0, new Particle.DustOptions(Color.RED, 1));
        for(Entity en : p.getWorld().getNearbyEntities(BoundingBox.of(p.getLocation(), 5, 5, 5))) {
            if(!en.equals(p)&&en instanceof LivingEntity le) {
                le.damage(Weapon.BLOOD_BUTCHERER.getDamge(), p);
                le.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 100, 4));
            }
        }
        isEnd = true;
    }
}
