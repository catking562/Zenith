package taewookim.zenith.skills.Entity;

import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.World;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;
import taewookim.zenith.ZenithPlugin;
import taewookim.zenith.weapons.Weapon;

import java.util.Random;

public class StarWrathEntity extends Display.ItemDisplay {

    int i = 0;
    Player p;
    Vector v;
    double dis;

    public StarWrathEntity(World world, Location loc, Location targetloc, Player p) {
        super(EntityTypes.ae, world);
        this.getBukkitEntity().teleport(loc);
        ItemStack i = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta m = i.getItemMeta();
        m.setCustomModelData(27);
        i.setItemMeta(m);
        dis = loc.distance(targetloc);
        ((org.bukkit.entity.ItemDisplay) this.getBukkitEntity()).setItemStack(i);
        this.p = p;
        this.v = targetloc.clone().add(loc.clone().multiply(-1)).toVector().multiply(1.0d/(3.0d*dis));
        ZenithPlugin.entities.add(this.getBukkitEntity());
    }

    Random r = new Random();

    @Override
    public void postTick() {
        super.postTick();
        i++;
        for(int j = 0; j<3;j++) {
            this.getBukkitEntity().teleport(this.getBukkitEntity().getLocation().add(v));
        }
        if(i>=dis) {
            ZenithPlugin.entities.remove(this.getBukkitEntity());
            this.getBukkitEntity().remove();
            for(Entity en : this.getBukkitEntity().getWorld().getNearbyEntities(BoundingBox.of(this.getBukkitEntity().getLocation(), 2, 2, 2))) {
                if(!en.equals(p) && en instanceof LivingEntity le) {
                    le.damage(Weapon.STAR_WRATH.getDamge(), p);
                }
            }
            Firework w = (Firework) this.getBukkitEntity().getWorld().spawnEntity(this.getBukkitEntity().getLocation().add(0, 1, 0), EntityType.FIREWORK);
            FireworkMeta m = w.getFireworkMeta();
            m.setPower(0);
            m.addEffect(FireworkEffect.builder().with(FireworkEffect.Type.STAR).withTrail().withFlicker().withColor(Color.fromARGB(255, r.nextInt(255), r.nextInt(255), r.nextInt(255))).build());
            w.setFireworkMeta(m);
            w.detonate();
        }
    }

    @Override
    public boolean f_() {
        return super.f_();
    }
}
