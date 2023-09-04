package taewookim.zenith.skills.Entity;

import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.World;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;
import taewookim.zenith.ZenithPlugin;
import taewookim.zenith.weapons.Weapon;

import java.util.Random;

public class Catking extends Display.ItemDisplay {

    int i = 0;
    Player p;
    Vector v;

    public Catking(World world, Location loc, Player p) {
        super(EntityTypes.ae, world);
        this.getBukkitEntity().teleport(loc);
        ItemStack i = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta m = i.getItemMeta();
        m.setCustomModelData(28);
        i.setItemMeta(m);
        ((org.bukkit.entity.ItemDisplay) this.getBukkitEntity()).setItemStack(i);
        this.p = p;
        ZenithPlugin.entities.add(this.getBukkitEntity());
        v = p.getLocation().getDirection().multiply(0.3);
    }

    Random r = new Random();

    @Override
    public void postTick() {
        i++;
        this.getBukkitEntity().getWorld().spawnParticle(Particle.REDSTONE, this.getBukkitEntity().getLocation(), 1, 0, -0.2, 0, 0, new Particle.DustOptions(Color.RED, 0.5f));
        this.getBukkitEntity().getWorld().spawnParticle(Particle.REDSTONE, this.getBukkitEntity().getLocation(), 1, 0, -0.1, 0, 0, new Particle.DustOptions(Color.ORANGE, 0.5f));
        this.getBukkitEntity().getWorld().spawnParticle(Particle.REDSTONE, this.getBukkitEntity().getLocation(), 1, 0, 0, 0, 0, new Particle.DustOptions(Color.YELLOW, 0.5f));
        this.getBukkitEntity().getWorld().spawnParticle(Particle.REDSTONE, this.getBukkitEntity().getLocation(), 1, 0, 0.1, 0, 0, new Particle.DustOptions(Color.GREEN, 0.5f));
        this.getBukkitEntity().getWorld().spawnParticle(Particle.REDSTONE, this.getBukkitEntity().getLocation(), 1, 0, 0.2, 0, 0, new Particle.DustOptions(Color.BLUE, 0.5f));
        for(int j = 0; j<3;j++) {
            this.getBukkitEntity().teleport(this.getBukkitEntity().getLocation().add(v));
            if(!this.getBukkitEntity().getLocation().getBlock().isPassable()) {
                //explosion
                for(Entity en : this.getBukkitEntity().getWorld().getNearbyEntities(BoundingBox.of(this.getBukkitEntity().getLocation(), 3, 3, 3))) {
                    if(!en.equals(p)&&en instanceof LivingEntity le) {
                        le.damage(Weapon.MEOWMERE.getDamge()*1.5D, p);
                    }
                }
                Firework w = (Firework) this.getBukkitEntity().getWorld().spawnEntity(this.getBukkitEntity().getLocation().add(0, 1, 0), EntityType.FIREWORK);
                FireworkMeta m = w.getFireworkMeta();
                m.setPower(0);
                m.addEffect(FireworkEffect.builder().with(FireworkEffect.Type.BALL).withColor(Color.fromARGB(255, r.nextInt(255), r.nextInt(255), r.nextInt(255))).build());
                w.setFireworkMeta(m);
                w.detonate();
                //reflect
                double dx = v.getX()-v.getX()-0.5;
                double dy = v.getY()-v.getY()-0.5;
                double dz = v.getZ()-v.getZ()-0.5;
                if(Math.abs(dx)<Math.abs((dy))) {
                    if(Math.abs(dy)<Math.abs(dz)) {
                        //dx<dy<dz
                        v.setZ(v.getZ()*-1.0);
                    }else if(Math.abs(dx)<Math.abs(dz)){
                        //dx<dz<dy
                        v.setY(v.getY()*-1.0);
                    }else {
                        //dz<dx<dy
                        v.setY(v.getY()*-1.0);
                    }
                }else {
                    if(Math.abs(dy)<Math.abs(dz)) {
                        if(Math.abs(dx)<Math.abs(dz)) {
                            //dy<dx<dz
                            v.setZ(v.getZ()*-1.0);
                        }else {
                            //dy<dz<dx
                            v.setX(v.getX()*-1.0);
                        }
                    }else {
                        //dz<dy<dx
                        v.setX(v.getX()*-1.0);
                    }
                }
                Location loc = this.getBukkitEntity().getLocation();
                loc.setDirection(v);
                this.getBukkitEntity().teleport(loc);
            }
        }
        for(Entity en : this.getBukkitEntity().getWorld().getNearbyEntities(BoundingBox.of(this.getBukkitEntity().getLocation(), 1, 1, 1))) {
            if(!en.equals(p)&&en instanceof LivingEntity le) {
                le.damage(Weapon.MEOWMERE.getDamge()*1.5D, p);
            }
        }
        if(i>=40) {
            ZenithPlugin.entities.remove(this.getBukkitEntity());
            this.getBukkitEntity().remove();
        }
    }

    @Override
    public boolean f_() {
        return super.f_();
    }
}
