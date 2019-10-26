package com.snowfallentertainment.mastersurvival.temp_modifiers;

public class TimeOfDayModifier {
    private static final long TIME_LENGTH_OF_FULL_DAY = 24000;

    private static final double MAX_TEMP_EFFECT = 11.11;
    private static final double MIN_TEMP_EFFECT = -11.11;
    private static final double TIME_LENGTH_HALF_FULL_DAY = TIME_LENGTH_OF_FULL_DAY / 2;
    private static final double TIME_RELATIVE_TO_PI_MULTIPLIER = Math.PI / TIME_LENGTH_HALF_FULL_DAY;

    public static double getTimeOfDayModifierMultiplier(long time){
        double timeRelativeToPi = TIME_RELATIVE_TO_PI_MULTIPLIER * time;
        double multiplier = Math.sin(timeRelativeToPi);
        if(timeRelativeToPi >= 0)
            return multiplier * MAX_TEMP_EFFECT;
        else
            return multiplier * MIN_TEMP_EFFECT;
    }
}
