package com.trytheitguy.labels.runnable;

import com.trytheitguy.labels.Labels;
import org.bukkit.entity.ArmorStand;
import org.bukkit.scheduler.BukkitRunnable;

public class HeadRotationRunnable extends BukkitRunnable {
    @Override
    public void run() {
        for (ArmorStand materialStand : Labels.getMaterialStands()) {
            materialStand.setHeadRotations(materialStand.getHeadRotations().add(0, 1, 0));
        }
    }
}
