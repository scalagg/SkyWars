package gg.scala.skywars.game.task

import gg.scala.skywars.SkyWarsGame
import net.evilblock.cubed.util.nms.particle.ParticleMeta
import net.evilblock.cubed.util.nms.particle.ParticleUtil
import net.minecraft.server.v1_8_R3.EnumParticle
import org.bukkit.Color
import org.bukkit.Material
import org.bukkit.metadata.FixedMetadataValue

/**
 * @date 11/12/2024
 * @author Moose1301
 */
class GameTntGeneratorTask : Runnable {
    override fun run() {
        val gameHandler = SkyWarsGame.gameInstance.gameHandler;
        if(!gameHandler.tntRespawning)
            return

        val location = gameHandler.map.tntGeneratorSpawnLoc!!.clone().add(0.0, 1.0, 0.0);

        ParticleUtil.sendsParticleToAll(location.world, ParticleMeta(
            location, EnumParticle.REDSTONE, Color.YELLOW,0.1f, 5
        ))
        if(System.currentTimeMillis() < gameHandler.tntRespawnsAt)
            return

        gameHandler.tntRespawning = false;
        val item = location.world.dropItem(
            location,
            SkyWarsGame.gameInstance.abilityHandler.getAbilityById("throwabletnt")?.getItem()
        )

        item.setMetadata("TNT_GEN_ITEM", FixedMetadataValue(SkyWarsGame.gameInstance.plugin, true))
        gameHandler.map.tntGeneratorSpawnLoc!!.block.type = Material.GOLD_BLOCK
    }
}