package dev.justinf.infinitywarps.io;

import dev.justinf.infinitywarps.InfinityWarps;
import dev.justinf.infinitywarps.api.object.DataObject;
import dev.justinf.infinitywarps.exception.MissingKeyException;
import dev.justinf.infinitywarps.util.BaseConfiguration;

import java.io.File;
import java.io.IOException;

public abstract class DataConfiguration<T extends DataObject> extends BaseConfiguration {

    protected File dataFile;

    // Used when loading
    public DataConfiguration(InfinityWarps iw, String filePath) {
        super(iw, filePath);
    }

    // Used when saving
    public DataConfiguration(InfinityWarps iw, T data) {
        super(iw, data.pluginFilePath());
        dataFile = new File(iw.getDataFolder() + File.separator + data.pluginFilePath());
        config = data.toYaml();
    }

    public abstract T build() throws MissingKeyException;

    public void save() throws IOException {
        config.save(dataFile);
    }

    public boolean delete() {
        return dataFile.exists() && dataFile.delete();
    }

    /* MissingKeyException getters */
    public String getString(String path) throws MissingKeyException {
        if (!config.contains(path)) throw new MissingKeyException(path);
        return config.getString(path);
    }

    public boolean getBoolean(String path) throws MissingKeyException {
        if (!config.contains(path)) throw new MissingKeyException(path);
        return config.getBoolean(path);
    }

    public double getDouble(String path) throws MissingKeyException {
        if (!config.contains(path)) throw new MissingKeyException(path);
        return config.getDouble(path);
    }

    public int getInt(String path) throws MissingKeyException {
        if (!config.contains(path)) throw new MissingKeyException(path);
        return config.getInt(path);
    }
}