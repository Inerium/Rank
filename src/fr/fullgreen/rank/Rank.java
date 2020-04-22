package fr.fullgreen.rank;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import com.google.common.collect.Maps;

public class Rank {
  private static final Map<String, RankList> playerRanks = Maps.newHashMap();
  
  private static Scoreboard scoreboard;
  
  private final Plugin plugin;
  
  private static FileConfiguration config;
  
  private static File file;
  
  public Rank(Plugin plugin) {
    this.plugin = plugin;
    initConfig();
  }
  
  public final Plugin getPlugin() {
    return this.plugin;
  }
  
  public String getPrefix() {
    return "";
  }
  
  public final Scoreboard getScoreboard() {
    return scoreboard;
  }
  
  public final FileConfiguration getConfig() {
    return config;
  }
  
  private void initConfig() {
    File f = new File("plugins/Rank");
    if (!f.exists())
      f.mkdirs(); 
    file = new File(f, "rank.yml");
    if (!file.exists())
      try {
        file.createNewFile();
      } catch (IOException ioe) {
        ioe.printStackTrace();
      }  
    config = (FileConfiguration)YamlConfiguration.loadConfiguration(file);
  }
  
  public void initScoreboard() {
    if (scoreboard != null)
      throw new UnsupportedOperationException("Le scoreboard est deja initialise."); 
    scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
    byte b;
    int i;
    RankList[] arrayOfRankList;
    for (i = (arrayOfRankList = RankList.values()).length, b = 0; b < i; ) {
      RankList rankList = arrayOfRankList[b];
      Team team = scoreboard.registerNewTeam(rankList.getName());
      team.setPrefix(rankList.getPrefix());
      team.setSuffix(rankList.getSuffix());
      b = (byte)(b + 1);
    } 
  }
  
  public static void loadPlayer(Player player) {
    String uuid = player.getUniqueId().toString();
    if (playerRanks.containsKey(uuid))
      return; 
    if (!config.contains(uuid))
      changeRank(uuid, RankList.PLAYER); 
    playerRanks.put(uuid, getRankById(config.getInt(uuid)));
    scoreboard.getTeam(((RankList)playerRanks.get(uuid)).getName()).addEntry(player.getName());
  }
  
  public void deletePlayer(Player player) {
    if (!playerRanks.containsKey(player.getUniqueId().toString()))
      return; 
    playerRanks.remove(player.getUniqueId().toString());
  }
  
  public static RankList getPlayerRank(Player player) {
    if (!playerRanks.containsKey(player.getUniqueId().toString()))
      loadPlayer(player); 
    return playerRanks.get(player.getUniqueId().toString());
  }
  
  public static RankList getRankById(int id) {
    byte b;
    int i;
    RankList[] arrayOfRankList;
    for (i = (arrayOfRankList = RankList.values()).length, b = 0; b < i; ) {
      RankList rankList = arrayOfRankList[b];
      if (rankList.getId() == id)
        return rankList; 
      b = (byte)(b + 1);
    } 
    return RankList.PLAYER;
  }
  
  public static void saveConfig() {
    try {
      config.save(file);
    } catch (IOException ioe) {
      ioe.printStackTrace();
    } 
  }
  
  public boolean hasPower(Player player, int power) {
    return (getPlayerRank(player).getPower() == power);
  }
  
  public boolean hasPowerSup(Player player, int power) {
    return (getPlayerRank(player).getPower() > power);
  }
  
  public boolean hasPowerInf(Player player, int power) {
    return (getPlayerRank(player).getPower() < power);
  }
  
  public static void changeRank(String uuid, RankList rankList) {
    config.set(uuid, Integer.valueOf(rankList.getId()));
    saveConfig();
  }
}

