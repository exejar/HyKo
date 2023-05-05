import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stats(
    @SerialName("Bedwars") val bedwars: Bedwars,
    @SerialName("SkyWars") val skywars: Skywars,
    @SerialName("Arena") val arenaBrawl: ArenaBrawl,
    @SerialName("UHC") val uhc: UHC,

)

@Serializable
data class Bedwars(
    @SerialName("winstreak") val winstreak: Int = 0,
    @SerialName("wins_bedwars") val wins: Int = 0,
    @SerialName("losses_bedwars") val losses: Int = 0,
    @SerialName("experience") val experience: Int = 0,
    @SerialName("final_kills_bedwars") val finalKills : Int = 0,
    @SerialName("final_deaths_bedwars") val finalDeaths: Int = 0,
    @SerialName("void_final_kills_bedwars") val voidFinalKills: Int = 0,
    @SerialName("void_final_deaths_bedwars") val voidFinalDeaths: Int = 0,
    @SerialName("kills_bedwars") val kills: Int = 0,
    @SerialName("deaths_bedwars") val deaths: Int = 0,
    @SerialName("beds_lost_bedwars") val bedsLost: Int = 0,
    @SerialName("beds_broken_bedwars") val bedsBroken: Int = 0,
    @SerialName("void_kills_bedwars") val voidKills: Int = 0,
    @SerialName("void_deaths_bedwars") val voidDeaths: Int = 0,
    @SerialName("coins") val coins: Int = 0,

)

@Serializable
data class UHC(
    @SerialName("kills") val kills: Int = 0,
    @SerialName("deaths") val deaths: Int = 0,
    @SerialName("coins") val coins: Int = 0,
    @SerialName("equippedKit") val equippedKit: String = "",
    @SerialName("heads_eaten") val headsEaten: Int = 0,
)

@Serializable
data class Skywars(
    @SerialName("levelFormatted") val formattedLevel: String = ""
)

@Serializable
data class ArenaBrawl(
    @SerialName("coins") val coins: Int = 0
)

