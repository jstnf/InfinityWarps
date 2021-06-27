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

    public boolean addOrUpdateLocally(Warp w) {
        return warps.put(w.getId().toLowerCase(), w) != null;
    }

    public boolean addOrUpdateLocally(WarpGroup g) {
        return groups.put(g.getId().toLowerCase(), g) != null;
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