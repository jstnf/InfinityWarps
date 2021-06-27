package dev.justinf.infinitywarps.command;

import dev.justinf.infinitywarps.InfinityWarps;
import dev.justinf.infinitywarps.locale.IWRefs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ListwarpsCommand implements CommandExecutor {

    private final InfinityWarps iw;

    public ListwarpsCommand(InfinityWarps iw) {
        this.iw = iw;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (iw.getWarpManager().getWarps().isEmpty()) {
            // Display there are no warps
            sender.sendMessage(iw.getLocale().formatPrefixed(IWRefs.COMMAND_LISTWARPS_NO_WARPS));
            return true;
        }

        int numPages = (int) Math.ceil(iw.getWarpManager().getWarps().size() / 10.0);
        int selectedPage = 1;
        if (args.length > 0) {
            try {
                selectedPage = Integer.parseInt(args[0]);
            } catch (NumberFormatException ignored) {
                // Invalid number passed in
                sender.sendMessage(iw.getLocale().formatPrefixed(IWRefs.COMMAND_LISTWARPS_INVALID_PAGE));
                return true;
            }
        }

        if (selectedPage < 1 || selectedPage > numPages) {
            // Showing invalid page
            sender.sendMessage(iw.getLocale().formatPrefixed(IWRefs.COMMAND_LISTWARPS_INVALID_PAGE));
            return true;
        }

        // TODO
        sender.sendMessage(iw.getLocale().formatPrefixed(IWRefs.GENERAL_TO_BE_IMPLEMENTED));
        return true;
    }
}
