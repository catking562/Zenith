package taewookim.zenith.skills.Entity;

import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.World;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.BoundingBox;
import taewookim.zenith.ZenithPlugin;
import taewookim.zenith.weapons.Weapon;

import java.util.Random;

public class StarfuryEntity extends Display.ItemDisplay {

    int i = 0;
    Player p;

    public StarfuryEntity(World world, Location loc, Player p) {
        super(EntityTypes.ae, world);
        this.getBukkitEntity().teleport(loc.add(0, 30, 0));
        ((org.bukkit.entity.ItemDisplay) this.getBukkitEntity()).setItemStack(new ItemStack(Material.NETHER_STAR));
        this.p = p;
        ZenithPlugin.entities.add(this.getBukkitEntity());
    }

    Random r = new Random();

    @Override
    public void postTick() {
        super.postTick();
        i++;
        this.getBukkitEntity().teleport(this.getBukkitEntity().getLocation().add(0, -3, 0));
        this.getBukkitEntity().getWorld().spawnParticle(Particle.COMPOSTER, this.getBukkitEntity().getLocation(), 10, 0.5, 1.5, 0.5, 0);
        if(i>=10) {
            ZenithPlugin.entities.remove(this.getBukkitEntity());
            this.getBukkitEntity().remove();
            for(Entity en : this.getBukkitEntity().getWorld().getNearbyEntities(BoundingBox.of(this.getBukkitEntity().getLocation(), 2, 2, 2))) {
                if(!en.equals(p) && en instanceof LivingEntity le) {
                    le.damage(Weapon.STARFURY.getDamge(), p);
                }
            }
            Firework w = (Firework) this.getBukkitEntity().getWorld().spawnEntity(this.getBukkitEntity().getLocation().add(0, 1, 0), EntityType.FIREWORK);
            FireworkMeta m = w.getFireworkMeta();
            m.setPower(0);
            m.addEffect(FireworkEffect.builder().with(FireworkEffect.Type.STAR).withColor(Color.fromARGB(255, r.nextInt(255), r.nextInt(255), r.nextInt(255))).build());
            w.setFireworkMeta(m);
            w.detonate();
        }
    }

    @Override
    public boolean f_() {
        return super.f_();
    }
}
