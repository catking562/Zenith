package taewookim.zenith.skills;

import net.minecraft.world.level.World;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import taewookim.zenith.skills.Entity.EnchantedEntity;
import taewookim.zenith.skills.Entity.SeedEntity;

public class Seed extends Skill {
    public Seed(Player p) {
        super(p);
    }

    @Override
    public void Update() {
        World w = ((CraftWorld)p.getWorld()).getHandle();
        SeedEntity en = new SeedEntity(w, p.getLocation().add(0, 1.75, 0), p);
        w.addFreshEntity(en, CreatureSpawnEvent.SpawnReason.COMMAND);
        isEnd = true;
    }
}
