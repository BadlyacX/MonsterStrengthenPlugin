package com.badlyac.MonsterStrengthen.Monsters.ZombieStrengthen;

import org.bukkit.Material;
import org.bukkit.entity.Zombie;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class ZombieWeaponizer {

    public static void equipZombie(Zombie zombie) {
        EntityEquipment equipment = zombie.getEquipment();
        if (equipment != null) {
            Random random = new Random();

            if (random.nextInt(100) < 20) {
                equipment.setItemInHand(new ItemStack(Material.BOW));
                zombie.setCanPickupItems(true);
            } else {
                ItemStack[] swords = {
                        new ItemStack(Material.WOOD_SWORD),
                        new ItemStack(Material.STONE_SWORD),
                        new ItemStack(Material.GOLD_SWORD),
                        new ItemStack(Material.IRON_SWORD),
                        new ItemStack(Material.DIAMOND_SWORD),
                        new ItemStack(Material.AIR)
                };

                ItemStack chosenSword = swords[random.nextInt(swords.length)];
                equipment.setItemInHand(chosenSword);
            }
            equipment.setItemInHandDropChance(0.5f);
        }
    }
}