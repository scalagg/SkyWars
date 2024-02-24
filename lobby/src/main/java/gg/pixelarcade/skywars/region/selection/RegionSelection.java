package gg.pixelarcade.skywars.region.selection;

import gg.pixelarcade.skywars.region.Region;
import gg.pixelarcade.skywars.util.cuboid.Cuboid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.XMaterial;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;


/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.region.selection
 * @date 19/10/2023 / 16:16
 */

@RequiredArgsConstructor
@Getter @Setter
public class RegionSelection {

    public final static String SELECTION_WAND_KEY = "SelectionWand";
    public final static ItemStack SELECTION_WAND = new ItemBuilder(XMaterial.GOLDEN_AXE)
            .setNameWithArrows(Color.translate("&e&lSelection Wand"))
            .toItemStack();

    private Location position1;
    private Location position2;

    public boolean isValidSelection() {
        return position1 != null && position2 != null;
    }

    public Region getRegion() {
        if (!isValidSelection())
            return null;

        return new Region(new Cuboid(position1, position2));
    }

}
