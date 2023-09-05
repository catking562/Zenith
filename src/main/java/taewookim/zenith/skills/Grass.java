package taewookim.zenith.skills;

import org.bukkit.entity.Player;

public class Grass extends Skill {
    public Grass(Player p) {
        super(p);
    }

    @Override
    public void Update() {
        isEnd = true;
    }
}
