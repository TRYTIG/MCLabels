package com.trytheitguy.labels.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.TextComponent;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

public class ArmorStandUtil {
    public static ArmorStand spawnMaterialArmorStand(Location location, ItemStack item, String text) {
        final TextComponent titleComponent = Component.text(TextUtil.translateColor(text));

        ArmorStand armorStand = (ArmorStand) location.getWorld()
                .spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setVisible(false); /* Invisible */
        armorStand.setGravity(false); /* Will not fall */
        armorStand.setCanPickupItems(false); /* Will not pick up items */

        // Prevent changing armor stand
        armorStand.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.REMOVING_OR_CHANGING);
        armorStand.addEquipmentLock(EquipmentSlot.CHEST, ArmorStand.LockType.ADDING_OR_CHANGING);
        armorStand.addEquipmentLock(EquipmentSlot.LEGS, ArmorStand.LockType.ADDING_OR_CHANGING);
        armorStand.addEquipmentLock(EquipmentSlot.FEET, ArmorStand.LockType.ADDING_OR_CHANGING);
        armorStand.addEquipmentLock(EquipmentSlot.HAND, ArmorStand.LockType.ADDING_OR_CHANGING);
        armorStand.addEquipmentLock(EquipmentSlot.OFF_HAND, ArmorStand.LockType.ADDING_OR_CHANGING);

        armorStand.customName(titleComponent);
        armorStand.setCustomNameVisible(true); /* Show custom name */
        armorStand.getEquipment().setHelmet(item);

        return armorStand;
    }

    public static ArmorStand spawnTextArmorStand(Location location, String text) {
        final TextComponent textComponent = Component.text(TextUtil.translateColor(text));

        ArmorStand armorStand = (ArmorStand) location.getWorld()
                .spawnEntity(location, EntityType.ARMOR_STAND);
        armorStand.setVisible(false); /* Invisible */
        armorStand.setGravity(false); /* Will not fall */
        armorStand.setCanPickupItems(false); /* Will not pick up items */

        // Prevent changing armor stand
        armorStand.addEquipmentLock(EquipmentSlot.HEAD, ArmorStand.LockType.REMOVING_OR_CHANGING);
        armorStand.addEquipmentLock(EquipmentSlot.CHEST, ArmorStand.LockType.ADDING_OR_CHANGING);
        armorStand.addEquipmentLock(EquipmentSlot.LEGS, ArmorStand.LockType.ADDING_OR_CHANGING);
        armorStand.addEquipmentLock(EquipmentSlot.FEET, ArmorStand.LockType.ADDING_OR_CHANGING);
        armorStand.addEquipmentLock(EquipmentSlot.HAND, ArmorStand.LockType.ADDING_OR_CHANGING);
        armorStand.addEquipmentLock(EquipmentSlot.OFF_HAND, ArmorStand.LockType.ADDING_OR_CHANGING);

        armorStand.customName(textComponent);
        armorStand.setCustomNameVisible(true); /* Show custom name */

        return armorStand;
    }
}
