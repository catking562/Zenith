package taewookim.zenith.skills.Entity;

import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.World;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Transformation;
import taewookim.zenith.ZenithPlugin;

import java.util.Random;

public class NightEntity extends Display.ItemDisplay {

    Player p;
    int i = 0;
    Random r = new Random();
    float yaw = 0;
    double ra = Math.PI/360.0D;
    org.bukkit.entity.ItemDisplay dis;
    Location loc;

    public NightEntity(World world, Location loc, Player p) {
        super(EntityTypes.ae, world);
        loc.setPitch(0);
        this.getBukkitEntity().teleport(loc.add(0, 5, 0));
        ItemStack i = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta m = i.getItemMeta();
        m.setCustomModelData(54);
        i.setItemMeta(m);
        dis = (org.bukkit.entity.ItemDisplay) this.getBukkitEntity();
        Transformation tran = dis.getTransformation();
        tran.getScale().set(10,10,10);
        tran.getLeftRotation().set(0, 0.965f, 0, -0.258f);
        dis.setTransformation(tran);
        dis.setItemStack(i);
        this.p = p;
        yaw =  + 150;
        this.loc = loc.add(0, -3.7, 0);
        loc.setYaw(loc.getYaw()-150);
        ZenithPlugin.entities.add(this.getBukkitEntity());
    }

    @Override
    public void postTick() {
        super.postTick();
        i++;
        for(int j = 0; j<3;j++) {
            yaw -= 12.5f;
            loc.setYaw(loc.getYaw()+12.5f);
            loc.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(loc.getDirection().multiply(3.5)), 10, 0.2, 0.2, 0.2, 0, new Particle.DustOptions(Color.FUCHSIA, 1));
            loc.getWorld().spawnParticle(Particle.REDSTONE, loc.clone().add(loc.getDirection().multiply(1.75)), 50, 1, 0.1, 1, 0, new Particle.DustOptions(Color.PURPLE, 0.5f));
            Transformation tran = dis.getTransformation();
            float angle = (float) (yaw*ra);
            tran.getLeftRotation().set(0,
                    (float) Math.sin(angle),
                    0,
                    (float) Math.cos(angle));
            dis.setTransformation(tran);
        }
        if(i>=12) {
            ZenithPlugin.entities.remove(this.getBukkitEntity());
            this.getBukkitEntity().remove();
        }
    }

    @Override
    public boolean f_() {
        return super.f_();
    }
}
