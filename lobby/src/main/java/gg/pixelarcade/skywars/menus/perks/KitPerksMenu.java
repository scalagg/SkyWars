package gg.pixelarcade.skywars.menus.perks;

import gg.pixelarcade.skywars.SkyWarsShared;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.menus.SkyWarsMenu;
import gg.pixelarcade.skywars.menus.utils.BackButton;
import gg.pixelarcade.skywars.menus.utils.CoinsButton;
import gg.pixelarcade.skywars.menus.utils.ExitButton;
import gg.pixelarcade.skywars.perk.Perk;
import gg.pixelarcade.skywars.perk.PerkHandler;
import lombok.RequiredArgsConstructor;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.menu.api.Menu;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.HashMap;
import java.util.Map;

public class KitPerksMenu extends Menu {

    private final PerkHandler perkHandler = SkyWarsShared.getInstance().getPerkHandler();

    public KitPerksMenu(){
        this.setBordered(true);
    }

    @Override
    public String getTitle(Player player) {
        return "Kit Perks";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {
        final Map<Integer, Button> buttons = new HashMap<>();

        perkHandler.getPerks()
                .stream().filter(Perk::isKitPerk)
                .forEach(perk -> buttons.put(buttons.size() + 10, new PerkButton(perk)));

        //-- Footer
        buttons.put(this.getSlot(3, 4), new BackButton(new SkyWarsMenu()));
        buttons.put(this.getSlot(4, 4), new ExitButton());
        buttons.put(this.getSlot(5, 4), new CoinsButton());

        return buttons;
    }

    @RequiredArgsConstructor
    private static class PerkButton extends Button {
        private final Perk perk;

        @Override
        public ItemStack getItem(Player player) {
            return new ItemBuilder(perk.getDisplayMaterial())
                    .setName(ChatColor.GOLD + perk.getName())
                    .setLore(
                            "",
                            ChatColor.GRAY + perk.getDescription()
                            )
                    .toItemStack();
        }
    }
}
