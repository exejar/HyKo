import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stats(
    @SerialName("Bedwars") val bedwars: Bedwars,
    @SerialName("SkyWars") val skywars: Skywars,
    @SerialName("Arena") val arenaBrawl: ArenaBrawl
)

@Serializable
data class Bedwars(
    @SerialName("final_kills_bedwars") val finalKills : Int,
    @SerialName("kills_bedwars") val kills: Int
)

@Serializable
data class Skywars(
    @SerialName("levelFormatted") val formattedLevel: String
)

@Serializable
data class ArenaBrawl(
    @SerialName("coins") val coins: Int
)

