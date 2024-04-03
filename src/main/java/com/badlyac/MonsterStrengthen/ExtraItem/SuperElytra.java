package com.badlyac.MonsterStrengthen.ExtraItem;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

public class SuperElytra  implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "Only players can use this command.");
            return true;
        }

        if (cmd.getName().equalsIgnoreCase("elytra")) {
            Player player = (Player) sender;
            ItemStack elytra = new ItemStack(Material.ELYTRA);
            ItemMeta elytraMeta = elytra.getItemMeta();
            if (elytraMeta != null) {
                elytraMeta.setUnbreakable(true);
                elytra.setItemMeta(elytraMeta);
            }
            player.getInventory().addItem(elytra);

            ItemStack fireworks = new ItemStack(Material.FIREWORK_ROCKET, 64);
            FireworkMeta fireworkMeta = (FireworkMeta) fireworks.getItemMeta();
            if (fireworkMeta != null) {
                fireworkMeta.setPower(1);
                fireworks.setItemMeta(fireworkMeta);
            }
            player.getInventory().addItem(fireworks);

            sender.sendMessage(ChatColor.GREEN + "You have been given an unbreakable Elytra and infinite fireworks!");
            return true;
        }

        return false;
    }
}