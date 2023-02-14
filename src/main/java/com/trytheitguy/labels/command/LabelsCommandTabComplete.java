package com.trytheitguy.labels.command;

import com.trytheitguy.labels.Labels;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.Arrays;
import java.util.List;

public class LabelsCommandTabComplete implements TabCompleter {
    /**
     * /labels command tab completer
     * @param plugin Plugin
     */
    public LabelsCommandTabComplete(Labels plugin) {
        plugin.getServer().getPluginCommand("labels").setTabCompleter(this);
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        if (sender.hasPermission("labels.reload")) {
            return Arrays.asList("about", "help", "reload");
        }
        return Arrays.asList("about", "help");
    }
}
