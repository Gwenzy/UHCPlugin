package fr.gwenzy.uhc.setuping;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.gwenzy.uhc.main.UHCPlugin;

public class UHCCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String CommandLabel,
			String[] args) {

		if(sender instanceof Player)
		{
			Player p = (Player) sender;
			if(args.length>0)
			{
				if(args[0].equalsIgnoreCase("config"))
				{
					if(p.hasPermission("UHC.config"))
					{
						Inventory i = UHCPlugin.s.createInventory(null, 18, "Modification de configuration");

						ItemStack title = new ItemStack(Material.NAME_TAG);
						ItemMeta titleMeta = title.getItemMeta();
						titleMeta.setDisplayName(ChatColor.GREEN+"Titre du scoreboard");
						title.setItemMeta(titleMeta);

						ItemStack teams = new ItemStack(Material.BANNER);
						ItemMeta teamsMeta = title.getItemMeta();
						teamsMeta.setDisplayName(ChatColor.GREEN+"Gestion des équipes");
						teams.setItemMeta(teamsMeta);

						ItemStack border = new ItemStack(Material.BARRIER);
						ItemMeta borderMeta = title.getItemMeta();
						borderMeta.setDisplayName(ChatColor.GREEN+"Bordures");
						//TODO Affichage en Lore des bordures actuelles
						border.setItemMeta(borderMeta);

						ItemStack pvp = new ItemStack(Material.DIAMOND_SWORD);
						ItemMeta pvpMeta = title.getItemMeta();
						pvpMeta.setDisplayName(ChatColor.GREEN+"Temps avant PVP");
						//TODO Temps actuel pvp
						pvp.setItemMeta(pvpMeta);

						ItemStack pots = new ItemStack(Material.POTION, 1, (byte)8194);
						ItemMeta potsMeta = title.getItemMeta();
						potsMeta.setDisplayName(ChatColor.GREEN+"Potions");
						//TODO Affichage en Lore pots1&2 enabled
						pots.setItemMeta(potsMeta);

						ItemStack apples = new ItemStack(Material.GOLDEN_APPLE, 1, (byte) 1);
						ItemMeta applesMeta = title.getItemMeta();
						applesMeta.setDisplayName(ChatColor.GREEN+"Notch's Apples");
						//TODO LORE ENABLED
						apples.setItemMeta(applesMeta);

						ItemStack timePerEp = new ItemStack(Material.WATCH);
						ItemMeta timePerEpMeta = title.getItemMeta();
						timePerEpMeta.setDisplayName(ChatColor.GREEN+"Temps par épisode");
						timePerEp.setItemMeta(timePerEpMeta);

						ItemStack start = new ItemStack(Material.EMERALD);
						ItemMeta startMeta = title.getItemMeta();
						startMeta.setDisplayName(ChatColor.GRAY+"Démarrage forcé");
						start.setItemMeta(startMeta);
						
						i.setItem(1, title);
						i.setItem(2, teams);
						i.setItem(3, border);
						i.setItem(4, pvp);
						i.setItem(5, pots);
						i.setItem(6, apples);
						i.setItem(7, timePerEp);
						i.setItem(13, start);
						
						p.openInventory(i);
					}
					else
					{
						p.sendMessage(UHCPlugin.TAG+ChatColor.RED+"Vous n'avez pas la permission de faire ceci.");
					}
				}
			}
			else
			{
				UHCPlugin.g.sendScoreboard();
			}
		}
		return true;
	}

}
