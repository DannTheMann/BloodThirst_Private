package org.hcraid.bloodthirst;

import java.util.Random;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class DamageListener implements Listener {

	private static final double BLOOD_DROP_CHANCE = 0.9;

	@EventHandler
	public void playerDamage(EntityDamageByEntityEvent e) {

		if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {

			if (new Random().nextDouble() <= BLOOD_DROP_CHANCE) {
				new Blood((Player) e.getEntity());
			}

		}
	}

	public void playerInt(PlayerInteractEvent e) {

		if (e.getClickedBlock() != null) {

			e.getPlayer().sendMessage(
					e.getClickedBlock().getType() + " - "
							+ e.getClickedBlock().getTypeId());

		}

	}

	@EventHandler
	public void playerB(PlayerPickupItemEvent e) {

		if (e.getItem().getItemStack().getType() == Material.INK_SACK) {

			for (Blood b : Blood.bloodspawn) {

				b.getBlood();

				if (e.getItem() == b.getBlood()) {

					b.stopRunnable();

					break;
				}
			}
		}
	}

	@EventHandler
	public void playerC(BlockBreakEvent e) {

		if (e.getBlock().getType() == Material.REDSTONE_WIRE) {

			for (Blood b : Blood.bloodspawn) {

				if (b.getBloodBlock() == e.getBlock()) {

					e.setCancelled(true);
					e.getBlock().setType(Material.AIR);
					
					new Blood(b.getPlayer());
					b.stopRunnable();
				}
			}
		}

	}

}
