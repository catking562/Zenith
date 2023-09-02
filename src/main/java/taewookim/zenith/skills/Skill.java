package taewookim.zenith.skills;

import org.bukkit.entity.Player;

public class Skill {

    public Boolean isEnd = false;
    Player p;

    public Skill(Player p) {
        this.p = p;
    }

    public void Update() {
        isEnd = true;
    }

}
