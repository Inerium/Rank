package fr.fullgreen.rank.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import fr.fullgreen.rank.Rank;
import fr.fullgreen.rank.RankList;


public class PlayerListener implements Listener {
	  private final Rank rank;
	  
	  public PlayerListener(Rank rank) {
	    this.rank = rank;
	  }
	  
	  @EventHandler
	  private void playerJoin(PlayerJoinEvent pje) {
	    Rank.loadPlayer(pje.getPlayer());
	    pje.getPlayer().setScoreboard(this.rank.getScoreboard());
	  }
	  
	  @EventHandler
	  private void playerQuit(PlayerQuitEvent pqe) {
	    this.rank.deletePlayer(pqe.getPlayer());
	  }
	  
	  @EventHandler
	  private void playerChat(AsyncPlayerChatEvent pce) {
	    RankList rankList = Rank.getPlayerRank(pce.getPlayer());
	    pce.setFormat(String.valueOf(String.valueOf(rankList.getPrefix())) + pce.getPlayer().getName() + rankList.getSuffix() + rankList.getChatSeparator() + "%2$s");
	  }
	}
