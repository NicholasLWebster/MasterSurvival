package com.snowfallentertainment.mastersurvival.temp_modifiers;

public class TimeOfDayModifier {


    private static final double DAY_MAX_TEMP_EFFECT = 8.25;
    private static final double NIGHT_MAX_TEMP_EFFECT = -9.25;
    private static final long TIME_OF_SUN_RISE = 22000;
    private static final long TIME_START_COOL_DOWN = 8000;
    private static final long TIME_OF_SUN_SET = 12000;
    private static final long TIME_LENGTH_OF_FULL_DAY = 24000;

    private static final double MIN_MAX_TIME_EFFECT = DAY_MAX_TEMP_EFFECT - NIGHT_MAX_TEMP_EFFECT;
    private static final long TIME_LENGTH_OF_DAY = TIME_OF_SUN_SET + (TIME_LENGTH_OF_FULL_DAY - TIME_OF_SUN_SET);
    private static final long TIME_LENGTH_OF_NIGHT = TIME_OF_SUN_RISE - TIME_OF_SUN_SET;
    private static final long TIME_LENGTH_OF_SUN_RISE_BEFORE_NEW_DAY = TIME_LENGTH_OF_FULL_DAY - TIME_OF_SUN_RISE;
    private static final double TIME_TEMP_UNIT_DAY = MIN_MAX_TIME_EFFECT / TIME_LENGTH_OF_DAY;
    private static final double TIME_TEMP_UNIT_NIGHT = MIN_MAX_TIME_EFFECT / TIME_LENGTH_OF_NIGHT;

    private static final double V2_MAX_TEMP_EFFECT = 11.11;
    private static final double V2_MIN_TEMP_EFFECT = -11.11;
    private static final double TIME_LENGTH_HALF_FULL_DAY = TIME_LENGTH_OF_FULL_DAY / 2;
    private static final double TIME_RELATIVE_TO_PI_MULTIPLIER = Math.PI / TIME_LENGTH_HALF_FULL_DAY;

    public static double getSimpleTimeModifier(long time) {
        if(time < TIME_START_COOL_DOWN) {
            double timeSinceSunRiseOrSet = time + TIME_LENGTH_OF_SUN_RISE_BEFORE_NEW_DAY;
            return timeSinceSunRiseOrSet * TIME_TEMP_UNIT_DAY;
        }
        else if (time > TIME_OF_SUN_RISE)  {
            double timeSinceSunRiseOrSet = time - TIME_OF_SUN_RISE;
            return NIGHT_MAX_TEMP_EFFECT + timeSinceSunRiseOrSet * TIME_TEMP_UNIT_DAY;
        }
        else {
            double timeSinceSunRiseOrSet = time - TIME_OF_SUN_SET;
            return DAY_MAX_TEMP_EFFECT - timeSinceSunRiseOrSet * TIME_TEMP_UNIT_NIGHT;
        }
    }

    public static double getComplexTimeModifierV2(long time){
        double timeRelativeToPi = TIME_RELATIVE_TO_PI_MULTIPLIER * time;
        double multiplier = Math.sin(timeRelativeToPi);
        if(timeRelativeToPi >= 0)
            return multiplier * V2_MAX_TEMP_EFFECT;
        else
            return multiplier * V2_MIN_TEMP_EFFECT;
    }

    @Deprecated
    public static double getComplexTimeModifier(long time)
    {
        double hours = time/1000;
//        double offsetHours;
//        if(hours <= 1) {
//            offsetHours = 24 - ( 1 - hours);
//        }
//        else {
//            offsetHours = hours - 1;
////        }
        double hoursRelativeToPi = hours * Math.PI / 12;
        double modifierMultiplier = 0;
        modifierMultiplier += Math.cos(hoursRelativeToPi);

//        if(hoursRelativeToPi <= (3 * Math.PI / 3)) {
//            modifierMultiplier -= Math.cos(hoursRelativeToPi * 4/3);
//        }
//        else {
//            modifierMultiplier = Math.cos((hoursRelativeToPi - 3 * Math.PI / 4) * 0.8);
//        }

        return modifierMultiplier * MIN_MAX_TIME_EFFECT;

    }
}
