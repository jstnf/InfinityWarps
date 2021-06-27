package dev.justinf.infinitywarps.command;

import dev.justinf.infinitywarps.InfinityWarps;
import dev.justinf.infinitywarps.locale.IWRefs;
import dev.justinf.infinitywarps.object.Warp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand extends TabWarpExecutor {

    public WarpCommand(InfinityWarps iw) {
        super(iw);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(iw.getLocale().formatPrefixed(IWRefs.GENERAL_MUST_BE_PLAYER));
            return true;
        }

        if (args.length > 0) {
            Warp w = iw.getWarpManager().getWarp(args[0].toLowerCase());
            if (w == null) {
                sender.sendMessage(iw.getLocale().formatNoColorArgsPrefixed(IWRefs.COMMAND_WARP_INVALID_WARP, args[0]));
                return true;
            }

            // Player target
            if (args.length > 1) {
                // TODO
                sender.sendMessage(iw.getLocale().formatPrefixed(IWRefs.GENERAL_TO_BE_IMPLEMENTED));
                return true;
            }

            // TODO message
            ((Player) sender).teleport(w.getLocation());
        } else {
            sendHelp(sender, label);
            return true;
        }
        return true;
    }

    private void sendHelp(CommandSender sender, String label) {
        sender.sendMessage(iw.getLocale().formatPrefixed(IWRefs.COMMAND_WARP_USAGE, label));
    }
}