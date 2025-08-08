import ru.netology.notes.Note
import ru.netology.notes.NoteService

class NoteNotFoundException(message: String) : RuntimeException(message)
class CommentNotFoundException(message: String) : RuntimeException(message)
class IllegalNoteStateException(message: String) : RuntimeException(message)

interface CrudService<T> {
    fun add(item: T): T
    fun getById(id: Int): T
    fun get(): List<T>
    fun edit(id: Int, patch: T): Boolean
    fun delete(id: Int): Boolean
}

fun main() {
    val noteService = NoteService()
    val commentService = CommentService(noteService)

    println("=== Создаём заметку ===")
    val note = noteService.add(Note(id = 0, title = "Первая", text = "Текст первой"))
    println("Заметка: $note")

    println("\n=== Добавляем комментарии ===")
    val c1 = commentService.create(note.id, "Первый комментарий")
    val c2 = commentService.create(note.id, "Второй комментарий")
    println("Комментарии: " + commentService.getCommentsForNote(note.id))

    println("\n=== Обновляем комментарий c1 ===")
    commentService.update(c1.id, "Первый комментарий (обновлён)")
    println("c1 сейчас: " + commentService.read(c1.id))

    println("\n=== Удаляем c2 ===")
    commentService.delete(c2.id)
    println("Комментарии после удаления c2: " + commentService.getCommentsForNote(note.id))

    println("\n=== Восстанавливаем c2 ===")
    commentService.restore(c2.id)
    println("Комментарии после восстановления c2: " + commentService.getCommentsForNote(note.id))

    println("\n=== Негативные кейсы ===")
    try {
        commentService.create(999, "Не существует такой заметки")
    } catch (e: Exception) {
        println("Ожидаемая ошибка: ${e.message}")
    }

    noteService.delete(note.id)
    try {
        commentService.create(note.id, "К удалённой заметке нельзя")
    } catch (e: Exception) {
        println("Ожидаемая ошибка: ${e.message}")
    }
}
