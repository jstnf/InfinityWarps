package dev.justinf.infinitywarps.api.object;

import java.util.Map;

public interface IWarpManager {

    /**
     * Add a warp and then save it to a file
     * @param w the warp
     * @return 0 if a warp was created, 1 if a warp was updated, -1 if there was an error saving the warp
     */
    int addOrUpdate(Warp w);

    /**
     * Add a warp group and then save it to a file
     * @param wg the warp group
     * @return 0 if a group was created, 1 if a group was updated, -1 if there was an error saving the group
     */
    int addOrUpdate(WarpGroup wg);

    /**
     * Remove and delete a warp
     * @param id the warp's id
     * @return 0 if a warp was deleted, -1 if there was an error saving the deletion, -2 if the warp doesn't exist
     */
    int removeWarp(String id);

    /**
     * Remove and delete a warp group
     * @param id the warp group's id
     * @return 0 if a group was deleted, -1 if there was an error saving the deletion, -2 if the group doesn't exist
     */
    int removeGroup(String id);

    Warp getWarp(String id);
    WarpGroup getGroup(String id);
    Map<String, Warp> getWarps();
    Map<String, WarpGroup> getGroups();
}