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
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import taewookim.zenith.ZenithPlugin;
import taewookim.zenith.weapons.Weapon;

import java.util.Random;

public class InfluxEntity extends Display.ItemDisplay {

    Vector v;
    org.bukkit.entity.ItemDisplay dis;
    Player p;
    int i = 0;
    int a;

    public InfluxEntity(World world, Location loc, Player p, Vector v, int a) {
        super(EntityTypes.ae, world);
        this.getBukkitEntity().teleport(loc);
        ItemStack i = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta m = i.getItemMeta();
        m.setCustomModelData(26);
        i.setItemMeta(m);
        dis = (org.bukkit.entity.ItemDisplay) this.getBukkitEntity();
        dis.setItemStack(i);
        this.p = p;
        this.v = v.multiply(0.3);
        ZenithPlugin.entities.add(this.getBukkitEntity());
        this.a = a;
    }

    Random r = new Random();

    @Override
    public void postTick() {
        super.postTick();
        i++;
        boolean isbroken = false;
        for(int j = 0; j<3;j++) {
            this.getBukkitEntity().teleport(this.getBukkitEntity().getLocation().add(v));
            this.getBukkitEntity().getWorld().spawnParticle(Particle.REDSTONE, this.getBukkitEntity().getLocation(), 1, 0.1, 0.1, 0.1, 0, new Particle.DustOptions(Color.AQUA, 1));
            for(Entity en : this.getBukkitEntity().getWorld().getNearbyEntities(BoundingBox.of(this.getBukkitEntity().getLocation(), 1, 1, 1))) {
                if(!en.equals(p)&&en instanceof LivingEntity le) {
                    le.damage(Weapon.INFLUX_WAVER.getDamge(), p);
                    if(a != 0) {
                        //소환
                        double x = r.nextDouble()*10 - 5;
                        double y = r.nextDouble()*10 - 5;
                        double z = r.nextDouble()*10 - 5;
                        double dis = Math.sqrt(x*x+y*y+z*z);
                        InfluxEntity en1 = new InfluxEntity(dI(), le.getLocation().add(x, y, z), p, new Vector(-x/dis, -y/dis, -z/dis), a-1);
                        dI().addFreshEntity(en1, CreatureSpawnEvent.SpawnReason.COMMAND);
                    }
                    isbroken = true;
                }
            }
        }
        if(i>=40||isbroken) {
            ZenithPlugin.entities.remove(this.getBukkitEntity());
            this.getBukkitEntity().remove();
        }
    }

    @Override
    public boolean f_() {
        return super.f_();
    }
}
