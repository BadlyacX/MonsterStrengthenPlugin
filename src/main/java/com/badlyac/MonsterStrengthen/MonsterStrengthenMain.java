package com.badlyac.MonsterStrengthen;

import com.badlyac.MonsterStrengthen.ExtraItem.SuperTNT;
import com.badlyac.MonsterStrengthen.Monsters.Detector;
import com.badlyac.MonsterStrengthen.Monsters.ZombieStrengthen.BlockBreaker;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MonsterStrengthenMain extends JavaPlugin {
    public static MonsterStrengthenMain instance;
    @Override
    public void onEnable() {
        instance = this;
        Bukkit.getServer().getPluginManager().registerEvents(new Detector(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new BlockBreaker(), this);
        Bukkit.getServer().getPluginManager().registerEvents(new SuperTNT(this), this);
    }
    public static MonsterStrengthenMain getInstance() {
        return instance;
    }
}
