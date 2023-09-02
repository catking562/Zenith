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

import java.util.UUID;

public enum Weapon {

    ZENITH("§4제니스", 1, 10, 190, Skill.class),
    COPPER_SHORTSWORD("§7구리 단검", 2, 5, 5, Skill.class),
    STARFURY("§5스타퓨리", 3, 20, 25, Starfury.class),
    ENCHANTED_SWORD("§9마법의 검", 4, 20, 23, Enchanted_Sword.class),
    BEE_KEEPER("§8양봉가", 5, 7, 30, Bee.class),
    SEEDLER("§a시들러", 6, 7, 50, Seed.class),
    THE_HORSEMANS_BLADE("§c기수의 검", 7, 8, 150, Horseman.class),
    TERRA_BLADE("§2테라 블레이드", 8, 7, 85, Terra.class),
    INFLUX_WAVER("§3인플럭스 웨이버", 9, 6, 100, Skill.class),
    STAR_WRATH("§6별의 진노", 10, 5, 170, Skill.class),
    MEOWMERE("§d미야우미어", 11, 4, 200, Skill.class),
    TRUE_EXCALIBUR("§f진정한 엑스칼리버", 12, 6, 72, Skill.class),
    TRUE_NIGHTS_EDGE("§1진정한 밤의 칼날", 13, 10, 70, Skill.class),
    BROKEN_HERO_SWORD("§0부셔진 영웅의 검", 14, 0, 0, Skill.class),
    EXCALIBUR("§5엑스칼리버", 15, 6, 72, Skill.class),
    NIGHTS_EDGE("§9밤의 칼날", 16, 8, 40, Skill.class),
    LIGHTS_BANE("§9빛의 파멸", 17, 6, 16, Skill.class),
    BLOOD_BUTCHERER("§c피의 도살자", 18, 5, 22, Skill.class),
    MURAMASA("§3무라마사", 19, 6, 24, Skill.class),
    BLADE_OF_GRASS("§2초목의 검", 20, 5, 18, Skill.class),
    VOLCANO("§c볼케이노", 21, 13, 40, Skill.class);



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
