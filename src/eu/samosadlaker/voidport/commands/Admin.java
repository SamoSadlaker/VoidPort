package eu.samosadlaker.voidport.commands;

import eu.samosadlaker.voidport.core.Colors;
import eu.samosadlaker.voidport.core.Main;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Admin implements CommandExecutor {

    static Main plugin = Main.getPlugin();
    FileConfiguration config = plugin.getConfig();
    String prefix = config.getString("prefix") + " &f» &3";

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(label.equalsIgnoreCase("voidport")){
            if (!(sender instanceof Player)) {
                sender.sendMessage(Colors.formatColor(prefix + config.getString("notplayer")));
                return false;
            }
            if(args.length == 0){
                sender.sendMessage(Colors.formatColor(prefix + config.getString("command-usage")));
                return false;
            }

            if (!sender.hasPermission(config.getString("permission"))){
                return false;
            }

            if(args[0].equalsIgnoreCase("reload")){
                plugin.getPluginLoader().disablePlugin(plugin);
                plugin.getPluginLoader().enablePlugin(plugin);
                sender.sendMessage(Colors.formatColor(prefix + config.getString("reload-completed")));
                return true;
            }

            if(args[0].equalsIgnoreCase("setworld")){
                String world = ((Player) sender).getWorld().getName();
                config.set("world", world);
                plugin.saveConfig();
                plugin.reloadConfig();
                sender.sendMessage(Colors.formatColor(prefix + config.getString("worldsaved")));
                return true;
            }

        }
        return false;
    }
}
