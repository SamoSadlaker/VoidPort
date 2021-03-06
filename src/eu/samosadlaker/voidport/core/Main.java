package eu.samosadlaker.voidport.core;

import eu.samosadlaker.voidport.commands.Admin;
import eu.samosadlaker.voidport.commands.TabCompleter;
import eu.samosadlaker.voidport.listeners.VoidEnter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

    static Main plugin;
    CommandSender logger = Bukkit.getConsoleSender();
    PluginDescriptionFile pdf = this.getDescription();

    public static Main getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable(){
        plugin = this;
        File file = new File(getDataFolder(), "config.yml");
        if(!file.exists()) {
            getConfig().options().copyDefaults(true);
            getConfig().options().copyHeader(true);
            saveDefaultConfig();
        }
        reloadConfig();

        registerListeners();
        registerCommands();
        registerTabCompleter();

        logger.sendMessage(Colors.formatColor("&b-------------------------------------"));
        logger.sendMessage(Colors.formatColor("&aPlugin &b" + pdf.getName() + " &asuccessfully enabled"));
        logger.sendMessage(Colors.formatColor("&a" + pdf.getVersion() + " &f| &a" + pdf.getAuthors().toString() + " &f| &a" + pdf.getWebsite() ));
        logger.sendMessage(Colors.formatColor("&b-------------------------------------"));
    }

    private void registerListeners(){
        getServer().getPluginManager().registerEvents(new VoidEnter(), this);
    }
    private void registerCommands(){
        getCommand("voidport").setExecutor(new Admin());
    }
    private void registerTabCompleter(){
        getCommand("voidport").setTabCompleter(new TabCompleter());
    }

    @Override
    public void onDisable(){

        logger.sendMessage(Colors.formatColor("&b-------------------------------------"));
        logger.sendMessage(Colors.formatColor("&4Plugin &b" + pdf.getName() + " &4successfully disabled"));
        logger.sendMessage(Colors.formatColor("&c" + pdf.getVersion() + " &f| &c" + pdf.getAuthors().toString() + " &f| &c" + pdf.getWebsite() ));
        logger.sendMessage(Colors.formatColor("&b-------------------------------------"));

    }
}
