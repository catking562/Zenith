package taewookim.zenith;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.recipe.CraftingBookCategory;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BoundingBox;
import taewookim.zenith.commands.getweapon;
import taewookim.zenith.commands.offplugin;
import taewookim.zenith.events.DontFireWorkDamage;
import taewookim.zenith.events.EntityDeath;
import taewookim.zenith.events.Event;
import taewookim.zenith.skills.Entity.ItemCreateEntity;
import taewookim.zenith.skills.Skill;
import taewookim.zenith.weapons.Weapon;

import java.util.ArrayList;
import java.util.List;

public class ZenithPlugin extends JavaPlugin {

    public static ArrayList<Entity> entities = new ArrayList<>();
    public static NamespacedKey skillkey;
    public static ArrayList<Skill> UpdatingSkill = new ArrayList<>();
    public static ArrayList<Skill> AddingSkill = new ArrayList<>();
    public static Boolean isUpdating = false;
    public static ArrayList<ItemCreateEntity> itemcreator = new ArrayList<>();

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

    public void loadRecipy() {
        //구리 단검
        ShapedRecipe copper_sword = new ShapedRecipe(Weapon.COPPER_SHORTSWORD.getItem());
        copper_sword.shape("%","%","&");
        copper_sword.setIngredient('%', Material.COPPER_INGOT);
        copper_sword.setIngredient('&', Material.STICK);
        copper_sword.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(copper_sword);
        //인첸트 검
        ShapedRecipe enchanted = new ShapedRecipe(Weapon.ENCHANTED_SWORD.getItem());
        enchanted.shape("%&%","&*&","%&%");
        enchanted.setIngredient('%', Material.AIR);
        enchanted.setIngredient('&', Material.AMETHYST_SHARD);
        enchanted.setIngredient('*', Material.IRON_SWORD);
        enchanted.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(enchanted);
        //시들러
        ShapedRecipe seelder = new ShapedRecipe(Weapon.SEEDLER.getItem());
        seelder.shape("123","456");
        seelder.setIngredient('1', Material.WHEAT_SEEDS);
        seelder.setIngredient('2', Material.PUMPKIN_SEEDS);
        seelder.setIngredient('3', Material.MELON_SEEDS);
        seelder.setIngredient('4', Material.BEETROOT_SEEDS);
        seelder.setIngredient('5', Material.CARROT);
        seelder.setIngredient('6', Material.POTATO);
        seelder.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(seelder);
        //엑스칼리버
        ShapedRecipe excalibur = new ShapedRecipe(Weapon.EXCALIBUR.getItem());
        excalibur.shape("121","232", "121");
        excalibur.setIngredient('1', Material.AIR);
        excalibur.setIngredient('2', Material.GOLD_INGOT);
        excalibur.setIngredient('3', Material.DIAMOND_SWORD);
        excalibur.setCategory(CraftingBookCategory.EQUIPMENT);
        Bukkit.addRecipe(excalibur);
        //빛의 파멸
        ShapedRecipe light = new ShapedRecipe(Weapon.LIGHTS_BANE.getItem());
        light.shape("121","343","131");
        light.setIngredient('1', Material.AIR);
        light.setIngredient('2', Material.NETHER_STAR);
        light.setIngredient('3', Material.AMETHYST_SHARD);
        light.setIngredient('4', Material.GOLDEN_SWORD);
        Bukkit.addRecipe(light);
        //피의 도살자
        ShapedRecipe blood = new ShapedRecipe(Weapon.BLOOD_BUTCHERER.getItem());
        blood.shape("121","232","121");
        blood.setIngredient('1', Material.REDSTONE);
        blood.setIngredient('2', Material.COAL);
        blood.setIngredient('3', Material.STONE_SWORD);
        Bukkit.addRecipe(blood);
        //초목의 검
        ShapedRecipe grass = new ShapedRecipe(Weapon.BLADE_OF_GRASS.getItem());
        grass.shape("1","1","2");
        grass.setIngredient('1', Material.GRASS_BLOCK);
        grass.setIngredient('2', Material.WOODEN_SWORD);
        Bukkit.addRecipe(grass);
        //불케이노
        ShapedRecipe volcano = new ShapedRecipe(Weapon.VOLCANO.getItem());
        volcano.shape("121","343","121");
        volcano.setIngredient('1', Material.AIR);
        volcano.setIngredient('2', Material.FIRE_CHARGE);
        volcano.setIngredient('3', Material.LAVA_BUCKET);
        volcano.setIngredient('4', Material.IRON_SWORD);
        Bukkit.addRecipe(volcano);
    }

