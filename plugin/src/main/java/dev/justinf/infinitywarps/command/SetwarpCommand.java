package dev.justinf.infinitywarps.command;

import dev.justinf.infinitywarps.InfinityWarps;
import dev.justinf.infinitywarps.api.object.Warp;
import dev.justinf.infinitywarps.config.IWConVar;
import dev.justinf.infinitywarps.locale.IWRefs;
import dev.justinf.infinitywarps.util.IWPermissions;
import dev.justinf.infinitywarps.util.Runnables;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetwarpCommand implements CommandExecutor {

    private final InfinityWarps iw;

    public SetwarpCommand(InfinityWarps iw) {
        this.iw = iw;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(iw.getLocale().formatPrefixed(IWRefs.GENERAL_MUST_BE_PLAYER));
            return true;
        }

        if (!sender.hasPermission(IWPermissions.SETWARP)) {
            sender.sendMessage(iw.getLocale().formatPrefixed(IWRefs.GENERAL_NO_PERMISSION, IWPermissions.SETWARP));
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage(iw.getLocale().formatPrefixed(IWRefs.COMMAND_SETWARP_USAGE, label));
            return true;
        }

        Warp w = iw.getWarpManager().getWarp(args[0]);
        if (w != null) {
            w.setLocation(((Player) sender).getLocation());
        } else {
            w = new Warp(args[0], ((Player) sender).getLocation());
            w.setIconMaterial(iw.getConfiguration().getString(IWConVar.ITEM_WARP));
        }

        Warp finalW = w;
        Runnables.runAsync(iw, () -> {
            switch (iw.getWarpManager().addOrUpdate(finalW)) {
                case 1:                         // Warp was updated
                    sender.sendMessage(iw.getLocale().formatNoColorArgsPrefixed(IWRefs.COMMAND_SETWARP_SUCCESS_UPDATED, finalW.getId()));
                    break;
                case 0:                         // Warp was created
                    sender.sendMessage(iw.getLocale().formatNoColorArgsPrefixed(IWRefs.COMMAND_SETWARP_SUCCESS, finalW.getId()));
                    break;
                default:                        // Error saving the warp
                    sender.sendMessage(iw.getLocale().formatNoColorArgsPrefixed(IWRefs.COMMAND_SETWARP_FAILURE_ERROR_SAVING, finalW.getId()));
                    break;
            }
        });
        return true;
    }
}