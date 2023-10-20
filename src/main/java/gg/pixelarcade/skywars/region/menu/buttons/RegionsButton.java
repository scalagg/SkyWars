package gg.pixelarcade.skywars.region.menu.buttons;

import gg.pixelarcade.skywars.SkyWarsPlugin;
import gg.pixelarcade.skywars.region.Region;
import gg.pixelarcade.skywars.region.menu.RegionEditMenu;
import lombok.RequiredArgsConstructor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.Arrays;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.skywars.region.menu.buttons
 * @date 19/10/2023 / 19:25
 */

@RequiredArgsConstructor
public class RegionsButton extends Button {

    private final Region region;

    @Override
    public ItemStack getItem(Player player) {
        return new ItemBuilder(Material.PAPER)
                .setName("&6&l" + region.getName())
                .setLore(Arrays.asList(
                        "",
                        "&6■ &fToggled&7: &e" + colorBoolean(region.isEnabled()),
                        "&6■ &fBreak Blocks&7: &e" + colorBoolean(region.isBreakBlocks()),
                        "&6■ &fPlace Blocks&7: &e" + colorBoolean(region.isPlaceBlocks()),
                        "&6■ &fDamage&7: &e" + colorBoolean(region.isDamage()),
                        "&6■ &fHunger&7: &e" + colorBoolean(region.isHunger()),
                        "&6■ &fDrop Items&7: &e" + colorBoolean(region.isDropItems()),
                        "&6■ &fPickup Items&7: &e" + colorBoolean(region.isPickupItems()),
                        "&6■ &fInteract&7: &e" + colorBoolean(region.isInteract()),
                        "&6■ &fLeaves Decay&7: &e" + colorBoolean(region.isLeavesDecay()),
                        "",
                        "&a&lLEFT CLICK &ato edit the region.",
                        "&c&lRIGHT CLICK &cto delete the region.",
                        "&e&lSHIFT CLICK &eto teleport to region."
                )).toItemStack();
    }

    @Override
    public boolean shouldUpdate(Player player, ClickType clickType) {
        return true;
    }

    @Override
    public void clicked(Player player, ClickType clickType) {
        switch (clickType) {
            case LEFT -> Phoenix.getInstance().getMenuHandler().openMenu(new RegionEditMenu(region), player);

            case RIGHT -> {
                SkyWarsPlugin.getInstance().getRegionHandler().removeRegion(region);
                SkyWarsPlugin.getInstance().getRegionHandler().saveRegions();

                player.sendMessage(Color.translate("&eYou have deleted the region &6" + region.getName() + "&e."));
                player.closeInventory();
            }

            case SHIFT_RIGHT -> {
                player.closeInventory();
                player.teleport(region.getBounds().getCenter());
            }
        }
    }

    private String colorBoolean(boolean bool) {
        return bool ? "&aEnabled" : "&cDisabled";
    }

}
