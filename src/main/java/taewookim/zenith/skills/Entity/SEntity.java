package taewookim.zenith.skills.Entity;

import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.World;
import org.bukkit.*;
import org.bukkit.entity.*;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;
import taewookim.zenith.ZenithPlugin;
import taewookim.zenith.weapons.Weapon;

public class SEntity extends Display.ItemDisplay {

    Player p;
    Vector v;
    int i = 0;

    public SEntity(World world, Location loc, Vector v, Player p) {
        super(EntityTypes.ae, world);
        this.getBukkitEntity().teleport(loc);
        ((org.bukkit.entity.ItemDisplay) this.getBukkitEntity()).setItemStack(new ItemStack(Material.IRON_NUGGET));
        this.p = p;
        ZenithPlugin.entities.add(this.getBukkitEntity());
        this.v = v;
    }

    @Override
    public void postTick() {
        super.postTick();
        i++;
        Boolean explo = false;
        this.getBukkitEntity().teleport(this.getBukkitEntity().getLocation().add(v));
        v.add(new Vector(0, -0.01, 0));
        for(Entity en : this.getBukkitEntity().getWorld().getNearbyEntities(this.getBukkitEntity().getLocation(), 1, 1, 1)) {
            if(!en.equals(p)&&en instanceof LivingEntity le) {
                explo = true;
                le.damage(Weapon.BEE_KEEPER.getDamge(), p);
            }
        }
        if(i>=100||!this.getBukkitEntity().getLocation().getBlock().isPassable()) {
            explo = true;
        }
        if(explo) {
            ZenithPlugin.entities.remove(this.getBukkitEntity());
            this.getBukkitEntity().remove();
        }
    }
}
