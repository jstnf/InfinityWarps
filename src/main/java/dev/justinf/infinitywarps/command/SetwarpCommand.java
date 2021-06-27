package dev.justinf.infinitywarps.command;

import dev.justinf.infinitywarps.InfinityWarps;
import dev.justinf.infinitywarps.locale.IWRefs;
import dev.justinf.infinitywarps.util.IWPermissions;
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

        // TODO
        sender.sendMessage(iw.getLocale().formatPrefixed(IWRefs.GENERAL_TO_BE_IMPLEMENTED));
        return true;
    }
}