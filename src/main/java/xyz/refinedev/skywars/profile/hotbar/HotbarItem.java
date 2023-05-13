package xyz.refinedev.skywars.profile.hotbar;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import java.util.function.Consumer;

@Getter
@RequiredArgsConstructor
public class HotbarItem {

    private final int slot;
    private final ItemStack item;
    private final Consumer<Player> onClick;
}