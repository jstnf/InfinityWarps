package dev.justinf.infinitywarps.command;

import dev.justinf.infinitywarps.InfinityWarps;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
                results = new ArrayList<>(iw.getWarpManager().getWarps().keySet());
                results.sort(Comparator.naturalOrder());
                break;
            case 1:
                String arg0 = args[0];
                results = new ArrayList<>();
                for (String warpId : iw.getWarpManager().getWarps().keySet()) {
                    if (warpId.toLowerCase().indexOf(arg0.toLowerCase()) == 0) {
                        results.add(warpId);
                    }
                }
                results.sort(Comparator.naturalOrder());
                break;
            default:
                return null;
        }
        return results;
    }
}