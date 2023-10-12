package gg.pixelarcade.skywars.arena.menu.manageschematic;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.arena.ArenaSchematic;
import gg.pixelarcade.skywars.arena.menu.manageschematics.ManageSchematicsMenu;
import org.bukkit.entity.Player;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.menu.api.Menu;
import xyz.refinedev.phoenix.menu.api.button.BackButton;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public final class ManageSchematicMenu extends Menu {

    private final ArenaSchematic schematic;

    public ManageSchematicMenu(ArenaSchematic schematic) {
        setAutoUpdate(true);

        this.schematic = schematic;
    }

    @Override
    public String getTitle(Player player) {
        return "Manage " + schematic.getName();
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        Map<Integer, Button> buttons = new HashMap<>();
        Consumer<ArenaSchematic> save = schematic -> {
            try {
                SkyWarsPlugin.getInstance().getArenaHandler().saveSchematics();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        };


        buttons.put(0, new ToggleEnabledButton(schematic));
//        buttons.put(1, new IntegerTraitButton<>(schematic, "Max Player Count", ArenaSchematic::setMaxPlayerCount, ArenaSchematic::getMaxPlayerCount, save));
//        buttons.put(2, new IntegerTraitButton<>(schematic, "Min Player Count", ArenaSchematic::setMinPlayerCount, ArenaSchematic::getMinPlayerCount, save));

        buttons.put(4, new BackButton(new ManageSchematicsMenu()));

        buttons.put(8, new ModelEditorButton(schematic));

        // 1st line

        return buttons;
    }

}