package player

import kotlinx.serialization.*
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.json.*

@OptIn(ExperimentalSerializationApi::class)
@Serializer(forClass = Bedwars::class)
object BedwarsSerializer : KSerializer<Bedwars> {
    override fun deserialize(decoder: Decoder): Bedwars {
        val decoder = decoder as? JsonDecoder
            ?: throw SerializationException("Expected JsonDecoder for ${decoder::class}")

        val bedwars = decoder.decodeJsonElement() as JsonObject
        return Bedwars(
            decode<OverallBedwars>(bedwars),
            decode<FourFourBedwars>(bedwars.getAllWithPrefix("four_four")),
            decode<FourThreeBedwars>(bedwars.getAllWithPrefix("four_three")),
            decode<EightTwoBedwars>(bedwars.getAllWithPrefix("eight_two")),
            decode<EightOneBedwars>(bedwars.getAllWithPrefix("eight_one")),
            decode<CastleBedwars>(bedwars.getAllWithPrefix("castle"))
        )
    }
}

@Serializable(with = BedwarsSerializer::class)
data class Bedwars(
    val overall: OverallBedwars,
    val fourFour: FourFourBedwars,
    val fourThree: FourThreeBedwars,
    val eightTwo: EightTwoBedwars,
    val eightOne: EightOneBedwars,
    val castle: CastleBedwars
)

@Serializable
data class OverallBedwars (
    @SerialName("final_kills_bedwars") val finalKills: Int,
    @SerialName("wins_bedwars") val wins: Int
)

@Serializable
@SerialName("four_four")
data class FourFourBedwars(
    @SerialName("four_four_final_kills_bedwars") val finalKills: Int = 0,
    @SerialName("four_four_wins_bedwars") val wins: Int = 0
)

@Serializable
@SerialName("four_three")
data class FourThreeBedwars(
    @SerialName("four_three_final_kills_bedwars") val finalKills: Int = 0,
    @SerialName("four_three_wins_bedwars") val wins: Int = 0
)

@Serializable
@SerialName("eight_two")
data class EightTwoBedwars(
    @SerialName("eight_two_final_kills_bedwars") val finalKills: Int = 0,
    @SerialName("eight_two_wins_bedwars") val wins: Int = 0
)

@Serializable
@SerialName("eight_one")
data class EightOneBedwars(
    @SerialName("eight_one_final_kills_bedwars") val finalKills: Int = 0,
    @SerialName("eight_one_wins_bedwars") val wins: Int = 0
)

@Serializable
@SerialName("castle")
data class CastleBedwars(
    @SerialName("castle_final_kills_bedwars") val finalKills: Int = 0,
    @SerialName("castle_wins_bedwars") val wins: Int = 0
)