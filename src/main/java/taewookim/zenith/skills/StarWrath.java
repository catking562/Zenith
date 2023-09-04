package taewookim.zenith.skills;

import net.minecraft.world.level.World;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import taewookim.zenith.skills.Entity.StarWrathEntity;
import taewookim.zenith.skills.Entity.StarfuryEntity;

import java.util.Random;

public class StarWrath extends Skill {
    public StarWrath(Player p) {
        super(p);
    }

    Random r = new Random();

    @Override
    public void Update() {
        World w = ((CraftWorld)p.getWorld()).getHandle();
        for(int i = 0; i<3;i++) {
            StarWrathEntity en = new StarWrathEntity(w,p.getLocation().add(r.nextDouble()*10-5, 30, r.nextDouble()*10-5), p.getTargetBlock(null, 50).getLocation(), p);
            w.addFreshEntity(en, CreatureSpawnEvent.SpawnReason.COMMAND);
        }
        isEnd = true;
    }
}
