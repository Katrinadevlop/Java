fun main() {
    val service = WallService()

    val post = Post(id = 1, fromId = 456, date = System.currentTimeMillis(), text = "Hello!")
    service.add(post)

    try {
        val comment = Comment(
            postId = 1,
            fromId = 789,
            date = System.currentTimeMillis(),
            text = "Nice post!"
        )
        val result = service.createComment(1, comment)
        val result1 = service.createComment(2, comment)
        println("Comment added: $result")
    } catch (e: PostNotFoundException) {
        println("Error: ${e.message}")
    }
}
class PostNotFoundException(message: String) : RuntimeException(message)
