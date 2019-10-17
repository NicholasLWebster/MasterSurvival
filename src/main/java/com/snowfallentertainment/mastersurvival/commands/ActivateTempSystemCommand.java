package com.snowfallentertainment.mastersurvival.commands;

import com.snowfallentertainment.mastersurvival.MasterSurvival;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ActivateTempSystemCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player)sender;
            MasterSurvival.getInstance().activateTempSystem(player);
            return true;
        }

        sender.sendMessage("Only players can use the ActivateTempSystemCommand");
        return false;
    }
}
