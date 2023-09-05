package taewookim.zenith.skills.Entity;

import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.World;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import taewookim.zenith.ZenithPlugin;
import taewookim.zenith.weapons.Weapon;

public class TrueNightEntity extends Display.ItemDisplay {

    org.bukkit.entity.ItemDisplay dis;
    int i = 0;
    Player p;
    Vector v;

    public TrueNightEntity(World world, Location loc, Player p) {
        super(EntityTypes.ae, world);
        this.getBukkitEntity().teleport(loc.add(0, 5, 0));
        ItemStack i = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta m = i.getItemMeta();
        m.setCustomModelData(52);
        i.setItemMeta(m);
        dis = (org.bukkit.entity.ItemDisplay) this.getBukkitEntity();
        Transformation tran = dis.getTransformation();
        tran.getScale().set(7.5,7.5,7.5);
        dis.setTransformation(tran);
        ((org.bukkit.entity.ItemDisplay) this.getBukkitEntity()).setItemStack(i);
        this.p = p;
        ZenithPlugin.entities.add(this.getBukkitEntity());
        v = loc.getDirection().multiply(1);
    }

    @Override
    public void postTick() {
        super.postTick();
        this.getBukkitEntity().teleport(this.getBukkitEntity().getLocation().add(v));
        i++;
        Transformation tran = dis.getTransformation();
        double k = (Math.PI*i)/60.0D;
        tran.getLeftRotation().set(0, (float) Math.sin(k), 0, (float) Math.cos(k));
        dis.setTransformation(tran);
        switch(i) {
            case 3:
            case 6:
            case 9:
            case 12:
            case 15:
            case 18:
            case 20:
                for(Entity en : dI().getWorld().getNearbyEntities(this.getBukkitEntity().getLocation().add(0, -5, 0), 3, 1.5, 3)) {
                    if(!en.equals(p)&&en instanceof LivingEntity le) {
                        le.damage(Weapon.TRUE_NIGHTS_EDGE.getDamge(), p);
                    }
                }
                break;
        }
        if(i>=20) {
            ZenithPlugin.entities.remove(this.getBukkitEntity());
            this.getBukkitEntity().remove();
        }
    }

    @Override
    public boolean f_() {
        return super.f_();
    }
}
