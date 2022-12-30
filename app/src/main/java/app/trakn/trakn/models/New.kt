package app.trakn.trakn.models

data class New(
    val coins: List<Coin>,
    val content: Boolean,
    val description: String,
    val feedDate: Long,
    val id: String,
    val imgURL: String,
    val link: String,
    val reactionsCount: ReactionsCount,
    val relatedCoins: List<String>,
    val searchKeyWords: List<String>,
    val shareURL: String,
    val source: String,
    val sourceLink: String,
    val title: String
)