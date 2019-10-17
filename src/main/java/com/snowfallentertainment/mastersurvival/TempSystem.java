package com.snowfallentertainment.mastersurvival;

import com.snowfallentertainment.mastersurvival.ui.TempGauge;
import org.bukkit.Location;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

import java.util.UUID;

public class TempSystem {
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
        temp = new Temperature(getBaseTemp(linkedPlayer.getLocation()));
        tempGauge = new TempGauge(linkedPlayer, temp);
    }

    public void updateIfActive() {
        if(isActive){
            Location playerLoc = linkedPlayer.getLocation();
            double baseTemp = getBaseTemp(playerLoc);
            double currentTemp = temp.getTempInCelsius();
            if(baseTemp < currentTemp) {
                temp.setTempInCelsius(currentTemp - 0.5);
            }
            else if(baseTemp > currentTemp) {
                temp.setTempInCelsius(currentTemp + 0.5);
            }
            tempGauge.updateTempDisplays(temp);
        }
    }

    private double getBaseTemp(Location playerLoc) {
        Biome currentBiome = linkedPlayer
                .getWorld()
                .getBiome(playerLoc.getBlockX(), playerLoc.getBlockZ());
        double biomeBaseTemp = getBaseBiomeTemp(currentBiome);

        double baseTemp = applyHeightModifier(biomeBaseTemp, playerLoc.getBlockY());
        return baseTemp;
    }

    private double getBaseBiomeTemp(Biome biome) {
        switch (biome) {
            case BADLANDS:
            case ERODED_BADLANDS:
            case BADLANDS_PLATEAU:
            case WOODED_BADLANDS_PLATEAU:
            case MODIFIED_BADLANDS_PLATEAU:
            case MODIFIED_WOODED_BADLANDS_PLATEAU:
                return 39.55;

            case BEACH:
            case STONE_SHORE:
                return 22.22;

            case SNOWY_BEACH:
                return 0.00;

            case DESERT:
            case DESERT_HILLS:
            case DESERT_LAKES:
                return 40.55;

            case FOREST:
                return 15.55;

            case FLOWER_FOREST:
                return 27.77;

            case DARK_FOREST:
            case DARK_FOREST_HILLS:
                return 14.00;

            case BIRCH_FOREST:
            case TALL_BIRCH_FOREST:
            case TALL_BIRCH_HILLS:
            case BIRCH_FOREST_HILLS:
                return 10.50;

            case JUNGLE:
            case JUNGLE_EDGE:
            case JUNGLE_HILLS:
            case MODIFIED_JUNGLE_EDGE:
                return 29.44;

            case BAMBOO_JUNGLE:
            case MODIFIED_JUNGLE:
            case BAMBOO_JUNGLE_HILLS:
                return 26.44;

            case OCEAN:
            case DEEP_OCEAN:
                return 10.00;

            case COLD_OCEAN:
            case DEEP_COLD_OCEAN:
                return 4.44;

            case LUKEWARM_OCEAN:
            case DEEP_LUKEWARM_OCEAN:
                return 24.55;

            case WARM_OCEAN:
            case DEEP_WARM_OCEAN:
                return 27.55;

            case FROZEN_OCEAN:
            case DEEP_FROZEN_OCEAN:
                return 0;

            case RIVER:
                return 12.00;

            case FROZEN_RIVER:
                return 0;

            case SWAMP:
            case SWAMP_HILLS:
                return 25;

            case TAIGA:
            case SNOWY_TAIGA:
            case TAIGA_HILLS:
            case TAIGA_MOUNTAINS:
            case GIANT_TREE_TAIGA:
            case SNOWY_TAIGA_HILLS:
            case GIANT_SPRUCE_TAIGA:
            case SNOWY_TAIGA_MOUNTAINS:
            case GIANT_TREE_TAIGA_HILLS:
            case GIANT_SPRUCE_TAIGA_HILLS:
                return -10;

            case NETHER:
                return 57.80;

            case PLAINS:
                return 19.44;

            case SUNFLOWER_PLAINS:
                return 26.66;

            case SAVANNA:
            case SAVANNA_PLATEAU:
            case SHATTERED_SAVANNA:
            case SHATTERED_SAVANNA_PLATEAU:
                return 15.00;

            case THE_END:
            case END_MIDLANDS:
            case END_BARRENS:
            case SMALL_END_ISLANDS:
            case END_HIGHLANDS:
                return 20.55;

            case SNOWY_MOUNTAINS:
                return 0;

            case MOUNTAINS:
            case WOODED_MOUNTAINS:
            case GRAVELLY_MOUNTAINS:
            case MODIFIED_GRAVELLY_MOUNTAINS:
            case MOUNTAIN_EDGE:
                return 7.22;

            case THE_VOID:
                return -273.15;

            case ICE_SPIKES:
                return -3.88;

            case SNOWY_TUNDRA:
                return -4;

            case WOODED_HILLS:
                return 14.55;

            case MUSHROOM_FIELDS:
            case MUSHROOM_FIELD_SHORE:
                return 25;


        }
        return 32.5;
    }

    private double applyHeightModifier(double tempInCelsius, int y){
        int heightRelativeToSeaLevel = y - 62;
        double modifier = heightRelativeToSeaLevel * 0.04;
        return  tempInCelsius - modifier;
    }
}
