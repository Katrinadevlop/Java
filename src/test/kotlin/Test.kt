package ru.netology.notes

import CommentNotFoundException
import IllegalNoteStateException
import NoteNotFoundException
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class NoteServiceTest {

    @Test
    fun getById() {
        val service = NoteService()
        val n1 = service.add(Note(0, "t1", "x1"))
        val n2 = service.add(Note(0, "t2", "x2"))

        assertEquals(n1, service.getById(n1.id))
        assertEquals(2, service.get().size)
        assertEquals("t2", service.getById(n2.id).title)
    }

    @Test
    fun createComment() {
        val service = NoteService()
        val n = service.add(Note(0, "t", "x"))
        val c1 = service.createComment(n.id, "c1")
        val c2 = service.createComment(n.id, "c2")

        val list = service.getComments(n.id)
        assertEquals(listOf(c1, c2), list)
    }

    @Test
    fun deleteComment() {
        val service = NoteService()
        val n = service.add(Note(0, "t", "x"))
        val c = service.createComment(n.id, "c")

        assertTrue(service.deleteComment(c.id))
        assertEquals(emptyList(), service.getComments(n.id))
        assertTrue(service.restoreComment(c.id))
        assertEquals(1, service.getComments(n.id).size)
    }

    @Test
    fun createCommentThrows() {
        val service = NoteService()
        assertThrows<NoteNotFoundException> {
            service.createComment(999, "nope")
        }
    }

    @Test
    fun editCommentThrows() {
        val service = NoteService()
        val n = service.add(Note(0, "t", "x"))
        val c = service.createComment(n.id, "c")
        service.deleteComment(c.id)
        assertThrows<CommentNotFoundException> {
            service.editComment(c.id, "new")
        }
    }

    @Test
    fun deleteNote() {
        val service = NoteService()
        val n = service.add(Note(0, "t", "x"))
        service.createComment(n.id, "c1")
        service.createComment(n.id, "c2")

        service.delete(n.id)
        assertThrows<IllegalNoteStateException> {
            service.getComments(n.id)
        }
        assertThrows<IllegalNoteStateException> {
            service.restoreComment(1)
        }
    }
}
