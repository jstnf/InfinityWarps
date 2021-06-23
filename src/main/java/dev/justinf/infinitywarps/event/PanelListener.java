package dev.justinf.infinitywarps.event;

import dev.justinf.infinitywarps.gui.BasePanel;
import dev.justinf.infinitywarps.gui.IPanelButton;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class PanelListener implements Listener {

    @EventHandler
    public void on(InventoryClickEvent e) {
        if (!(e.getInventory().getHolder() instanceof BasePanel)) return;
        e.setCancelled(true);

        IPanelButton button = ((BasePanel) e.getInventory().getHolder()).getButtons().get(e.getSlot());
        if (button == null) return;

        Player p = (Player) e.getWhoClicked();
        switch (e.getClick()) {
            case LEFT:
                button.onLeft(p);
                break;
            case RIGHT:
                button.onRight(p);
                break;
            case MIDDLE:
                button.onMiddle(p);
                break;
            case SHIFT_LEFT:
                button.onShiftLeft(p);
                break;
        }
    }
}