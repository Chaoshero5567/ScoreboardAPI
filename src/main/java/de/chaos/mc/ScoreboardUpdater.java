package de.chaos.mc.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ScoreboardUpdater {
    private Scoreboard scoreboard;
    private HashMap<UUID, BoardPLayer> map;
    private BukkitTask task;

    public ScoreboardUpdater(HashMap<UUID, BoardPLayer> map, Plugin plugin) {
        this.map = map;

        this.scoreboard = Bukkit.getScoreboardManager().getMainScoreboard();

    }

    public void startUpdating(Plugin plugin, int updateTime) {
        this.task = new BukkitRunnable() {
            public void run() {
                for (UUID uuid : map.keySet()) {
                    BoardPLayer boardPLayer = map.get(uuid);
                    Player player = Bukkit.getPlayer(uuid);

                    if (boardPLayer.getEasyBoard().getObjective() == null) {
                        Objective objective = scoreboard.registerNewObjective(player.getName(),"board");
                        List<String> colours = new ArrayList<String>();
                        for (ChatColor color : ChatColor.values()) {
                            colours.add(color.toString());
                        }

                        for (BoardLine boardLine : boardPLayer.getEasyBoard().getLines()) {
                            Team team = scoreboard.registerNewTeam(player.getName() + "." + boardLine.getLINENAME());
                            team.setSuffix(boardLine.getLINESUFFIX());
                            team.setPrefix(boardLine.getLINEPREFIX());
                            team.addEntry(colours.get(boardLine.getPOSITION()));
                        }

                        boardPLayer.getEasyBoard().setObjective(objective);
                    } else {
                        for (BoardLine boardLine : boardPLayer.getEasyBoard().getLines()) {
                            if (boardLine.isUpdate()) {
                                Team team = scoreboard.getTeam(player.getName()+"."+boardLine.getLINENAME());
                                team.setPrefix(boardLine.getLINEPREFIX());
                                team.setSuffix(boardLine.getLINESUFFIX());
                            }
                        }
                    }
                }
            }
        }.runTaskTimerAsynchronously(plugin, 0, 20*1);
    }

    public void stopUpdating() {
        task.cancel();
    }

    public void updateBoards(BoardPLayer boardPLayer) {
        map.put(boardPLayer.getUuid(), boardPLayer);
    }
}
