package dev.justinf.infinitywarps.locale;

import dev.justinf.infinitywarps.InfinityWarps;
import dev.justinf.infinitywarps.config.IWConVar;
import dev.justinf.infinitywarps.util.BaseConfiguration;
import org.bukkit.ChatColor;

import java.util.HashMap;
import java.util.Map;

public class IWLocale {

    private final String DEFAULT_LOCALE = "en_US";

    private final InfinityWarps iw;
    private final Map<String, String> entries;

    public IWLocale(InfinityWarps iw) {
        this.iw = iw;
        entries = new HashMap<>();
    }

    public void load() {
        // Fill in locale with en_US defaults
        if (!merge(DEFAULT_LOCALE)) {
            iw.getLogger().warning("Error loading default locale " + DEFAULT_LOCALE + ".");
        }

        String configuredLocale = iw.getConfiguration().getString(IWConVar.LOCALE);
        if (!configuredLocale.equals(DEFAULT_LOCALE)) {
            iw.getLogger().info("Detected non-default configured locale: " + configuredLocale);
            iw.getLogger().info("Attempting to load " + configuredLocale);
            if (merge(configuredLocale)) {
                iw.getLogger().info("Successfully loaded " + configuredLocale + "!");
            } else {
                iw.getLogger().warning("Error loading configured locale " + configuredLocale + ".");
            }
        }
    }

    public boolean merge(String locale) {
        BaseConfiguration localeFile = new BaseConfiguration(iw, "locale/" + locale + ".yml");
        if (!localeFile.initialize(false)) return false;

        for (String key : localeFile.getConfig().getKeys(true)) {
            entries.put(key, localeFile.getString(key, key));
        }
        return true;
    }

    public String raw(String reference) {
        String result = entries.getOrDefault(reference, reference);
        if (result == null) {
            return reference;
        }
        return result;
    }

    public String raw(String reference, String... args) {
        String manip = raw(reference);
        for (int i = 0; i < args.length; i++) {
            manip = manip.replace("{" + i + "}", args[i]);
        }
        return manip;
    }

    public String format(String reference) {
        return ChatColor.translateAlternateColorCodes('&', raw(reference));
    }

    public String format(String reference, String... args) {
        return ChatColor.translateAlternateColorCodes('&', raw(reference, args));
    }

    public String formatPrefixed(String reference, String... args) {
        return ChatColor.translateAlternateColorCodes('&', (iw.getConfiguration().getBoolean(IWConVar.USE_PREFIX_IN_MESSAGES) ? raw(IWRefs.GENERAL_PREFIX) : "") + raw(reference, args));
    }

    public String formatNoColorArgs(String reference, String... args) {
        String manip = format(reference);
        for (int i = 0; i < args.length; i++) {
            manip = manip.replace("{" + i + "}", args[i]);
        }
        return manip;
    }

    public String formatNoColorArgsPrefixed(String reference, String... args) {
        String manip = formatPrefixed(reference);
        for (int i = 0; i < args.length; i++) {
            manip = manip.replace("{" + i + "}", args[i]);
        }
        return manip;
    }
}