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
import taewookim.zenith.skills.Entity.NightEntity;
import taewookim.zenith.skills.Entity.TrueExcaliburEntity;
import taewookim.zenith.skills.Entity.TrueNightEntity;
import taewookim.zenith.weapons.Weapon;

public class Night extends Skill {
    public Night(Player p) {
        super(p);
    }

    @Override
    public void Update() {
        World w = ((CraftWorld)p.getWorld()).getHandle();
        TrueNightEntity en1 = new TrueNightEntity(w, p.getLocation(), p);
        w.addFreshEntity(en1, CreatureSpawnEvent.SpawnReason.COMMAND);
        p.getWorld().playSound(p, Sound.ENTITY_WITHER_SHOOT, 1, 2);
        for(Entity en : p.getWorld().getNearbyEntities(BoundingBox.of(p.getLocation(), 12, 3, 12))) {
            if(!en.equals(p)&&en instanceof LivingEntity le) {
                le.damage(Weapon.NIGHTS_EDGE.getDamge(), p);
                le.getWorld().playSound(le, Sound.ENTITY_PLAYER_ATTACK_CRIT, 5, 1);
                le.getWorld().spawnParticle(Particle.CRIT, le.getLocation(), 30, 0.5, 0.5, 0.5, 0);
            }
        }
        NightEntity en2 = new NightEntity(w, p.getLocation(), p);
        w.addFreshEntity(en2, CreatureSpawnEvent.SpawnReason.COMMAND);
        isEnd = true;
    }
}
