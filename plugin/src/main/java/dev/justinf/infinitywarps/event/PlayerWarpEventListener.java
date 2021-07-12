package dev.justinf.infinitywarps.event;

import dev.justinf.infinitywarps.InfinityWarps;
import dev.justinf.infinitywarps.api.event.PlayerWarpEvent;
import dev.justinf.infinitywarps.locale.IWRefs;
import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;

public class PlayerWarpEventListener implements Listener {

    private final InfinityWarps iw;

    public PlayerWarpEventListener(InfinityWarps iw) {
        this.iw = iw;
    }

    @EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
    public void on(PlayerWarpEvent e) {
        Location l = e.getWarp().getLocation();
        // Handle invalid location
        if (l == null) {
            e.getPlayer().sendMessage(iw.getLocale().formatNoColorArgs(IWRefs.GENERAL_WARP_INVALID_LOCATION, e.getWarp().getId()));
            return;
        }

        // Do teleportation
        e.getPlayer().teleport(e.getWarp().getLocation());
    }
}