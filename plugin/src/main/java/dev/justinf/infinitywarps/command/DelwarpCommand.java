package dev.justinf.infinitywarps.command;

import dev.justinf.infinitywarps.InfinityWarps;
import dev.justinf.infinitywarps.locale.IWRefs;
import dev.justinf.infinitywarps.util.IWPermissions;
import dev.justinf.infinitywarps.util.Runnables;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DelwarpCommand extends TabWarpExecutor {

    public DelwarpCommand(InfinityWarps iw) {
        super(iw);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(iw.getLocale().formatPrefixed(IWRefs.GENERAL_MUST_BE_PLAYER));
            return true;
        }

        if (!sender.hasPermission(IWPermissions.DELWARP)) {
            sender.sendMessage(iw.getLocale().formatPrefixed(IWRefs.GENERAL_NO_PERMISSION, IWPermissions.DELWARP));
            return true;
        }

        if (args.length != 1) {
            sender.sendMessage(iw.getLocale().formatPrefixed(IWRefs.COMMAND_DELWARP_USAGE, label));
            return true;
        }

        Runnables.runAsync(iw, () -> {
            switch (iw.getWarpManager().removeWarp(args[0])) {
                case 0:                         // Warp was deleted
                    sender.sendMessage(iw.getLocale().formatNoColorArgsPrefixed(IWRefs.COMMAND_DELWARP_SUCCESS, args[0]));
                    break;
                case -2:                        // Warp does not exist
                    sender.sendMessage(iw.getLocale().formatNoColorArgsPrefixed(IWRefs.COMMAND_DELWARP_INVALID_WARP, args[0]));
                    break;
                default:                        // Error saving deletion
                    sender.sendMessage(iw.getLocale().formatPrefixed(IWRefs.COMMAND_DELWARP_FAILURE_ERROR_SAVING));
                    break;
            }
        });
        return true;
    }
}
