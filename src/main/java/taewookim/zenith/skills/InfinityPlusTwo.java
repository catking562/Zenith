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

    int i1 = 0;
    int i2 = r.nextInt(12)+1;
    int i3 = r.nextInt(12)+1;
    double dd1 = 5;
    double dd2 = r.nextDouble()*4;
    double dd3 = r.nextDouble()*4;
    Location loc1 = p.getTargetBlock(null, 50).getLocation();
    Location loc2 = p.getTargetBlock(null, 25).getLocation().add(r.nextDouble()*10-5D, r.nextDouble()*10-5D, r.nextDouble()*10-5D);;
    Location loc3 = p.getTargetBlock(null, 10).getLocation().add(r.nextDouble()*5-2.5D, r.nextDouble()*5-2.5D, r.nextDouble()*5-2.5D);;

    @Override
    public void Update() {
        i++;
        World w = ((CraftWorld)p.getWorld()).getHandle();
        if(i2==i3) {
            if(i3==1) {
                i3++;
            }else {
                i3--;
            }
        }
        switch(i) {
            case 1:
                if(true) {
                    Zenith en = new Zenith(w, p.getLocation().add(0, 1.5, 0), loc1, p, i1, 5, dd1);
                    w.addFreshEntity(en, CreatureSpawnEvent.SpawnReason.COMMAND);
                }
                break;
            case 4:
                if(true) {
                    Zenith en = new Zenith(w, p.getLocation().add(0, 1.5, 0), loc1, p, i1, 3, dd1);
                    w.addFreshEntity(en, CreatureSpawnEvent.SpawnReason.COMMAND);
                }
                break;
            case 7:
                if(true) {
                    Zenith en = new Zenith(w, p.getLocation().add(0, 1.5, 0), loc1, p, i1, 1, dd1);
                    w.addFreshEntity(en, CreatureSpawnEvent.SpawnReason.COMMAND);
                }
                if(true) {

                    double dis = Double.MAX_VALUE;
                    for(Entity en : p.getWorld().getNearbyEntities(BoundingBox.of(p.getLocation(), 10, 10, 10))) {
                        if(!en.equals(p)&&en instanceof LivingEntity le&&le.getLocation().distance(p.getLocation())<dis) {
                            loc2 = le.getLocation();
                            dis = le.getLocation().distance(p.getLocation());
                        }
                    }
                    Zenith en = new Zenith(w, p.getLocation().add(0, 1.5, 0), loc2, p, i2, 5, dd2);
                    w.addFreshEntity(en, CreatureSpawnEvent.SpawnReason.COMMAND);
                }
                break;
            case 10:
                if(true) {
                    Zenith en = new Zenith(w, p.getLocation().add(0, 1.5, 0), loc2, p, i2, 3, dd2);
                    w.addFreshEntity(en, CreatureSpawnEvent.SpawnReason.COMMAND);
                }
                break;
            case 13:
                if(true) {
                    Zenith en = new Zenith(w, p.getLocation().add(0, 1.5, 0), loc2, p, i2, 1, dd2);
                    w.addFreshEntity(en, CreatureSpawnEvent.SpawnReason.COMMAND);
                }
                if(true) {

                    for(Entity en : p.getWorld().getNearbyEntities(BoundingBox.of(p.getLocation(), 10, 10, 10))) {
                        double dis = Double.MAX_VALUE;
                        if(!en.equals(p)&&en instanceof LivingEntity le&&le.getLocation().distance(p.getLocation())<dis) {
                            loc3 = le.getLocation();
                            dis = le.getLocation().distance(p.getLocation());
                        }
                    }
                    Zenith en = new Zenith(w, p.getLocation().add(0, 1.5, 0), loc3, p, i3, 5, dd3);
                    w.addFreshEntity(en, CreatureSpawnEvent.SpawnReason.COMMAND);
                }
                break;
            case 16:
                if(true) {
                    Zenith en = new Zenith(w, p.getLocation().add(0, 1.5, 0), loc3, p, i3, 3, dd3);
                    w.addFreshEntity(en, CreatureSpawnEvent.SpawnReason.COMMAND);
                }
                break;
            case 19:
                if(true) {
                    Zenith en = new Zenith(w, p.getLocation().add(0, 1.5, 0), loc3, p, i3, 1, dd3);
                    w.addFreshEntity(en, CreatureSpawnEvent.SpawnReason.COMMAND);
                }
                break;
        }
    }
}
