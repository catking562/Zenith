package taewookim.zenith.skills;

import net.minecraft.world.level.World;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import taewookim.zenith.skills.Entity.StarfuryEntity;

public class Starfury extends Skill {
    public Starfury(Player p) {
        super(p);
    }

    @Override
    public void Update() {
        World w = ((CraftWorld)p.getWorld()).getHandle();
        StarfuryEntity en = new StarfuryEntity(w, p.getTargetBlock(null, 50).getLocation(), p);
        w.addFreshEntity(en, CreatureSpawnEvent.SpawnReason.COMMAND);
        isEnd = true;
    }
}
