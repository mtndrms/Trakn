package app.trakn.trakn.models

data class Cryptocurrency(
    val id: String,
    val symbol: String,
    val name: String,
    val image: String,
    val current_price: String,
    val market_cap: String,
    val market_cap_rank: String,
    val fully_diluted_valuation: String,
    val total_volume: String,
    val high_24h: String,
    val low_24h: String,
    val price_change_24h: String,
    val price_change_percentage_24h: String,
    val market_cap_change_24h: String,
    val market_cap_change_percentage_24h: String,
    val circulating_supply: String,
    val total_supply: String,
    val max_supply: String,
    val ath: String,
    val ath_change_percentage: String,
    val ath_date: String,
    val atl: String,
    val atl_change_percentage: String,
    val atl_date: String,
    val last_updated: String
)
