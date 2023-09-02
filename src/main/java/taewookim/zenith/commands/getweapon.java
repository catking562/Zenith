package taewookim.zenith.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import taewookim.zenith.weapons.Weapon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class getweapon implements CommandExecutor, TabCompleter {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length==1&&commandSender instanceof Player p) {
            Weapon w = Weapon.valueOf(strings[0]);
            p.getInventory().addItem(w.getItem());
        }
        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] strings) {
        if(strings.length==1) {
            ArrayList<String> list = new ArrayList<>();
            for(Weapon wea : Weapon.values()) {
                list.add(wea.toString());
            }
            return list;
        }
        return Arrays.asList("");
    }
}
