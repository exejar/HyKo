package club.maxstats.hyko

import kotlinx.serialization.Serializable


/**
 * **HypixelMetadata**
 *
 * Information about the rate limit to assist HyKo.
 */
@Serializable
data class HypixelMetadata(
    /**
     * An **int** or **null** based on if the key has been rate limited or not.
     * @return Int
     */
    val retryAfter: Int? = 0,
    /**
     * An **int** counting the amount of requests the user has left within the current one minute timeframe.
     * @return Int
     */
    val rateLimitRemaining: Int?=0,
    /**
     * An **int** telling the user how long is left before the limit is reset.
     * @return Int
     */
    val rateLimitReset: Int?=0
)
