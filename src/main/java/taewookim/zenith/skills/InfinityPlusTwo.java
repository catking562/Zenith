package taewookim.zenith.skills;

import net.minecraft.world.level.World;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_20_R1.CraftWorld;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.util.BoundingBox;
import taewookim.zenith.skills.Entity.Zenith;
import taewookim.zenith.weapons.Weapon;

import java.util.Random;

public class InfinityPlusTwo extends Skill {
    public InfinityPlusTwo(Player p) {
        super(p);
    }

    int i = 0;
    Random r = new Random();

    @Override
    public void Update() {
        i++;
        World w = ((CraftWorld)p.getWorld()).getHandle();
        int i1 = 0;
        int i2 = 0;
        int i3 = 0;
        Location loc1 = p.getTargetBlock(null, 50).getLocation();
        Location loc2 = null;
        Location loc3 = null;
        switch(i) {
            case 1:
                if(true) {
                    Zenith en = new Zenith(w, p.getLocation(), loc1, p, i1);
                    w.addFreshEntity(en, CreatureSpawnEvent.SpawnReason.COMMAND);
                }
                break;
            case 4:
                if(true) {
                    Zenith en = new Zenith(w, p.getLocation(), loc1, p, i1);
                    w.addFreshEntity(en, CreatureSpawnEvent.SpawnReason.COMMAND);
                }
                break;
            case 7:
                if(true) {
                    Zenith en = new Zenith(w, p.getLocation(), loc1, p, i1);
                    w.addFreshEntity(en, CreatureSpawnEvent.SpawnReason.COMMAND);
                }
                if(true) {
                    loc2 = p.getTargetBlock(null, 50).getLocation().add(r.nextDouble()*5-2.5D, r.nextDouble()*5-2.5D, r.nextDouble()*5-2.5D);
                    double dis = Double.MAX_VALUE;
                    for(Entity en : p.getWorld().getNearbyEntities(BoundingBox.of(p.getLocation(), 80, 80, 80))) {
                        if(!en.equals(p)&&en instanceof LivingEntity le&&le.getLocation().distance(p.getLocation())<dis) {
                            loc2 = le.getLocation();
                            dis = le.getLocation().distance(p.getLocation());
                        }
                    }
                    Zenith en = new Zenith(w, p.getLocation(), loc2, p, i2);
                    w.addFreshEntity(en, CreatureSpawnEvent.SpawnReason.COMMAND);
                }
                break;
            case 10:
                if(true) {
                    Zenith en = new Zenith(w, p.getLocation(), loc2, p, i2);
                    w.addFreshEntity(en, CreatureSpawnEvent.SpawnReason.COMMAND);
                }
                break;
            case 13:
                if(true) {
                    Zenith en = new Zenith(w, p.getLocation(), loc2, p, i2);
                    w.addFreshEntity(en, CreatureSpawnEvent.SpawnReason.COMMAND);
                }
                if(true) {
                    loc3 = p.getTargetBlock(null, 50).getLocation().add(r.nextDouble()*5-2.5D, r.nextDouble()*5-2.5D, r.nextDouble()*5-2.5D);
                    for(Entity en : p.getWorld().getNearbyEntities(BoundingBox.of(p.getLocation(), 80, 80, 80))) {
                        double dis = Double.MAX_VALUE;
                        if(!en.equals(p)&&en instanceof LivingEntity le&&le.getLocation().distance(p.getLocation())<dis) {
                            loc3 = le.getLocation();
                            dis = le.getLocation().distance(p.getLocation());
                        }
                    }
                    Zenith en = new Zenith(w, p.getLocation(), loc3, p, i3);
                    w.addFreshEntity(en, CreatureSpawnEvent.SpawnReason.COMMAND);
                }
                break;
            case 16:
                if(true) {
                    Zenith en = new Zenith(w, p.getLocation(), loc3, p, i3);
                    w.addFreshEntity(en, CreatureSpawnEvent.SpawnReason.COMMAND);
                }
                break;
            case 19:
                if(true) {
                    Zenith en = new Zenith(w, p.getLocation(), loc3, p, i3);
                    w.addFreshEntity(en, CreatureSpawnEvent.SpawnReason.COMMAND);
                }
                break;
        }
    }
}
