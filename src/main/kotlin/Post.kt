data class Post(
    val id: Int,
    val fromId: Int,
    val date: Long,
    val text: String
)

class WallService {
    private var posts = emptyArray<Post>()
    private var comments = emptyArray<Comment>()
    private var nextCommentId = 1

    fun createComment(postId: Int, comment: Comment): Comment {
        val postExists = posts.any { it.id == postId }
        if (!postExists) {
            throw PostNotFoundException("Пост с таким id $postId не найден")
        }

        val commentToAdd = comment.copy(id = nextCommentId++, postId = postId)
        comments += commentToAdd
        return commentToAdd
    }

    fun add(post: Post): Post {
        posts += post
        return post
    }

    fun getComments(): Array<Comment> = comments
    fun getPosts(): Array<Post> = posts
}
