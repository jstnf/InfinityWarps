package dev.justinf.infinitywarps.gui;

import dev.justinf.infinitywarps.InfinityWarps;
import dev.justinf.infinitywarps.gui.panel.WarpPanel;
import dev.justinf.infinitywarps.util.Runnables;
import org.bukkit.entity.Player;

public class PanelManager {

    private final InfinityWarps iw;

    public PanelManager(InfinityWarps iw) {
        this.iw = iw;
    }

    public void openWarpsPanel(Player p) {
        Runnables.later(iw, () -> p.openInventory(new WarpPanel(iw).getInventory()), 1L);
    }
}