import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.withContext
import kotlinx.coroutines.async
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.*
import player.Player
import java.lang.RuntimeException
import java.net.HttpURLConnection
import java.net.URL

private val safeJson = Json { ignoreUnknownKeys = true }

@Serializable
data class PlayerResponse(
    val success: Boolean,
    val cause: String? = null,
    val throttle: Boolean = false,
    val global: Boolean = false,
    val player: Player? = null
)
suspend fun getPlayerFromUUID(playerUUID: String, apiKey: String) : Player {
    val formattedURL = "https://api.hypixel.net/player?uuid=$playerUUID&key=$apiKey"
    val response = GlobalScope.async { fetchApiData(formattedURL) }.await()
    val data = safeJson.decodeFromString<PlayerResponse>(response)

    if (data.success) {
        return data.player
            ?: throw HypixelAPIException("Failed to deserialize player $playerUUID")
    } else {
        if (data.cause == "Invalid API key") throw ApiKeyException(apiKey)
        else if (data.throttle) throw ApiKeyThrottleException(apiKey)
        else if (data.cause == "Malformed UUID") throw MalformedUUIDException(playerUUID)
        else if (data.player == null) throw PlayerNotFoundException(playerUUID)
        else throw HypixelAPIException("Failed to contact Hypixel API")
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