package com.snowfallentertainment.mastersurvival;

import com.snowfallentertainment.mastersurvival.commands.ActivateTempSystemCommand;
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
        this.getCommand("temp-gauge").setExecutor(new ActivateTempSystemCommand());
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
//        if(tempSystems.containsKey(player.getUniqueId())){
//            tempSystems.get(player.getUniqueId()).setActive(true);
//        }
//        else {
            TempSystem newTempSystem = new TempSystem(player);
            tempSystems.put(player.getUniqueId(), newTempSystem);
        //}
    }

    public void deactivateTempSystem(Player player){
        tempSystems.remove(player.getUniqueId());
//        if(tempSystems.containsKey(player.getUniqueId())){
//            tempSystems.get(player.getUniqueId()).setActive(false);
//        }
//        else {
//            String errorMessage = "Temp system for " + player.getDisplayName() + "not found!";
//            getLogger().log(Level.WARNING, errorMessage);
//        }
    }

    public int getTempSystemCount() {
        return  tempSystems.size();
    }

    public void updateTempSystems(){
        for (Map.Entry<UUID, TempSystem> system : tempSystems.entrySet()) {
            system.getValue().updateIfActive();
        }
    }
}
