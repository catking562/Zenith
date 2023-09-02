package taewookim.zenith.skills;

import net.minecraft.world.level.World;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Transformation;
import taewookim.zenith.ZenithPlugin;
import taewookim.zenith.skills.Entity.HorsemanEntity;
import taewookim.zenith.skills.Entity.Pumpkin;
import taewookim.zenith.skills.Entity.TerraEntity;
import taewookim.zenith.skills.Entity.TerraProejctile;
import taewookim.zenith.weapons.Weapon;

public class Terra extends Skill {
    public Terra(Player p) {
        super(p);
    }

    @Override
    public void Update() {
        super.Update();
        World w = ((CraftWorld)p.getWorld()).getHandle();
        TerraEntity en1 = new TerraEntity(w, p.getLocation(), p);
        w.addFreshEntity(en1, CreatureSpawnEvent.SpawnReason.COMMAND);
        TerraProejctile en2 = new TerraProejctile(w, p.getLocation(), p);
        w.addFreshEntity(en2, CreatureSpawnEvent.SpawnReason.COMMAND);
        p.getWorld().playSound(p, Sound.ENTITY_WITHER_SHOOT, 1, 2);
        for(Entity en : p.getWorld().getNearbyEntities(BoundingBox.of(p.getLocation(), 6, 3, 6))) {
            if(!en.equals(p)&&en instanceof LivingEntity le) {
                le.damage(Weapon.TERRA_BLADE.getDamge(), p);
                le.getWorld().playSound(le, Sound.ENTITY_PLAYER_ATTACK_CRIT, 5, 1);
                le.getWorld().spawnParticle(Particle.CRIT, le.getLocation(), 30, 0.5, 0.5, 0.5, 0);
            }
        }
        isEnd = true;
    }
}
