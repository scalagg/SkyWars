package gg.pixelarcade.skywars.battlepass.menu.button



import gg.pixelarcade.skywars.battlepass.BattlePass
import gg.pixelarcade.skywars.battlepass.BattlePassHandler
import gg.pixelarcade.skywars.battlepass.tier.Tier
import gg.pixelarcade.skywars.battlepass.tier.TierReward
import net.evilblock.cubed.menu.Button
import net.evilblock.cubed.util.bukkit.Constants
import net.evilblock.cubed.util.bukkit.ItemBuilder
import net.evilblock.cubed.util.text.TextSplitter
import org.bukkit.ChatColor
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.inventory.ClickType
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.inventory.InventoryView
import org.bukkit.inventory.ItemStack

class RewardButton(
    private val tier: Tier,
    private val reward: TierReward,
    private val battlePass: BattlePass
) : Button() {

    override fun getButtonItem(player: Player): ItemStack {

        val type = if (this.battlePass.isTierUnlocked(tier)) {
            if (this.battlePass.hasClaimedReward(tier, reward)) {
                Material.MINECART
            } else {
                Material.STORAGE_MINECART
            }
        } else {
            Material.HOPPER_MINECART
        }

        val name = if (this.battlePass.isTierUnlocked(tier)) {

            if (this.battlePass.hasClaimedReward(tier, reward)) {
                "${ChatColor.GREEN}${ChatColor.BOLD}Claimed Reward"
            } else {

                if (!this.reward.isFreeReward() && !this.battlePass.isPremium()) {
                    "${ChatColor.RED}${ChatColor.BOLD}Reward Locked"
                } else {
                    "${ChatColor.YELLOW}${ChatColor.BOLD}Claim Reward"
                }

            }

        } else {
            "${ChatColor.RED}${ChatColor.BOLD}Reward Locked"
        }

        val lore = arrayListOf<String>()

        for (text in this.reward.getTextLines()) {
            lore.add("${ChatColor.GRAY}${Constants.DOT_SYMBOL} $text")
        }

        if (!this.reward.isFreeReward() && !this.battlePass.isPremium()) {
            lore.add("")
            lore.addAll(TextSplitter.split(text = BattlePassHandler.NOT_PREMIUM,linePrefix = ChatColor.RED.toString()))
        }

        return ItemBuilder.of(type)
            .name(name)
            .setLore(lore)
            .build()
    }

    override fun clicked(player: Player, slot: Int, clickType: ClickType, view: InventoryView) {
        if (!this.battlePass.isTierUnlocked(this.tier) || this.battlePass.hasClaimedReward(this.tier,this.reward)) {
            return
        }

        if (!this.reward.isFreeReward() && !this.battlePass.isPremium()) {
            return
        }

        this.battlePass.claimReward(player,this.tier,this.reward)
        this.reward.execute(player)
    }


}