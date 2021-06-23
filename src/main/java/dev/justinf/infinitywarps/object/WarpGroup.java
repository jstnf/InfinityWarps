package dev.justinf.infinitywarps.object;

import dev.justinf.infinitywarps.InfinityWarps;
import dev.justinf.infinitywarps.config.IWConVar;

import java.util.ArrayList;
import java.util.List;

public class WarpGroup {

    private String id;
    private String title;
    private List<String> lore;
    private String iconMaterial;

    // On creation
    public WarpGroup(InfinityWarps iw, String id) {
        this.id = id;
        title = id;
        lore = new ArrayList<>();
        iconMaterial = iw.getConfiguration().getString(IWConVar.ITEM_GROUP);
    }

    // On load
    public WarpGroup(String id, String title, List<String> lore, String iconMaterial) {
        this.id = id;
        this.title = title;
        this.lore = lore;
        this.iconMaterial = iconMaterial;
    }

    /* getset */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getLore() {
        return lore;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public String getIconMaterial() {
        return iconMaterial;
    }

    public void setIconMaterial(String iconMaterial) {
        this.iconMaterial = iconMaterial;
    }
}