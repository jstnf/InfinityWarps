package dev.justinf.infinitywarps.command;

import dev.justinf.infinitywarps.InfinityWarps;
import dev.justinf.infinitywarps.locale.IWRefs;
import dev.justinf.infinitywarps.util.IWPermissions;
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

        // TODO
        sender.sendMessage(iw.getLocale().formatPrefixed(IWRefs.GENERAL_TO_BE_IMPLEMENTED));
        return true;
    }
}
