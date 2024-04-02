package com.badlyac.MonsterStrengthen.Monsters.ZombieStrengthen;

import com.badlyac.MonsterStrengthen.MonsterStrengthenMain;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.entity.Zombie;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class BlockBreaker implements Listener {

    private static final List<Material> UNBREAKABLE_BLOCKS = Arrays.asList(
            Material.BEDROCK, Material.OBSIDIAN, Material.WATER, Material.LAVA
    );

    private static final double TNT_ZOMBIE_CHANCE = 0.05;

    @EventHandler
    public void onZombieSpawn(EntitySpawnEvent event) {
        if (event.getEntityType() == EntityType.ZOMBIE) {
            Zombie zombie = (Zombie) event.getEntity();
            Random rand = new Random();
            if (rand.nextDouble() < TNT_ZOMBIE_CHANCE) {
                EntityEquipment equipment = zombie.getEquipment();
                if (equipment != null) {
                    equipment.setHelmet(new ItemStack(Material.TNT));
                }

                zombie.setMetadata("BlockBreaker", new FixedMetadataValue(MonsterStrengthenMain.getInstance(), true));
            }
        }
    }

    @EventHandler
    public void onZombieDeath(EntityDeathEvent event) {
        if (event.getEntityType() == EntityType.ZOMBIE) {
            Zombie zombie = (Zombie) event.getEntity();

            if (zombie.hasMetadata("BlockBreaker")) {
                TNTPrimed tnt = (TNTPrimed) zombie.getWorld().spawnEntity(zombie.getLocation(), EntityType.PRIMED_TNT);
                tnt.setFuseTicks(40);
            }
        }
    }
}