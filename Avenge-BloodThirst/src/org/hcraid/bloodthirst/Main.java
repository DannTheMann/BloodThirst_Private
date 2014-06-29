package org.hcraid.bloodthirst;

import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	public static Main m;

	@Override
	public void onEnable() {
		getServer().getPluginManager().registerEvents(new DamageListener(),this);
		log("enabled");
		m = this;
	}

	@Override
	public void onDisable() {
		log("disabled");
	}

	public static void log(String message) {

		System.out.println("[Bloodthirst] " + message);

	}

}
