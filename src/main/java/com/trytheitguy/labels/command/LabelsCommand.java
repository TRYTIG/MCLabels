package com.trytheitguy.labels.command;

import com.trytheitguy.labels.Labels;
import com.trytheitguy.labels.util.TextUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class LabelsCommand implements CommandExecutor {
    private final Labels plugin;

    public LabelsCommand(Labels plugin) {
        this.plugin = plugin;

        plugin.getCommand("labels").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0) {
            aboutMessage(sender);
            return true;
        }

        switch (args[0]) {
            case "about" -> {
                aboutMessage(sender);
                return true;
            }
            case "help" -> {
                helpMessage(sender);
                return true;
            }
            case "reload" -> {
                pluginReload(sender);
                return true;
            }
            default -> {
                invalidUsage(sender);
                return true;
            }
        }
    }

    private void aboutMessage(CommandSender sender) {
        sender.sendMessage(TextUtil.translateColor("&bAbout Labels:\n" +
                "&bVersion: &9" + plugin.getDescription().getVersion() + "\n" +
                "&bDescription: &9" + plugin.getDescription().getDescription() + "\n" +
                "&bAuthors: &9" + plugin.getDescription().getAuthors() + "\n" +
                "&bWebsite: &9" + plugin.getDescription().getWebsite() + "\n" +
                "&bFor help, run /labels help"));
    }

    private void pluginReload(CommandSender sender) {
        if (!sender.hasPermission("labels.reload")) {
            sender.sendMessage(TextUtil.translateColor("&cYou do not have permission to execute this command!"));
            return;
        }

        sender.sendMessage(TextUtil.translateColor("&bReloading..."));
        plugin.reload();
        sender.sendMessage(TextUtil.translateColor("&bReload complete."));
    }

    private void helpMessage(CommandSender sender) {
        sender.sendMessage(TextUtil.translateColor("""
                &bLabels Help:
                &b/labels &9- Shows About page
                &b/labels about &9- Shows About page
                &b/labels reload &9- Reloads configuration file
                &b/labels help &9- Shows this page
                """));
    }

    private void invalidUsage(CommandSender sender) {
        sender.sendMessage(TextUtil.translateColor("&cInvalid Usage! Use /labels help"));
    }
}
