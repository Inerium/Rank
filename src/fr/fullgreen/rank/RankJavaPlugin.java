package fr.fullgreen.rank;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import fr.fullgreen.rank.commands.RankCommand;
import fr.fullgreen.rank.listeners.PlayerListener;

public final class RankJavaPlugin extends JavaPlugin{
	private Rank rank;
	
	public final void onLoad() {
		rank = new Rank(this);
	}
	public final void onEnable() {
		rank.initScoreboard();
		
		Bukkit.getPluginManager().registerEvents(new PlayerListener(rank), this);
		getCommand("rank").setExecutor(new RankCommand(rank));
	}
	
	public final void onDisable() {
		
	}
}