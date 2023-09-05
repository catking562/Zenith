package taewookim.zenith.skills;

import net.minecraft.world.level.World;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import taewookim.zenith.skills.Entity.GrassEntity;

public class Grass extends Skill {
    public Grass(Player p) {
        super(p);
    }

    @Override
    public void Update() {
        World w = ((CraftWorld)p.getWorld()).getHandle();
        GrassEntity en = new GrassEntity(w, p.getLocation(), p);
        w.addFreshEntity(en, CreatureSpawnEvent.SpawnReason.COMMAND);
        isEnd = true;
    }
}
