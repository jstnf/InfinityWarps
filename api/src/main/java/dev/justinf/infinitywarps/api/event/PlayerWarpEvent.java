package dev.justinf.infinitywarps.api.event;

import dev.justinf.infinitywarps.api.object.Warp;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public class PlayerWarpEvent extends Event implements Cancellable {

    protected static final HandlerList handlerList = new HandlerList();

    private final Player player;
    private final Warp warp;
    private boolean cancelled = false;

    public PlayerWarpEvent(Player p, Warp w) {
        this.player = p;
        this.warp = w;
    }

    @Override
    public HandlerList getHandlers() {
        return handlerList;
    }

    public static HandlerList getHandlerList() {
        return handlerList;
    }

    public Player getPlayer() {
        return player;
    }

    public Warp getWarp() {
        return warp;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancelled) {
        this.cancelled = cancelled;
    }
}