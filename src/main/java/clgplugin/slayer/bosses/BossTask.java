package clgplugin.slayer.bosses;

import clgplugin.CLGPlugin;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;

public class BossTask extends BukkitRunnable {
    private final Zombie boss;
    private int tickCount = 0;

    private int attackLastingCount = 0;
    private boolean changingBlocks = false;
    private int blockAOETotal = 1;

    private boolean enraged = false;
    private boolean enragedMode = false;

    private final Map<Location, Material> originalBlocks = new HashMap<>();

    private float heartbeatVolume = 1.0f; // volume
    private float heartbeatPitch = 1.0f; // pitch

    public BossTask(Zombie boss) {
        this.boss = boss;
        boss.setBaby(false);
        boss.setVisualFire(false);
        boss.getAttribute(Attribute.GENERIC_KNOCKBACK_RESISTANCE).setBaseValue(1);
        boss.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.3);
        boss.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, Integer.MAX_VALUE, 1, false, false));
    }

    @Override
    public void run() {
        if (boss == null || boss.isDead()) {
            boss.getEquipment().setHelmet(null);
            boss.getEquipment().setChestplate(null);
            boss.getEquipment().setLeggings(null);
            boss.getEquipment().setBoots(null);
            boss.getEquipment().setItemInMainHand(null);
            boss.getEquipment().setItemInOffHand(null);
            this.cancel(); // boss dead...null
            resetBlocks();
            return;
        }
        boss.setTarget(getNearestPlayer());
        tickCount++;
        boss.setFireTicks(0);


        if (getBossPhase2() >= boss.getHealth()){
            enraged = true;
        }

        //-------------- ticks under

        if (tickCount % 100 == 0 && !changingBlocks){
            if (getNearestPlayer() != null) {
                attack1();
                //changeBlocksAroundPlayer(getNearestPlayer().getLocation());
                //launchBlockAtPlayer(getNearestPlayer());
                //getNearestPlayer().playSound(boss.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 2, 1);
            }
        }
        if (!enraged){
            if (tickCount %25 == 0){
                heartbeatPitch = 4f;
                playHeartbeatSound();
            }
        }


        //Enraged mode
        if (enraged){
            // 15sec 300 tick
            // 10sec 200
            if (!enragedMode){
                getNearestPlayer().playSound(boss.getLocation(), Sound.ENTITY_WITHER_SPAWN, 2, 1);
                boss.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.5);
                enragedMode =true;
            }
            if (tickCount % 450 == 0 && !changingBlocks) {
                changingBlocks = true;
                boss.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);
            }
            if (!changingBlocks){
                if (tickCount %15 == 0){
                    heartbeatPitch = 1.0f;
                    playHeartbeatSound();
                }
            }
            if (changingBlocks) {
                if (tickCount % 2 == 0){
                    heartbeatPitch = 0.1f;
                    playHeartbeatSound();
                }
                if (tickCount % 5 == 0 && attackLastingCount < 11){
                    changeBlocksAroundBoss(blockAOETotal);
                    blockAOETotal++;
                    attackLastingCount++;
                    if (attackLastingCount == 11){
                        attackOnBlocks();
                        attackLastingCount++;
                    }
                }
                if (attackLastingCount >= 12 && tickCount % 100 == 0){
                    changingBlocks = false;
                    resetBlocks();
                    boss.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.5);
                    blockAOETotal = 1;
                    attackLastingCount = 0;
                }
            }
        }

        //end
    }

    private void attack1(){
        Location spawnBlockLocation = boss.getLocation().add(0, 2, 0).clone();

        Location bossLocation = boss.getLocation().clone().add(0, 1.5, 0);
        Location playerLocation = getNearestPlayer().getLocation().clone().add(0, 0.5, 0);

        Vector direction = playerLocation.toVector().subtract(bossLocation.toVector());
        double distanceXZ = Math.sqrt(Math.pow(direction.getX(), 2) + Math.pow(direction.getZ(), 2)); // Horizontal distance

        double height = 8;
        double gravity = 0.08;
        double time = Math.sqrt((2 * height) / gravity);

        double verticalVelocity = time * gravity;
        double horizontalVelocity = distanceXZ / (2 * time);

        Vector velocity = new Vector(direction.getX(), verticalVelocity, direction.getZ()).normalize().multiply(horizontalVelocity);
        velocity.setY(verticalVelocity);

        FallingBlock fallingBeacon = boss.getWorld().spawnFallingBlock(bossLocation, Material.BEACON.createBlockData());

        fallingBeacon.setVelocity(velocity);
        fallingBeacon.setDropItem(false);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (fallingBeacon.getLocation().subtract(0,1,0).getBlock().getType() != Material.AIR) {
                    fallingBeacon.remove();
                    this.cancel();
                }
            }
        }.runTaskTimer(CLGPlugin.getPlugin(), 0, 1);
    }


    private void attack2(){
        Location bossLocation = boss.getLocation();
        Location spawnLocation = bossLocation.clone().add(0, 1.5, 0);

        List<Integer> directionsX = new ArrayList<>();
        List<Integer> directionsY = new ArrayList<>();

        directionsY.add(-1);
        directionsY.add(1);
        directionsY.add(1);
        directionsY.add(-1);

        directionsX.add(-1);
        directionsX.add(1);
        directionsX.add(-1);
        directionsX.add(1);


        List<FallingBlock> fallingBeacons = new ArrayList<>();
        for (int i = 0; i < directionsY.size(); i++){
            FallingBlock fallingBeacon = boss.getWorld().spawnFallingBlock(spawnLocation, Material.BEACON.createBlockData());
            Vector velocity = new Vector(directionsX.get(i), 0.5, directionsY.get(i)).normalize().multiply(1);
            fallingBeacon.setVelocity(velocity);
            fallingBeacon.setDropItem(false);
            fallingBeacon.setHurtEntities(true);

            fallingBeacons.add(fallingBeacon);
        }
        directionsY.clear();
        directionsX.clear();
        new BukkitRunnable() {
            @Override
            public void run() {
                for (int j = 0; j < fallingBeacons.size(); j++) {
                    FallingBlock fallingBeacon = fallingBeacons.get(j);

                    if (fallingBeacon.isDead() || fallingBeacon.isOnGround()) {

                        //Fix this ogaboga
                        fallingBeacon.remove();

                        fallingBeacon.remove();
                        fallingBeacons.remove(j);
                        j--;
                    }
                }

                if (fallingBeacons.isEmpty()) {
                    this.cancel();
                }
            }
        }.runTaskTimer(CLGPlugin.getPlugin(), 0, 1);
    }

    private void playHeartbeatSound() {
        Location bossLocation = boss.getLocation();

        for (Player player : bossLocation.getWorld().getPlayers()) {
            if (player.getLocation().distance(bossLocation) < 50) {
                player.playSound(bossLocation, Sound.ENTITY_WARDEN_HEARTBEAT, heartbeatVolume, heartbeatPitch);
            }
        }
    }

    private double getBossPhase2(){
        double max = Objects.requireNonNull(boss.getAttribute(Attribute.GENERIC_MAX_HEALTH)).getValue();
        return max/3;
        }
    private void attackOnBlocks(){

        for (Map.Entry<Location, Material> e : originalBlocks.entrySet()) {
            World w = boss.getWorld();
            w.strikeLightningEffect(e.getKey());

            List<Player> playersWithinRadius = getNearbyPlayers(boss, 11.0);
            for (Player p : playersWithinRadius){
                p.damage(5);
            }
        }
    }

    private void changeBlocksAroundBoss(int radius) {
        Location bossLocation = boss.getLocation();

        for (int x = -radius; x <= radius; x++) {
            for (int z = -radius; z <= radius; z++) {

                Location blockLocation = bossLocation.clone().add(x, -1, z);
                Block block = blockLocation.getBlock();
                if (block.getType() == Material.AIR){
                    continue;
                }
                if (block.getType() != Material.NETHER_WART_BLOCK) {
                    originalBlocks.put(blockLocation, block.getType());
                }

                if (block.getType() != Material.NETHER_WART_BLOCK) {
                    block.setType(Material.NETHER_WART_BLOCK);
                }
            }
        }
    }

    public List<Player> getNearbyPlayers(Entity entity, double radius) {
        List<Player> nearbyPlayers = new ArrayList<>();

        for (Entity nearbyEntity : entity.getNearbyEntities(radius, radius, radius)) {
            if (nearbyEntity instanceof Player) {
                nearbyPlayers.add((Player) nearbyEntity);
            }
        }

        return nearbyPlayers;
    }

    private void resetBlocks() {
        for (Map.Entry<Location, Material> e : originalBlocks.entrySet()) {
            e.getKey().getBlock().setType(e.getValue());
        }
        originalBlocks.clear();
    }

    private Player getNearestPlayer() {
        Player nearest = null;
        double nearestDistance = Double.MAX_VALUE;
        for (Player player : boss.getWorld().getPlayers()) {
            double distance = player.getLocation().distance(boss.getLocation());
            if (distance < nearestDistance) {
                nearest = player;
                nearestDistance = distance;
            }
        }
        return nearest;
    }

}
