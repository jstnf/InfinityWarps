package dev.justinf.infinitywarps.io;

import dev.justinf.infinitywarps.InfinityWarps;
import dev.justinf.infinitywarps.exception.MissingKeyException;
import dev.justinf.infinitywarps.object.WarpGroup;
import dev.justinf.infinitywarps.util.BaseConfiguration;

import java.io.File;

public class WarpGroupConfiguration extends BaseConfiguration {

    public WarpGroupConfiguration(InfinityWarps iw, File groupFile) {
        super(iw, "groups" + File.separator + groupFile.getName());
    }

    public WarpGroup toWarpGroup() throws MissingKeyException {
        // TODO
        return null;
    }
}