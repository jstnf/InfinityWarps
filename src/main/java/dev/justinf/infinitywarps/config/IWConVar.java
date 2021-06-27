package dev.justinf.infinitywarps.config;

public enum IWConVar {

    LOCALE("infinitywarps.locale", "en_US"),
    USE_PREFIX_IN_MESSAGES("infinitywarps.use-prefix-in-messages", true),
    CENTER_ON_BLOCK("infinitywarps.center-on-block", true),
    SHOW_COORDS("infinitywarps.show-coords", true),
    ITEM_BORDER("infinitywarps.item.border", "BLACK_STAINED_GLASS_PANE"),
    ITEM_WARP("infinitywarps.item.warp", "ENDER_PEARL"),
    ITEM_GROUP("infinitywarps.item.group", "BOOK");

    public String path;
    public Object def;

    IWConVar(String path, Object def) {
        this.path = path;
        this.def = def;
    }
}