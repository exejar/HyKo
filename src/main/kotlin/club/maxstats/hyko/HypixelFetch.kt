package club.maxstats.hyko

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import java.net.HttpURLConnection
import java.net.URL

const val hypixelBaseURL = "https://api.hypixel.net/"
private val safeJson = Json { ignoreUnknownKeys = true }

@Serializable
data class PlayerResponse(
    val success: Boolean,
    val cause: String? = null,
    val throttle: Boolean = false,
    val global: Boolean = false,
    val player: Player? = null
)

suspend fun getPlayerFromUUID(playerUUID: String, apiKey: String): Player {
    // FIXME: ktor allows you to serialize more efficiently
    val response = fetchAPIData("$hypixelBaseURL/player?uuid=$playerUUID&key=$apiKey")
    val data = safeJson.decodeFromString<PlayerResponse>(response)

    return when {
        data.success -> data.player ?: throw HypixelAPIException("Failed to deserialize player $playerUUID")
        data.cause == "Invalid API key" -> throw ApiKeyException(apiKey)
        data.throttle -> throw ApiKeyThrottleException(apiKey)
        data.cause == "Malformed UUID" -> throw MalformedUUIDException(playerUUID)
        data.player == null -> throw PlayerNotFoundException(playerUUID)
        else -> throw HypixelAPIException("Failed to contact Hypixel API")
    }
}

private suspend fun fetchAPIData(url: String) = withContext(Dispatchers.IO) {
    with(URL(url).openConnection() as HttpURLConnection) {
        connect()

        if (responseCode != HttpURLConnection.HTTP_OK)
            throw HypixelAPIException("Failed to fetch data from URL $url. Response code: $responseCode")

        inputStream.bufferedReader().use { it.readText() }.also { disconnect() }
    }
}