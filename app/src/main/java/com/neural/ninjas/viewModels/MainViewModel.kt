package com.neural.ninjas.viewModels

import androidx.lifecycle.ViewModel
import com.neural.ninjas.modules.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel :ViewModel(){
    private val _previousMessage =MutableStateFlow<List<Message>>(emptyList())

    val previousMessage = _previousMessage.asStateFlow()

    fun onSendMessage(message: Message){
        _previousMessage.value = _previousMessage.value + message
    }
}