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
import taewookim.zenith.skills.Entity.HorsemanEntity;
import taewookim.zenith.skills.Entity.Pumpkin;
import taewookim.zenith.skills.Entity.TrueExcaliburEntity;
import taewookim.zenith.weapons.Weapon;

import java.util.Random;

public class True_Excalibur extends Skill{
    public True_Excalibur(Player p) {
        super(p);
    }

    @Override
    public void Update() {
        World w = ((CraftWorld)p.getWorld()).getHandle();
        TrueExcaliburEntity en1 = new TrueExcaliburEntity(w, p.getLocation(), p);
        w.addFreshEntity(en1, CreatureSpawnEvent.SpawnReason.COMMAND);
        p.getWorld().playSound(p, Sound.ENTITY_WITHER_SHOOT, 1, 2);
        for(Entity en : p.getWorld().getNearbyEntities(BoundingBox.of(p.getLocation(), 10, 3, 10))) {
            if(!en.equals(p)&&en instanceof LivingEntity le) {
                le.damage(Weapon.TRUE_EXCALIBUR.getDamge(), p);
                le.getWorld().playSound(le, Sound.ENTITY_PLAYER_ATTACK_CRIT, 5, 1);
                le.getWorld().spawnParticle(Particle.CRIT, le.getLocation(), 30, 0.5, 0.5, 0.5, 0);
            }
        }
        isEnd = true;
    }
}
