package taewookim.zenith.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import taewookim.zenith.ZenithPlugin;

public class offplugin implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Bukkit.getPluginManager().disablePlugin(ZenithPlugin.getPlugin(ZenithPlugin.class));
        return true;
    }
}
