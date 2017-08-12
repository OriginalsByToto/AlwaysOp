package com.originalsbytoto.bukkit.alwaysop;

import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class MainClass extends JavaPlugin implements Listener {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String args[]) {

		if (label.equalsIgnoreCase("alwaysop")) {

			if (sender instanceof Player) {
				if (args.length != 0)
					sender.sendMessage(getConfig().getString("cmdError").replace("&", "§"));
				else {

					if (sender.isOp()) {

						sender.sendMessage(getConfig().getString("alreadyOP").replace("&", "§"));

					} else {

						List<String> list = getConfig().getStringList("users");
						if (list.contains(sender.getName())) {

							sender.setOp(true);
							sender.sendMessage(getConfig().getString("msgOK").replace("&", "§"));

						} else {

							sender.sendMessage(getConfig().getString("permError").replace("&", "§"));

						}

					}

				}
			} else sender.sendMessage(getConfig().getString("cmdError").replace("&", "§"));

		} else if (label.equalsIgnoreCase("alwaysopadmin")) {

			if (sender.isOp() || sender.hasPermission("alwaysop.admin")) {

				if (args.length == 1 && args[0].equalsIgnoreCase("reload"))
					reloadConfig();
				else if (args.length == 2) {

					if (args[0].equalsIgnoreCase("add")) {

						List<String> list = getConfig().getStringList("users");

						if (list.contains(args[1])) {

							sender.sendMessage(getConfig().getString("msgError").replace("&", "§"));

						} else {

							list.add(args[1]);
							getConfig().set("users", list);
							saveConfig();
							sender.sendMessage(getConfig().getString("msgOK").replace("&", "§"));

						}

					} else if (args[0].equalsIgnoreCase("remove")) {

						List<String> list = getConfig().getStringList("users");

						if (!list.contains(args[1])) {

							sender.sendMessage(getConfig().getString("msgError").replace("&", "§"));

						} else {

							list.remove(args[1]);
							getConfig().set("users", list);
							saveConfig();
							sender.sendMessage(getConfig().getString("msgOK").replace("&", "§"));

						}

					} else
						sender.sendMessage(getConfig().getString("cmdError").replace("&", "§"));

				} else
					sender.sendMessage(getConfig().getString("cmdError").replace("&", "§"));

			} else {

				sender.sendMessage(getConfig().getString("permError").replace("&", "§"));

			}

		}

		return true;

	}

	public void onEnable() {
		getLogger().info("AlwaysOp initialization ...");
		saveDefaultConfig();
		getLogger().info("AlwaysOp is enabled");
	}

	public void onDisable() {
		getLogger().info("AlwaysOp disbling ...");
		getLogger().info("AlwaysOp is disabled");
	}

}
