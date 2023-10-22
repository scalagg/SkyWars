package gg.pixelarcade.skywars.utils.logging;

import xyz.refinedev.phoenix.utils.Color;

public enum LoggerType
{
    GREEN("&a"),
    RED("&c"),
    AQUA("&b"),
    PINK("&d"),
    YELLOW("&e"),
    DEBUG("&6&l"),
    BLUE("&9");

    private final String color;

    private LoggerType(final String color) {
        this.color = Color.translate(color);
    }

    public String getColor() {
        return this.color;
    }
}
