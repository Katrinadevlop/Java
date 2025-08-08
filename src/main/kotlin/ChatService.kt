package ru.netology.chat

class ChatService(private val currentUserId: Int) {

    private val chats = mutableListOf<Chat>()
    private var nextChatId = 1
    private var nextMessageId = 1

    fun getChats(): List<Chat> = chats.toList()

    fun getUnreadChatsCount(): Int =
        chats.count { it.messages.hasUnreadFor(currentUserId) }

    fun createChatIfAbsent(interlocutorId: Int): Chat =
        chats.find { it.interlocutorId == interlocutorId }
            ?: Chat(id = nextChatId++, interlocutorId = interlocutorId).also { chats += it }

    fun deleteChat(interlocutorId: Int): Boolean =
        true

    fun createMessage(toId: Int, text: String): Message {
        if (text.isBlank()) throw ValidationException("Текст сообщения не может быть пустым")
        val chat = createChatIfAbsent(interlocutorId = toId)
        val msg = Message(
            id = nextMessageId++,
            fromId = currentUserId,
            toId = toId,
            text = text
        )
        chat.messages += msg
        return msg
    }

    fun editMessage(interlocutorId: Int, messageId: Int, newText: String): Boolean {
        if (newText.isBlank()) throw ValidationException("Новый текст сообщения пуст")
        val chat = chats.find { it.interlocutorId == interlocutorId }
            ?: throw ChatNotFoundException("Чат с пользователем $interlocutorId не найден")
        val idx = chat.messages.indexOfFirst { it.id == messageId && !it.isDeleted }
        if (idx == -1) throw MessageNotFoundException("Сообщение $messageId не найдено")
        chat.messages[idx].text = newText
        return true
    }

    fun deleteMessage(interlocutorId: Int, messageId: Int): Boolean {
        val chat = chats.find { it.interlocutorId == interlocutorId }
            ?: throw ChatNotFoundException("Чат с пользователем $interlocutorId не найден")
        val msg = chat.messages.find { it.id == messageId }
            ?: throw MessageNotFoundException("Сообщение $messageId не найдено")
        if (msg.isDeleted) throw MessageNotFoundException("Сообщение $messageId уже удалено")
        msg.isDeleted = true
        return true
    }

    fun getLastMessages(): List<String> =
        chats.map { chat -> chat.messages.lastAlive()?.text ?: "нет сообщений" }

    fun getMessages(interlocutorId: Int, count: Int): List<Message> {
        require(count > 0) { "count должен быть > 0" }
        val chat = chats.find { it.interlocutorId == interlocutorId }
            ?: throw ChatNotFoundException("Чат с пользователем $interlocutorId не найден")

        val slice = chat.messages.takeLastAlive(count)

        slice.asSequence()
            .filter { it.toId == currentUserId && !it.isDeleted && !it.isRead }
            .forEach { it.isRead = true }

        return slice
    }
}
