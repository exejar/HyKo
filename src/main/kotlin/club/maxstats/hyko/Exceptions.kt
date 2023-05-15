package club.maxstats.hyko

open class HypixelAPIException(message: String) : Exception(message)
class PlayerNotFoundException(playerUUID: String) : HypixelAPIException("Player $playerUUID not found")
class MalformedUUIDException(playerUUID: String) : HypixelAPIException("UUID $playerUUID is malformed")
class ApiKeyException(apiKey: String) : HypixelAPIException("Invalid API Key: $apiKey")
class ApiKeyThrottleException(apiKey: String) : HypixelAPIException("API Request Limit reached for key: $apiKey")