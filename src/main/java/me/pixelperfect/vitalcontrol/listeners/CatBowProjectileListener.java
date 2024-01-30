package me.pixelperfect.vitalcontrol.listeners;

import me.pixelperfect.vitalcontrol.VitalControl;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

public class CatBowProjectileListener implements Listener {

    public VitalControl plugin;
    public CatBowProjectileListener(VitalControl plugin) {this.plugin = plugin;}

    private final java.util.Map<Integer, BukkitTask> beeTasks = new java.util.HashMap<>();
    private final java.util.Map<Integer, Vector> beeVelocities = new java.util.HashMap<>();

    @EventHandler
    public void onProjectileLaunch2(ProjectileLaunchEvent event) {
        Projectile projectile = event.getEntity();

        if (projectile.getShooter() instanceof Player && projectile instanceof org.bukkit.entity.Arrow) {
            Player player = (Player) projectile.getShooter();
            org.bukkit.inventory.ItemStack itemInHand = player.getInventory().getItemInMainHand();

            // Check if the player is holding a Catbow
            if (itemInHand.hasItemMeta() && itemInHand.getItemMeta().getDisplayName().equals(ChatColor.LIGHT_PURPLE + "Beebow")) {
                // Cancel the arrow
                event.setCancelled(true);

                // Spawn a cat at the same location as the arrow
                Bee bee = (Bee) projectile.getWorld().spawnEntity(projectile.getLocation(), EntityType.BEE);

                // Set the velocity of the cat to match the arrow's velocity
                bee.setVelocity(projectile.getVelocity());


                // Scale the arrow's velocity to adjust the cat's falling speed
                Vector scaledVelocity = projectile.getVelocity().multiply(0.7);

                BukkitTask task = new BukkitRunnable() {
                    @Override
                    public void run() {
                        Location catLocation = bee.getLocation();

                        // Check if there's a block below or next to the cat's location
                        boolean isBlockBelow = catLocation.subtract(0, 1, 0).getBlock().getType() != Material.AIR;
                        boolean isBlockNextTo = checkBlockNextTo(catLocation);

                        if (isBlockBelow || isBlockNextTo) {
                            // Explode the cat and kill it upon landing or being next to a block
                            bee.getWorld().createExplosion(catLocation, 2.0f);
                            bee.remove();
                            this.cancel(); // Cancel the task once the cat has landed or is next to a block
                        }
                    }
                    private boolean checkBlockNextTo(Location location) {
                        // Check if there's a block to the north, south, east, or west of the given location
                        return location.clone().add(1, 0, 0).getBlock().getType() != Material.AIR ||
                                location.clone().add(-1, 0, 0).getBlock().getType() != Material.AIR ||
                                location.clone().add(0, 0, 1).getBlock().getType() != Material.AIR ||
                                location.clone().add(0, 0, -1).getBlock().getType() != Material.AIR;
                    }
                }.runTaskTimer(plugin, 0L, 1L);

                // Store the task associated with the cat's entity ID
                beeTasks.put(bee.getEntityId(), task);
                // Store the scaled velocity associated with the cat's entity ID
                beeVelocities.put(bee.getEntityId(), scaledVelocity);
            }
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Bee) {
            Bee bee = (Bee) event.getDamager();

            // If the cat is damaged by another entity, cancel the scheduled task
            BukkitTask task = beeTasks.get(bee.getEntityId());
            if (task != null) {
                task.cancel();
                beeTasks.remove(bee.getEntityId());
            }

            // Retrieve the scaled velocity associated with the cat's entity ID
            Vector scaledVelocity = beeVelocities.get(bee.getEntityId());

            // If the velocity is available, set it to the cat to adjust its falling speed
            if (scaledVelocity != null) {
                bee.setVelocity(scaledVelocity);
                beeVelocities.remove(bee.getEntityId()); // Remove the stored velocity after setting it
            }

            // Explode the cat and kill it upon damaging a target
            bee.getWorld().createExplosion(bee.getLocation(), 3.0f);
            bee.remove();
        }
    }
}
