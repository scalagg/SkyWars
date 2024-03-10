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
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.Phoenix;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.menu.api.Menu;
import xyz.refinedev.phoenix.utils.XMaterial;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Disunion
 * @Date: 03/09/2024
 * @Description: GUI that allows a player to purchase a perk
 * @Notes: once we add a certain amount of perks, this needs to become a Paginated Menu
 */

public class PurchasePerkMenu extends Menu {

    //-- Instance Variables
    private final PerkHandler perkHandler = SkyWarsShared.getInstance().getPerkHandler();

    //-- Constructor
    public PurchasePerkMenu(){
        this.setBordered(true);
    }

    //-- Return title for gui
    @Override
    public String getTitle(Player player) {
        return "Perk Store";
    }

    //-- Returns a map of "buttons" or display items with functionality
    @Override
    public Map<Integer, Button> getButtons(Player player) {
        final Map<Integer, Button> buttons = new HashMap<>();

        perkHandler.getPerks()
                .stream().filter(perk -> !perk.isGlobalPerk() && !perk.isKitPerk())
                .forEach(perk -> buttons.put(buttons.size() + 10, new PerksButton(perk)));

        //-- Footer
        buttons.put(this.getSlot(3, 4), new BackButton(new PerkListMenu()));
        buttons.put(this.getSlot(4, 4), new ExitButton());
        buttons.put(this.getSlot(5, 4), new CoinsButton());

        return buttons;
    }

    @RequiredArgsConstructor
    public static class PerksButton extends Button {
        private final Perk perk;

        @Override
        public ItemStack getItem(Player player){

            if (alreadyBought(player)){
                return new ItemBuilder(perk.getDisplayMaterial())
                        .setName(ChatColor.GREEN + perk.getName())
                        .setLore(
                                ChatColor.GRAY + perk.getDescription(),
                                "",
                                ChatColor.GRAY + "Rarity: " + (perk.getRarity().getColor() + perk.getRarity().getDisplayName()),
                                "",
                                ChatColor.GREEN + "Already purchased!"
                        )
                        .toItemStack();
            } else {
                return new ItemBuilder(perk.getDisplayMaterial())
                        .setName(ChatColor.RED + perk.getName())
                        .setLore(
                                ChatColor.GRAY + perk.getDescription(),
                                "",
                                ChatColor.GRAY + "Rarity: " + (perk.getRarity().getColor() + perk.getRarity().getDisplayName()),
                                "",
                                ChatColor.GRAY + "Cost: " + ChatColor.GOLD + perk.getCost()
                        )
                        .toItemStack();
            }

        }

        @Override
        public void clicked(Player player, ClickType clickType){
            if (alreadyBought(player)){
                player.closeInventory();
                player.sendMessage(ChatColor.RED + "You have already purchased that perk!");
            }
            else if (SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId()).getCoins() >= perk.getCost()){
                Phoenix.getInstance().getMenuHandler().openMenu(new ConfirmationMenu(perk), player);
            } else {
                player.closeInventory();
                player.sendMessage(ChatColor.RED + "Sorry, but you can't afford that!");
            }
        }

        private boolean alreadyBought(Player player){
            List<String> ownedPerks = SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId()).getOwnedPerks();
            return ownedPerks.contains(perk.getName());
        }
    }
}

