package clgplugin.Items.createItem;

import org.bukkit.enchantments.Enchantment;

public class EnchantmentSub {
    private Enchantment enchantmentName;
    private int levelAmount;
    private boolean ignoreLevelCap;
    public EnchantmentSub(Enchantment enchantment, int level, boolean canEnchant){
        enchantmentName = enchantment;
        levelAmount = level;
        ignoreLevelCap = canEnchant;
    }

    public Enchantment getEnchantmentName() {
        return enchantmentName;
    }

    public void setEnchantmentName(Enchantment enchantmentName) {
        this.enchantmentName = enchantmentName;
    }

    public int getLevelAmount() {
        return levelAmount;
    }

    public void setLevelAmount(int levelAmount) {
        this.levelAmount = levelAmount;
    }

    public boolean isIgnoreLevelCap() {
        return ignoreLevelCap;
    }

    public void setIgnoreLevelCap(boolean ignoreLevelCap) {
        this.ignoreLevelCap = ignoreLevelCap;
    }
}
