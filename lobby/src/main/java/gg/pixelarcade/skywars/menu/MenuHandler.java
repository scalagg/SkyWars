package gg.pixelarcade.skywars.menu;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import xyz.refinedev.phoenix.menu.api.Button;
import xyz.refinedev.phoenix.menu.api.Menu;
import xyz.refinedev.phoenix.menu.impl.admin.history.staff.uuid.StaffUUIDCacheRefreshTask;
import xyz.refinedev.phoenix.utils.Color;
import xyz.refinedev.phoenix.utils.general.Tasks;
import gg.pixelarcade.skywars.SkyWarsLobbyPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

/**
 * @Author Disunion
 * Name: MenuHandler
 * Project: SkywarsLobby
 * Created At: 3/02/2024
 */

@Getter
@SuppressWarnings("unused")
public class MenuHandler {

    private final Map<UUID, Menu> openedMenus = new ConcurrentHashMap<>();

    private final SkyWarsLobbyPlugin plugin;

    /**
     * Create an instance of MenuHandler with support for configuration
     *
     * @param plugin {@link SkyWarsLobbyPlugin} plugin
     */
    public MenuHandler(SkyWarsLobbyPlugin plugin){
        this.plugin = plugin;
        StaffUUIDCacheRefreshTask.status = System.currentTimeMillis();
    }

    /**
     * Get the menu that is opened to the player by
     * their UniqueId
     *
     * @param player {@link Player}
     * @return {@link Menu} queried menu
     */
    public Menu getByPlayer(Player player) {
        return this.openedMenus.get(player.getUniqueId());
    }

    public void openMenuAsync(Menu menu, Player player) {
        CompletableFuture.supplyAsync(() -> {
            menu.setButtons(menu.getButtons(player));

            int oldSize = menu.getSize() == -1 ? menu.size(menu.getButtons()) : menu.getSize();

            if (menu.isBordered()) {
                addBorderButtons(menu, oldSize);
            }

            Map<Integer, ItemStack> items = new HashMap<>();

            for (Map.Entry<Integer, Button> buttonEntry : menu.getButtons().entrySet()) {
                items.put(buttonEntry.getKey(), buttonEntry.getValue().getItem(player));
            }

            return items;
        }).whenCompleteAsync((items, throwable) -> {
            if (throwable != null) {
                Bukkit.getLogger().log(Level.SEVERE, "There was an error while opening this menu: ", throwable);
                return;
            }

            Tasks.run(() -> {
                menu.setFullyAsync(true);

                String title = Color.translate(menu.getTitle(player));

                Inventory inventory = null;

                int size = menu.getSize() == -1 ? menu.size(menu.getButtons()) : menu.getSize();
                boolean update = false;

                if (title.length() > 32) {
                    title = title.substring(0, 32);
                }

                Menu previousMenu = openedMenus.get(player.getUniqueId());

                if (previousMenu != null && player.getOpenInventory() != null) {
                    int previousSize = player.getOpenInventory().getTopInventory().getSize();

                    if (previousSize == size && title.equals(Color.translate(player.getOpenInventory().getTitle()))) {
                        inventory = player.getOpenInventory().getTopInventory();
                        update = true;
                    } else {
                        previousMenu.setClosedByMenu(true);
                        previousMenu.onClose(player);

                        openedMenus.remove(player.getUniqueId());
                    }
                }

                if (inventory == null) {
                    inventory = Bukkit.createInventory(player, size, title);
                }

                if (update) {
                    try {
                        Inventory temporaryInventory = Bukkit.createInventory(player, inventory.getSize(), title);

                        for (Map.Entry<Integer, ItemStack> buttonEntry : items.entrySet()) {
                            temporaryInventory.setItem(buttonEntry.getKey(), buttonEntry.getValue());
                        }

                        inventory.setContents(temporaryInventory.getContents());
                        player.updateInventory();

                        openedMenus.put(player.getUniqueId(), menu);
                        menu.onOpen(player);
                    } catch (Exception exception) {
                        Bukkit.getLogger().log(Level.SEVERE, "There was an error while opening this menu: ", exception);
                    }
                } else {
                    try {
                        for (Map.Entry<Integer, ItemStack> buttonEntry : items.entrySet()) {
                            inventory.setItem(buttonEntry.getKey(), buttonEntry.getValue());
                        }

                        player.openInventory(inventory);

                        openedMenus.put(player.getUniqueId(), menu);
                        menu.onOpen(player);
                    } catch (Exception exception) {
                        Bukkit.getLogger().log(Level.SEVERE, "There was an error while opening this menu: ", exception);
                    }
                }
            });
        });
    }

