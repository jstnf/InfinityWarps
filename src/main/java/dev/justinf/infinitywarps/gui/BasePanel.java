package dev.justinf.infinitywarps.gui;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import java.util.HashMap;
import java.util.Map;

public abstract class BasePanel implements InventoryHolder {

    private final Map<Integer, IPanelButton> buttons;
    private Inventory inventory;

    public BasePanel() {
        buttons = new HashMap<>();
    }

    /* getset */
    public Map<Integer, IPanelButton> getButtons() {
        return buttons;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}