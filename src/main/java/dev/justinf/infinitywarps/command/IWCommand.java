package dev.justinf.infinitywarps.command;

import dev.justinf.infinitywarps.InfinityWarps;
import dev.justinf.infinitywarps.locale.IWRefs;
import dev.justinf.infinitywarps.util.IWPermissions;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class IWCommand implements CommandExecutor {

    private final InfinityWarps iw;

    public IWCommand(InfinityWarps iw) {
        this.iw = iw;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (args.length == 0) {
            sender.sendMessage(credits());
        } else {
            switch (args[0].toLowerCase()) {
                case "help":
                    sender.sendMessage(iw.getLocale().format(IWRefs.COMMAND_IW_HELP_MESSAGE));
                    break;
                case "reload":
                    if (!sender.hasPermission(IWPermissions.IW_RELOAD)) {
                        sender.sendMessage(iw.getLocale().formatPrefixed(IWRefs.GENERAL_NO_PERMISSION, IWPermissions.IW_RELOAD));
                        return true;
                    }

                    // TODO
                    sender.sendMessage(iw.getLocale().formatPrefixed(IWRefs.GENERAL_TO_BE_IMPLEMENTED));
                    break;
                default:
                    sender.sendMessage(credits());
                    break;
            }
        }
        return true;
    }

    private final String credits() {
        return ChatColor.translateAlternateColorCodes('&',
               "  &8\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\n" +
               "  &8\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b&f     &b&lInfinity&d&lWarps&8 (&7v" + iw.getDescription().getVersion() + "&8)\n" +
               "  &8\u2b1b&b\u2b1b&3\u2b1b&8\u2b1b&5\u2b1b&8\u2b1b&d\u2b1b&8\u2b1b&f     &fWarp Management and GUI\n" +
               "  &8\u2b1b&b\u2b1b&3\u2b1b&8\u2b1b&5\u2b1b&8\u2b1b&d\u2b1b&8\u2b1b\n" +
               "  &8\u2b1b&b\u2b1b&3\u2b1b&8\u2b1b&5\u2b1b&8\u2b1b&d\u2b1b&8\u2b1b&f     &fBy &bjstnf\n" +
               "  &8\u2b1b&b\u2b1b&8\u2b1b&3\u2b1b&8\u2b1b&5\u2b1b&8\u2b1b\u2b1b&f     &fMade with &c\u2764\n" +
               "  &8\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b&f     &7github.com/jstnf/InfinityWarps\n" +
               "  &8\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b\u2b1b"
        );
    }
}