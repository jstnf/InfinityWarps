package dev.justinf.infinitywarps;

import dev.justinf.infinitywarps.api.InfinityWarpsAPI;
import dev.justinf.infinitywarps.command.DelwarpCommand;
import dev.justinf.infinitywarps.command.IWCommand;
import dev.justinf.infinitywarps.command.ListwarpsCommand;
import dev.justinf.infinitywarps.command.SetwarpCommand;
import dev.justinf.infinitywarps.command.WarpCommand;
import dev.justinf.infinitywarps.command.WarpsCommand;
import dev.justinf.infinitywarps.config.IWConfiguration;
import dev.justinf.infinitywarps.event.PanelListener;
import dev.justinf.infinitywarps.event.PlayerWarpEventListener;
import dev.justinf.infinitywarps.gui.PanelManager;
import dev.justinf.infinitywarps.io.FileIO;
import dev.justinf.infinitywarps.locale.IWLocale;
import dev.justinf.infinitywarps.object.WarpManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.ServicePriority;
import org.bukkit.plugin.java.JavaPlugin;

public class InfinityWarps extends JavaPlugin implements InfinityWarpsAPI {

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

        fileIO = new FileIO(this);
        if (!fileIO.onEnable()) return;

        Bukkit.getServicesManager().register(InfinityWarpsAPI.class, this, this, ServicePriority.Highest);
        loaded = true;
    }

    private boolean loaded = false;

    @Override
    public void onEnable() {
        if (!loaded) {
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        warpManager = new WarpManager(this);
        fileIO.load();
        panelManager = new PanelManager(this);

        getCommand("iwdelwarp").setExecutor(new DelwarpCommand(this));
        getCommand("iw").setExecutor(new IWCommand(this));
        getCommand("iwlistwarps").setExecutor(new ListwarpsCommand(this));
        getCommand("iwsetwarp").setExecutor(new SetwarpCommand(this));
        getCommand("iwwarp").setExecutor(new WarpCommand(this));
        getCommand("iwwarps").setExecutor(new WarpsCommand(this));

        getServer().getPluginManager().registerEvents(new PanelListener(), this);
        getServer().getPluginManager().registerEvents(new PlayerWarpEventListener(this), this);
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

    @Override
    public WarpManager getWarpManager() {
        return warpManager;
    }
}