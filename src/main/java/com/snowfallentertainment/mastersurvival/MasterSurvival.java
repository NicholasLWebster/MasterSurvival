package com.snowfallentertainment.mastersurvival;

import com.snowfallentertainment.mastersurvival.commands.DisplayModifierStatsCommand;
import com.snowfallentertainment.mastersurvival.commands.ToggleNearByBlockModifierCommand;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public final class MasterSurvival extends JavaPlugin {

    // Singleton design pattern:
    private static MasterSurvival instance;
    public static MasterSurvival getInstance() {
        return instance;
    }

    private HashMap<UUID, TempSystem> tempSystems;

    public MasterSurvival() {
        // Singleton design pattern:
        if(instance == null){
            instance = this;
            tempSystems = new HashMap<>();
        }
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("MasterSurvival plugin enabled...");
        this.getCommand("temp-stats").setExecutor(new DisplayModifierStatsCommand());
        this.getCommand("temp-checkNearbyBlocks").setExecutor(new ToggleNearByBlockModifierCommand());
        registerEvents();
        scheduleUpdate();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("MasterSurvival plugin disabled...");
    }

    private void registerEvents() {
        PluginManager pm = getServer().getPluginManager();
        PlayerEventListener playerEventListener = new PlayerEventListener();
        pm.registerEvents(playerEventListener, this);
    }

    private void scheduleUpdate() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask(this, () -> {
            updateTempSystems();
        }, 100, 20);
    }

    public void activateTempSystem(Player player){
        TempSystem newTempSystem = new TempSystem(player);
        tempSystems.put(player.getUniqueId(), newTempSystem);
    }

    public void deactivateTempSystem(Player player){
        tempSystems.remove(player.getUniqueId());
    }

    public int getTempSystemCount() {
        return  tempSystems.size();
    }

    public void updateTempSystems(){
        for (Map.Entry<UUID, TempSystem> system : tempSystems.entrySet()) {
            system.getValue().update();
        }
    }

    public void togglePlayerNearByTempModifer(Player player, boolean isEnabled){
        TempSystem ts = tempSystems.get(player.getUniqueId());
        ts.setUseNearbyBlockModifier(isEnabled);
    }

    public boolean getUseNearbyBlockModifier(Player player){
        return tempSystems.get(player.getUniqueId()).useNearbyBlockModifier();
    }
}
