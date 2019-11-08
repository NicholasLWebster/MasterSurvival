package com.snowfallentertainment.mastersurvival;

import com.snowfallentertainment.mastersurvival.temp_modifiers.*;
import com.snowfallentertainment.mastersurvival.ui.TempGauge;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TempSystem {

    private static final double BASE_INDOOR_TEMP = 15.55;

    private Player linkedPlayer;

    private boolean useNearbyBlockModifier = true;
    public boolean useNearbyBlockModifier() {
        return useNearbyBlockModifier;
    }
    public void setUseNearbyBlockModifier(boolean useNearbyBlockModifier) {
        this.useNearbyBlockModifier = useNearbyBlockModifier;
    }


    public UUID getLinkedPlayerUUID() {
        return linkedPlayer.getUniqueId();
    }

    private Temperature temp;
    private TempGauge tempGauge;

    public TempSystem(Player linkedPlayer) {
        this.linkedPlayer = linkedPlayer;
        temp = new Temperature(getCalculatedTemp(linkedPlayer.getLocation()));
        tempGauge = new TempGauge(linkedPlayer, temp);
    }

    public void update() {
        Location playerLoc = linkedPlayer.getLocation();
        double calculatedTemp = getCalculatedTemp(playerLoc);
        double currentTemp = temp.getTempInCelsius();
        double deltaTemp = calculatedTemp - currentTemp;
        if (Math.abs(deltaTemp) > .5) {
            temp.setTempInCelsius(currentTemp + (deltaTemp / 4));
        } else {
            temp.setTempInCelsius(calculatedTemp);
        }
        tempGauge.updateTempDisplays(temp);
    }

    private double getCalculatedTemp(Location location) {
        long currentTime = location.getWorld().getTime(); // The relative time is analogous to hours * 1000 // taken from spigot API documentation
        boolean isStorming = location.getWorld().hasStorm();
        int elevation = location.getBlockY();

        double modifier = BiomeModifier.getBiomeModifier(location);
        modifier += TimeOfDayModifier.getTimeOfDayModifierMultiplier(currentTime);
//        modifier += TimeOfDayModifier.getSimpleTimeModifier(currentTime);
        modifier += WeatherModifier.getWeatherModifier(isStorming);
        double modifierMultiplier = ShelterModifierMultiplier.getShelterModifier(location);
        modifier *= modifierMultiplier;
        modifier += ElevationModifier.getElevationModifier(elevation);
        if(useNearbyBlockModifier){
            modifier += NearByBlocksModifier.getNearByBlockModifier(linkedPlayer);
        }

        return BASE_INDOOR_TEMP + modifier;
    }

}
