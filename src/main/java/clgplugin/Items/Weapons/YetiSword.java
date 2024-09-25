package clgplugin.Items.Weapons;

import clgplugin.CLGPlugin;
import clgplugin.Items.ItemManager;
import clgplugin.Items.ModItem;
import clgplugin.Items.annotations.ItemName;
import clgplugin.Items.createItem.EnchantmentSub;
import clgplugin.Items.createItem.ItemCreator;
import clgplugin.events.annotations.EventAlias;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@EventAlias(eventAlias = "YetiSword")
@ItemName(itemname = "yetisword")
public class YetiSword extends ModItem implements Listener {
    private ItemStack itemStack;
    private List<EnchantmentSub> enchantments = new ArrayList<>();
    private List<ItemFlag> flags = new ArrayList<>();


    public YetiSword(){
        enchantments.add(new EnchantmentSub(Enchantment.LUCK, 2, false));
        enchantments.add(new EnchantmentSub(Enchantment.PROTECTION_FIRE, 4, false));
        flags.add(ItemFlag.HIDE_ATTRIBUTES);
        ItemCreator item1 = new ItemCreator(
                Material.IRON_SWORD,
                1,
                enchantments,
                flags,
                ChatColor.GREEN+"Yeti Sword",
                ChatColor.GRAY+"Damage: "+ChatColor.DARK_RED+"{value}",
                ChatColor.GRAY+"Strength: "+ChatColor.DARK_RED+"{value}",
                ChatColor.GRAY+"Intelligence: "+ChatColor.DARK_RED+"{value}",
                " ",
                ChatColor.GREEN+"Ability: Terrain Toss "+ChatColor.YELLOW+ChatColor.BOLD+" RIGHT CLICK",
                ChatColor.GRAY+"Throws a chunk of terrain in the",
                ChatColor.GRAY+"direction you are facing! Deals up to",
                ChatColor.DARK_RED+"{value}"+ ChatColor.GRAY+" damage.",
                ChatColor.DARK_GRAY+"Mana Cost: "+ChatColor.GRAY+"{value}",
                ChatColor.DARK_GRAY+"Cooldown: "+ChatColor.GRAY+"{value}",
                " ",
                ChatColor.GOLD+"{rarityGradeLevel}"
        );
        item1.getItemMeta().setUnbreakable(true);
        itemStack = item1.getFullItem();
    }


    @EventHandler
    public void onRightClick(PlayerInteractEvent event){
        if (event.getAction() == Action.RIGHT_CLICK_AIR){
            if (Objects.equals(Objects.requireNonNull(event.getItem()).getItemMeta(), ItemManager.getYetiSwordItem().getItemMeta())){
                Player player = event.getPlayer();
                player.playSound(player.getLocation(), Sound.ENTITY_IRON_GOLEM_DEATH, 10f, 10f);

                Block below = player.getLocation().clone().subtract(0, 1, 0).getBlock();
                Block belowTwo = player.getLocation().clone().subtract(0, 2, 0).getBlock();

                Location loc = player.getLocation().clone().add(0, 3, 0);

                List<Location> tops = new ArrayList<>();
                List<Location> bottoms = new ArrayList<>();

                for (int i = 1; i < 3; i++) tops.add(loc.clone().add(i, 0, 0));
                for (int i = 1; i < 3; i++) tops.add(loc.clone().add(0, 0, i));
                for (int i = 1; i < 3; i++) tops.add(loc.clone().add(-i, 0, 0));
                for (int i = 1; i < 3; i++) tops.add(loc.clone().add(0, 0, -i));

                tops.add(loc.clone().add(1, 0, 1));
                tops.add(loc.clone().add(1, 0, -1));
                tops.add(loc.clone().add(-1, 0, 1));
                tops.add(loc.clone().add(-1, 0, -1));
                tops.add(loc.clone());

                bottoms.add(loc.clone().add(1, -1, 0));
                bottoms.add(loc.clone().add(-1, -1, 0));
                bottoms.add(loc.clone().add(0, -1, 1));
                bottoms.add(loc.clone().add(0, -1, -1));
                bottoms.add(loc.clone().add(0, -1, 0));

                Vector vector = player.getLocation().getDirection().multiply(0.9);
                vector.setY(0.5);

                List<FallingBlock> blocks = new ArrayList<>();

                if (belowTwo.getType().equals(Material.AIR)) belowTwo = below;

                for (Location top : tops) {
                    FallingBlock block = Objects.requireNonNull(loc.getWorld()).spawnFallingBlock(top, below.getBlockData());

                    block.setDropItem(false);
                    block.setHurtEntities(false);
                    block.setVelocity(vector);

                    blocks.add(block);
                }

                for (Location bottom : bottoms) {
                    FallingBlock block = loc.getWorld().spawnFallingBlock(bottom, belowTwo.getBlockData());

                    block.setDropItem(false);
                    block.setHurtEntities(false);
                    block.setVelocity(vector);

                    blocks.add(block);
                }
                markFalling(blocks, player);
            }
        }
    }

    private void markFalling(List<FallingBlock> blocks, Player player) {
        new BukkitRunnable() {
            @Override
            public void run() {
                for (FallingBlock block : blocks) {
                    if (block.isOnGround() || block.getLocation().clone().subtract(0, 1, 0).getBlock().getType() != Material.AIR) {
                        for (Entity entity : block.getNearbyEntities(block.getLocation().getBlockX(), block.getLocation().getBlockY(), block.getLocation().getBlockZ())) {
                            entity.setGlowing(false);
                        }

                        block.getWorld().playSound(block.getLocation(), Sound.ENTITY_DRAGON_FIREBALL_EXPLODE, 5, 5);
                        block.getWorld().createExplosion(block.getLocation(), 5f);
                        blocks.forEach(Entity::remove);

                        cancel();
                    }
                }
            }
        }.runTaskTimer(CLGPlugin.getPlugin(CLGPlugin.class), 1, 1);
    }

    public ItemStack getItemStack() {
        return itemStack;
    }
}
