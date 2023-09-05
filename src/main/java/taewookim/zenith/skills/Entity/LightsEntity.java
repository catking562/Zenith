package taewookim.zenith.skills.Entity;

import net.minecraft.world.entity.Display;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.World;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ItemDisplay;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Transformation;
import taewookim.zenith.ZenithPlugin;

public class LightsEntity extends Display.ItemDisplay {

    org.bukkit.entity.ItemDisplay dis;
    Player p;
    int i = 0;

    public LightsEntity(World world, Location loc, Player p) {
        super(EntityTypes.ae, world);
        loc.setPitch(0);
        this.getBukkitEntity().teleport(loc.add(0, 3, 0));
        ItemStack i = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta m = i.getItemMeta();
        m.setCustomModelData(56);
        i.setItemMeta(m);
        dis = (org.bukkit.entity.ItemDisplay) this.getBukkitEntity();
        Transformation tran = dis.getTransformation();
        tran.getScale().set(5,5,5);
        dis.setTransformation(tran);
        dis.setItemStack(i);
        this.p = p;
        ZenithPlugin.entities.add(this.getBukkitEntity());
    }

    @Override
    public void postTick() {
        i++;
        ItemStack i = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta m = i.getItemMeta();
        m.setCustomModelData(56+this.i);
        i.setItemMeta(m);
        dis.setItemStack(i);
        if(this.i>=11) {
            ZenithPlugin.entities.remove(this.getBukkitEntity());
            this.getBukkitEntity().remove();
        }
    }

    @Override
    public boolean f_() {
        return super.f_();
    }
}
