import ru.netology.notes.NoteService

data class Comment(
    val id: Int,
    val noteId: Int,
    var message: String,
    var isDeleted: Boolean = false
)

class CommentService(private val noteService: NoteService) {
    private val comments = mutableListOf<Comment>()
    private var nextId = 1

    fun create(noteId: Int, message: String): Comment {
        val note = noteService.getById(noteId)
        val comment = Comment(id = nextId++, noteId = note.id, message = message)
        comments += comment
        return comment
    }

    fun read(id: Int): Comment? =
        comments.find { it.id == id && !it.isDeleted }

    fun update(id: Int, newMessage: String): Boolean {
        val idx = comments.indexOfFirst { it.id == id }
        if (idx == -1 || comments[idx].isDeleted) {
            throw CommentNotFoundException("Комментарий $id не найден")
        }
        val noteId = comments[idx].noteId
        noteService.getById(noteId)

        comments[idx] = comments[idx].copy(message = newMessage)
        return true
    }

    fun delete(id: Int): Boolean {
        val comment = comments.find { it.id == id }
            ?: throw CommentNotFoundException("Комментарий $id не найден")
        if (comment.isDeleted) {
            throw CommentNotFoundException("Комментарий $id уже удалён")
        }
        comment.isDeleted = true
        return true
    }

    fun restore(id: Int): Boolean {
        val comment = comments.find { it.id == id }
            ?: throw CommentNotFoundException("Комментарий $id не найден")
        if (!comment.isDeleted) return false
        noteService.getById(comment.noteId)

        comment.isDeleted = false
        return true
    }

    fun getCommentsForNote(noteId: Int): List<Comment> {
        noteService.getById(noteId)
        return comments.filter { it.noteId == noteId && !it.isDeleted }
    }
}
