package com.snowfallentertainment.mastersurvival;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEventListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        MasterSurvival pluginInstance = MasterSurvival.getInstance();
        pluginInstance.activateTempSystem(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        MasterSurvival pluginInstance = MasterSurvival.getInstance();
        pluginInstance.deactivateTempSystem(event.getPlayer());
    }
}
