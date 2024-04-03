package com.badlyac.MonsterStrengthen.Monsters.ZombieStrengthen;

import com.badlyac.MonsterStrengthen.ExtraItem.SuperTNT;
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

            if (random.nextInt(100) < 10) {
                ItemStack superTNT = SuperTNT.createSuperTNT();
                equipment.setHelmet(superTNT);
            } else {
                ItemStack[] swords = {
                        new ItemStack(Material.WOODEN_SWORD),
                        new ItemStack(Material.STONE_SWORD),
                        new ItemStack(Material.GOLDEN_APPLE),
                        new ItemStack(Material.IRON_SWORD),
                        new ItemStack(Material.DIAMOND_SWORD),
                        new ItemStack(Material.NETHERITE_AXE),
                        new ItemStack(Material.NETHERITE_SWORD),
                        new ItemStack(Material.TOTEM_OF_UNDYING),
                        new ItemStack(Material.AIR)
                };

                ItemStack chosenSword = swords[random.nextInt(swords.length)];
                equipment.setItemInMainHand(chosenSword);
            }
            equipment.setItemInMainHandDropChance(0.25f);
        }
    }

    // Assuming you have a separate class named SuperTNT that has a method createSuperTNT()
    // If not, you will need to create this method or class as per the previous instructions.
}