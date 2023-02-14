package com.trytheitguy.labels.util;

import com.destroystokyo.paper.profile.PlayerProfile;
import com.destroystokyo.paper.profile.ProfileProperty;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class HeadUtil {
    public static ItemStack getCustomHead(String base64) {
        // create item stack and meta
        ItemStack head = new ItemStack(Material.PLAYER_HEAD);
        SkullMeta skullMeta = (SkullMeta) head.getItemMeta();

        // create new profile with random UUID and base64 as texture
        PlayerProfile playerProfile = Bukkit.createProfile(UUID.randomUUID());
        playerProfile.getProperties().add(new ProfileProperty("textures", base64));

        // set profile
        skullMeta.setPlayerProfile(playerProfile);

        // set meta
        head.setItemMeta(skullMeta);

        // return modified item stack
        return head;
    }
}
