package fr.gwenzy.uhc.main;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import fr.gwenzy.uhc.classes.UHCGame;
import fr.gwenzy.uhc.setuping.UHCCommand;

public class UHCPlugin extends JavaPlugin{
	public static Server s;
	public static Logger log;
	public static String TAG;
	public static FileConfiguration config;
	public static UHCGame g;
	
	@Override
	public void onEnable()
	{
		s=Bukkit.getServer();
		log=s.getLogger();
		TAG = ChatColor.AQUA+"[UHCPlugin] "+ChatColor.GOLD;
		log.info("Plugin enabled.");
		this.saveDefaultConfig();
		config = this.getConfig();
		
		getCommand("uhc").setExecutor(new UHCCommand());
		g=new UHCGame();
	}
	
	@Override
	public void onDisable()
	{
		log.info("Plugin Disabled");
	}

}
