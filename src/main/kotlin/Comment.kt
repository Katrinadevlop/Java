data class Comment(
    val id: Int = 0,
    val postId: Int,
    val fromId: Int,
    val date: Long,
    val text: String
)
