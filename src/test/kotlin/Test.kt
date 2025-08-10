package ru.netology.chat

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class ChatServiceTest {

    @Test
    fun createRead() {
        val me = 1
        val svc = ChatService(currentUserId = me)

        svc.createMessage(toId = 2, text = "hi")

        val chat = svc.createChatIfAbsent(2)
        chat.messages += Message(id = 100, fromId = 2, toId = me, text = "yo", isRead = false)

        assertEquals(1, svc.getUnreadChatsCount())

        val last = svc.getMessages(interlocutorId = 2, count = 10)
        assertTrue(last.any { it.id == 100 })
        assertTrue(chat.messages.first { it.id == 100 }.isRead)

        assertEquals(0, svc.getUnreadChatsCount())
    }

    @Test
    fun deleteChat() {
        val svc = ChatService(currentUserId = 1)
        svc.createMessage(toId = 3, text = "hello")

        assertTrue(svc.getChats().any { it.interlocutorId == 3 })

        svc.deleteChat(3)
        assertTrue(svc.getChats().none { it.interlocutorId == 3 })

        assertThrows<ChatNotFoundException> { svc.deleteChat(3) }
    }

    @Test
    fun deleteMessage() {
        val svc = ChatService(currentUserId = 1)
        val m = svc.createMessage(toId = 2, text = "one")

        svc.deleteMessage(interlocutorId = 2, messageId = m.id)
        assertTrue(svc.getChats().none { it.interlocutorId == 2 })
    }

    @Test
    fun editMessage() {
        val svc = ChatService(currentUserId = 1)
        svc.createMessage(toId = 2, text = "msg")
        assertThrows<MessageNotFoundException> {
            svc.editMessage(interlocutorId = 2, messageId = 999, newText = "x")
        }
    }

    @Test
    fun getMessages() {
        val svc = ChatService(currentUserId = 1)
        assertThrows<ChatNotFoundException> {
            svc.getMessages(interlocutorId = 42, count = 10)
        }
    }

    @Test
    fun validationEmptyText() {
        val svc = ChatService(currentUserId = 1)
        assertThrows<ValidationException> {
            svc.createMessage(toId = 2, text = "  ")
        }
        svc.createMessage(toId = 2, text = "ok")
        assertThrows<ValidationException> {
            svc.editMessage(interlocutorId = 2, messageId = 1, newText = "")
        }
    }
}
