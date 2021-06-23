package dev.justinf.infinitywarps.object;

import java.util.HashMap;
import java.util.Map;

public class WarpManager {

    private final Map<String, Warp> warps;
    private final Map<String, WarpGroup> groups;

    public WarpManager() {
        warps = new HashMap<>();
        groups = new HashMap<>();
    }

    public boolean addOrUpdate(Warp w) {
        return warps.put(w.getId(), w) != null;
    }

    public boolean addOrUpdate(WarpGroup g) {
        return groups.put(g.getId(), g) != null;
    }

    public Warp getWarp(String id) {
        return warps.get(id);
    }

    public WarpGroup getGroup(String id) {
        return groups.get(id);
    }

    /* getset */
    public Map<String, Warp> getWarps() {
        return warps;
    }

    public Map<String, WarpGroup> getGroups() {
        return groups;
    }
}