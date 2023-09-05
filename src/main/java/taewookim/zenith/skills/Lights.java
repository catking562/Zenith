package taewookim.zenith.skills;

import net.minecraft.world.level.World;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.util.BoundingBox;
import taewookim.zenith.skills.Entity.LightsEntity;
import taewookim.zenith.skills.Entity.NightEntity;
import taewookim.zenith.skills.Entity.TrueNightEntity;
import taewookim.zenith.weapons.Weapon;

public class Lights extends Skill {
    public Lights(Player p) {
        super(p);
    }

    @Override
    public void Update() {
        World w = ((CraftWorld)p.getWorld()).getHandle();
        LightsEntity en1 = new LightsEntity(w, p.getLocation().add(p.getLocation().getDirection().multiply(2)), p);
        w.addFreshEntity(en1, CreatureSpawnEvent.SpawnReason.COMMAND);
        p.getWorld().playSound(p, Sound.ENTITY_EVOKER_CAST_SPELL, 1, 2);
        for(Entity en : p.getWorld().getNearbyEntities(BoundingBox.of(p.getLocation().add(p.getLocation().getDirection().multiply(2)), 5, 5, 5))) {
            if(!en.equals(p)&&en instanceof LivingEntity le) {
                le.damage(Weapon.LIGHTS_BANE.getDamge(), p);
                le.getWorld().playSound(le, Sound.ENTITY_PLAYER_ATTACK_CRIT, 5, 1);
                le.getWorld().spawnParticle(Particle.CRIT_MAGIC, le.getLocation(), 30, 0.5, 0.5, 0.5, 0);
            }
        }
        isEnd = true;
    }
}