    public void SkillUpdate() {
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

    public void ItemCreator() {
        ArrayList<ItemCreateEntity> removingcrea = new ArrayList<>();
        for(ItemCreateEntity en : itemcreator) {
            en.Update();
            if(en.isEnd) {
                removingcrea.add(en);
            }
        }
        itemcreator.removeAll(removingcrea);
    }

    public boolean isSimuler(ItemStack i1, ItemStack i2) {
        if(i1.hasItemMeta()&&i2.hasItemMeta()) {
            ItemMeta m1 = i1.getItemMeta();
            ItemMeta m2 = i2.getItemMeta();
            if(m1.hasDisplayName()&&m2.hasDisplayName()&&m1.getDisplayName().equalsIgnoreCase(m2.getDisplayName())) {
                PersistentDataContainer container1 = m1.getPersistentDataContainer();
                PersistentDataContainer container2 = m2.getPersistentDataContainer();
                if(container1.has(skillkey, PersistentDataType.STRING)&&container2.has(skillkey, PersistentDataType.STRING)&&container1.get(skillkey, PersistentDataType.STRING).equalsIgnoreCase(container2.get(skillkey, PersistentDataType.STRING))) {
                    return true;
                }
            }
        }
        return false;
    }

    public void ItemCheck() {
        for(World w : Bukkit.getWorlds()) {
            for(Entity en : w.getEntities()) {
                if(en instanceof Item it) {
                    Material material = en.getLocation().add(0, -0.5, 0).getBlock().getType();
                    if(material.equals(Material.ANVIL)||material.equals(Material.CHIPPED_ANVIL)||material.equals(Material.DAMAGED_ANVIL)) {
                        if(isSimuler(it.getItemStack(), Weapon.BROKEN_HERO_SWORD.getItem())) {
                            List<Entity> list = (List<Entity>) it.getWorld().getNearbyEntities(BoundingBox.of(it.getLocation(), 0.1, 0.1, 0.1));
                            if(list.size()==3) {
                                boolean isexcal = false;
                                boolean isnight = false;
                                for(Entity item : list) {
                                    if(!it.equals(item)&&item instanceof Item ite) {
                                        if(isSimuler(ite.getItemStack(), Weapon.TRUE_EXCALIBUR.getItem())) {
                                            isexcal=true;
                                        }else if(isSimuler(ite.getItemStack(), Weapon.TRUE_NIGHTS_EDGE.getItem())){
                                            isnight=true;
                                        }
                                    }
                                }
                                if(isexcal&&isnight) {
                                    ItemCreateEntity crea = new ItemCreateEntity(Weapon.TERRA_BLADE.getItem(), it.getLocation());
                                    itemcreator.add(crea);
                                    for(Entity en1 : list) {
                                        en1.remove();
                                    }
                                }
                            }
                        }else if(isSimuler(it.getItemStack(), Weapon.EXCALIBUR.getItem())) {
                            List<Entity> list = (List<Entity>) it.getWorld().getNearbyEntities(BoundingBox.of(it.getLocation(), 0.1, 0.1, 0.1));
                            if(list.size()==2) {
                                for(Entity item : list) {
                                    if(!it.equals(item)&&item instanceof Item ite) {
                                        if(ite.getItemStack().getType().equals(Material.DIAMOND)&&ite.getItemStack().getAmount()==5) {
                                            ItemCreateEntity crea = new ItemCreateEntity(Weapon.TRUE_EXCALIBUR.getItem(), it.getLocation());
                                            itemcreator.add(crea);
                                            for(Entity en1 : list) {
                                                en1.remove();
                                            }
                                        }
                                    }
                                }
                            }
                        }else if(isSimuler(it.getItemStack(), Weapon.NIGHTS_EDGE.getItem())) {
                            List<Entity> list = (List<Entity>) it.getWorld().getNearbyEntities(BoundingBox.of(it.getLocation(), 0.1, 0.1, 0.1));
                            if(list.size()==4) {
                                boolean isbone = false;
                                boolean isroot = false;
                                boolean isspid = false;
                                for(Entity item : list) {
                                    if(!item.equals(it)&&item instanceof Item ite) {
                                        if(ite.getItemStack().getType().equals(Material.BONE)&&ite.getItemStack().getAmount()==20) {
                                            isbone = true;
                                        }else if(ite.getItemStack().getType().equals(Material.ROTTEN_FLESH)&&ite.getItemStack().getAmount()==20) {
                                            isroot = true;
                                        }else if(ite.getItemStack().getType().equals(Material.SPIDER_EYE)&&ite.getItemStack().getAmount()==20) {
                                            isspid = true;
                                        }
                                    }
                                }if(isspid&&isbone&&isroot) {
                                    ItemCreateEntity crea = new ItemCreateEntity(Weapon.TRUE_NIGHTS_EDGE.getItem(), it.getLocation());
                                    itemcreator.add(crea);
                                    for(Entity en1 : list) {
                                        en1.remove();
                                    }
                                }
                            }
                        }else if(isSimuler(it.getItemStack(), Weapon.MURAMASA.getItem())) {
                            List<Entity> list = (List<Entity>) it.getWorld().getNearbyEntities(BoundingBox.of(it.getLocation(), 0.1, 0.1, 0.1));
                            if(list.size()==5) {
                                boolean islight = false;
                                boolean isblood = false;
                                boolean isgrass = false;
                                boolean isvolca = false;
                                for(Entity item : list) {
                                    if(!item.equals(it)&&item instanceof Item ite) {
                                        if(isSimuler(ite.getItemStack(), Weapon.LIGHTS_BANE.getItem())) {
                                            islight = true;
                                        }else if(isSimuler(ite.getItemStack(), Weapon.BLOOD_BUTCHERER.getItem())) {
                                            isblood = true;
                                        }else if(isSimuler(ite.getItemStack(), Weapon.BLADE_OF_GRASS.getItem())) {
                                            isgrass = true;
                                        }else if(isSimuler(ite.getItemStack(), Weapon.VOLCANO.getItem())) {
                                            isvolca = true;
                                        }
                                    }
                                }
                                if(islight&&isblood&&isgrass&&isvolca) {
                                    ItemCreateEntity crea = new ItemCreateEntity(Weapon.NIGHTS_EDGE.getItem(), it.getLocation());
                                    itemcreator.add(crea);
                                    for(Entity en1 : list) {
                                        en1.remove();
                                    }
                                }
                            }
                        }else if(isSimuler(it.getItemStack(), Weapon.COPPER_SHORTSWORD.getItem())) {
                            List<Entity> list = (List<Entity>) it.getWorld().getNearbyEntities(BoundingBox.of(it.getLocation(), 0.1, 0.1, 0.1));
                            if(list.size()==10) {
                                boolean isstarfury = false;
                                boolean isenchante = false;
                                boolean isbeesword = false;
                                boolean isseedlers = false;
                                boolean isthehorse = false;
                                boolean isterrabla = false;
                                boolean isinfluxwa = false;
                                boolean isstarwrat = false;
                                boolean ismeowcats = false;
                                for(Entity item : list) {
                                    if(!item.equals(it)&&item instanceof Item ite) {
                                        if(isSimuler(ite.getItemStack(), Weapon.STARFURY.getItem())) {
                                            isstarfury = true;
                                        }else if(isSimuler(ite.getItemStack(), Weapon.ENCHANTED_SWORD.getItem())) {
                                            isenchante = true;
                                        }else if(isSimuler(ite.getItemStack(), Weapon.BEE_KEEPER.getItem())) {
                                            isbeesword = true;
                                        }else if(isSimuler(ite.getItemStack(), Weapon.SEEDLER.getItem())) {
                                            isseedlers = true;
                                        }else if(isSimuler(ite.getItemStack(), Weapon.THE_HORSEMANS_BLADE.getItem())) {
                                            isthehorse = true;
                                        }else if(isSimuler(ite.getItemStack(), Weapon.TERRA_BLADE.getItem())) {
                                            isterrabla = true;
                                        }else if(isSimuler(ite.getItemStack(), Weapon.INFLUX_WAVER.getItem())) {
                                            isinfluxwa = true;
                                        }else if(isSimuler(ite.getItemStack(), Weapon.STAR_WRATH.getItem())) {
                                            isstarwrat = true;
                                        }else if(isSimuler(ite.getItemStack(), Weapon.MEOWMERE.getItem())) {
                                            ismeowcats = true;
                                        }
                                    }
                                }
                                if(isstarfury&&isenchante&&isbeesword&&isseedlers&&isthehorse&&isterrabla&&isstarwrat&&isinfluxwa&&ismeowcats) {
                                    ItemCreateEntity crea = new ItemCreateEntity(Weapon.ZENITH.getItem(), it.getLocation());
                                    itemcreator.add(crea);
                                    for(Entity en1 : list) {
                                        en1.remove();
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    int a = 0;

    public void Update() {
        BukkitRunnable brun = new BukkitRunnable() {
            @Override
            public void run() {
                SkillUpdate();
                a++;
                if(a>=20) {
                    ItemCheck();
                    a=0;
                }
                ItemCreator();
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
        Bukkit.getPluginManager().registerEvents(new EntityDeath(), this);
        Update();
        loadRecipy();
    }

    public void onDisable() {
        for(Entity en : entities) {
            en.remove();
        }
        for(ItemCreateEntity en : itemcreator) {
            en.getLocation().getWorld().dropItem(en.getLocation(), en.getItem());
        }
    }

}
