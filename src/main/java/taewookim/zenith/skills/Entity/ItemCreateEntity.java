package taewookim.zenith.skills.Entity;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.inventory.ItemStack;

public class ItemCreateEntity {

    public boolean isEnd = false;
    ItemStack item;
    int i = 0;
    Location loc;

    public ItemCreateEntity(ItemStack i, Location loc) {
        this.item = i;
        this.loc = loc;
    }

    public ItemStack getItem() {
        return item;
    }

    public Location getLocation() {
        return loc;
    }

    public void Update() {
        i++;
        loc.add(0, 0.1, 0);
        loc.getWorld().spawnParticle(Particle.CLOUD, loc, 1, 0, 0, 0, 0);
        if(i>=30) {
            loc.getWorld().dropItem(loc, item);
            isEnd = true;
        }
    }


}
