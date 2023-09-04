package taewookim.zenith.skills.Entity;

import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.World;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import taewookim.zenith.ZenithPlugin;
import taewookim.zenith.weapons.Weapon;

public class Zenith extends Display.ItemDisplay {

    float time = -1;
    Player p;
    Location loc;
    Vector v;
    double distan;
    org.bukkit.entity.ItemDisplay dis;
    double a;
    double b;

    public Zenith(World world, Location loc, Location targetloc, Player p, int num) {
        super(EntityTypes.ae, world);
        this.getBukkitEntity().teleport(p.getLocation().add(p.getLocation().getDirection().multiply(2)));
        ItemStack i = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta m = i.getItemMeta();
        m.setCustomModelData(num + 29);
        i.setItemMeta(m);
        dis = (org.bukkit.entity.ItemDisplay) this.getBukkitEntity();
        Transformation tran = dis.getTransformation();
        tran.getScale().set(3,3,3);
        dis.setTransformation(tran);
        dis.setItemStack(i);
        this.p = p;
        this.loc = loc;
        distan = loc.distance(targetloc);
        v = new Vector(targetloc.getX()-loc.getX(), targetloc.getY()-loc.getY(), targetloc.getZ()-loc.getZ());
        v.multiply(1.0D/distan);
        a=Math.sqrt(distan+2);
        b=distan+2;
        ZenithPlugin.entities.add(this.getBukkitEntity());
    }

    public double getR(double t) {
        double aa = (2*(t-1-(distan/2.0d)))/b;
        return (a/2.0d)*Math.sqrt(1-aa*aa);
    }

    public Location getLocation(double t) {
        if(t>=distan*2+4) {
            t = distan*2+4;
        }
        if(t<=distan+2) {
            return loc.clone().add(-v.getZ()*getR(t) + v.getX()*t, v.getY()*t, v.getX()*getR(t) + v.getZ()*t);
        }else {
            t = (distan+2)*2-t;
            return loc.clone().add(v.getZ()*getR(t) + v.getX()*t, v.getY()*t, -v.getX()*getR(t) + v.getZ()*t);
        }
    }

    public void SpawnParticle(Color color) {
        for(Player p : dI().getWorld().getPlayers()) {
            p.spawnParticle(Particle.REDSTONE, this.getBukkitEntity().getLocation(), 10, 0.25, 0.25, 0.25, 0, new Particle.DustOptions(color, 1));
        }
    }

    @Override
    public void postTick() {
        for(int j =0;j<5;j++) {
           time+=distan/25.0D;
           Location ll = getLocation(time);
           ll.setDirection(getLocation(time+1.0D/3.0D).add(getLocation(time).multiply(-1)).toVector());
           try{
               this.getBukkitEntity().teleport(ll);
           }catch(Exception e) {
           }
           for(Entity en : p.getWorld().getNearbyEntities(BoundingBox.of(this.getBukkitEntity().getLocation(), 3, 3, 3))) {
                if(!en.equals(p)&&en instanceof LivingEntity le) {
                    le.damage(Weapon.ZENITH.getDamge(), p);
                }
           }
           SpawnParticle(Color.AQUA);
        }
        if(time>=distan*2+4) {
            ZenithPlugin.entities.remove(this.getBukkitEntity());
            this.getBukkitEntity().remove();
        }
    }

    @Override
    public boolean f_() {
        return super.f_();
    }
}
