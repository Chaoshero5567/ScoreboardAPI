package de.chaos.mc;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.Plugin;


import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class ScoreboardAPI implements Listener {
   private HashMap<UUID, BoardPLayer> players;
   private List<BoardLine> BoardLines;
   private Plugin plugin;

   private ScoreboardUpdater updater;
   String title;
   public ScoreboardAPI(Plugin plugin, List<BoardLine> BoardLines, String title) {
   this.players = new HashMap<UUID, BoardPLayer>();
   this.plugin = plugin;
   this.BoardLines = BoardLines;
   this.updater = new ScoreboardUpdater(players, plugin);
      Bukkit.getPluginManager().registerEvents(this, plugin);
   }

   // This is also overiding player specific boards
   public void updateGeneralBoards(List<BoardLine> BoardLines, int updateTime, String title) {
      for (Player player : Bukkit.getOnlinePlayers()) {
         createPlayerSpecificBoard(player, BoardLines, title);
      }
   }

   public void createPlayerSpecificBoard(Player player, List<BoardLine> BoardLines, String title) {
      EasyBoard easyBoard = new EasyBoard(BoardLines, null, title);
      BoardPLayer boardPLayer = new BoardPLayer(player.getUniqueId(), easyBoard);

      players.put(player.getUniqueId(), boardPLayer);
      updater.updateBoards(boardPLayer);
   }

   public void deleteBoards() {
      updater.stopUpdating();
   }

   @EventHandler
   public void onJoin(PlayerJoinEvent event) {
      createPlayerSpecificBoard(event.getPlayer(), BoardLines, title);
   }

   @EventHandler
   public void onLeave(PlayerQuitEvent event) {
      players.remove(event.getPlayer().getUniqueId());
   }
}