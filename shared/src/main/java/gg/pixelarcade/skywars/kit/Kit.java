package gg.pixelarcade.skywars.kit;

import gg.pixelarcade.skywars.util.PlayerUtil;
import lombok.Data;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.util.CalenderUtil;

import java.util.List;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.kit
 * @date 26/09/2023 / 02:37
 */
@Data
public abstract class Kit {

    private final String name;
    private final Material displayMaterial;
    private final int cost;
    private final GameType gameType;
    private final KitRarity rarity;

    public abstract ItemStack[] getArmor();

    // TODO: This method should be to set the items in a map or something.
    // So the player can edit the kit.
    // For equipping the kit, that should be in the game module.
    public abstract void apply(Player player);

    public List<PotionEffect> getPotionEffects() {
        return null;
    }

    public void equipKit(Kit kit, Player player) {
        PlayerUtil.resetPlayer(player);

        apply(player);
        player.getInventory().setArmorContents(kit.getArmor());
    }

    public boolean isDefault() {
        return false;
    }

    public boolean isFree() {
        return getCost() == 0
                || CalenderUtil.isWeekend();
    }

}