    public void openMenuSync(Menu menu, Player player) {
        Tasks.run(() -> {
            menu.setButtons(menu.getButtons(player));

            String title = Color.translate(menu.getTitle(player));

            int oldSize = menu.getSize() == -1 ? menu.size(menu.getButtons()) : menu.getSize();

            if (menu.isBordered()) {
                addBorderButtons(menu, oldSize);
            }

            Inventory inventory = null;

            int size = menu.getSize() == -1 ? menu.size(menu.getButtons()) : menu.getSize();
            boolean update = false;

            if (title.length() > 32) {
                title = title.substring(0, 32);
            }

            Menu previousMenu = openedMenus.get(player.getUniqueId());

            if (previousMenu != null && player.getOpenInventory() != null) {
                int previousSize = player.getOpenInventory().getTopInventory().getSize();

                if (previousSize == size && title.equals(Color.translate(player.getOpenInventory().getTitle()))) {
                    inventory = player.getOpenInventory().getTopInventory();
                    update = true;
                } else {
                    previousMenu.setClosedByMenu(true);
                    previousMenu.onClose(player);

                    openedMenus.remove(player.getUniqueId());
                }
            }

            if (inventory == null) {
                inventory = Bukkit.createInventory(player, size, title);
            }

            Inventory finalInventory = inventory;

            if (update) {
                Inventory temporaryInventory = Bukkit.createInventory(player, inventory.getSize(), title);

                for (Map.Entry<Integer, Button> buttonEntry : menu.getButtons().entrySet()) {
                    temporaryInventory.setItem(buttonEntry.getKey(), buttonEntry.getValue().getItem(player));
                }

                try {
                    finalInventory.setContents(temporaryInventory.getContents());
                    player.updateInventory();

                    openedMenus.put(player.getUniqueId(), menu);
                    menu.onOpen(player);
                } catch (Exception exception) {
                    Bukkit.getLogger().log(Level.SEVERE, "There was an error while opening this menu: ", exception);
                }
            } else {
                for (Map.Entry<Integer, Button> buttonEntry : menu.getButtons().entrySet()) {
                    inventory.setItem(buttonEntry.getKey(), buttonEntry.getValue().getItem(player));
                }

                try {
                    player.openInventory(finalInventory);

                    openedMenus.put(player.getUniqueId(), menu);
                    menu.onOpen(player);
                } catch (Exception exception) {
                    Bukkit.getLogger().log(Level.SEVERE, "There was an error while opening this menu: ", exception);
                }
            }
        });
    }

    /**
     * Open the menu for the Player
     *
     * @param menu   The Menu player is supposed to get displayed
     * @param player Player viewing the menu
     */
    public void openMenu(Menu menu, Player player) {
        menu.setButtons(menu.getButtons(player));

        String title = Color.translate(menu.getTitle(player));

        int oldSize = menu.getSize() == -1 ? menu.size(menu.getButtons()) : menu.getSize();

        if (menu.isBordered()) {
            addBorderButtons(menu, oldSize);
        }

        Inventory inventory = null;

        int size = menu.getSize() == -1 ? menu.size(menu.getButtons()) : menu.getSize();
        boolean update = false;

        if (title.length() > 32) {
            title = title.substring(0, 32);
        }

        Menu previousMenu = openedMenus.get(player.getUniqueId());

        if (previousMenu != null && player.getOpenInventory() != null) {
            int previousSize = player.getOpenInventory().getTopInventory().getSize();

            if (previousSize == size && title.equals(Color.translate(player.getOpenInventory().getTitle()))) {
                inventory = player.getOpenInventory().getTopInventory();
                update = true;
            } else {
                previousMenu.setClosedByMenu(true);
                previousMenu.onClose(player);

                openedMenus.remove(player.getUniqueId());
            }
        }

        if (inventory == null) {
            inventory = Bukkit.createInventory(player, size, title);
        }

        if (update) {
            Inventory temporaryInventory = Bukkit.createInventory(player, inventory.getSize(), title);
            for (Map.Entry<Integer, Button> buttonEntry : menu.getButtons().entrySet()) {
                temporaryInventory.setItem(buttonEntry.getKey(), buttonEntry.getValue().getItem(player));
            }
            try {
                inventory.setContents(temporaryInventory.getContents());
                player.updateInventory();
            } catch (Exception ignored) {
            }
        } else {
            for (Map.Entry<Integer, Button> buttonEntry : menu.getButtons().entrySet()) {
                inventory.setItem(buttonEntry.getKey(), buttonEntry.getValue().getItem(player));
            }
            try {
                player.openInventory(inventory);
            } catch (Exception ignored) {
            }
        }

        openedMenus.put(player.getUniqueId(), menu);
        menu.onOpen(player);
    }

    public void addBorderButtons(Menu menu, int size) {
        int number = size >= 36 ? 36 : size >= 27 ? 27 : 18;

        for (int i = 0; i < 9; i++) {
            menu.getButtons().computeIfAbsent(i, k -> Button.placeholder());
        }

        menu.getButtons().put(9, Button.placeholder());
        menu.getButtons().put(17, Button.placeholder());

        if (number >= 27) {
            menu.getButtons().put(18, Button.placeholder());
            menu.getButtons().put(26, Button.placeholder());
        }

        if (number == 36) {
            menu.getButtons().put(27, Button.placeholder());
            menu.getButtons().put(35, Button.placeholder());
        }

        for (int i = number; i <= number + 8; i++) {
            menu.getButtons().computeIfAbsent(i, k -> Button.placeholder());
        }
    }
}