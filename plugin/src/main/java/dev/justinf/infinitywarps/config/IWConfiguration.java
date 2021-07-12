package dev.justinf.infinitywarps.config;

import dev.justinf.infinitywarps.InfinityWarps;
import dev.justinf.infinitywarps.util.BaseConfiguration;
import org.bukkit.Material;

public class IWConfiguration extends BaseConfiguration {

    private final IWItemBuilder itemBuilder;

    public IWConfiguration(InfinityWarps iw) {
        super(iw, "config.yml");
        itemBuilder = new IWItemBuilder(iw);
    }

    public IWItemBuilder items() {
        return itemBuilder;
    }

    public Material getMaterial(IWConVar conVar) {
        String conf = getString(conVar);
        try {
            return Material.valueOf(conf);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            plugin.getLogger().warning("Invalid material specified in configuration!");
            plugin.getLogger().warning("at: " + conVar.path + " / value: " + conf);

            // We hope that the default Material will always be valid...
            try {
                return Material.valueOf((String) conVar.def);
            } catch (IllegalArgumentException ignored) {
                plugin.getLogger().severe("Invalid material default in configuration! (Are the server/plugin updated?)");
                plugin.getLogger().severe("at: " + conVar.path + " / def: " + conVar.def);
                return Material.valueOf("STONE"); // STONE should be safe?
            }
        }
    }

    /* Standard config getters */
    public int getInt(IWConVar conVar) {
        return getInt(conVar.path, conVar.def);
    }

    public double getDouble(IWConVar conVar) {
        return getDouble(conVar.path, conVar.def);
    }

    public String getString(IWConVar conVar) {
        return getString(conVar.path, conVar.def);
    }

    public boolean getBoolean(IWConVar conVar) {
        return getBoolean(conVar.path, conVar.def);
    }
}