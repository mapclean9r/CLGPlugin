package clgplugin.Items.createItem;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ItemCreator {
    private ItemStack fullItem;
    private ItemMeta itemMeta;
    private Material material;
    private String name;
    private int stackAmount;
    private List<String> loreTexts = new ArrayList<>();

    public ItemCreator(Material itemMaterial, int itemStackAmount, List<EnchantmentSub> enchantments, List<ItemFlag> itemFlags, String itemName,String... lore) {
        material = itemMaterial;
        for (int u = 0; u < lore.length; u++){
            if (!(Objects.equals(lore[u], ""))){
                loreTexts.add(lore[u]);
            }
        }
        name = itemName;
        stackAmount = itemStackAmount;
        ItemStack item = new ItemStack(material, stackAmount);
        itemMeta = item.getItemMeta();
        itemMeta.setDisplayName(name);

        if (loreTexts.size() != 0){
            itemMeta.setLore(loreTexts);
        }

        for (int i = 0; i < itemFlags.size(); i++){
            itemMeta.addItemFlags(itemFlags.get(i));
        }

        for (int p = 0; p < enchantments.size(); p++){
            EnchantmentSub eItem = enchantments.get(p);
            itemMeta.addEnchant(eItem.getEnchantmentName(), eItem.getLevelAmount(), eItem.isIgnoreLevelCap());
        }

        //itemMeta.addEnchant(Enchantment.DAMAGE_ARTHROPODS, 2, false);
        item.setItemMeta(itemMeta);
        fullItem = item;
    }

    public ItemStack getFullItem() {
        return fullItem;
    }

    public ItemMeta getItemMeta() {
        return itemMeta;
    }
}
