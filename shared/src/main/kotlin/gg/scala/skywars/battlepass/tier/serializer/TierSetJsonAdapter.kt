package gg.scala.skywars.battlepass.tier.serializer

import com.google.gson.*
import gg.scala.skywars.battlepass.tier.Tier
import gg.scala.skywars.battlepass.tier.TierHandler
import java.lang.reflect.Type

/**
 * @date 12/10/2024
 * @author Moose1301
 */
object TierSetJsonAdapter : JsonDeserializer<MutableSet<Tier>>, JsonSerializer<MutableSet<Tier>> {
    override fun deserialize(element: JsonElement, type: Type?, ctx: JsonDeserializationContext?): MutableSet<Tier> {
        return element.asJsonArray.mapNotNull{ TierHandler.getTierByNumber(it.asInt)}.toMutableSet()
    }

    override fun serialize(data: MutableSet<Tier>, type: Type?, ctx: JsonSerializationContext?): JsonElement {
        val array = JsonArray()

        for (tier in data)
            array.add(tier.number)

        return array;
    }
}