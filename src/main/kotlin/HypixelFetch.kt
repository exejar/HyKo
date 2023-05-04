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
    @SerialName("networkExp") val networkExp: Long = 0,
    @SerialName("stats") val stats: Stats
) {
    /**
     * Retrieves the displayed rank of this player (Player's top-most rank in the Rank Hierarchy)
     */
    fun getHighestRank() {

    }

    fun getNetworkLevel() {

    }
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