package dev.justinf.infinitywarps.gui.panel;

import dev.justinf.infinitywarps.InfinityWarps;
import dev.justinf.infinitywarps.gui.BasePanel;
import dev.justinf.infinitywarps.locale.IWRefs;
import org.bukkit.Bukkit;
import org.bukkit.inventory.ItemStack;

public class WarpPanel extends BasePanel {

    private static final int[] BORDER_SLOTS = {  0,  1,  2,  3,  4,  5,  6,  7,  8,
                                                 9,                             17,
                                                18,                             26,
                                                27,                             35,
                                                36,                             44,
                                                45, 46, 47, 48, 49, 50, 51, 52, 53 };

    private final InfinityWarps iw;
    private int page;

    public WarpPanel(InfinityWarps iw) {
        super();
        this.iw = iw;
        page = 0;
        construct();
    }

    private void construct() {
        inventory = Bukkit.createInventory(this, 9 * 6, iw.getLocale().format(IWRefs.PANEL_TITLE_WARPS));
        ItemStack border = iw.getConfiguration().items().BORDER();
        for (int slot : BORDER_SLOTS) {
            inventory.setItem(slot, border);
        }
    }

    private void update() {

    }
}