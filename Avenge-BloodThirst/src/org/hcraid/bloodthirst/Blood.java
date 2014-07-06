package org.hcraid.bloodthirst;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class Blood {

	private Player p;
	private Block redstone;
	private Item blood;
	public static ArrayList<Block> bloodlocation = new ArrayList<Block>();
	public static ArrayList<Blood> bloodspawn = new ArrayList<Blood>();
	private BukkitTask bt;

	public Blood(Player p) {
		this.p = p;

		new BukkitRunnable() {

			@Override
			public void run() {

				dropBlood();
				countDown();
			}

		}.runTaskLater(Main.m, 10);
	}

	private void countDown() {

		bt = new BukkitRunnable() {

			@Override
			public void run() {
				placeRedstone();
				removeBlood();
			}
		}.runTaskLater(Main.m, 20 * 3);

	}

	public void dropBlood() {
		ItemStack i = new ItemStack(Material.INK_SACK);
		i.setDurability((short) 1);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(ChatColor.DARK_RED + p.getName() + "'s blood");
		i.setItemMeta(im);

		blood = p.getWorld().dropItem(p.getLocation(), i);
		bloodspawn.add(this);

	}

	public void removeBlood() {

		blood.remove();
	}

	public void removeRedstone() {
		redstone.setType(Material.AIR);
		bloodlocation.remove(redstone);
		bloodspawn.remove(this);
	}

	public void placeRedstone() {
		blood.getLocation().getBlock().setType(Material.REDSTONE_WIRE);

		redstone = blood.getLocation().getBlock();
		bloodlocation.add(redstone);

		new BukkitRunnable() {

			@Override
			public void run() {
				removeRedstone();
			}
		}.runTaskLater(Main.m, 20 * 30);

	}

	public Item getBlood() {

		return blood;
	}
	
	public void stopRunnable(){
		bt.cancel();
		bloodspawn.remove(this);
	}
	
	public Block getBloodBlock(){
		
		return redstone;
	}

	public Player getPlayer() {
		return p;
		
	}
}
