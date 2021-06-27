package dev.justinf.infinitywarps.io;

import dev.justinf.infinitywarps.InfinityWarps;
import dev.justinf.infinitywarps.exception.MissingKeyException;
import dev.justinf.infinitywarps.object.Warp;
import dev.justinf.infinitywarps.util.BaseConfiguration;

import java.io.File;

public class WarpConfiguration extends BaseConfiguration {

    public WarpConfiguration(InfinityWarps iw, File warpFile) {
        super(iw, "warps" + File.separator + warpFile.getName());
    }

    public Warp toWarp() throws MissingKeyException {
        // TODO
        return null;
    }
}