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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Transformation;
import org.bukkit.util.Vector;
import taewookim.zenith.ZenithPlugin;
import taewookim.zenith.weapons.Weapon;

public class GrassEntity extends Display.ItemDisplay {

    org.bukkit.entity.ItemDisplay dis;
    Player p;
    int i = 0;

    public GrassEntity(World world, Location loc, Player p) {
        super(EntityTypes.ae, world);
        loc.setPitch(0);
        this.getBukkitEntity().teleport(loc);
        ItemStack i = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta m = i.getItemMeta();
        m.setCustomModelData(55);
        i.setItemMeta(m);
        dis = (org.bukkit.entity.ItemDisplay) this.getBukkitEntity();
        Transformation tran = dis.getTransformation();
        tran.getScale().set(1,1,1);
        dis.setTransformation(tran);
        dis.setItemStack(i);
        this.p = p;
        ZenithPlugin.entities.add(this.getBukkitEntity());
    }

    @Override
    public void postTick() {
        i++;
        this.getBukkitEntity().getLocation().setPitch(this.getBukkitEntity().getLocation().getPitch()-20);
        this.getBukkitEntity().teleport(this.getBukkitEntity().getLocation().add(this.getBukkitEntity().getLocation().getDirection()));
        for(Entity en : dI().getWorld().getNearbyEntities(BoundingBox.of(this.getBukkitEntity().getLocation(), 1, 1, 1))) {
            if(!en.equals(p)&&en instanceof LivingEntity le) {
                le.damage(Weapon.BLADE_OF_GRASS.getDamge(), p);
                le.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 100, 4));
            }
        }
    }

    @Override
    public boolean f_() {
        return super.f_();
    }
}
