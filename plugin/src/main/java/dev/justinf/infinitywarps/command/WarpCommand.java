package dev.justinf.infinitywarps.command;

import dev.justinf.infinitywarps.InfinityWarps;
import dev.justinf.infinitywarps.api.event.PlayerWarpEvent;
import dev.justinf.infinitywarps.api.object.Warp;
import dev.justinf.infinitywarps.locale.IWRefs;
import dev.justinf.infinitywarps.util.IWPermissions;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class WarpCommand extends TabWarpExecutor {

    public WarpCommand(InfinityWarps iw) {
        super(iw);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length > 0) {
            Warp w = iw.getWarpManager().getWarp(args[0].toLowerCase());
            if (w == null) {
                sender.sendMessage(iw.getLocale().formatNoColorArgsPrefixed(IWRefs.COMMAND_WARP_INVALID_WARP, args[0]));
                return true;
            }

            Player target;

            // Player target
            if (args.length > 1) {
                if (!(sender.hasPermission(IWPermissions.WARP_OTHERS))) {
                    sender.sendMessage(iw.getLocale().formatPrefixed(IWRefs.GENERAL_NO_PERMISSION, IWPermissions.WARP_OTHERS));
                    return true;
                }

                target = Bukkit.getPlayer(args[1]);
                if (target == null) {
                    sender.sendMessage(iw.getLocale().formatNoColorArgsPrefixed(IWRefs.GENERAL_COULD_NOT_FIND_PLAYER, args[1]));
                    return true;
                }
                sender.sendMessage(iw.getLocale().formatNoColorArgsPrefixed(IWRefs.COMMAND_WARP_SUCCESS_TARGET, target.getName(), w.getId()));
            } else {
                if (!(sender instanceof Player)) {
                    sender.sendMessage(iw.getLocale().formatPrefixed(IWRefs.GENERAL_MUST_BE_PLAYER));
                    return true;
                }
                target = (Player) sender;
                sender.sendMessage(iw.getLocale().formatNoColorArgsPrefixed(IWRefs.COMMAND_WARP_SUCCESS, w.getId()));
            }

            // Call an event so other plugins can cancel it via API
            iw.getServer().getPluginManager().callEvent(new PlayerWarpEvent(target, w));
        } else {
            sender.sendMessage(iw.getLocale().formatPrefixed(IWRefs.COMMAND_WARP_USAGE, label));
            return true;
        }
        return true;
    }
}