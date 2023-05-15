import club.maxstats.hyko.HypixelAPIException
import club.maxstats.hyko.getPlayerFromUUID
import java.io.File
import java.util.*

suspend fun main() {
    /* Obviously use your own API Key, this is here so that I can test without exposing my API Key */
    val properties = Properties().apply { load(File("config.properties").inputStream()) }

    /* Checking stats of player "exejar" */
    try {
        val player = getPlayerFromUUID("98a3cecc37494981b2464fc8bb6d418c", properties.getProperty("apiKey"))

        println("${player.displayName}'s Void Final Kills in 4v4v4v4: ${player.stats.bedwars.fourFour.voidFinalKills}")
        println("${player.displayName}'s UHC Duels Kills: ${player.stats.duels.uhc.kills}")
    } catch (ex: HypixelAPIException) {
        ex.printStackTrace()
    }
}