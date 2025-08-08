package ru.netology.notes
import Comment
import CommentNotFoundException
import CrudService
import IllegalNoteStateException
import NoteNotFoundException

data class Note(
    val id: Int,
    var title: String,
    var text: String,
    var isDeleted: Boolean = false
)

class NoteService : CrudService<Note> {

    private val notes = mutableListOf<Note>()
    private val comments = mutableListOf<Comment>()
    private var nextNoteId = 1
    private var nextCommentId = 1

    override fun add(item: Note): Note {
        val note = item.copy(id = nextNoteId++)
        notes += note
        return note
    }

    override fun getById(id: Int): Note {
        val note = notes.find { it.id == id } ?: throw NoteNotFoundException("Note $id not found")
        if (note.isDeleted) throw IllegalNoteStateException("Note $id is deleted")
        return note
    }

    override fun get(): List<Note> = notes.filter { !it.isDeleted }

    override fun edit(id: Int, patch: Note): Boolean {
        val idx = notes.indexOfFirst { it.id == id }
        if (idx == -1) throw NoteNotFoundException("Note $id not found")
        val current = notes[idx]
        if (current.isDeleted) throw IllegalNoteStateException("Cannot edit deleted note $id")
        notes[idx] = current.copy(title = patch.title, text = patch.text)
        return true
    }

    override fun delete(id: Int): Boolean {
        val note = notes.find { it.id == id } ?: throw NoteNotFoundException("Note $id not found")
        if (note.isDeleted) return false
        note.isDeleted = true
        comments.filter { it.noteId == id }.forEach { it.isDeleted = true }
        return true
    }

    fun createComment(noteId: Int, message: String): Comment {
        val note = notes.find { it.id == noteId } ?: throw NoteNotFoundException("Note $noteId not found")
        if (note.isDeleted) throw IllegalNoteStateException("Cannot comment deleted note $noteId")
        val comment = Comment(id = nextCommentId++, noteId = noteId, message = message)
        comments += comment
        return comment
    }

    fun deleteComment(commentId: Int): Boolean {
        val comment = comments.find { it.id == commentId }
            ?: throw CommentNotFoundException("Comment $commentId not found")
        if (comment.isDeleted) throw CommentNotFoundException("Comment $commentId already deleted")
        comment.isDeleted = true
        return true
    }

    fun editComment(commentId: Int, newMessage: String): Boolean {
        val idx = comments.indexOfFirst { it.id == commentId }
        if (idx == -1) throw CommentNotFoundException("Comment $commentId not found")
        val current = comments[idx]
        if (current.isDeleted) throw CommentNotFoundException("Cannot edit deleted comment $commentId")
        val note =
            notes.find { it.id == current.noteId } ?: throw NoteNotFoundException("Note ${current.noteId} not found")
        if (note.isDeleted) throw IllegalNoteStateException("Cannot edit comment for deleted note ${note.id}")
        comments[idx] = current.copy(message = newMessage)
        return true
    }

    fun getComments(noteId: Int): List<Comment> {
        val note = notes.find { it.id == noteId } ?: throw NoteNotFoundException("Note $noteId not found")
        if (note.isDeleted) throw IllegalNoteStateException("Cannot get comments for deleted note $noteId")
        return comments.filter { it.noteId == noteId && !it.isDeleted }
    }

    fun restoreComment(commentId: Int): Boolean {
        val comment = comments.find { it.id == commentId }
            ?: throw CommentNotFoundException("Comment $commentId not found")
        if (!comment.isDeleted) return false
        val note =
            notes.find { it.id == comment.noteId } ?: throw NoteNotFoundException("Note ${comment.noteId} not found")
        if (note.isDeleted) throw IllegalNoteStateException("Cannot restore comment for deleted note ${note.id}")
        comment.isDeleted = false
        return true
    }
}
