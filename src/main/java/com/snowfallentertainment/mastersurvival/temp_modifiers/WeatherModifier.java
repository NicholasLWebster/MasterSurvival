package com.snowfallentertainment.mastersurvival.temp_modifiers;

public class WeatherModifier {

    private static final double WEATHER_EFFECT = 5.55;

    public static double getWeatherModifier(boolean isStorming) {
        if(isStorming) {
            return WEATHER_EFFECT;
        }
        else return 0;
    }
}
