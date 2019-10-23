package com.snowfallentertainment.mastersurvival.temp_modifiers;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;

public class ShelterModifierMultiplier {
    private static final double BASE_SHELTER_EFFECT = 0.5; // reduces other modifiers effect by half
    private static final double MAX_SHELTER_STRENGTH = 5;
    private static final double INCREMENT_SHELTER_EFFECT = (1 - BASE_SHELTER_EFFECT) / MAX_SHELTER_STRENGTH;



    // Block shelter strengths:
    private static final double DEFAULT_BLOCK_SHELTER_STRENGTH = 1.0;
    private static final double AIR_BLOCK_SHELTER_STRENGTH = 0;
    private static final double LEAVES_BLOCK_SHELTER_STRENGTH = .25;
    private static final double GLASS_BLOCK_SHELTER_STRENGTH = .5;

    public static double getShelterModifier(Location location) {
        double shelterStrength = getShelterStrength(location);
        if(shelterStrength > 0){
            double shelterEffectFromIncrement = INCREMENT_SHELTER_EFFECT * shelterStrength;
            return 1 - (BASE_SHELTER_EFFECT + shelterEffectFromIncrement);
        }
        else {
            return 1;
        }
    }

    private static double getShelterStrength (Location location) {
        if(isNether(location))
            return 0;

        int x = location.getBlockX();
        int y = location.getBlockY();
        int yMax = location.getWorld().getMaxHeight();
        int z = location.getBlockZ();

        int shelterStrength = 0;

        for (int i = y + 1; i < yMax; i++) {
            Block block = location.getWorld().getBlockAt(x, i, z);
            shelterStrength += getBlockShelterStrength(block);
        }
        return shelterStrength < MAX_SHELTER_STRENGTH ? shelterStrength : MAX_SHELTER_STRENGTH;
    }

    private static boolean isNether(Location location){
        Biome currentBiome = location
                .getWorld()
                .getBiome(location.getBlockX(), location.getBlockZ());
        return currentBiome == Biome.NETHER;
    }

    private static double getBlockShelterStrength(Block block) {
        Material mat = block.getType();
        switch (mat){
            case AIR:
            case CAVE_AIR:
            case VOID_AIR:
                return  AIR_BLOCK_SHELTER_STRENGTH;

            case ACACIA_LEAVES:
            case BIRCH_LEAVES:
            case DARK_OAK_LEAVES:
            case JUNGLE_LEAVES:
            case OAK_LEAVES:
            case SPRUCE_LEAVES:
                return LEAVES_BLOCK_SHELTER_STRENGTH;

            case GLASS:
            case RED_STAINED_GLASS:
            case BLUE_STAINED_GLASS:
            case CYAN_STAINED_GLASS:
            case GRAY_STAINED_GLASS:
            case LIME_STAINED_GLASS:
            case PINK_STAINED_GLASS:
            case BLACK_STAINED_GLASS:
            case BROWN_STAINED_GLASS:
            case GREEN_STAINED_GLASS:
            case WHITE_STAINED_GLASS:
            case ORANGE_STAINED_GLASS:
            case PURPLE_STAINED_GLASS:
            case YELLOW_STAINED_GLASS:
            case MAGENTA_STAINED_GLASS:
            case LIGHT_BLUE_STAINED_GLASS:
            case LIGHT_GRAY_STAINED_GLASS:
            case LEGACY_STAINED_GLASS:

            case GLASS_PANE:
            case RED_STAINED_GLASS_PANE:
            case BLUE_STAINED_GLASS_PANE:
            case CYAN_STAINED_GLASS_PANE:
            case LIME_STAINED_GLASS_PANE:
            case GRAY_STAINED_GLASS_PANE:
            case PINK_STAINED_GLASS_PANE:
            case BLACK_STAINED_GLASS_PANE:
            case BROWN_STAINED_GLASS_PANE:
            case GREEN_STAINED_GLASS_PANE:
            case WHITE_STAINED_GLASS_PANE:
            case ORANGE_STAINED_GLASS_PANE:
            case PURPLE_STAINED_GLASS_PANE:
            case YELLOW_STAINED_GLASS_PANE:
            case MAGENTA_STAINED_GLASS_PANE:
            case LIGHT_BLUE_STAINED_GLASS_PANE:
            case LIGHT_GRAY_STAINED_GLASS_PANE:
            case LEGACY_STAINED_GLASS_PANE:

            case GLASS_BOTTLE:
            case LEGACY_GLASS_BOTTLE:

            case LEGACY_THIN_GLASS:

            case LEGACY_GLASS:
                return GLASS_BLOCK_SHELTER_STRENGTH;
        }

        return DEFAULT_BLOCK_SHELTER_STRENGTH;
    }
}
