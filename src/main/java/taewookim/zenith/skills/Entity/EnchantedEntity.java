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
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import taewookim.zenith.ZenithPlugin;
import taewookim.zenith.weapons.Weapon;

import java.util.List;

public class EnchantedEntity extends Display.ItemDisplay {

    Player p;
    int i = 0;
    Vector v;

    public EnchantedEntity(World world, Location loc, Player p) {
        super(EntityTypes.ae, world);
        this.getBukkitEntity().teleport(loc);
        ItemStack i = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta m = i.getItemMeta();
        m.setCustomModelData(22);
        i.setItemMeta(m);
        ((org.bukkit.entity.ItemDisplay) this.getBukkitEntity()).setItemStack(i);
        this.p = p;
        ZenithPlugin.entities.add(this.getBukkitEntity());
        v = p.getLocation().getDirection().multiply(0.3);
    }

    @Override
    public void postTick() {
        super.postTick();
        i++;
        for(int i = 0; i<3; i++) {
            this.getBukkitEntity().teleport(this.getBukkitEntity().getLocation().add(v));
            this.getBukkitEntity().getWorld().spawnParticle(Particle.COMPOSTER, this.getBukkitEntity().getLocation(), 1, 0.1, 0.1, 0.1, 0);
            List<Entity> list = (List<Entity>) this.getBukkitEntity().getWorld().getNearbyEntities(BoundingBox.of(this.getBukkitEntity().getLocation(), 1, 1, 1));
            Boolean canEnd = false;
            if(list.size()>=1) {
                for(Entity en : list) {
                    if(!en.equals(p)&&en instanceof LivingEntity le) {
                        le.damage(Weapon.STARFURY.getDamge(), p);
                        canEnd = true;
                    }
                }
            }
            if(!this.getBukkitEntity().getLocation().getBlock().isPassable()) {
                canEnd=true;
            }
            if(canEnd) {
                ZenithPlugin.entities.remove(this.getBukkitEntity());
                this.getBukkitEntity().getWorld().spawnParticle(Particle.COMPOSTER, this.getBukkitEntity().getLocation(), 20, 1, 1, 1, 0);
                this.getBukkitEntity().getWorld().playSound(this.getBukkitEntity().getLocation().add(0, 1, 0), Sound.ENTITY_FIREWORK_ROCKET_LARGE_BLAST, 1, 2);
                this.getBukkitEntity().remove();
            }
        }
        if(i>=50) {
            this.getBukkitEntity().remove();
        }
    }

    @Override
    public boolean f_() {
        return super.f_();
    }
}
