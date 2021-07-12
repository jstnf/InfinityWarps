package dev.justinf.infinitywarps.command;

import dev.justinf.infinitywarps.InfinityWarps;
import dev.justinf.infinitywarps.api.object.Warp;
import dev.justinf.infinitywarps.locale.IWRefs;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.hover.content.Text;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.List;
import java.util.stream.Collectors;

public class ListwarpsCommand implements CommandExecutor {

    private static final int WARPS_PER_PAGE = 15;

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

        int numWarps = iw.getWarpManager().getWarps().size();
        int numPages = (int) Math.ceil(numWarps / (double) WARPS_PER_PAGE);
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

        // Header
        sender.sendMessage(iw.getLocale().format(IWRefs.COMMAND_LISTWARPS_HEADER, numWarps + "", selectedPage + "", numPages + ""));

        String color = iw.getLocale().format(IWRefs.COMMAND_LISTWARPS_LISTING_COLOR);
        String colorAlt = iw.getLocale().format(IWRefs.COMMAND_LISTWARPS_LISTING_COLOR_ALT);

        // Sort the list of warps alphabetically, and then get the warps for that specific page
        List<String> warpIds = iw.getWarpManager().getWarps().values()
                .stream()
                .map(Warp::getId)
                .sorted(String.CASE_INSENSITIVE_ORDER)
                .collect(Collectors.toList());

        ComponentBuilder warpContents = new ComponentBuilder();
        int startIndex = WARPS_PER_PAGE * (selectedPage - 1);
        for (int i = startIndex; i < warpIds.size() && i < startIndex + WARPS_PER_PAGE; i++) {
            if (i != startIndex) warpContents.append(iw.getLocale().format(IWRefs.COMMAND_LISTWARPS_LISTING_DIVIDER), ComponentBuilder.FormatRetention.NONE);
            String id = warpIds.get(i);
            warpContents.append((i % 2 == 0 ? color : colorAlt) + id);
            warpContents.event(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/warp " + id));
            warpContents.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text(iw.getLocale().format(IWRefs.COMMAND_LISTWARPS_HOVER_TIP, id))));
        }

        sender.spigot().sendMessage(warpContents.create());
        return true;
    }
}
