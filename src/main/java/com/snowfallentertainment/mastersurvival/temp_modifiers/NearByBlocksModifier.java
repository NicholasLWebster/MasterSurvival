package com.snowfallentertainment.mastersurvival.temp_modifiers;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

public class NearByBlocksModifier {
    private static final int RADIUS = 16;
    private static final double MAX_EFFECT_FURNACE = 5;
    private static final double MAX_LAVA_EFFECT = 2.5;
    private static final double MAX_TORCH_EFFECT = 1;


    private static double getBlockModifierBaseValue(Block block) {
        Material blockType = block.getType();
        block.getTemperature();
        switch (blockType) {
            case FURNACE:
                return MAX_EFFECT_FURNACE;
            case LAVA:
                return MAX_LAVA_EFFECT;
            case TORCH:
                return MAX_TORCH_EFFECT;
        }
        return  0;
    }

    public static double getNearByBlockModifier(Player player) {
        Location originLocation = player.getLocation();
        int xOrigin = originLocation.getBlockX();
        int yOrigin = originLocation.getBlockY();
        int zOrigin = originLocation.getBlockZ();

        int yStart = 0 - RADIUS;
        int yEnd = 0 + RADIUS;

        double tempModifier = 0;

        for (int y = yStart; y <= yEnd; y++) {
            int xRange = RADIUS - Math.abs(y) - 1; // -1 to account for origin block
            int xStart = 0 - xRange;
            int xEnd = 0 + xRange;
            for (int x = xStart; x <= xEnd; x++) {
                int zRange = RADIUS - Math.abs(y) - Math.abs(x) - 1; // -1 to account for origin block
                int zStart = 0 - zRange;
                int zEnd = 0 + zRange;
                for (int z = zStart; z <= zEnd; z++) {
                    int xWorking = x + xOrigin;
                    int yWorking = y + yOrigin;
                    int zWorking = z + zOrigin;
                    Block block = originLocation.getWorld().getBlockAt(xWorking, yWorking, zWorking);
                    double baseEffect = getBlockModifierBaseValue(block);
                    if(baseEffect > 0){
                        double distance = originLocation.distance(block.getLocation());
                        double relativeDistance = 1 - (distance / RADIUS);
                        tempModifier += baseEffect * relativeDistance;
                    }
                }
            }
        }
        return tempModifier;
    }
}
