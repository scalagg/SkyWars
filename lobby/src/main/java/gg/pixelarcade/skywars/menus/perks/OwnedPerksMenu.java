package gg.pixelarcade.skywars.menus.perks;

import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.menus.SkyWarsMenu;
import gg.pixelarcade.skywars.menus.utils.BackButton;
import gg.pixelarcade.skywars.menus.utils.CoinsButton;
import gg.pixelarcade.skywars.menus.utils.ExitButton;
import gg.pixelarcade.skywars.perk.Perk;
import gg.pixelarcade.skywars.perk.PerkHandler;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.menu.api.Menu;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OwnedPerksMenu extends Menu {

    private final PerkHandler perkHandler = SkyWarsShared.getInstance().getPerkHandler();

    public OwnedPerksMenu(){
        this.setBordered(true);
    }

    @Override
    public String getTitle(Player player) {
        return "Owned Perks";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        final Map<Integer, Button> buttons = new HashMap<>();

        perkHandler.getPerks()
                .stream().filter(perk -> !perk.isGlobalPerk() && !perk.isKitPerk())
                .forEach(perk -> buttons.put(buttons.size() + 10, new OwnedPerksMenu.PerkButton(perk)));

        //-- Footer
        buttons.put(this.getSlot(3, 4), new BackButton(new PerkListMenu()));
        buttons.put(this.getSlot(4, 4), new ExitButton());
        buttons.put(this.getSlot(5, 4), new CoinsButton());

        return buttons;
    }

    @RequiredArgsConstructor
    private static class PerkButton extends Button {

        private final Perk perk;

        @Override
        public ItemStack getItem(Player player) {
            List<String> ownedPerks = SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId()).getOwnedPerks();

            //-- If the player doesn't own any perks, or if they already have it selected, return nothing
            if (ownedPerks == null){
                return null;
            }

            if (!ownedPerks.contains(perk.getName())) {
                return null;
            } else {

                if (SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId()).getSelectedPerks() == null){
                    return new ItemBuilder(perk.getDisplayMaterial())
                            .setName(ChatColor.RED + perk.getName())
                            .setLore(
                                    ChatColor.GRAY + perk.getDescription(),
                                    "",
                                    ChatColor.YELLOW + "Click to Select!"
                            )
                            .toItemStack();
                } else {
                    if (SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId()).getSelectedPerks().contains(perk.getName())) {

                        return new ItemBuilder(perk.getDisplayMaterial())
                                .setName(ChatColor.GREEN + perk.getName())
                                .setLore(
                                        ChatColor.GRAY + perk.getDescription(),
                                        "",
                                        ChatColor.GREEN + "This perk is already active."
                                )
                                .toItemStack();
                    } else {
                        return new ItemBuilder(perk.getDisplayMaterial())
                                .setName(ChatColor.RED + perk.getName())
                                .setLore(
                                        ChatColor.GRAY + perk.getDescription(),
                                        "",
                                        ChatColor.YELLOW + "Click to Select!"
                                )
                                .toItemStack();
                    }
                }
            }
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            List<String> selectedPerks = SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId()).getSelectedPerks();

            if (selectedPerks == null){
                List<String> newSelectedPerks = Collections.singletonList(perk.getName());
                SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId()).setSelectedPerks(newSelectedPerks);
            } else if (!selectedPerks.contains(perk.getName())){
                SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId()).addSelectedPerk(perk);
            } else {
                player.sendMessage(ChatColor.RED + "This perk is already active!");
            }

            Phoenix.getInstance().getMenuHandler().openMenu(new PerkListMenu(), player);
        }
    }
}
