package gg.pixelarcade.skywars.kit;

import gg.pixelarcade.skywars.game.state.GameTeamType;
import gg.pixelarcade.skywars.util.PlayerUtil;
import lombok.Data;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import gg.pixelarcade.skywars.game.state.GameType;
import gg.pixelarcade.skywars.kit.enums.KitRarity;
import gg.pixelarcade.skywars.util.CalenderUtil;
import xyz.refinedev.phoenix.utils.item.ItemBuilder;

import java.util.List;

/**
 * @author alfie
 * @project Skywars / gg.pixelarcade.game.kit
 * @date 26/09/2023 / 02:37
 */

@Data
public abstract class Kit {

    public abstract String getName();
    public abstract int getCost();

    public abstract GameType getGameType();
//    public abstract GameTeamType getGameTeamType();
    public abstract KitRarity getRarity();

    public abstract ItemBuilder getDisplayItem();
    public abstract ItemStack[] getArmor();

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
