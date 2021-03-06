package dev.justinf.infinitywarps.api.object;

import org.bukkit.configuration.file.YamlConfiguration;

public abstract class DataObject {

    protected String id;

    protected DataObject(String id) {
        this.id = id;
    }

    public String getInternalId() {
        return id.toLowerCase();
    }

    public abstract String pluginFilePath();

    public abstract YamlConfiguration toYaml();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}