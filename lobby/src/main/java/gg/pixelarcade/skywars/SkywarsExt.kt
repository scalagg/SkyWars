package gg.pixelarcade.skywars

import com.google.gson.JsonParser
import net.evilblock.cubed.entity.npc.NpcEntity
import net.evilblock.cubed.entity.npc.texture.NpcTexture
import java.net.URL
import java.util.*

/**
 * @date 11/8/2024
 * @author Moose1301
 */
infix fun NpcEntity.updateSkin(uuid: UUID) {
    fetchTextureByUuid(uuid).ifPresent {
        updateTexture(it.textureValue, it.textureSignature)
    }
}



fun fetchTextureByUuid(uuid: UUID): Optional<NpcTexture> {

    return try {
        val response = runCatching {
            URL("https://api.mineskin.org/generate/user/$uuid").readText()
        }.getOrNull()

        if (response != null) {
            val json = JsonParser().parse(response).asJsonObject
            val data = json.get("data").asJsonObject
            val texture = data.get("texture").asJsonObject

            Optional.of(NpcTexture(texture.get("value").asString, texture.get("signature").asString))
        } else {
            Optional.empty()
        }
    } catch (e: Exception) {
        throw IllegalStateException("Failed to fetch player texture from API", e)
    }
}