package dev.justinf.infinitywarps.io;

import dev.justinf.infinitywarps.InfinityWarps;
import dev.justinf.infinitywarps.api.object.Warp;
import dev.justinf.infinitywarps.exception.MissingKeyException;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WarpConfiguration extends DataConfiguration<Warp> {

    // Used when loading
    public WarpConfiguration(InfinityWarps iw, File warpFile) {
        super(iw, "warps" + File.separator + warpFile.getName());
        dataFile = warpFile;
    }

    // Used when saving
    public WarpConfiguration(InfinityWarps iw, Warp w) {
        super(iw, w);
    }

    public WarpConfiguration(InfinityWarps iw, String id) {
        super(iw, "warps" + File.separator + id.toLowerCase());
        dataFile = new File(iw.getDataFolder() + File.separator + "warps", id.toLowerCase() + ".warp");
    }

    @Override
    public Warp build() throws MissingKeyException {
        String id = getString("id");
        String title = getString("title");
        String flatLore = config.getString("lore"); // Considered optional
        String iconMaterial = getString("icon-material");
        String world = getString("location.world");
        double x = getDouble("location.x");
        double y = getDouble("location.y");
        double z = getDouble("location.z");
        float yaw = (float) getDouble("location.yaw");
        float pitch = (float) getDouble("location.pitch");

        List<String> lore = new ArrayList<>();
        if (flatLore != null && !flatLore.isEmpty()) {
            lore.addAll(Arrays.asList(flatLore.split("\n")));
        }

        return new Warp(id, title, lore, iconMaterial, world, x, y, z, yaw, pitch);
    }
}