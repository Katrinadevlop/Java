package ru.netology.chat

class ChatService(private val currentUserId: Int) {

    private val chats = mutableMapOf<Int, Chat>()
    private var nextChatId = 1
    private var nextMessageId = 1

    fun getChats(): List<Chat> =
        chats.values.asSequence()
            .filter { chat -> chat.messages.any { !it.isDeleted } }
            .toList()

    fun getUnreadChatsCount(): Int =
        chats.values.asSequence()
            .count { it.messages.hasUnreadFor(currentUserId) }

    fun createChatIfAbsent(interlocutorId: Int): Chat =
        chats.getOrPut(interlocutorId) {
            Chat(id = nextChatId++, interlocutorId = interlocutorId)
        }

    fun deleteChat(interlocutorId: Int) {
        val chat = chats.remove(interlocutorId)
            ?: throw ChatNotFoundException("Чат $interlocutorId не найден")
        chat.messages.asSequence().forEach { it.isDeleted = true }
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
        val message = chat.messages.asSequence()
            .firstOrNull { it.id == messageId && !it.isDeleted }
            ?: throw MessageNotFoundException("Сообщение $messageId не найдено")
        message.text = newText
        return true
    }

    fun deleteMessage(interlocutorId: Int, messageId: Int): Boolean {
        val chat = chats[interlocutorId]
            ?: throw ChatNotFoundException("Чат с пользователем $interlocutorId не найден")
        val msg = chat.messages.asSequence()
            .firstOrNull { it.id == messageId }
            ?: throw MessageNotFoundException("Сообщение $messageId не найдено")
        if (msg.isDeleted) throw MessageNotFoundException("Сообщение $messageId уже удалено")
        msg.isDeleted = true

        if (chat.messages.asSequence().none { !it.isDeleted }) {
            chats.remove(interlocutorId)
        }
        return true
    }

    fun getLastMessages(): List<String> =
        chats.values.asSequence()
            .map { chat -> chat.messages.lastAlive()?.text ?: "нет сообщений" }
            .toList()

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
