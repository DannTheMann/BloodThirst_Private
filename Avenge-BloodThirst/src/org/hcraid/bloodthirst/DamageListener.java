package org.hcraid.bloodthirst;

import java.util.Random;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class DamageListener implements Listener {

	private static final double BLOOD_DROP_CHANCE = 0.9;
	
	@EventHandler
	public void playerDamage(EntityDamageByEntityEvent e) {
		
		if(e.getEntity() instanceof Player && e.getDamager() instanceof Player){
			
			if(new Random().nextDouble() <= BLOOD_DROP_CHANCE){
				new Blood((Player)e.getEntity());
			}
			
		}
	}
	
	@EventHandler
	public void playerInt(PlayerInteractEvent e){
		
		if(e.getClickedBlock() != null){
			
			e.getPlayer().sendMessage(e.getClickedBlock().getType() + " - " + e.getClickedBlock().getTypeId());
			
		}
		
	}

}
