package fr.fullgreen.rank.commands;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import com.google.common.collect.Lists;
import fr.fullgreen.rank.Rank;
import fr.fullgreen.rank.RankList;

public final class RankCommand implements CommandExecutor, TabCompleter {
	  private final Rank rank;
	  
	  public RankCommand(Rank rank) {
	    this.rank = rank;
	  }
	  
	  public boolean onCommand(CommandSender s, Command c, String l, String[] a) {
	    if (s instanceof Player && 
	      this.rank.hasPowerInf((Player)s, 90))
	      return sendMessage(s, "n'avez pas la permission."); 
	    if (a.length < 2)
	      return sendMessage(s, "incorrect : /rank <Player> <Rank>"); 
	    OfflinePlayer target = Bukkit.getOfflinePlayer(a[0]);
	    if (target == null)
	      return sendMessage(s, "joueur n'a pas trouvé"); 
	    String uuid = target.getUniqueId().toString();
	    if (!this.rank.getConfig().contains(uuid))
	      return sendMessage(s, "joueur n'a pas trouvé"); 
	    RankList rankList = null;
	    try {
	      rankList = Rank.getRankById(Integer.parseInt(a[1]));
	    } catch (NumberFormatException nbe) {
	      try {
	        rankList = RankList.valueOf(a[1].toUpperCase());
	      } catch (Exception e) {
	        return sendMessage(s, "rank n'a pas trouvé");
	      } 
	    } 
	    Rank.changeRank(uuid, rankList);
	    if (target.isOnline()) {
	      sendMessage((CommandSender)target.getPlayer(), "grade a modifié");
	      this.rank.deletePlayer(target.getPlayer());
	      Rank.loadPlayer(target.getPlayer());
	    } 
	    return sendMessage(s, "§6"+ target.getName() + " bien obtenu sont grade " + rankList.getName().toLowerCase());
	  }
	  
	  private boolean sendMessage(CommandSender s, String msg) {
	    s.sendMessage(String.valueOf(String.valueOf(this.rank.getPrefix())) + msg);
	    return true;
	  }
	  
	  public List<String> onTabComplete(CommandSender s, Command c, String l, String[] a) {
	    List<String> tabComplete = Lists.newArrayList();
	    if (a.length == 2) {
	      byte b;
	      int i;
	      RankList[] arrayOfRankList;
	      for (i = (arrayOfRankList = RankList.values()).length, b = 0; b < i; ) {
	        RankList rankList = arrayOfRankList[b];
	        if (rankList.getName().toLowerCase().startsWith(a[1].toLowerCase()))
	          tabComplete.add(rankList.getName()); 
	        b = (byte)(b + 1);
	      } 
	      return tabComplete;
	    } 
	    return null;
	  }
	}
