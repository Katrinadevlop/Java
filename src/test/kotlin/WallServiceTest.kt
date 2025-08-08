import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class WallServiceTest {

    @Test
    fun addCommentPost() {
        val service = WallService()

        val post = Post(
            id = 1,
            fromId = 456,
            date = System.currentTimeMillis(),
            text = "Пример поста"
        )
        service.add(post)

        val comment = Comment(
            postId = 1,
            fromId = 789,
            date = System.currentTimeMillis(),
            text = "Комментарий к существующему посту"
        )
        val result = service.createComment(postId = 1, comment)

        assertEquals(1, result.id)
        assertEquals(comment.text, result.text)
        assertEquals(comment.fromId, result.fromId)
    }

    @Test
    fun postNotFound() {
        val service = WallService()

        val comment = Comment(
            postId = 999,
            fromId = 1,
            date = System.currentTimeMillis(),
            text = "Невалидный комментарий"
        )

        assertThrows(PostNotFoundException::class.java) {
            service.createComment(postId = 999, comment)
        }
    }
}
