package clgplugin.slayer.bosses;

import org.bukkit.Sound;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.entity.Zombie;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import org.bukkit.Location;

import java.util.Objects;

public class BossTask extends BukkitRunnable {
    private final Zombie boss;
    private int tickCount = 0;
    private int fireballCount = 0;
    private boolean firing = false;

    public BossTask(Zombie boss) {
        this.boss = boss;
    }

    @Override
    public void run() {
        if (boss == null || boss.isDead()) {
            this.cancel(); // Stop the task if the boss is null or dead
            return;
        }

        tickCount++;

        // 100 ticks (5 seconds)
        if (tickCount % 100 == 0) {
            Player nearestPlayer = getNearestPlayer();
            if (nearestPlayer != null) {
                boss.setTarget(nearestPlayer); // Set target
                launchFireball();
            }
        }


        // 300 ticks (15 seconds)
        if (tickCount % 300 == 0 && !firing) {
            fireballCount = 0; // Reset fireball count for this attack
            firing = true; // Set firing state to true
            launchFireballs(); // Start the fireball attack
        }

        if (firing) {
            if (tickCount % 20 == 0 && fireballCount < 3) {
                launchFireball();
                fireballCount++;
                boss.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0);
            }

            if (fireballCount >= 3) {
                firing = false;
                boss.getAttribute(Attribute.GENERIC_MOVEMENT_SPEED).setBaseValue(0.5);
            }
        }
    }

    private void launchFireballs() {
        launchFireball();
    }

    private void launchFireball() {
        Player nearestPlayer = getNearestPlayer();
        if (nearestPlayer != null) {
            Location loc = boss.getEyeLocation();
            Vector direction = nearestPlayer.getLocation().subtract(loc).toVector().normalize();
            Fireball fireball = boss.getWorld().spawn(loc.add(direction.multiply(2)), Fireball.class);
            fireball.setDirection(direction); // Set fireball direction towards the player
        }
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
