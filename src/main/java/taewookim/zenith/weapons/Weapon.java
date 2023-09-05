package taewookim.zenith.weapons;

import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import taewookim.zenith.ZenithPlugin;
import taewookim.zenith.skills.*;
import taewookim.zenith.skills.Entity.Zenith;

import java.util.UUID;

public enum Weapon {

    ZENITH("§4제니스", 1, 10, 19.0, InfinityPlusTwo.class),
    COPPER_SHORTSWORD("§7구리 단검", 2, 5, 0.5, Skill.class),
    STARFURY("§5스타퓨리", 3, 20, 2.5, Starfury.class),
    ENCHANTED_SWORD("§9마법의 검", 4, 20, 2.3, Enchanted_Sword.class),
    BEE_KEEPER("§8양봉가", 5, 7, 3.0, Bee.class),
    SEEDLER("§a시들러", 6, 7, 5.0, Seed.class),
    THE_HORSEMANS_BLADE("§c기수의 검", 7, 8, 15.0, Horseman.class),
    TERRA_BLADE("§2테라 블레이드", 8, 7, 8.5, Terra.class),
    INFLUX_WAVER("§3인플럭스 웨이버", 9, 6, 10.0, Influx.class),
    STAR_WRATH("§6별의 진노", 10, 5, 17.0, StarWrath.class),
    MEOWMERE("§d미야우미어", 11, 4, 20.0, Meow.class),
    TRUE_EXCALIBUR("§f진정한 엑스칼리버", 12, 6, 7.2, True_Excalibur.class),
    TRUE_NIGHTS_EDGE("§1진정한 밤의 칼날", 13, 10, 7.0, TrueNight.class),
    BROKEN_HERO_SWORD("§0부셔진 영웅의 검", 14, 0, 0.0, Skill.class),
    EXCALIBUR("§5엑스칼리버", 15, 6, 7.2, Excalibur.class),
    NIGHTS_EDGE("§9밤의 칼날", 16, 8, 4.0, Night.class),
    LIGHTS_BANE("§9빛의 파멸", 17, 6, 1.6, Lights.class),
    BLOOD_BUTCHERER("§c피의 도살자", 18, 5, 2.2, Blood.class),
    MURAMASA("§3무라마사", 19, 6, 2.4, Skill.class),
    BLADE_OF_GRASS("§2초목의 검", 20, 5, 1.8, Grass.class),
    VOLCANO("§c볼케이노", 21, 13, 4.0, Volcano.class);



    final String name;
    final int custommodeldata;
    final int attack_speed;
    final double attack_damage;
    final Class<? extends Skill> skill;

    Weapon(String name, int custommodeldata, int attack_speed, double attack_damage, Class<? extends Skill> skill) {
        this.name = name;
        this.custommodeldata = custommodeldata;
        this.attack_speed = attack_speed;
        this.attack_damage = attack_damage;
        this.skill = skill;
    }

    public double getDamge() {
        return attack_damage;
    }

    public int getCool() {
        return attack_speed;
    }

    public ItemStack getItem() {
        ItemStack i = new ItemStack(Material.DIAMOND_SWORD);
        ItemMeta m = i.getItemMeta();
        m.setDisplayName(name);
        m.setCustomModelData(custommodeldata);
        m.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ATTACK_SPEED.name(), 100, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        m.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), Attribute.GENERIC_ATTACK_DAMAGE.name(), attack_damage, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
        m.getPersistentDataContainer().set(ZenithPlugin.skillkey, PersistentDataType.STRING, this.toString());
        m.setUnbreakable(true);
        i.setItemMeta(m);
        return i;
    }

    public Class<? extends Skill> getClasses() {
        return skill;
    }
}
