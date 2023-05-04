suspend fun main() {
    val player = getPlayerFromUUID("98a3cecc37494981b2464fc8bb6d418c", "")

    println("${player.displayName}'s Final Kill Count: ${player.stats.bedwars.finalKills}")
}