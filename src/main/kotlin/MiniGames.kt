import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stats(
    @SerialName("Bedwars") val bedwars: Bedwars,
    @SerialName("SkyWars") val skywars: Skywars,
    @SerialName("Arena") val arenaBrawl: ArenaBrawl,
    @SerialName("UHC") val uhc: UHC,

)

/*TODO Don't use POJO classes, or find a better way to store the data */
@Serializable
data class Bedwars(
    @SerialName("Experience_new") val experienceNew : Int = 0,
    @SerialName("Experience") val experience : Int = 0,
    @SerialName("winstreak") val winstreak : Int = 0,
    @SerialName("eight_two_beds_broken_bedwars") val eightTwoBedsBroken : Int = 0,
    @SerialName("coins") val coins : Int = 0,
    @SerialName("eight_two_wins_bedwars") val eightTwoWins : Int = 0,
    @SerialName("eight_two_kills_bedwars") val eightTwoKills : Int = 0,
    @SerialName("games_played_bedwars") val gamesPlayed : Int = 0,
    @SerialName("kills_bedwars") val kills : Int = 0,
    @SerialName("eight_two_games_played_bedwars") val eightTwoGamesPlayed : Int = 0,
    @SerialName("wins_bedwars") val wins : Int = 0,
    @SerialName("final_kills_bedwars") val finalKills : Int = 0,
    @SerialName("beds_broken_bedwars") val bedsBroken : Int = 0,
    @SerialName("eight_two_final_kills_bedwars") val eightTwoFinalKills : Int = 0,
    @SerialName("final_deaths_bedwars") val finalDeaths : Int = 0,
    @SerialName("deaths_bedwars") val deaths : Int = 0,
    @SerialName("beds_lost_bedwars") val bedsLost : Int = 0,
    @SerialName("eight_two_final_deaths_bedwars") val eightTwoFinalDeaths : Int = 0,
    @SerialName("eight_two_deaths_bedwars") val eightTwoDeaths : Int = 0,
    @SerialName("eight_two_beds_lost_bedwars") val eightTwoBedsLost : Int = 0,
    @SerialName("losses_bedwars") val losses : Int = 0,
    @SerialName("eight_two_losses_bedwars") val eightTwoLosses : Int = 0,
    @SerialName("eight_one_beds_lost_bedwars") val eightOneBedsLost : Int = 0,
    @SerialName("eight_one_final_deaths_bedwars") val eightOneFinalDeaths : Int = 0,
    @SerialName("eight_one_games_played_bedwars") val eightOneGamesPlayed : Int = 0,
    @SerialName("eight_one_final_kills_bedwars") val eightOneFinalKills : Int = 0,
    @SerialName("eight_one_beds_broken_bedwars") val eightOneBedsBroken : Int = 0,
    @SerialName("eight_one_losses_bedwars") val eightOneLosses : Int = 0,
    @SerialName("eight_one_kills_bedwars") val eightOneKills : Int = 0,
    @SerialName("four_four_beds_broken_bedwars") val fourFourBedsBroken : Int = 0,
    @SerialName("four_four_games_played_bedwars") val fourFourGamesPlayed : Int = 0,
    @SerialName("four_four_wins_bedwars") val fourFourWins : Int = 0,
    @SerialName("four_four_final_kills_bedwars") val fourFourFinalKills : Int = 0,
    @SerialName("four_four_kills_bedwars") val fourFourKills : Int = 0,
    @SerialName("four_four_deaths_bedwars") val fourFourDeaths : Int = 0,
    @SerialName("four_four_beds_lost_bedwars") val fourFourBedsLost : Int = 0,
    @SerialName("activeNPCSkin") val activenpcskin : String = "",
    @SerialName("four_four_final_deaths_bedwars") val fourFourFinalDeaths : Int = 0,
    @SerialName("four_four_losses_bedwars") val fourFourLosses : Int = 0,
    @SerialName("four_four_custom_final_kills_bedwars") val fourFourCustomFinalKills : Int = 0,
    @SerialName("custom_final_kills_bedwars") val customFinalKills : Int = 0,
    @SerialName("four_four_custom_deaths_bedwars") val fourFourCustomDeaths : Int = 0,
    @SerialName("custom_deaths_bedwars") val customDeaths : Int = 0,
    @SerialName("four_three_wins_bedwars") val fourThreeWins : Int = 0,
    @SerialName("four_three_games_played_bedwars") val fourThreeGamesPlayed : Int = 0,
    @SerialName("four_three_custom_final_kills_bedwars") val fourThreeCustomFinalKills : Int = 0,
    @SerialName("four_three_beds_lost_bedwars") val fourThreeBedsLost : Int = 0,
    @SerialName("four_three_deaths_bedwars") val fourThreeDeaths : Int = 0,
    @SerialName("four_three_beds_broken_bedwars") val fourThreeBedsBroken : Int = 0,
    @SerialName("four_three_final_kills_bedwars") val fourThreeFinalKills : Int = 0,
    @SerialName("four_three_final_deaths_bedwars") val fourThreeFinalDeaths : Int = 0,
    @SerialName("four_three_kills_bedwars") val fourThreeKills : Int = 0,
    @SerialName("four_three_losses_bedwars") val fourThreeLosses : Int = 0,
    @SerialName("activeKillEffect") val activekilleffect : String = "",
    @SerialName("activeVictoryDance") val activevictorydance : String = "",
    @SerialName("activeIslandTopper") val activeislandtopper : String = "",
    @SerialName("eight_one_deaths_bedwars") val eightOneDeaths : Int = 0,
    @SerialName("eight_one_wins_bedwars") val eightOneWins : Int = 0,
    @SerialName("spray_glyph_field") val sprayGlyphField : String = "",
    @SerialName("activeKillMessages") val activekillmessages : String = "",
    @SerialName("activeDeathCry") val activedeathcry : String = "",
    @SerialName("activeProjectileTrail") val activeprojectiletrail : String = "",
    @SerialName("activeGlyph") val activeglyph : String = "",
    @SerialName("favorite_slots") val favoriteSlots : String = "",
    @SerialName("activeBedDestroy") val activebeddestroy : String = "",
    @SerialName("eight_two_winstreak") val eightTwoWinstreak : Int = 0,
    @SerialName("four_four_winstreak") val fourFourWinstreak : Int = 0,
    @SerialName("four_three_winstreak") val fourThreeWinstreak : Int = 0,
    @SerialName("eight_one_winstreak") val eightOneWinstreak : Int = 0,
    @SerialName("favourites_2") val favourites2 : String = "",
    @SerialName("activeSprays") val activesprays : String = "",
    @SerialName("glyph_storage_new") val glyphStorageNew : String = "",
    @SerialName("spray_storage_new") val sprayStorageNew : String = "",
    @SerialName("favourites_1") val favourites1 : String = "",
    @SerialName("castle_winstreak") val castleWinstreak : Int = 0,
    @SerialName("castle_deaths_bedwars") val castleDeaths : Int = 0,
    @SerialName("castle_games_played_bedwars") val castleGamesPlayed : Int = 0,
    @SerialName("castle_wins_bedwars") val castleWins : Int = 0,
    @SerialName("castle_beds_lost_bedwars") val castleBedsLost : Int = 0,
    @SerialName("castle_beds_broken_bedwars") val castleBedsBroken : Int = 0,
    @SerialName("castle_kills_bedwars") val castleKills : Int = 0,
    @SerialName("castle_final_deaths_bedwars") val castleFinalDeaths : Int = 0,
    @SerialName("castle_losses_bedwars") val castleLosses : Int = 0,
    @SerialName("castle_final_kills_bedwars") val castleFinalKills : Int = 0,
    @SerialName("shop_sort") val shopSort : String = "",
    @SerialName("two_four_winstreak") val twoFourWinstreak : Int = 0,
    @SerialName("two_four_games_played_bedwars") val twoFourGamesPlayed : Int = 0,
    @SerialName("two_four_wins_bedwars") val twoFourWins : Int = 0,
    @SerialName("two_four_beds_broken_bedwars") val twoFourBedsBroken : Int = 0,
    @SerialName("two_four_deaths_bedwars") val twoFourDeaths : Int = 0,
    @SerialName("two_four_kills_bedwars") val twoFourKills : Int = 0,
    @SerialName("two_four_final_kills_bedwars") val twoFourFinalKills : Int = 0,
    @SerialName("two_four_beds_lost_bedwars") val twoFourBedsLost : Int = 0,
    @SerialName("two_four_final_deaths_bedwars") val twoFourFinalDeaths : Int = 0,
    @SerialName("two_four_losses_bedwars") val twoFourLosses : Int = 0,
)

@Serializable
data class UHC(
    @SerialName("kills") val kills: Int = 0,
    @SerialName("deaths") val deaths: Int = 0,
    @SerialName("coins") val coins: Int = 0,
    @SerialName("equippedKit") val equippedKit: String = "",
    @SerialName("heads_eaten") val headsEaten: Int = 0,
)

@Serializable
data class Skywars(
    @SerialName("levelFormatted") val formattedLevel: String = ""
)

@Serializable
data class ArenaBrawl(
    @SerialName("coins") val coins: Int = 0
)

