package taewookim.zenith.skills.Entity;

import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.World;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;
import taewookim.zenith.ZenithPlugin;
import taewookim.zenith.weapons.Weapon;

import java.util.Random;

public class SeedEntity extends Display.ItemDisplay {

    Player p;
    Vector v;
    int i = 0;

    public SeedEntity(World world, Location loc, Player p) {
        super(EntityTypes.ae, world);
        this.getBukkitEntity().teleport(loc);
        ((org.bukkit.entity.ItemDisplay) this.getBukkitEntity()).setItemStack(new ItemStack(Material.BROWN_DYE));
        this.p = p;
        ZenithPlugin.entities.add(this.getBukkitEntity());
        v = p.getLocation().getDirection().multiply(0.3);
    }

    Random r = new Random();

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
        if(i>=20||!this.getBukkitEntity().getLocation().getBlock().isPassable()) {
            explo = true;
        }
        if(explo) {
            for(Entity en : this.getBukkitEntity().getWorld().getNearbyEntities(this.getBukkitEntity().getLocation(), 3, 3, 3)) {
                if(!en.equals(p)&&en instanceof LivingEntity le) {
                    le.damage(Weapon.BEE_KEEPER.getDamge(), p);
                }
            }
            for(int j = 0;j<20;j++) {
                SEntity en = new SEntity(dI(), this.getBukkitEntity().getLocation().add(0, 1, 0), new Vector(r.nextDouble()-0.5, r.nextDouble()-0.5, r.nextDouble()-0.5), p);
                dI().addFreshEntity(en, CreatureSpawnEvent.SpawnReason.COMMAND);
            }
            this.getBukkitEntity().getWorld().createExplosion(this.getBukkitEntity().getLocation(), 0);
            ZenithPlugin.entities.remove(this.getBukkitEntity());
            this.getBukkitEntity().remove();
        }
    }

    @Override
    public boolean f_() {
        return super.f_();
    }
}
