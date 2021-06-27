package dev.justinf.infinitywarps.io;

import dev.justinf.infinitywarps.InfinityWarps;
import dev.justinf.infinitywarps.object.Warp;
import dev.justinf.infinitywarps.object.WarpGroup;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
            List<WarpGroup> loadedGroups = new ArrayList<>();
            Map<File, Exception> invalidGroups = new HashMap<>();
            for (File f : Objects.requireNonNull(groupsFolder.listFiles())) {
                try {
                    WarpGroupConfiguration groupConfiguration = new WarpGroupConfiguration(iw, f);
                    groupConfiguration.unsafeInitialize(false, false);
                    loadedGroups.add(groupConfiguration.toWarpGroup());
                } catch (Exception e2) {
                    invalidGroups.put(f, e2);
                }
            }

            // Clear all current data
            iw.getWarpManager().getGroups().clear();
            loadedGroups.forEach(iw.getWarpManager()::addOrUpdateLocally);
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
            List<Warp> loadedWarps = new ArrayList<>();
            Map<File, Exception> invalidWarps = new HashMap<>();
            for (File f : Objects.requireNonNull(warpsFolder.listFiles())) {
                try {
                    WarpConfiguration warpConfiguration = new WarpConfiguration(iw, f);
                    warpConfiguration.unsafeInitialize(false, false);
                    loadedWarps.add(warpConfiguration.toWarp());
                } catch (Exception e2) {
                    invalidWarps.put(f, e2);
                }

                // Clear all current data
                iw.getWarpManager().getWarps().clear();
                loadedWarps.forEach(iw.getWarpManager()::addOrUpdateLocally);
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