package dev.justinf.infinitywarps.command;

import dev.justinf.infinitywarps.InfinityWarps;
import dev.justinf.infinitywarps.locale.IWRefs;
import dev.justinf.infinitywarps.object.Warp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.List;

public class WarpCommand implements TabExecutor {

    private final InfinityWarps iw;

    public WarpCommand(InfinityWarps iw) {
        this.iw = iw;
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

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        // TODO
        return null;
    }

    private void sendHelp(CommandSender sender, String label) {
        sender.sendMessage(iw.getLocale().formatPrefixed(IWRefs.COMMAND_WARP_USAGE, label));
    }
}