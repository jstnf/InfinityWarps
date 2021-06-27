package dev.justinf.infinitywarps.object;

import dev.justinf.infinitywarps.InfinityWarps;
import dev.justinf.infinitywarps.config.IWConVar;
import org.bukkit.Bukkit;
import org.bukkit.Location;

import java.util.ArrayList;
import java.util.List;

public class Warp {

    private String id;
    private String title;
    private List<String> lore;
    private WarpGroup group;
    private String iconMaterial;

    private String world;
    private double x;
    private double y;
    private double z;
    private float pitch;
    private float yaw;

    // On creation
    public Warp(InfinityWarps iw, String id, Location l) {
        this.id = id;
        title = id;
        lore = new ArrayList<>();
        group = null;
        iconMaterial = iw.getConfiguration().getString(IWConVar.ITEM_WARP);
        world = l.getWorld().getName();
        x = l.getX();
        y = l.getY();
        z = l.getZ();
        pitch = l.getPitch();
        yaw = l.getYaw();
    }

    // On load
    public Warp(String id, String title, List<String> lore, WarpGroup group, String iconMaterial, String world, double x, double y, double z, float pitch, float yaw) {
        this.id = id;
        this.title = title;
        this.lore = lore;
        this.group = group;
        this.iconMaterial = iconMaterial;
        this.world = world;
        this.x = x;
        this.y = y;
        this.z = z;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public Location getLocation() {
        if (Bukkit.getWorld(world) != null) {
            return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
        }

        return null;
    }

    public void setLocation(Location l) {
        world = l.getWorld().getName();
        x = l.getX();
        y = l.getY();
        z = l.getZ();
        pitch = l.getPitch();
        yaw = l.getYaw();
    }

    /* getset */
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getLore() {
        return lore;
    }

    public void setLore(List<String> lore) {
        this.lore = lore;
    }

    public WarpGroup getGroup() {
        return group;
    }

    public void setGroup(WarpGroup group) {
        this.group = group;
    }

    public String getIconMaterial() {
        return iconMaterial;
    }

    public void setIconMaterial(String iconMaterial) {
        this.iconMaterial = iconMaterial;
    }

    public String getWorld() {
        return world;
    }

    public void setWorld(String world) {
        this.world = world;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }

    public float getPitch() {
        return pitch;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }
}