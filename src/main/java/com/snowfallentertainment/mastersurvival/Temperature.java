package com.snowfallentertainment.mastersurvival;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Temperature {

    public static double ConvertCelsiusToFahrenheit(double celsius){
        return (celsius * 1.8) + 32;
    }

    public static double ConvertFahrenheitToCelsius(double fahrenheit){
        return (fahrenheit - 32) / 1.8;
    }

    private double tempInCelsius;

    public Temperature(double tempInCelsius) {
        this.tempInCelsius = tempInCelsius;
    }

    public double getTempInCelsius() {
        return tempInCelsius;
    }

    public void setTempInCelsius(double tempInCelsius) {
        this.tempInCelsius = tempInCelsius;
    }

    public double  getTempInFahrenheit() {
        return ConvertCelsiusToFahrenheit(tempInCelsius);
    }

    public void setTempInFahrenheit(double tempInFahrenheit){
        this.tempInCelsius = ConvertFahrenheitToCelsius(tempInFahrenheit);
    }

    public int getCelsiusInt(){
        return (int) Math.round(tempInCelsius);
    }

    public int getFahrenheitInt() {
        return (int) Math.round(getTempInFahrenheit());
    }

    @Override
    public String toString() {
        DecimalFormat formatter = new DecimalFormat("#.#");
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        return formatter.format(tempInCelsius);
    }

    public String fahrenheitToString() {
        DecimalFormat formatter = new DecimalFormat("#.#");
        formatter.setRoundingMode(RoundingMode.HALF_UP);
        return formatter.format(getTempInFahrenheit());
    }
}
