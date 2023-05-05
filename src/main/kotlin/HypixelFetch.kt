import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.withContext
import kotlinx.coroutines.async
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.*
import java.lang.RuntimeException
import java.net.HttpURLConnection
import java.net.URL

private val deserializer = Json { ignoreUnknownKeys = true }

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

suspend fun getPlayerFromUUID(playerUUID: String, apiKey: String) : Player {
    val formattedURL = "https://api.hypixel.net/player?uuid=$playerUUID&key=$apiKey"
    val response = GlobalScope.async { fetchApiData(formattedURL) }.await()
    val data = Json.decodeFromString<JsonObject>(response)

    if (data["success"]?.jsonPrimitive?.boolean == true) {
        return deserializer.decodeFromString<Player>(data["player"]!!.jsonObject.toString())
    } else {
        throw RuntimeException("Failed to contact Hypixel API")
    }
}

private suspend fun fetchApiData(url: String) : String {
    return withContext(Dispatchers.IO) {
        val conn = URL(url).openConnection() as HttpURLConnection
        conn.connect()

        if (conn.responseCode != HttpURLConnection.HTTP_OK)
            throw Exception("Failed to fetch data from URL $url. Response code: ${conn.responseCode}")

        val content = conn.inputStream.bufferedReader().use { it.readText() }
        conn.disconnect()

        content
    }
}