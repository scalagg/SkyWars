package xyz.refinedev.skywars.kit;

import lombok.Data;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import sun.util.calendar.CalendarUtils;
import xyz.refinedev.skywars.game.state.GameType;
import xyz.refinedev.skywars.kit.enums.Rarity;
import xyz.refinedev.skywars.utils.CalenderUtil;

import java.util.Calendar;
import java.util.List;

/**
 * @author alfie
 * @project Skywars / xyz.refinedev.skywars.kit
 * @date 26/09/2023 / 02:37
 */

@Data
public abstract class Kit {

    public abstract String getName();
    public abstract int getCost();

    public abstract List<GameType> getGameType();
    public abstract Rarity getRarity();

    public abstract ItemStack getDisplayItem();
    public abstract ItemStack[] getArmor();

    public abstract void apply(Player player);

    public List<PotionEffect> getPotionEffects() {
        return null;
    }

    public void equipKit(Kit kit, Player player) {
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);

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
