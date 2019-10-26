package com.snowfallentertainment.mastersurvival.commands;

import com.snowfallentertainment.mastersurvival.temp_modifiers.*;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DisplayModifierStatsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            Location playerLoc = player.getLocation();
            long time = playerLoc.getWorld().getTime();


            String biomeModifier = "Biome: " + BiomeModifier.getBiomeModifier(playerLoc);
            //String timeOfDayModifier = "ToD: " + TimeOfDayModifier.getSimpleTimeModifier(time);
            String timeOfDayModifierV2 = "ToDv2: " + TimeOfDayModifier.getTimeOfDayModifierMultiplier(time);
            String weatherModifier = "Weather: " + WeatherModifier.getWeatherModifier(playerLoc.getWorld().hasStorm());
            String shelterModifier = "Shelter: " + ShelterModifierMultiplier.getShelterModifier(playerLoc);
            String elevationModifier = "Elevation: " + ElevationModifier.getElevationModifier(playerLoc.getBlockY());

            player.sendMessage(biomeModifier);
            player.sendMessage(timeOfDayModifierV2);
            player.sendMessage(weatherModifier);
            player.sendMessage(shelterModifier);
            player.sendMessage(elevationModifier);
        }
        return true;
    }
}
