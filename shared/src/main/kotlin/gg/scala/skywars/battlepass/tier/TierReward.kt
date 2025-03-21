package gg.scala.skywars.battlepass.tier

import org.bukkit.Bukkit
import org.bukkit.entity.Player

class TierReward() {

    @Transient lateinit var tier: Tier

    constructor(tier: Tier):this() {
        this.tier = tier
    }

    var text = mutableListOf<String>()
    var commands = mutableListOf<String>()

    fun addText(text: String) {
        this.text.add(text)
    }

    fun removeText(text: String) {
        this.text.remove(text)
    }

    fun addCommand(command: String) {
        this.commands.add(command)
    }

    fun removeCommand(command: String) {
        this.commands.remove(command)
    }

    @JvmName("getTextLines1")
    fun getTextLines(): List<String> {
        return this.text.toList()
    }

    @JvmName("getCommands1")
    fun getCommands(): List<String> {
        return this.commands.toList()
    }

    fun isFreeReward(): Boolean {
        return this.tier.freeReward == this
    }

    fun execute(player: Player) {
        this.commands.forEach{command -> Bukkit.dispatchCommand(Bukkit.getConsoleSender(),command
            .replace("{tier}",this.tier.number.toString())
            .replace("{playerId}",player.uniqueId.toString())
            .replace("{playerName}",player.name)
        )}
    }

}