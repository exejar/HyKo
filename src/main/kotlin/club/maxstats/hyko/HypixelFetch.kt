package club.maxstats.hyko

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.put
import java.net.HttpURLConnection
import java.net.URL

const val hypixelBaseURL = "https://api.hypixel.net"
private val safeJson = Json { ignoreUnknownKeys = true }

@Serializable
data class PlayerResponse(
    val success: Boolean,
    val cause: String? = null,
    val throttle: Boolean = false,
    val global: Boolean = false,
    val player: Player? = null,
    val meta: HypixelMetadata
)

@Deprecated("This has been deprecated in favour of more intuitive method name.", ReplaceWith("getPlayerByUuid"), DeprecationLevel.WARNING)
suspend fun getPlayerFromUUID(playerUUID: String, apiKey: String): Player {
    return getPlayerByUuid(playerUUID, apiKey)
}

/**
 * Fetches a player by their UUID.
 * @param playerUuid A Minecraft UUID, dashe or undashed
 * @param apiKey A valid Hypixel API Key.
 * @return Player
 */
suspend fun getPlayerByUuid(playerUuid: String, apiKey: String): Player {
    // FIXME: ktor allows you to serialize more efficiently - Kept this comment
    val response = fetchHypixelResponse("player?uuid=$playerUuid", apiKey)
    val decodedJson = safeJson.decodeFromString<PlayerResponse>(response)

    return when {
        decodedJson.success -> decodedJson.player ?: throw HypixelAPIException("Failed to deserialize player $playerUuid")
        decodedJson.cause == "Invalid API key" -> throw ApiKeyException(apiKey)
        decodedJson.throttle -> throw ApiKeyThrottleException(apiKey)
        decodedJson.cause == "Malformed UUID" -> throw MalformedUUIDException(playerUuid)
        decodedJson.player == null -> throw PlayerNotFoundException(playerUuid)
        else -> throw HypixelAPIException("Failed to contact Hypixel API")
    }
}

private suspend fun fetchHypixelResponse(urlPath: String, apiKey: String) = withContext(Dispatchers.IO) {
    with(URL("https://api.hypixel.net/$urlPath").openConnection() as HttpURLConnection) {
        setRequestProperty("api-key", apiKey)
        connect()

        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw HypixelAPIException("Failed to fetch data from URL $url. Response code: $responseCode")
        }

        inputStream.bufferedReader().use {
            val response = it.readText()
            val metaData = buildJsonObject {
                put("retryAfter", getHeaderField("retry-after"))
                put("rateLimitRemaining", getHeaderField("ratelimit-remaining"))
                put("rateLimitReset", getHeaderField("ratelimit-reset"))
            }
            response.dropLast(1).plus(",\"meta\":$metaData").plus("}")
        }.also {
            disconnect()
        }
    }
}
