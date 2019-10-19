package com.snowfallentertainment.mastersurvival;

import com.snowfallentertainment.mastersurvival.temp_modifiers.*;
import com.snowfallentertainment.mastersurvival.ui.TempGauge;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TempSystem {

    private static final double BASE_INDOOR_TEMP = 15.55;


    private Player linkedPlayer;
    public UUID getLinkedPlayerUUID() {
        return linkedPlayer.getUniqueId();
    }

    private Temperature temp;
    private TempGauge tempGauge;
    private boolean isActive;
    public boolean isActive() {
        return isActive;
    }
    public void setActive(boolean active) {
        if(active){
            tempGauge.activate();
        }
        isActive = active;
    }

    public TempSystem(Player linkedPlayer) {
        this.linkedPlayer = linkedPlayer;
        this.isActive = true;
        temp = new Temperature(getCalculatedTemp(linkedPlayer.getLocation()));
        tempGauge = new TempGauge(linkedPlayer, temp);
    }

    public void updateIfActive() {
        if(isActive){
            Location playerLoc = linkedPlayer.getLocation();
            double calculatedTemp = getCalculatedTemp(playerLoc);
            double currentTemp = temp.getTempInCelsius();
            double deltaTemp = calculatedTemp - currentTemp;
            if(Math.abs(deltaTemp) > .5) {
                temp.setTempInCelsius(currentTemp + (deltaTemp/4));
            }
            tempGauge.updateTempDisplays(temp);
        }
    }

    private double getCalculatedTemp(Location location) {
        long currentTime = location.getWorld().getTime(); // The relative time is analogous to hours * 1000 // taken from spigot API documentation
        boolean isStorming = location.getWorld().hasStorm();
        int elevation = location.getBlockY();

        double modifier = BiomeModifier.getBiomeModifier(location);
        modifier += TimeOfDayModifier.getSimpleTimeModifier(currentTime);
        modifier += WeatherModifier.getWeatherModifier(isStorming);
        double modifierMultiplier = ShelterModifierMultiplier.getShelterModifier(location);
        modifier *= modifierMultiplier;
        modifier += ElevationModifier.getElevationModifier(elevation);

        return BASE_INDOOR_TEMP + modifier;
    }
}
