package taewookim.zenith.Math;

import org.bukkit.Location;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.Map;

public class YP {

    public static Map<TwoKey, Vector> rot = new HashMap<>();

    {
        for(int y = 0; y<360; y++) {
            for(int p = 0; p<360; p++) {
                TwoKey k = new TwoKey<>(y, p);
                rot.put(k, new Vector(-Math.sin(y)*Math.sin(p), -Math.sin(p), Math.cos(y)*Math.sin(p)));
            }
        }
    }

}
