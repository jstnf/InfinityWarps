package dev.justinf.infinitywarps.gui;

import org.bukkit.entity.Player;

public interface IPanelButton {

    default void onLeft(Player p) { }
    default void onRight(Player p) { }
    default void onMiddle(Player p) { }
    default void onShiftLeft(Player p) { }
}