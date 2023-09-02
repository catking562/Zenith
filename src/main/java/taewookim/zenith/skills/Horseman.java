package taewookim.zenith.skills;

import net.minecraft.world.level.World;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;
import taewookim.zenith.Math.TwoKey;
import taewookim.zenith.Math.YP;
import taewookim.zenith.skills.Entity.HorsemanEntity;
import taewookim.zenith.skills.Entity.Pumpkin;
import taewookim.zenith.weapons.Weapon;

import java.util.Random;

public class Horseman extends Skill {

    public Horseman(Player p) {
        super(p);
    }

    Random r = new Random();

    @Override
    public void Update() {
        World w = ((CraftWorld)p.getWorld()).getHandle();
        HorsemanEntity en1 = new HorsemanEntity(w, p.getLocation(), p);
        w.addFreshEntity(en1, CreatureSpawnEvent.SpawnReason.COMMAND);
        p.getWorld().playSound(p, Sound.ENTITY_WITHER_SHOOT, 1, 2);
        for(Entity en : p.getWorld().getNearbyEntities(BoundingBox.of(p.getLocation(), 6, 3, 6))) {
            if(!en.equals(p)&&en instanceof LivingEntity le) {
                le.damage(Weapon.THE_HORSEMANS_BLADE.getDamge(), p);
                le.getWorld().playSound(le, Sound.ENTITY_PLAYER_ATTACK_CRIT, 5, 1);
                le.getWorld().spawnParticle(Particle.CRIT, le.getLocation(), 30, 0.5, 0.5, 0.5, 0);
                for(int i = 0; i<3; i++) {
                    Pumpkin pump = new Pumpkin(w, le.getLocation().add(r.nextDouble()*10-5, r.nextDouble()*10, r.nextDouble()*10-5), p);
                    w.addFreshEntity(pump, CreatureSpawnEvent.SpawnReason.COMMAND);
                }
            }
        }
        isEnd = true;
    }
}
