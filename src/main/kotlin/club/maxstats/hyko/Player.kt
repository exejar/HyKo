package club.maxstats.hyko

import ILeveling
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.*

@Serializable
data class Player(
    @SerialName("_id") val entryId: String = "",
    @SerialName("uuid") val uuid: String = "",
    @SerialName("displayname") val displayName: String = "",
    @SerialName("rank") val rank: String = "",
    @SerialName("packageRank") val packageRank: String = "",
    @SerialName("newPackageRank") val newPackageRank: String = "",
    @SerialName("monthlyPackageRank") val monthlyPackageRank: String = "",
    @SerialName("firstLogin") val firstLogin: Long = 0,
    @SerialName("lastLogin") val lastLogin: Long = 0,
    @SerialName("lastLogout") val lastLogout: Long = 0,
    @SerialName("networkExp") val networkExp: Double = 0.0,
    @SerialName("networkLevel") val networkLevel: Double = 0.0,
    @SerialName("mcVersionRp") val mcVersion: String = "",
    @SerialName("stats") val stats: Stats,
    @SerialName("achievements") val achievements: Achievements
) {
    /**
     * Retrieves the displayed rank of this player (Player's top-most rank in the Rank Hierarchy)
     */
    val highestRank
        get() = when {
            !rank.isDefaultRank() -> rank
            !monthlyPackageRank.isDefaultRank() -> monthlyPackageRank
            !newPackageRank.isDefaultRank() -> newPackageRank
            !packageRank.isDefaultRank() -> packageRank
            else -> "NONE"
        }

    val hasRank get() = highestRank != "NONE"

    private val fullNetworkExp get() = networkExp + (ILeveling.getTotalExpToFullLevel(networkLevel) + 1)
    val fullNetworkLevel get() = ILeveling.getExactLevel(fullNetworkExp)

    private fun String.isDefaultRank() = this == "NONE" || this == "NORMAL" || isEmpty()
}

val json = Json { ignoreUnknownKeys = true }

@Serializable
data class Stats(
    @SerialName("Bedwars") val bedwars: Bedwars = Bedwars(),
    @SerialName("Duels") val duels: Duels = Duels()
)

inline fun <reified T> decode(element: JsonElement): T = json.decodeFromJsonElement(element)
fun JsonObject.getAllWithPrefix(prefix: String) = JsonObject(filter { it.key.startsWith(prefix) })