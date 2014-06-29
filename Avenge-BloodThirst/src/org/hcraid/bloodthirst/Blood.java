package org.hcraid.bloodthirst;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class Blood {

	private Player p;
	private Block redstone;
	private Item blood;
	
	public Blood(Player p){
		this.p = p;
		
		dropBlood();
		
		countDown();
	}
	
	private void countDown() {
		
		new BukkitRunnable() {
			
			@Override
			public void run() {
				placeRedstone();
				removeBlood();
			}
		}.runTaskLater(Main.m, 20*3);
		
	}

	public void dropBlood(){
		ItemStack i = new ItemStack(Material.INK_SACK);
		i.setDurability((short) 1);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.DARK_RED + p.getName() + "'s blood");
		i.setItemMeta(im);

		blood = p.getWorld().dropItem(p.getLocation(), i);
		
	}
	
	public void removeBlood(){
		blood.remove();
	}
	
	public void placeRedstone(){
		blood.getLocation().getBlock().setType(Material.REDSTONE_WIRE);
		
		redstone = blood.getLocation().getBlock();
	}
}
