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

public class PerkListMenu extends Menu {

    private final PerkHandler perkHandler = SkyWarsShared.getInstance().getPerkHandler();

    public PerkListMenu(){
        this.setBordered(true);
    }

    @Override
    public String getTitle(Player player) {
        return "Perks";
    }

    @Override
    public Map<Integer, Button> getButtons(Player player) {

        final Map<Integer, Button> buttons = new HashMap<>();

        //-- Personal Perks
        buttons.put(this.getSlot(1, 1), new PersonalPerkDescription());
        for (int i = 1; i <= 6; i++){
            buttons.put(this.getSlot(i+1, 1), new PersonalPerksButton(i));
        }

        //-- Global Perks
        buttons.put(this.getSlot(1, 3), new GlobalPerkDescription());
        buttons.put(this.getSlot(2, 3), new GlobalPerksButton(perkHandler.getPerkByName("Bulldozer Perk")));
        buttons.put(this.getSlot(3, 3), new GlobalPerksButton(perkHandler.getPerkByName("Juggernaut Perk")));
        buttons.put(this.getSlot(6, 3), new BuyPerkMenu());
        buttons.put(this.getSlot(7, 3), new KitPerkMenu());

        //-- Footer
        buttons.put(this.getSlot(3, 4), new BackButton(new SkyWarsMenu()));
        buttons.put(this.getSlot(4, 4), new ExitButton());
        buttons.put(this.getSlot(5, 4), new CoinsButton());


        return buttons;
    }

    @RequiredArgsConstructor
    private static class PersonalPerksButton extends Button{

        private final int perkNum;

        @Override
        public ItemStack getItem(Player player){

            // variables
            List<String> playerPerks = SkyWarsShared.getInstance().getProfileHandler().getProfile(player.getUniqueId()).getSelectedPerks();

            if (playerPerks == null){
                return new ItemBuilder(XMaterial.OAK_BUTTON)
                        .setName(ChatColor.GREEN + "Perk Slot #" + perkNum + ChatColor.RED + " - None selected")
                        .setLore(
                                "",
                                ChatColor.YELLOW + "Left-Click to replace"
                        )
                        .toItemStack();
            } else if (playerPerks.size() >= perkNum) {

                Perk perk = SkyWarsShared.getInstance().getPerkHandler().getPerkByName(playerPerks.get(perkNum -1));
                return new ItemBuilder(perk.getDisplayMaterial())
                        .setName(ChatColor.GREEN + "Perk Slot #" + perkNum + ChatColor.YELLOW + " - " + perk.getName())
                        .setLore(
                                ChatColor.GRAY + perk.getDescription(),
                                "",
                                ChatColor.YELLOW + "Left-Click to replace"
                        )
                        .toItemStack();

            } else {
                return new ItemBuilder(XMaterial.OAK_BUTTON)
                        .setName(ChatColor.GREEN + "Perk Slot #" + perkNum + ChatColor.RED + " - None selected")
                        .setLore(
                                "",
                                ChatColor.YELLOW + "Left-Click to replace"
                        )
                        .toItemStack();
            }
        }

        @Override
        public void clicked(Player player, ClickType clickType){
            if (clickType.isLeftClick()){
                Phoenix.getInstance().getMenuHandler().openMenu(new OwnedPerksMenu(), player);
            }
        }
    }

    @RequiredArgsConstructor
    private static class GlobalPerksButton extends Button {

        private final Perk perk;

        @Override
        public ItemStack getItem(Player player) {
            return new ItemBuilder(perk.getDisplayMaterial())
                    .setName(ChatColor.GOLD + perk.getName())
                    .setLore(ChatColor.GRAY + perk.getDescription())
                    .toItemStack();
        }
    }

    public static class BuyPerkMenu extends Button {

        @Override
        public ItemStack getItem(Player player) {
            return new ItemBuilder(XMaterial.DIAMOND)
                    .setName(ChatColor.GREEN + "Buy Perks")
                    .setLore(
                            ChatColor.GRAY + "Click here to Purchase new Perks!"
                    )
                    .toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType) {
            Phoenix.getInstance().getMenuHandler().openMenu(new PurchasePerkMenu(), player);
        }
    }

    public static class KitPerkMenu extends Button {

        @Override
        public ItemStack getItem(Player player) {
            return new ItemBuilder(XMaterial.CLOCK)
                    .setName(ChatColor.GREEN + "Kit Perks")
                    .setLore(
                            ChatColor.GRAY + "These Perks are are active for every",
                            ChatColor.GRAY + "player, depending on the kit they have",
                            ChatColor.GRAY + "Selected",
                            "",
                            ChatColor.YELLOW + "Left-Click to view"
                    )
                    .toItemStack();
        }

        @Override
        public void clicked(Player player, ClickType clickType){
            if (clickType.isLeftClick()){
                Phoenix.getInstance().getMenuHandler().openMenu(new KitPerksMenu( ), player);
            }
        }

    }

    public static class PersonalPerkDescription extends Button {

        @Override
        public ItemStack getItem(Player player) {
            return new ItemBuilder(XMaterial.REDSTONE_TORCH)
                    .setName(ChatColor.GREEN + "Perk Slots")
                    .setLore(
                            ChatColor.GRAY + "You can have up to " + ChatColor.GREEN + "6 " + ChatColor.GRAY + "Perks active",
                            ChatColor.GRAY + "at any time.",
                            ChatColor.GRAY + "The slots to " + ChatColor.RED + "the right " + ChatColor.GRAY + "of this icon",
                            ChatColor.GRAY + "indicate your currently active perks.",
                            ChatColor.YELLOW + "Right-click " + ChatColor.GRAY + "on a perk to disable it,",
                            ChatColor.GRAY + "and make room to activate another",
                            ChatColor.GRAY + "perk.",

                            ChatColor.YELLOW + "Left-click " + ChatColor.GRAY + "on a perk to replace it",
                            ChatColor.GRAY + "directly with another perk.",
                            ChatColor.GRAY + "Click on any of your " + ChatColor.GREEN + "purchased",
                            ChatColor.GREEN + "perks " + ChatColor.GRAY + "to enable them if you have an",
                            ChatColor.GRAY + "available slot"
                    )
                    .toItemStack();
        }
    }

    public static class GlobalPerkDescription extends Button {

        @Override
        public ItemStack getItem(Player player) {
            return new ItemBuilder(XMaterial.REDSTONE_TORCH)
                    .setName(ChatColor.GREEN + "Global Perks")
                    .setLore(
                            ChatColor.GRAY + "Global perks are active for every",
                            ChatColor.GRAY + "player, on top of the 6 customizable",
                            ChatColor.GRAY + "perk slots."
                    )
                    .toItemStack();
        }
    }

}
