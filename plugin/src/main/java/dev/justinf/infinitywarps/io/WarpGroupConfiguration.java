package dev.justinf.infinitywarps.io;

import dev.justinf.infinitywarps.InfinityWarps;
import dev.justinf.infinitywarps.api.object.WarpGroup;
import dev.justinf.infinitywarps.exception.MissingKeyException;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WarpGroupConfiguration extends DataConfiguration<WarpGroup> {

    // Used when loading
    public WarpGroupConfiguration(InfinityWarps iw, File groupFile) {
        super(iw, "groups" + File.separator + groupFile.getName());
        dataFile = groupFile;
    }

    // Used when saving
    public WarpGroupConfiguration(InfinityWarps iw, WarpGroup wg) {
        super(iw, wg);
    }

    // Used when deleting
    public WarpGroupConfiguration(InfinityWarps iw, String id) {
        super(iw, "groups" + File.separator + id.toLowerCase() + ".group");
        dataFile = new File(iw.getDataFolder() + File.separator + "groups", id.toLowerCase() + "group");
    }

    @Override
    public WarpGroup build() throws MissingKeyException {
        String id = getString("id");
        String title = getString("title");
        String flatLore = config.getString("lore");
        String iconMaterial = getString("icon-material");
        List<String> warps = config.getStringList("warps");

        List<String> lore = new ArrayList<>();
        if (flatLore != null && !flatLore.isEmpty()) {
            lore.addAll(Arrays.asList(flatLore.split("\n")));
        }

        return new WarpGroup(id, title, lore, iconMaterial, warps);
    }
}