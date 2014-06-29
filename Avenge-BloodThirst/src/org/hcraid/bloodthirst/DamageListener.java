package org.hcraid.bloodthirst;

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

	@EventHandler
	public void playerDamage(EntityDamageByEntityEvent e) {
		
		if(e.getEntity() instanceof Player && e.getDamager() instanceof Player){
			
			Player p = (Player) e.getEntity();
		
			ItemStack i = new ItemStack(Material.INK_SACK);
			
			i.setDurability((short) 1);
			
			ItemMeta im = i.getItemMeta();
			
			im.setDisplayName(ChatColor.DARK_RED + p.getName() + "'s blood");
			
			i.setItemMeta(im);

			Item is = p.getWorld().dropItem(p.getLocation(), i);
		}
	}
	
	@EventHandler
	public void playerInt(PlayerInteractEvent e){
		
		if(e.getClickedBlock() != null){
			
			e.getPlayer().sendMessage(e.getClickedBlock().getType() + " - " + e.getClickedBlock().getTypeId());
			
		}
		
	}

}
