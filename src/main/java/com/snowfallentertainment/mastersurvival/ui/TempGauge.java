package com.snowfallentertainment.mastersurvival.ui;

import com.snowfallentertainment.mastersurvival.MasterSurvival;
import com.snowfallentertainment.mastersurvival.Temperature;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class TempGauge {
    private Player linkedPlayer;
    public Player getLinkedPlayer(){
        return linkedPlayer;
    }

    private TextComponent uiComponent;

    private Scoreboard scoreboard;
    private Score score_celsius;
    private Score score_fahrenheit;
    private String teamName;

    public TempGauge(Player linkedPlayer, Temperature temp) {
        this.linkedPlayer = linkedPlayer;
        initialize_scoreBoard(linkedPlayer);
        int numOfTempSystems = MasterSurvival.getInstance().getTempSystemCount();
        this.teamName = "temp" + numOfTempSystems;
        //initialize_textComponent();
        updateTempDisplays(temp);
    }

    public void activate(){
        initialize_scoreBoard(linkedPlayer);
    }

    private void initialize_scoreBoard(Player linkedPlayer) {
        ScoreboardManager boardManager = Bukkit.getScoreboardManager();
        scoreboard = boardManager.getNewScoreboard();
        Team playerTeam = scoreboard.registerNewTeam("temp0");
        playerTeam.addEntry(linkedPlayer.getName());
        Objective objective = scoreboard.registerNewObjective("temp","temperature", "Temp Stats:");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("Temp:");
        score_celsius = objective.getScore(ChatColor.GREEN + "C°:");
        score_fahrenheit = objective.getScore(ChatColor.GREEN + "F°:");
        linkedPlayer.setScoreboard(scoreboard);
    }

    private void initialize_textComponent() {
        uiComponent = new TextComponent();
        linkedPlayer.spigot().sendMessage(uiComponent);
    }

    public void updateTempDisplays(Temperature temp) {
        updateDisplayTemp_scoreBoard(temp);
        //updateDisplayTemp_textComponent(temp);
    }

    private void updateDisplayTemp_scoreBoard(Temperature temp) {
        score_celsius.setScore(temp.getCelsiusInt());
        score_fahrenheit.setScore(temp.getFahrenheitInt());
    }

    private void updateDisplayTemp_textComponent(Temperature temp) {
        uiComponent.setText(temp.toString());
    }
}
