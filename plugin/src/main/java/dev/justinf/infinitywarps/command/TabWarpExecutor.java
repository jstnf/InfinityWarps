package dev.justinf.infinitywarps.command;

import dev.justinf.infinitywarps.InfinityWarps;
import dev.justinf.infinitywarps.api.object.Warp;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public abstract class TabWarpExecutor implements TabExecutor {

    protected final InfinityWarps iw;

    public TabWarpExecutor(InfinityWarps iw) {
        this.iw = iw;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) {
        List<String> results;
        switch (args.length) {
            case 0:
                results = iw.getWarpManager().getWarps().values()
                        .stream()
                        .map(Warp::getId)
                        .sorted(String.CASE_INSENSITIVE_ORDER)
                        .collect(Collectors.toList());
                break;
            case 1:
                String arg0 = args[0];
                results = new ArrayList<>();
                for (Warp w : iw.getWarpManager().getWarps().values()) {
                    if (w.getId().toLowerCase().indexOf(arg0.toLowerCase()) == 0) {
                        results.add(w.getId());
                    }
                }
                results.sort(String.CASE_INSENSITIVE_ORDER);
                break;
            default:
                return null;
        }
        return results;
    }
}