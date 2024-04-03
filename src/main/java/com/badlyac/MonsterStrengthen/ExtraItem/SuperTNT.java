package com.badlyac.MonsterStrengthen.ExtraItem;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;


@SuppressWarnings("ALL")
public class SuperTNT implements Listener {
    private static JavaPlugin plugin;
    private static NamespacedKey superTNTKey;

    public SuperTNT(JavaPlugin plugin) {
        SuperTNT.plugin = plugin;
        superTNTKey = new NamespacedKey(plugin, "super_tnt");
    }

    public static ItemStack createSuperTNT() {
        ItemStack superTNT = new ItemStack(Material.TNT);
        ItemMeta meta = superTNT.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(ChatColor.RED + "SuperTNT");
            meta.getPersistentDataContainer().set(superTNTKey, PersistentDataType.INTEGER, 1);
            superTNT.setItemMeta(meta);
        }
        return superTNT;
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        ItemStack item = event.getItem();
        if (item != null && item.hasItemMeta()) {
            ItemMeta meta = item.getItemMeta();
            if (meta != null && meta.getPersistentDataContainer().has(superTNTKey, PersistentDataType.INTEGER)) {
                event.setCancelled(true);
                if (item.getAmount() > 1) {
                    item.setAmount(item.getAmount() - 1);
                } else {
                    player.getInventory().remove(item);
                }
                    TNTPrimed tnt = player.getWorld().spawn(player.getLocation(), TNTPrimed.class);
                    tnt.setFuseTicks(10 * 20);
                    tnt.setYield(20);

                    Vector direction = player.getLocation().getDirection().multiply(4);
                    tnt.setVelocity(direction);
            }
        }
    }
    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        if (entity.getEquipment() != null && entity.getEquipment().getHelmet() != null) {
            ItemStack helmet = entity.getEquipment().getHelmet();
            if (helmet.hasItemMeta()) {
                ItemMeta meta = helmet.getItemMeta();
                if (meta != null && meta.getPersistentDataContainer().has(superTNTKey, PersistentDataType.INTEGER)) {
                    TNTPrimed mainTnt = spawnTNT(entity.getLocation(), 5, 5 * 20);
                    if (mainTnt != null) {
                    }
                    for (int i = 0; i < 2; i++) {
                        TNTPrimed smallTnt = spawnTNT(entity.getLocation(), 3, 3 * 20);
                        if (smallTnt != null) {
                            smallTnt.setVelocity(entity.getLocation().getDirection().multiply(0.5).add(new Vector(Math.random() - 0.5, Math.random() * 0.75, Math.random() - 0.5)));
                        }
                    }
                }
            }
        }
    }

    private TNTPrimed spawnTNT(Location location, float yield, int fuseTicks) {
        if (location.getWorld() != null) {
            TNTPrimed tnt = (TNTPrimed) location.getWorld().spawnEntity(location, EntityType.PRIMED_TNT);
            tnt.setYield(yield);
            tnt.setFuseTicks(fuseTicks);
            return tnt;
        }
        return null;
    }
}
