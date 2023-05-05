package player

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
    @SerialName("stats") val stats: Stats
) {
    /**
     * Retrieves the displayed rank of this player (Player's top-most rank in the Rank Hierarchy)
     */
    fun getHighestRank() : String {
        println("$rank $monthlyPackageRank $newPackageRank $packageRank")
        if (!rank.isDefaultRank())
            return rank
        if (!monthlyPackageRank.isDefaultRank())
            return monthlyPackageRank
        if (!newPackageRank.isDefaultRank())
            return newPackageRank
        if (!packageRank.isDefaultRank())
            return packageRank

        return "NONE"
    }

    fun hasRank() : Boolean {
        return getHighestRank() != "NONE"
    }

    private fun getFullNetworkExp() : Double {
        var exp = networkExp
        exp += ILeveling.getTotalExpToFullLevel(networkLevel) + 1
        return exp
    }
    fun getFullNetworkLevel() : Double {
        return ILeveling.getExactLevel(getFullNetworkExp())
    }

    private fun String.isDefaultRank() : Boolean { return this == "NONE" || this == "NORMAL" || this.isEmpty() }
}

@Serializable
data class Stats(
    @SerialName("Bedwars") val bedwars: Bedwars
)

inline fun <reified T> decode(element : JsonElement) : T {
    val json = Json { ignoreUnknownKeys = true }
    val jsonElement = element.jsonObject
    return json.decodeFromJsonElement<T>(jsonElement)
}
fun JsonObject.getAllWithPrefix(prefix: String) : JsonObject {
    val matchingEntries = this.filter { it.key.startsWith(prefix) }
    return JsonObject(matchingEntries.toMap())
}