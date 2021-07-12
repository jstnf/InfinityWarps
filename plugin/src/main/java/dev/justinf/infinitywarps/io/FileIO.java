package dev.justinf.infinitywarps.io;

import dev.justinf.infinitywarps.InfinityWarps;
import dev.justinf.infinitywarps.api.object.Warp;
import dev.justinf.infinitywarps.api.object.WarpGroup;
import dev.justinf.infinitywarps.exception.DuplicateException;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class FileIO {

    private final InfinityWarps iw;
    private final File warpsFolder;
    private final File groupsFolder;

    public FileIO(InfinityWarps iw) {
        this.iw = iw;
        warpsFolder = new File(iw.getDataFolder().getPath() + File.separator + "warps");
        groupsFolder = new File(iw.getDataFolder().getPath() + File.separator + "groups");
    }

    public boolean onEnable() {
        if (!(verifyWarpsFolder() && verifyGroupsFolder())) {
            iw.getLogger().severe("Unable to create or verify data folders.");
            iw.getLogger().severe("InfinityWarps will now be disabled.");
            return false;
        }

        return true;
    }

    public void load() {
        try {
            Map<String, WarpGroup> loadedGroups = new HashMap<>();
            Map<File, Exception> invalidGroups = new HashMap<>();
            for (File f : Objects.requireNonNull(groupsFolder.listFiles())) {
                try {
                    WarpGroupConfiguration groupConfiguration = new WarpGroupConfiguration(iw, f);
                    groupConfiguration.unsafeInitialize(false, false);
                    WarpGroup wg = groupConfiguration.build();
                    if (loadedGroups.containsKey(wg.getInternalId())) throw new DuplicateException(wg.getInternalId());
                    loadedGroups.put(wg.getInternalId(), wg);
                } catch (Exception e2) {
                    invalidGroups.put(f, e2);
                }
            }

            // Clear all current data
            iw.getWarpManager().getGroups().clear();
            loadedGroups.values().forEach(iw.getWarpManager()::addOnLoad);
            iw.getLogger().info("Successfully loaded " + loadedGroups.size() + " warp groups!");

            if (!invalidGroups.isEmpty()) {
                iw.getLogger().warning("Could not load " + invalidGroups.size() + " warp groups!");
                iw.getLogger().warning("Dumping error(s) below:");
                invalidGroups.forEach((key, value) -> iw.getLogger().warning(" - " + key.getName() + ": " + value.getMessage()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            iw.getLogger().severe("Error grabbing warp group files. Check if the groups folder exists in your InfinityWarps folder!");
        }

        try {
            Map<String, Warp> loadedWarps = new HashMap<>();
            Map<File, Exception> invalidWarps = new HashMap<>();
            for (File f : Objects.requireNonNull(warpsFolder.listFiles())) {
                try {
                    WarpConfiguration warpConfiguration = new WarpConfiguration(iw, f);
                    warpConfiguration.unsafeInitialize(false, false);
                    Warp w = warpConfiguration.build();
                    if (loadedWarps.containsKey(w.getInternalId())) throw new DuplicateException(w.getInternalId());
                    loadedWarps.put(w.getInternalId(), w);
                } catch (Exception e2) {
                    invalidWarps.put(f, e2);
                }

                // Clear all current data
                iw.getWarpManager().getWarps().clear();
                loadedWarps.values().forEach(iw.getWarpManager()::addOnLoad);
                iw.getLogger().info("Successfully loaded " + loadedWarps.size() + " warps!");

                if (!invalidWarps.isEmpty()) {
                    iw.getLogger().warning("Could not load " + invalidWarps.size() + " warps!");
                    iw.getLogger().warning("Dumping error(s) below:");
                    invalidWarps.forEach((key, value) -> iw.getLogger().warning(" - " + key.getName() + ": " + value.getMessage()));
                }
            }
        } catch (NullPointerException e) {
            e.printStackTrace();
            iw.getLogger().severe("Error grabbing warp files. Check if the warps folder exists in your InfinityWarps folder!");
        }
    }

    public void write(Warp w) {
        try {
            WarpConfiguration conf = new WarpConfiguration(iw, w);
            conf.save();
        } catch (IOException e) {
            e.printStackTrace();
            iw.getLogger().severe("Error saving warp file " + w.getInternalId() + ".warp!");
        }
    }

    public void write(WarpGroup wg) {
        try {
            WarpGroupConfiguration conf = new WarpGroupConfiguration(iw, wg);
            conf.save();
        } catch (IOException e) {
            e.printStackTrace();
            iw.getLogger().severe("Error saving warp file " + wg.getInternalId() + ".group!");
        }
    }

    private boolean verifyWarpsFolder() {
        if (!warpsFolder.exists()) {
            iw.getLogger().info("Generating warps folder...");
            return warpsFolder.mkdirs();
        }

        return true;
    }

    private boolean verifyGroupsFolder() {
        if (!groupsFolder.exists()) {
            iw.getLogger().info("Generating warp groups folder...");
            return groupsFolder.mkdirs();
        }

        return true;
    }
}