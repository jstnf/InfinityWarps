package dev.justinf.infinitywarps.object;

import dev.justinf.infinitywarps.InfinityWarps;
import dev.justinf.infinitywarps.config.IWConVar;
import org.apache.commons.lang.StringUtils;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class WarpGroup extends DataObject {

    private String title;
    private List<String> lore;
    private String iconMaterial;
    private List<String> warps;

    // On creation
    public WarpGroup(InfinityWarps iw, String id) {
        super(id);
        title = id;
        lore = new ArrayList<>();
        iconMaterial = iw.getConfiguration().getString(IWConVar.ITEM_GROUP);
        warps = new ArrayList<>();
    }

    // On load
    public WarpGroup(String id, String title, List<String> lore, String iconMaterial, List<String> warps) {
        super(id);
        this.title = title;
        this.lore = lore;
        this.iconMaterial = iconMaterial;
        this.warps = warps;
    }

    @Override
    public String pluginFilePath() {
        return "groups" + File.separator + getInternalId() + ".group";
    }

    @Override
    public YamlConfiguration toYaml() {
        YamlConfiguration result = new YamlConfiguration();
        result.set("id", id);
        result.set("title", title);
        result.set("lore", StringUtils.join(lore, '\n'));
        result.set("icon-material", iconMaterial);
        result.set("warps", warps);
        return result;
    }

    /* getset */
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

    public List<String> getWarps() {
        return warps;
    }

    public void setWarps(List<String> warps) {
        this.warps = warps;
    }
}