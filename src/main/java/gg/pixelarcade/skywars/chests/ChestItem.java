package gg.pixelarcade.skywars.chests;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.bukkit.inventory.ItemStack;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.skywars.chests
 * @date 06/10/2023 / 02:21
 */

public record ChestItem(ItemStack item, double chance) {}
