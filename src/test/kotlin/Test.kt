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

    @Test
    fun deleteChatNotFound() {
        val svc = ChatService(currentUserId = 1)
        assertThrows<ChatNotFoundException> {
            svc.deleteChat(999)
        }
    }

    @Test
    fun deleteMessageNotFound() {
        val svc = ChatService(currentUserId = 1)
        assertThrows<ChatNotFoundException> {
            svc.deleteMessage(interlocutorId = 999, messageId = 1)
        }
    }

    @Test
    fun deleteAlreadyDeletedMessage() {
        val svc = ChatService(currentUserId = 1)
        val msg = svc.createMessage(toId = 2, text = "test")
        svc.deleteMessage(interlocutorId = 2, messageId = msg.id)
        
        svc.createMessage(toId = 2, text = "another")
        assertThrows<MessageNotFoundException> {
            svc.deleteMessage(interlocutorId = 2, messageId = msg.id)
        }
    }

    @Test
    fun editMessageNotFoundInChat() {
        val svc = ChatService(currentUserId = 1)
        svc.createMessage(toId = 2, text = "test")
        assertThrows<MessageNotFoundException> {
            svc.editMessage(interlocutorId = 2, messageId = 999, newText = "new")
        }
    }

    @Test
    fun editMessageInNonExistentChat() {
        val svc = ChatService(currentUserId = 1)
        assertThrows<ChatNotFoundException> {
            svc.editMessage(interlocutorId = 999, messageId = 1, newText = "new")
        }
    }

    @Test
    fun getMessagesWithInvalidCount() {
        val svc = ChatService(currentUserId = 1)
        svc.createMessage(toId = 2, text = "test")
        assertThrows<IllegalArgumentException> {
            svc.getMessages(interlocutorId = 2, count = 0)
        }
        assertThrows<IllegalArgumentException> {
            svc.getMessages(interlocutorId = 2, count = -1)
        }
    }

    @Test
    fun sequencesWorkCorrectly() {
        val svc = ChatService(currentUserId = 1)
        
        // Создаем несколько чатов
        svc.createMessage(toId = 2, text = "msg1")
        svc.createMessage(toId = 3, text = "msg2")
        svc.createMessage(toId = 4, text = "msg3")
        
        assertEquals(3, svc.getChats().size)
        assertEquals(3, svc.getLastMessages().size)
        
        // Удаляем все сообщения в одном чате
        val chat2 = svc.createChatIfAbsent(2)
        chat2.messages[0].isDeleted = true
        
        assertEquals(2, svc.getChats().size)
    }

    @Test
    fun unreadMessagesSequenceOptimization() {
        val me = 1
        val svc = ChatService(currentUserId = me)
        
        // Создаем несколько чатов с непрочитанными сообщениями
        val chat1 = svc.createChatIfAbsent(2)
        chat1.messages += Message(id = 100, fromId = 2, toId = me, text = "unread1", isRead = false)
        
        val chat2 = svc.createChatIfAbsent(3)
        chat2.messages += Message(id = 101, fromId = 3, toId = me, text = "unread2", isRead = false)
        
        val chat3 = svc.createChatIfAbsent(4)
        chat3.messages += Message(id = 102, fromId = me, toId = 4, text = "outgoing", isRead = false)
        
        assertEquals(2, svc.getUnreadChatsCount())
        
        // Прочитываем сообщения из первого чата
        svc.getMessages(interlocutorId = 2, count = 10)
        assertEquals(1, svc.getUnreadChatsCount())
    }
}
