package com.trytheitguy.labels;

import com.trytheitguy.labels.command.LabelsCommand;
import com.trytheitguy.labels.command.LabelsCommandTabComplete;
import com.trytheitguy.labels.runnable.HeadRotationRunnable;
import com.trytheitguy.labels.util.ArmorStandUtil;
import com.trytheitguy.labels.util.HeadUtil;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.ArmorStand;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.ArrayList;
import java.util.List;

public final class Labels extends JavaPlugin {
    private static final List<ArmorStand> materialStands = new ArrayList<>();
    private static final List<ArmorStand> textStands = new ArrayList<>();

    private static BukkitTask rotationTask;

    @Override
    public void onEnable() {
        // Plugin startup logic
        initConfig();

        // Check if we should continue with parsing config
        if (getConfig().getBoolean("enabled"))
            readConfig();

        new LabelsCommand(this);
        new LabelsCommandTabComplete(this);

        // Run rotation runnable
        rotationTask = new HeadRotationRunnable().runTaskTimer(this, 0L, 0L);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("Plugin shutting down, removing labels!");

        // remove armor stands
        removeStands();
    }

    public void reload() {
        reloadConfig();
        removeStands();

        // Check if we should continue with parsing config
        if (getConfig().getBoolean("enabled")) {
            readConfig();

            // Run rotation runnable
            rotationTask = new HeadRotationRunnable().runTaskTimer(this, 0L, 0L);
        }
    }

    private void removeStands() {
        // cancel rotation task
        rotationTask.cancel();

        // remove material stands
        for (ArmorStand materialStand : materialStands) {
            materialStand.remove();
        }

        // remove text stands
        for (ArmorStand textStand : textStands) {
            textStand.remove();
        }
    }

    private void initConfig() {
        saveDefaultConfig();
        reloadConfig();
    }

    private void readConfig() {
        // get labels
        for (String label : getConfig().getConfigurationSection("labels").getKeys(false)) {
            // get label data
            ConfigurationSection labelSection = getConfig().getConfigurationSection("labels")
                    .getConfigurationSection(label);

            LabelType labelType = LabelType.valueOf(labelSection.getString("type"));
            String additionalData = null;
            if (!labelType.equals(LabelType.TEXT)) {
                additionalData = labelSection.getString("data");
            }
            double x = labelSection.getDouble("x");
            double y = labelSection.getDouble("y");
            double z = labelSection.getDouble("z");
            String world = labelSection.getString("world");
            List<String> textList = labelSection.getStringList("text");

            // pass to handler function
            Location location = new Location(Bukkit.getWorld(world),
                    x,
                    y,
                    z);

            handleConfigEntry(labelType, additionalData, location, textList);
        }
    }

    private static void handleConfigEntry(LabelType labelType, String additionalData, Location location, List<String> textList) {
        // Text Armor Stand
        if (labelType.equals(LabelType.TEXT)) {
            // loop through text
            for (String text : textList) {
                // Set new location
                location.setY(location.getY() - 0.25D);

                // Spawn text stand and add to list
                textStands.add(ArmorStandUtil.spawnTextArmorStand(location, text));
            }
        }

        // Material Armor Stand
        if (labelType.equals(LabelType.TEXT_WITH_MATERIAL)) {
            // loop through text
            int index = 0;
            for (String text : textList) {
                // the first text entry will be the "title"
                if (index == 0) {
                    // get item stack from additional data
                    ItemStack itemStack = new ItemStack(Material.valueOf(additionalData));

                    // spawn material armor stand and add to list
                    materialStands.add(ArmorStandUtil.spawnMaterialArmorStand(location, itemStack, text));
                } else if (index == 1) {
                    // Move down 1 block
                    location.setY(location.getY() - 1.25D);

                    // add text armor stand and add it to list
                    textStands.add(ArmorStandUtil.spawnTextArmorStand(location, text));
                } else {
                    // Set new location offset
                    location.setY(location.getY() - 0.25D);

                    // add text armor stand and add it to list
                    textStands.add(ArmorStandUtil.spawnTextArmorStand(location, text));
                }
                index++;
            }
        }

        // Material Armor Stand
        if (labelType.equals(LabelType.TEXT_WITH_HEAD)) {
            // loop through text
            int index = 0;
            for (String text : textList) {
                // the first text entry will be the "title"
                if (index == 0) {
                    // get item stack from additional data
                    ItemStack itemStack = HeadUtil.getCustomHead(additionalData);

                    // spawn material armor stand and add to list
                    materialStands.add(ArmorStandUtil.spawnMaterialArmorStand(location, itemStack, text));
                } else if (index == 1) {
                    // Move down 1 block
                    location.setY(location.getY() - 1.25D);

                    // add text armor stand and add it to list
                    textStands.add(ArmorStandUtil.spawnTextArmorStand(location, text));
                } else {
                    // Set new location offset
                    location.setY(location.getY() - 0.25D);

                    // add text armor stand and add it to list
                    textStands.add(ArmorStandUtil.spawnTextArmorStand(location, text));
                }
                index++;
            }
        }
    }

    public static List<ArmorStand> getMaterialStands() {
        return materialStands;
    }

    public static List<ArmorStand> getTextStands() {
        return textStands;
    }
}
