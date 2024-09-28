package clgplugin.Items.createItem;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class ItemBuilder {
    private ItemStack item;
    private final ItemMeta meta;

    public ItemBuilder(){
        item = new ItemStack(Material.DIRT);
        meta = item.getItemMeta();
    }

    public ItemBuilder(Material material) {
        item = new ItemStack(material);
        meta = item.getItemMeta();
    }

    public ItemBuilder addGlint(){
        meta.addEnchant(Enchantment.LUCK, 1, false);
        meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder dyeColor(Color color) {
        LeatherArmorMeta leather = (LeatherArmorMeta) meta;
        leather.setColor(color);
        item.setItemMeta(leather);
        return this;
    }

    public ItemBuilder addEnchantment(Enchantment enchantment, int level){
        meta.addEnchant(enchantment, level, true);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder addItemFlag(ItemFlag itemFlag) {
        meta.addItemFlags(itemFlag);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder isUnbreakable(){
        meta.setUnbreakable(true);
        item.setItemMeta(meta);
        return this;
    }

    public ItemBuilder setSkullID(String id) {
        this.item = HeadLookup.idToSkull(item, id);

        return this;
    }

    public ItemStack getItem() {
        return item;
    }
}

