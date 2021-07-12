package dev.justinf.infinitywarps.util;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Runnables {

    public static void run(JavaPlugin plugin, Runnable runnable) {
        Bukkit.getScheduler().runTask(plugin, runnable);
    }

    public static void runAsync(JavaPlugin plugin, Runnable runnable) {
        Bukkit.getScheduler().runTaskAsynchronously(plugin, runnable);
    }

    public static void timer(JavaPlugin plugin, Runnable runnable, long delay, long period) {
        Bukkit.getScheduler().runTaskTimer(plugin, runnable, delay, period);
    }

    public static void timerAsync(JavaPlugin plugin, Runnable runnable, long delay, long period) {
        Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, runnable, delay, period);
    }

    public static void later(JavaPlugin plugin, Runnable runnable, long delay) {
        Bukkit.getScheduler().runTaskLater(plugin, runnable, delay);
    }

    public static void laterAsync(JavaPlugin plugin, Runnable runnable, long delay) {
        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, runnable, delay);
    }

    public static void cancelTasks(JavaPlugin plugin) {
        Bukkit.getScheduler().cancelTasks(plugin);
    }
}