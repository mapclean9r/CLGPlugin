package clgplugin.slayer.bosses;

import clgplugin.CLGPlugin;
import clgplugin.Items.createItem.HeadLookup;
import clgplugin.Items.createItem.ItemBuilder;
import clgplugin.slayer.SlayerBoss;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class customZomb extends SlayerBoss {

    private Zombie bossEntity;

    public customZomb(Location location) {
        this.bossEntity = (Zombie) Objects.requireNonNull(location.getWorld()).spawnEntity(location, EntityType.ZOMBIE);
        this.bossEntity.setCustomName("§cMega dude bro");
        this.bossEntity.setCustomNameVisible(true);
        this.bossEntity.setHealth(20);


        ItemStack zombieHelmet = new ItemStack(Material.PLAYER_HEAD, 1, (byte) SkullType.PLAYER.ordinal());
        zombieHelmet = HeadLookup.idToSkull(zombieHelmet, "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYmE2OGM2ZDcyYzhkNzA0ZWNmNDczOWMzZWY5MDgyMTY3ZjBhZTQ2MWQ0ZTdmN2I5MDlhYjRmNjE1YTgxM2ExNiJ9fX0=");

        bossEntity.getEquipment().setHelmet(zombieHelmet);
        this.bossEntity.getEquipment().setChestplate(new ItemBuilder(Material.NETHERITE_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).getItem());
        this.bossEntity.getEquipment().setLeggings(new ItemBuilder(Material.NETHERITE_LEGGINGS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).getItem());
        this.bossEntity.getEquipment().setBoots(new ItemBuilder(Material.NETHERITE_BOOTS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 4).getItem());
        this.bossEntity.getEquipment().setItemInMainHand(new ItemBuilder(Material.NETHERITE_AXE).getItem());
    }

    public void spawn() {
        // Start tick
        new BossTask(this.bossEntity).runTaskTimer(CLGPlugin.getPlugin(), 0L, 1L);
    }

    public Zombie getBossEntity() {
        return bossEntity;
    }
}