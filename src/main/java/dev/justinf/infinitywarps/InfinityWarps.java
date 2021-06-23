package dev.justinf.infinitywarps;

import dev.justinf.infinitywarps.config.IWConfiguration;
import dev.justinf.infinitywarps.event.PanelListener;
import dev.justinf.infinitywarps.gui.PanelManager;
import dev.justinf.infinitywarps.io.FileIO;
import dev.justinf.infinitywarps.locale.IWLocale;
import dev.justinf.infinitywarps.object.WarpManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class InfinityWarps extends JavaPlugin {

    private FileIO fileIO;
    private IWConfiguration configuration;
    private IWLocale locale;
    private PanelManager panelManager;
    private WarpManager warpManager;

    @Override
    public void onLoad() {
        configuration = new IWConfiguration(this);
        if (!configuration.onEnable()) return;

        locale = new IWLocale(this);
        locale.load();

        loaded = true;
    }

    private boolean loaded = false;

    @Override
    public void onEnable() {
        if (!loaded) {
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        warpManager = new WarpManager();
        fileIO = new FileIO(this);

        getServer().getPluginManager().registerEvents(new PanelListener(), this);
    }

    @Override
    public void onDisable() { }

    /* getset */
    public FileIO getFileIO() {
        return fileIO;
    }

    public IWConfiguration getConfiguration() {
        return configuration;
    }

    public IWLocale getLocale() {
        return locale;
    }

    public PanelManager getPanelManager() {
        return panelManager;
    }

    public WarpManager getWarpManager() {
        return warpManager;
    }
}