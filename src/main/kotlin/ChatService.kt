package ru.netology.chat

class ChatService(private val currentUserId: Int) {

    private val chats = mutableMapOf<Int, Chat>()
    private var nextChatId = 1
    private var nextMessageId = 1

    fun getChats(): List<Chat> =
        chats.values.filter { chat -> chat.messages.any { !it.isDeleted } }

    fun getUnreadChatsCount(): Int =
        chats.values.count { it.messages.hasUnreadFor(currentUserId) }

    fun createChatIfAbsent(interlocutorId: Int): Chat =
        chats.getOrPut(interlocutorId) {
            Chat(id = nextChatId++, interlocutorId = interlocutorId)
        }

    fun deleteChat(interlocutorId: Int) {
        val chat = chats.remove(interlocutorId)
            ?: throw ChatNotFoundException("Чат $interlocutorId не найден")
        chat.messages.forEach { it.isDeleted = true } // пометим историю
    }

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
        val chat = chats[interlocutorId]
            ?: throw ChatNotFoundException("Чат с пользователем $interlocutorId не найден")
        val idx = chat.messages.indexOfFirst { it.id == messageId && !it.isDeleted }
        if (idx == -1) throw MessageNotFoundException("Сообщение $messageId не найдено")
        chat.messages[idx].text = newText
        return true
    }

    fun deleteMessage(interlocutorId: Int, messageId: Int): Boolean {
        val chat = chats[interlocutorId]
            ?: throw ChatNotFoundException("Чат с пользователем $interlocutorId не найден")
        val msg = chat.messages.find { it.id == messageId }
            ?: throw MessageNotFoundException("Сообщение $messageId не найдено")
        if (msg.isDeleted) throw MessageNotFoundException("Сообщение $messageId уже удалено")
        msg.isDeleted = true

        if (chat.messages.none { !it.isDeleted }) {
            chats.remove(interlocutorId)
        }
        return true
    }

    fun getLastMessages(): List<String> =
        chats.values.map { chat -> chat.messages.lastAlive()?.text ?: "нет сообщений" }

    fun getMessages(interlocutorId: Int, count: Int): List<Message> {
        require(count > 0) { "count должен быть > 0" }
        val chat = chats[interlocutorId]
            ?: throw ChatNotFoundException("Чат с пользователем $interlocutorId не найден")

        val slice = chat.messages.takeLastAlive(count)

        slice.asSequence()
            .filter { it.toId == currentUserId && !it.isDeleted && !it.isRead }
            .forEach { it.isRead = true }

        return slice
    }
}
