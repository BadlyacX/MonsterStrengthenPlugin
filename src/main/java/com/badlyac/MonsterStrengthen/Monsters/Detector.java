package com.badlyac.MonsterStrengthen.Monsters;

import com.badlyac.MonsterStrengthen.Monsters.ZombieStrengthen.ZombieWeaponizer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.entity.Zombie;

public class Detector implements Listener {

    private static final double DETECTION_RANGE = 200.0;

    @EventHandler
    public void onEntityTarget(EntityTargetLivingEntityEvent event) {
        if (event.getTarget() instanceof Player) {
            Zombie zombie = (Zombie) event.getEntity();

            if (event.getTarget().getLocation().distance(zombie.getLocation()) <= DETECTION_RANGE) {
                ZombieWeaponizer.equipZombie(zombie);
            }
        }
    }
}