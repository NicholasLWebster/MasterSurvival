package com.snowfallentertainment.mastersurvival.temp_modifiers;

import org.bukkit.Location;
import org.bukkit.block.Biome;

public class BiomeModifier {

    public static double getBiomeModifier(Location location) {
        Biome biome = location
                .getWorld()
                .getBiome(location.getBlockX(), location.getBlockZ());

        switch (biome) {
            case BADLANDS:
            case ERODED_BADLANDS:
            case BADLANDS_PLATEAU:
            case WOODED_BADLANDS_PLATEAU:
            case MODIFIED_BADLANDS_PLATEAU:
            case MODIFIED_WOODED_BADLANDS_PLATEAU:
                return 17.95;

            case BEACH:
            case STONE_SHORE:
                return 6.67;

            case SNOWY_BEACH:
                return -15.55;

            case DESERT:
            case DESERT_HILLS:
            case DESERT_LAKES:
                return 17.95;

            case FOREST:
                return 6.11;

            case FLOWER_FOREST:
                return 11.22;

            case DARK_FOREST:
            case DARK_FOREST_HILLS:
                return -1.55;

            case BIRCH_FOREST:
            case TALL_BIRCH_FOREST:
            case TALL_BIRCH_HILLS:
            case BIRCH_FOREST_HILLS:
                return -5.05;

            case JUNGLE:
            case JUNGLE_EDGE:
            case JUNGLE_HILLS:
            case MODIFIED_JUNGLE_EDGE:
                return 13.89;

            case BAMBOO_JUNGLE:
            case MODIFIED_JUNGLE:
            case BAMBOO_JUNGLE_HILLS:
                return 0.89;

            case OCEAN:
            case DEEP_OCEAN:
                return -5.55;

            case COLD_OCEAN:
            case DEEP_COLD_OCEAN:
                return -11.11;

            case LUKEWARM_OCEAN:
            case DEEP_LUKEWARM_OCEAN:
                return 9;

            case WARM_OCEAN:
            case DEEP_WARM_OCEAN:
                return 27.55;

            case FROZEN_OCEAN:
            case DEEP_FROZEN_OCEAN:
                return -15.55;

            case RIVER:
                return -3.55;

            case FROZEN_RIVER:
                return -15.55;

            case SWAMP:
            case SWAMP_HILLS:
                return 9.45;

            case TAIGA_MOUNTAINS:
            case TAIGA_HILLS:
            case GIANT_TREE_TAIGA:
            case GIANT_SPRUCE_TAIGA:
            case GIANT_TREE_TAIGA_HILLS:
            case GIANT_SPRUCE_TAIGA_HILLS:
            case TAIGA:
                return -2.78;

            case SNOWY_TAIGA:
            case SNOWY_TAIGA_HILLS:
            case SNOWY_TAIGA_MOUNTAINS:
                return -15.55;

            case NETHER:
                return 42.25;

            case PLAINS:
                return 6.7;

            case SUNFLOWER_PLAINS:
                return 11.11;

            case SAVANNA:
            case SAVANNA_PLATEAU:
            case SHATTERED_SAVANNA:
            case SHATTERED_SAVANNA_PLATEAU:
                return 6.5;

            case THE_END:
            case END_MIDLANDS:
            case END_BARRENS:
            case SMALL_END_ISLANDS:
            case END_HIGHLANDS:
                return 5;

            case SNOWY_MOUNTAINS:
                return -15.55;

            case MOUNTAINS:
            case WOODED_MOUNTAINS:
            case GRAVELLY_MOUNTAINS:
            case MODIFIED_GRAVELLY_MOUNTAINS:
            case MOUNTAIN_EDGE:
                return -8.33;

            case THE_VOID:
                return -288.70;

            case ICE_SPIKES:
                return -19.43;

            case SNOWY_TUNDRA:
                return -19.55;

            case WOODED_HILLS:
                return 6;

            case MUSHROOM_FIELDS:
            case MUSHROOM_FIELD_SHORE:
                return 9.45;
        }
        return 32.5;
    }
}
