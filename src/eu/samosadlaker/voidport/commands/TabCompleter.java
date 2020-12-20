package eu.samosadlaker.voidport.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

import java.util.ArrayList;
import java.util.List;

public class TabCompleter implements org.bukkit.command.TabCompleter {

    List<String> arguments = new ArrayList<String>();

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {

        if(arguments.isEmpty()){
            arguments.add("reload");
            arguments.add("setworld");
        }

        List<String> result = new ArrayList<String>();
        if(args.length == 1){
            for(String a : arguments){
                if(a.toLowerCase().startsWith(args[0].toLowerCase())){
                    result.add(a);
                }
            }
            return result;
        }

        return null;
    }
}
