package dev.justinf.infinitywarps.object;

import dev.justinf.infinitywarps.InfinityWarps;
import dev.justinf.infinitywarps.api.object.IWarpManager;
import dev.justinf.infinitywarps.api.object.Warp;
import dev.justinf.infinitywarps.api.object.WarpGroup;
import dev.justinf.infinitywarps.io.WarpConfiguration;
import dev.justinf.infinitywarps.io.WarpGroupConfiguration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class WarpManager implements IWarpManager {

    private final InfinityWarps iw;
    private final Map<String, Warp> warps;
    private final Map<String, WarpGroup> groups;

    public WarpManager(InfinityWarps iw) {
        this.iw = iw;
        warps = new HashMap<>();
        groups = new HashMap<>();
    }

    /**
     * Add a warp and then save it to a file
     * @param w the warp
     * @return 0 if a warp was created, 1 if a warp was updated, -1 if there was an error saving the warp
     */
    public int addOrUpdate(Warp w) {
        WarpConfiguration wc = new WarpConfiguration(iw, w);
        try {
            wc.save();
            return warps.put(w.getInternalId(), w) != null ? 1 : 0;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Add a warp group and then save it to a file
     * @param wg the warp group
     * @return 0 if a group was created, 1 if a group was updated, -1 if there was an error saving the group
     */
    public int addOrUpdate(WarpGroup wg) {
        WarpGroupConfiguration wgc = new WarpGroupConfiguration(iw, wg);
        try {
            wgc.save();
            return groups.put(wg.getInternalId(), wg) != null ? 1 : 0;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Remove and delete a warp
     * @param id the warp's id
     * @return 0 if a warp was deleted, -1 if there was an error saving the deletion, -2 if the warp doesn't exist
     */
    public int removeWarp(String id) {
        WarpConfiguration wc = new WarpConfiguration(iw, id);
        try {
            if (warps.remove(id.toLowerCase()) == null) return -2;
            return wc.delete() ? 0 : -1;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /**
     * Remove and delete a warp group
     * @param id the warp group's id
     * @return 0 if a group was deleted, -1 if there was an error saving the deletion, -2 if the group doesn't exist
     */
    public int removeGroup(String id) {
        WarpGroupConfiguration wgc = new WarpGroupConfiguration(iw, id);
        try {
            if (groups.remove(id.toLowerCase()) == null) return 2;
            return wgc.delete() ? 1 : 0;
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }

    /* Methods used when loading */
    public boolean addOnLoad(Warp w) {
        return warps.put(w.getInternalId(), w) != null;
    }

    public boolean addOnLoad(WarpGroup g) {
        return groups.put(g.getInternalId(), g) != null;
    }
    /* Methods used when loading */

    public Warp getWarp(String id) {
        return warps.get(id.toLowerCase());
    }

    public WarpGroup getGroup(String id) {
        return groups.get(id.toLowerCase());
    }

    /* getset */
    public Map<String, Warp> getWarps() {
        return warps;
    }

    public Map<String, WarpGroup> getGroups() {
        return groups;
    }
}