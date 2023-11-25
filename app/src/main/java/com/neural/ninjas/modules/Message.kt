package com.neural.ninjas.modules

data class Message(
    val message: String,
    val isUser: Boolean
)

data class ChatRequest(
    val frequency_penalty: Int,
    val max_tokens: Int,
    val messages: List<MessageX>,
    val model: String,
    val n: Int,
    val presence_penalty: Int,
    val stream: Boolean,
    val temperature: Int,
    val top_p: Int
)

data class ChatResponse(
    val choices: List<Choice>,
    val created: Int,
    val id: String,
    val model: String,
    val `object`: String,
    val usage: Usage
)

data class Choice(
    val finish_reason: String,
    val index: Int,
    val message: MessageX
)

data class MessageX(
    val content: String,
    val role: String
)

data class Usage(
    val completion_tokens: Int,
    val prompt_tokens: Int,
    val total_tokens: Int
)