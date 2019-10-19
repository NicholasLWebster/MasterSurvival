package com.snowfallentertainment.mastersurvival.temp_modifiers;

public class ElevationModifier {

    private static final double PER_BLOCK_ELEVATION_EFFECT = 0.5;
    private static final double PER_BLOCK_CORE_EFFECT = 1.5;

    public static double getElevationModifier(int y){
        int heightRelativeToSeaLevel = y - 62;
        double modifier;
        modifier = heightRelativeToSeaLevel * PER_BLOCK_ELEVATION_EFFECT;
        if(y <= 32) {
            int offset = 31 - y;
            modifier -= (offset * PER_BLOCK_CORE_EFFECT);
        }
        return  0 - modifier; // because modifier effects are always additive
    }
}
