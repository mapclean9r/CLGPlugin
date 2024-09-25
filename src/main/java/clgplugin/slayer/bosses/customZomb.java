package clgplugin.slayer.bosses;

import clgplugin.CLGPlugin;
import clgplugin.Items.createItem.ItemBuilder;
import clgplugin.slayer.SlayerBoss;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class customZomb extends SlayerBoss {

    private Zombie bossEntity;

    public customZomb(Location location) {
        this.bossEntity = (Zombie) Objects.requireNonNull(location.getWorld()).spawnEntity(location, EntityType.ZOMBIE);
        this.bossEntity.setCustomName("§cAtoned");
        this.bossEntity.setCustomNameVisible(true);
        this.bossEntity.setHealth(20);
        this.bossEntity.getEquipment().setHelmet(new ItemBuilder(Material.WITHER_SKELETON_SKULL).addEnchantGlint().getItem());
        this.bossEntity.getEquipment().setChestplate(new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchantGlint().getItem());
        this.bossEntity.getEquipment().setLeggings(new ItemBuilder(Material.DIAMOND_LEGGINGS).addEnchantGlint().getItem());
        this.bossEntity.getEquipment().setBoots(new ItemBuilder(Material.DIAMOND_BOOTS).addEnchantGlint().getItem());
        this.bossEntity.getEquipment().setItemInMainHand(new ItemStack(Material.NETHERITE_SWORD));
    }

    public void spawn() {
        // Start tick
        new BossTask(this.bossEntity).runTaskTimer(CLGPlugin.getPlugin(), 0L, 1L);
    }

    public Zombie getBossEntity() {
        return bossEntity;
    }

    // You can add more boss-related logic here, such as custom attacks, phases, etc.
}