package taewookim.zenith.skills.Entity;

import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.World;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import taewookim.zenith.ZenithPlugin;
import taewookim.zenith.weapons.Weapon;

import java.util.List;
import java.util.Random;

public class Pumpkin extends Display.ItemDisplay {

    Location target;
    Vector v;
    double dis = 0;
    Player p;
    Random r = new Random();
    int i = 0;
    Boolean remove = false;

    public Pumpkin(World world, Location loc, Player p) {
        super(EntityTypes.ae, world);
        List<Entity> list = (List<Entity>) world.getWorld().getNearbyEntities(loc, 50, 50, 50);
        list.remove(this.getBukkitEntity());
        list.remove(p);
        if(list.size()==0) {
            remove=true;
            return;
        }
        target = list.get(r.nextInt(list.size())).getLocation();
        dis = target.distance(loc);
        v = target.clone().add(loc.clone().multiply(-1)).toVector().multiply(1.0/(dis*3.0));
        this.getBukkitEntity().teleport(loc);
        org.bukkit.entity.ItemDisplay dis = (org.bukkit.entity.ItemDisplay) this.getBukkitEntity();
        dis.setItemStack(new ItemStack(Material.CARVED_PUMPKIN));
        Transformation tran = dis.getTransformation();
        tran.getScale().set(0.25, 0.25, 0.25);
        dis.setTransformation(tran);
        this.p = p;
        ZenithPlugin.entities.add(this.getBukkitEntity());
    }

    @Override
    public void postTick() {
        super.postTick();
        if(remove||v==null||dis<=1) {
            ZenithPlugin.entities.remove(this.getBukkitEntity());
            this.getBukkitEntity().remove();
            return;
        }
        i++;
        for(int j = 0; j<3; j++) {
            this.getBukkitEntity().teleport(this.getBukkitEntity().getLocation().add(v));
            this.getBukkitEntity().getWorld().spawnParticle(Particle.FLAME, this.getBukkitEntity().getLocation(), 1, 0, 0, 0 , 0.05);
        }
        if(i>=dis) {
            for(Entity en : this.getBukkitEntity().getWorld().getNearbyEntities(this.getBukkitEntity().getLocation(), 3, 3, 3)) {
                if(!en.equals(p)&&en instanceof LivingEntity le) {
                    le.damage(Weapon.THE_HORSEMANS_BLADE.getDamge(), p);
                }
            }
            this.getBukkitEntity().getWorld().spawnParticle(Particle.FLASH, this.getBukkitEntity().getLocation(), 1, 0, 0, 0, 0);
            this.getBukkitEntity().getWorld().playSound(this.getBukkitEntity(), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 5, 1);
            this.getBukkitEntity().getWorld().spawnParticle(Particle.FLAME, this.getBukkitEntity().getLocation(), 1, 0, 0, 0, 0.1);
            ZenithPlugin.entities.remove(this.getBukkitEntity());
            this.getBukkitEntity().remove();
        }
    }
}
