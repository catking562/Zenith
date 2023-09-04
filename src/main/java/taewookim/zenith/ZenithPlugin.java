package taewookim.zenith;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import taewookim.zenith.commands.getweapon;
import taewookim.zenith.commands.offplugin;
import taewookim.zenith.events.DontFireWorkDamage;
import taewookim.zenith.events.Event;
import taewookim.zenith.skills.Skill;

import java.util.ArrayList;

public class ZenithPlugin extends JavaPlugin {

    public static ArrayList<Entity> entities = new ArrayList<>();
    public static NamespacedKey skillkey;
    public static ArrayList<Skill> UpdatingSkill = new ArrayList<>();
    public static ArrayList<Skill> AddingSkill = new ArrayList<>();
    public static Boolean isUpdating = false;

    {
        skillkey = new NamespacedKey(this, "WeaponName");
    }

    public static void AddSkill(Skill skill) {
        if(isUpdating) {
            AddingSkill.add(skill);
        }else {
            UpdatingSkill.add(skill);
        }
    }

    public void Update() {
        BukkitRunnable brun = new BukkitRunnable() {
            @Override
            public void run() {
                ArrayList<Skill> removingskill = new ArrayList<>();
                isUpdating = true;
                for(Skill skill : UpdatingSkill) {
                    skill.Update();
                    if(skill.isEnd) {
                        removingskill.add(skill);
                    }
                }
                isUpdating = false;
                UpdatingSkill.addAll(AddingSkill);
                AddingSkill.clear();
                UpdatingSkill.removeAll(removingskill);
            }
        };brun.runTaskTimer(this, 0, 0);
    }

    public void onEnable() {
        Bukkit.getPluginCommand("offzenithplugin").setExecutor(new offplugin());
        getweapon g = new getweapon();
        Bukkit.getPluginCommand("getweapon").setExecutor(g);
        Bukkit.getPluginCommand("getweapon").setTabCompleter(g);
        Bukkit.getPluginManager().registerEvents(new Event(), this);
        Bukkit.getPluginManager().registerEvents(new DontFireWorkDamage(), this);
        Update();
    }

    public void onDisable() {
        for(Entity en : entities) {
            en.remove();
        }
    }

}
