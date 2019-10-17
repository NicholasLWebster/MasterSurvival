package com.snowfallentertainment.mastersurvival;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerEventListener implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event){
        event.getPlayer().sendMessage("Welcome to the server!");
        MasterSurvival plugin = MasterSurvival.getInstance();
        plugin.activateTempSystem(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        MasterSurvival plugin = MasterSurvival.getInstance();
        plugin.deactivateTempSystem(event.getPlayer());
    }
}
