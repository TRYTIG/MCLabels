package com.trytheitguy.labels.util;

import org.bukkit.ChatColor;

public class TextUtil {
    /**
     * Returns a String with translated color codes
     * @param input input String
     * @return a String with color codes translated
     */
    public static String translateColor(String input) {
        return ChatColor.translateAlternateColorCodes('&', input);
    }
}
