package dev.justinf.infinitywarps.config;

import dev.justinf.infinitywarps.InfinityWarps;
import dev.justinf.infinitywarps.util.BaseConfiguration;

public class IWConfiguration extends BaseConfiguration {

    public IWConfiguration(InfinityWarps iw) {
        super(iw, "config.yml");
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