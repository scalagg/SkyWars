package gg.scala.skywars.battlepass.challenge.serialize

import com.google.gson.*
import gg.scala.skywars.battlepass.challenge.Challenge
import gg.scala.skywars.battlepass.challenge.ChallengeHandler
import java.lang.reflect.Type

/**
 * @date 12/10/2024
 * @author Moose1301
 */
object ChallengeSetJsonAdapter : JsonDeserializer<MutableSet<Challenge>>, JsonSerializer<MutableSet<Challenge>> {
    override fun deserialize(
        element: JsonElement,
        type: Type?,
        ctx: JsonDeserializationContext?
    ): MutableSet<Challenge> {
        return element.asJsonArray.mapNotNull { ChallengeHandler.getChallengeById(it.asString) }.toMutableSet()
    }

    override fun serialize(data: MutableSet<Challenge>, type: Type?, ctx: JsonSerializationContext?): JsonElement {
        val array = JsonArray()

        for (challenge in data)
            array.add(challenge.id)

        return array;
    }
}