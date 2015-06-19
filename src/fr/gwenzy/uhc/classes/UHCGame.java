package fr.gwenzy.uhc.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

import fr.gwenzy.uhc.main.UHCPlugin;

public class UHCGame {
	/*
	  * UHC by Gwenzy
	  * ------TEAM-------
	  * Team $COLOR
	  * Joueurs en vie
	  * Joueur le plus proche
	  * -----GLOBAL------
	  * Joueurs en vie (nb)
	  * Centre
	  * Taille bordure
	  * ------TIME-------
	  * Temps avant bordule
	  * Timer episode
	  * 
	  */
	
	/*
	 * UHC by Gwenzy
	 * -----Joueurs-----
	 * Co/JoueursMax
	 * Team $OwnTeam
	 * -----Règles-----
	 * Centre X|Z
	 * Bordure $Size
	 * Timer min:sec
	 * x teams
	 * *****************
	 * Potions 1
	 * Potions 2      //Avec couleur vert/rouge
	 * Pommes notch
	 */
	private String title;
	private int b_center_x, b_center_z, b_size, b_min_size, t_min,t_sec, t_mpEp, t_spEp, teamsN, maxPlayers;
	private boolean popo1, popo2, apples, isCounting, configOk, inGame;
	private HashMap<String, List<UUID>> teams;
	private Scoreboard sb;
	
	public UHCGame()
	{
		this.title="UHC By Gwenzy";
		this.b_center_x=0;
		this.b_center_z=0;
		this.b_size=0;
		this.t_min=20;
		this.t_sec=0;
		this.t_spEp=0;
		this.t_mpEp=20;
		this.b_min_size=10;
		this.teams = new HashMap<String, List<UUID>>();
		this.teamsN=4;
		this.inGame=false;
		this.maxPlayers=16;
		this.sb=UHCPlugin.s.getScoreboardManager().getNewScoreboard();
		this.configOk=false;
		this.popo1=true;
	}
	
	public void loadConfig()
	{
		
	}
	public void createSb()
	{
		/*
		  * UHC by Gwenzy
		  * ------TEAM-------
		  * Team $COLOR
		  * Joueurs en vie
		  * Joueur le plus proche
		  * -----GLOBAL------
		  * Joueurs en vie (nb)
		  * Centre
		  * Taille bordure
		  * ------TIME-------
		  * Temps avant bordule
		  * Timer episode
		  * 
		  */
		
		/*
		 * UHC by Gwenzy
		 * -----Joueurs-----
		 * Co/JoueursMax
		 * Team $OwnTeam
		 * -----Règles-----
		 * Centre X|Z
		 * Bordure $Size
		 * Timer min:sec
		 * x teams
		 * *****************
		 * Potions 1
		 * Potions 2      //Avec couleur vert/rouge
		 * Pommes notch
		 */
		try {
			Objective o = this.sb.getObjective("UHC");
			if(this.inGame==false)
			{
				//GLOBAL
				o.setDisplayName(this.title);
				o.setDisplaySlot(DisplaySlot.SIDEBAR);
				o.getScore(ChatColor.GOLD+"-----Joueurs-----").setScore(-1);
				o.getScore(ChatColor.AQUA+""+UHCPlugin.s.getOnlinePlayers().size()+"/"+this.maxPlayers+ChatColor.RED+" Joueurs").setScore(-2);
				o.getScore(ChatColor.GOLD+"-----Règles------").setScore(-4);
				o.getScore(ChatColor.RED+"Centre : "+ChatColor.AQUA+this.b_center_x+"|"+this.b_center_z);
				o.getScore(ChatColor.RED+"Bordure : "+ChatColor.AQUA+this.b_size);
				o.getScore(ChatColor.RED+"Timer "+ChatColor.AQUA+this.t_mpEp+":"+this.t_spEp);
				o.getScore(ChatColor.AQUA+""+this.teamsN+ChatColor.RED+ "teams");
				o.getScore(ChatColor.GOLD+"******************");
				o.getScore(this.popo1?ChatColor.GREEN+"Potions 1":ChatColor.RED+"Potions 1");
				o.getScore(this.popo2?ChatColor.GREEN+"Potions 2":ChatColor.RED+"Potions 2");
				o.getScore(this.apples?ChatColor.GREEN+"Pommes notch":ChatColor.RED+"Pommes notch");
				
			}
		} catch (Exception e) {
			Objective o = this.sb.registerNewObjective("UHC", "dummy");
			if(this.inGame==false)
			{
				//GLOBAL
				o.setDisplayName(this.title);
				o.setDisplaySlot(DisplaySlot.SIDEBAR);
				o.getScore(ChatColor.GOLD+"-----Joueurs-----").setScore(-1);
				o.getScore(ChatColor.AQUA+""+UHCPlugin.s.getOnlinePlayers().size()+"/"+this.maxPlayers+ChatColor.RED+" Joueurs").setScore(-2);
				o.getScore(ChatColor.GOLD+"-----Règles------").setScore(-4);
				o.getScore(ChatColor.RED+"Centre : "+ChatColor.AQUA+this.b_center_x+"|"+this.b_center_z).setScore(-5);
				o.getScore(ChatColor.RED+"Bordure : "+ChatColor.AQUA+this.b_size).setScore(-6);
				o.getScore(ChatColor.RED+"Timer "+ChatColor.AQUA+this.t_mpEp+":"+this.t_spEp).setScore(-7);
				o.getScore(ChatColor.AQUA+""+this.teamsN+ChatColor.RED+ " teams").setScore(-8);
				o.getScore(ChatColor.GOLD+"*********************").setScore(-9);
				o.getScore(this.popo1?ChatColor.GREEN+"Potions 1":ChatColor.RED+"Potions 1").setScore(-10);
				o.getScore(this.popo2?ChatColor.GREEN+"Potions 2":ChatColor.RED+"Potions 2").setScore(-11);
				o.getScore(this.apples?ChatColor.GREEN+"Pommes notch":ChatColor.RED+"Pommes notch").setScore(-12);
				
			}
		}
	}
	public void sendScoreboard()
	{
		this.createSb();
		for(Player p : UHCPlugin.s.getOnlinePlayers())
		{
			p.setScoreboard(this.sb);
		}
	}
	
	//Config modif
	public boolean setTitle(String title)
	{
		if(title.length()>0)
		{
			return false;
		}
		else
		{
			this.title=title;
			return true;
		}
	}
	
	public void setBordureCenter(int x, int y)
	{
		this.b_center_x = x;
		this.b_center_z = y;
	}
	
	public void setBorderSize(int size)
	{
		this.b_size=size;
	}
	
	public void setBorderMinSize(int m)
	{
		this.b_min_size=m;
	}
	
	public void reduceBorder(int size)
	{
		if(this.b_size-size>=this.b_min_size)
			this.b_size-=size;
	}
	public void createTeams()
	{
		switch(this.teamsN)
		{
		case 1:
			this.teams.put("Rouge", new ArrayList<UUID>());
			break;
			
		case 2:
			this.teams.put("Vert", new ArrayList<UUID>());
			break;
			
		case 3:
			this.teams.put("Jaune", new ArrayList<UUID>());
			break;
			
		case 4:
			this.teams.put("Bleu", new ArrayList<UUID>());
			break;
			
		default:
			this.configOk=false;
		}
	}
	public void checkConfig()
	{
		
	}
	
	public void startGame()
	{
		
	}
	
	
}
