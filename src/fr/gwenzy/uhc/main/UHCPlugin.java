package fr.gwenzy.uhc.main;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class UHCPlugin extends JavaPlugin{
	public static Server s;
	public static Logger log;
	public static String TAG;
	public static FileConfiguration config;
	
	@Override
	public void onEnable()
	{
		s=Bukkit.getServer();
		log=s.getLogger();
		TAG = ChatColor.RED+"[UHCPlugin]"+ChatColor.GOLD;
		log.info("Plugin enabled.");
	}
	
	@Override
	public void onDisable()
	{
		
	}

}
