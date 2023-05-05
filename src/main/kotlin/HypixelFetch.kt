import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.withContext
import kotlinx.coroutines.async
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.*
import player.Player
import java.lang.RuntimeException
import java.net.HttpURLConnection
import java.net.URL

private val safeJson = Json { ignoreUnknownKeys = true }
suspend fun getPlayerFromUUID(playerUUID: String, apiKey: String) : Player {
    val formattedURL = "https://api.hypixel.net/player?uuid=$playerUUID&key=$apiKey"
    val response = GlobalScope.async { fetchApiData(formattedURL) }.await()
    val data = Json.decodeFromString<JsonObject>(response)

    if (data["success"]?.jsonPrimitive?.boolean == true) {
        return safeJson.decodeFromString<Player>(data["player"]!!.jsonObject.toString())
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