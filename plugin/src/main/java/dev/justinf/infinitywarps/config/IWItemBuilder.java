package dev.justinf.infinitywarps.config;

import dev.justinf.infinitywarps.InfinityWarps;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class IWItemBuilder {

    private final InfinityWarps iw;

    public IWItemBuilder(InfinityWarps iw) {
        this.iw = iw;
    }

    public ItemStack BORDER() {
        Material borderMat = iw.getConfiguration().getMaterial(IWConVar.ITEM_BORDER);
        ItemStack is = new ItemStack(borderMat);
        ItemMeta meta = is.getItemMeta();
        assert meta != null;
        meta.setDisplayName(" ");
        is.setItemMeta(meta);
        return is;
    }
}