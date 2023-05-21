package xyz.refinedev.skywars.menu.arena.manage;

import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import xyz.refinedev.skywars.menu.arena.ArenasMenu;
import xyz.refinedev.skywars.pxSkyWars;
import xyz.refinedev.skywars.arena.ArenaSchematic;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.menu.api.Menu;
import xyz.refinedev.phoenix.menu.api.button.BackButton;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
public final class ManageSchematicMenu extends Menu {
    private final ArenaSchematic schematic;
    @Override
    public String getTitle(Player player) {
        return "&b&lArenas &7&l► &3" + schematic.getName();
    }
    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> slots = new HashMap<>();
        slots.put(0, new SchematicStatusButton(schematic));
        slots.put(1, new ToggleEnabledButton(schematic));
        slots.put(3, new TeleportToModelButton(schematic));
        slots.put(4, new SaveModelButton(schematic));
        slots.put(5, new DeleteButton(schematic));
        if (!pxSkyWars.getInstance().getArenaHandler().getGrid().isBusy()) {
            slots.put(7, new CreateCopiesButton(schematic));
            slots.put(8, new RemoveCopiesButton(schematic));
        } else {
            slots.put(7, Button.placeholder());
            slots.put(8, Button.placeholder());
        }
        slots.put(9, new BackButton(new ArenasMenu()));
        return slots;
    }
}