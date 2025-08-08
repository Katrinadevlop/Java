package ru.netology.chat

data class Message(
    val id: Int,
    val fromId: Int,
    val toId: Int,
    var text: String,
    val date: Long = System.currentTimeMillis(),
    var isRead: Boolean = false,
    var isDeleted: Boolean = false
)

data class Chat(
    val id: Int,
    val interlocutorId: Int,
    val messages: MutableList<Message> = mutableListOf()
)

class ChatNotFoundException(msg: String) : RuntimeException(msg)
class MessageNotFoundException(msg: String) : RuntimeException(msg)
class ValidationException(msg: String) : RuntimeException(msg)
