package com.snowfallentertainment.mastersurvival.commands;

import com.snowfallentertainment.mastersurvival.MasterSurvival;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleNearByBlockModifierCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
            if(args.length > 1) {
                player.sendMessage("Command only takes 1 parameter with two values 'true' or 'false'");
            }
            else if (args[0].equalsIgnoreCase("TRUE")) {
                player.sendMessage("You have turned ON the NearByBlockTempModifier");
                MasterSurvival.getInstance().togglePlayerNearByTempModifer(player, true);
            }
            else if (args[0].equalsIgnoreCase("FALSE")){
                player.sendMessage("You have turned OFF the NearByBlockTempModifier");
                MasterSurvival.getInstance().togglePlayerNearByTempModifer(player, false);
            }
            else {
                player.sendMessage("you can only enter 'true' or 'false'");
            }
        }
        else {
            sender.sendMessage("Only a player can run that command.");
        }
        return true;
    }
}
