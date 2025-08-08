package ru.netology.chat

fun main() {
    val me = 1
    val service = ChatService(currentUserId = me)

    println("Непрочитанных чатов: " + service.getUnreadChatsCount())

    service.createMessage(toId = 2, text = "Привет!")
    service.createMessage(toId = 3, text = "Хей")
    println("Чаты: " + service.getChats().map { it.interlocutorId })

    println("Последние сообщения: " + service.getLastMessages())

    service.createChatIfAbsent(2).messages += Message(
        id = 999, fromId = 2, toId = me, text = "Ответ", isRead = false
    )

    println("Непрочитанных чатов: " + service.getUnreadChatsCount())
    val msgs = service.getMessages(interlocutorId = 2, count = 10)
    println("История с 2: $msgs")
    println("Непрочитанных чатов после getMessages: " + service.getUnreadChatsCount())

    val m = service.createMessage(toId = 2, text = "Ещё одно")
    service.editMessage(interlocutorId = 2, messageId = m.id, newText = "Ещё одно (правка)")
    service.deleteMessage(interlocutorId = 2, messageId = m.id)

    service.deleteChat(3)
    println("Чаты после удаления 3: " + service.getChats().map { it.interlocutorId })
}
