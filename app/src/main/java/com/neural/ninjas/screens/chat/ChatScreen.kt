package com.neural.ninjas.screens.chat

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.gson.Gson
import com.neural.ninjas.R
import com.neural.ninjas.components.InputMessage
import com.neural.ninjas.modules.ChatRequest
import com.neural.ninjas.modules.Message
import com.neural.ninjas.modules.MessageX
import com.neural.ninjas.viewModels.MainViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import okhttp3.MediaType
import okhttp3.RequestBody

private var botMessage: MutableList<Message> = mutableListOf(
    Message(
    "Nice to meet you, [Patient's Name]. What brings you in today? Could you please describe the main issue or symptoms you're experiencing?",
    false
    ),
    Message(
    "I'm sorry to hear that. To better assist the doctor, let me ask you a few more questions. On a scale from 1 to 10, how would you rate the intensity of your headache, with 1 being mild and 10 being severe?",
    false
    ),
    Message(
    "Thank you for providing that information. Have you noticed any specific triggers or patterns associated with your headaches, such as certain foods, activities, or times of day?",
    false
    ),
    Message(
    "I see. Are there any other symptoms you've been experiencing along with the headache? For example, nausea, dizziness, sensitivity to light or sound?",
    false
    ),
    Message(
    "Thank you for sharing that. In terms of your overall health, have you had any recent changes or issues that you think might be relevant? Any changes in medication or lifestyle?",
    false
    ),
    Message(
    "Stress can certainly contribute to headaches. Regarding your medical history, have you had any previous episodes of severe headaches or migraines? Any chronic conditions or family history that you're aware of?",
    false
    ),
    Message(
    "I appreciate you sharing that. Finally, do you have any specific questions or concerns.",
        false
    )
)


@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ChatScreen(navController: NavController, context: Context) {
    val i = remember {
        mutableIntStateOf(1)
    }

    val viewModel = viewModel<MainViewModel>()
    val previousMessages by viewModel.previousMessage.collectAsState()

//    viewModel.onSendMessage(Message("Who won the world series in 2020?", true))
//    viewModel.onSendMessage(Message("The Los Angeles Dodgers won the World Series in 2020.", false))

        Scaffold(
            modifier = Modifier
                .fillMaxSize(),
            containerColor = Color(0xFF121212),
            topBar = { TopBar(navController) },
            bottomBar = {
                InputMessage({ cameraBtn(navController) }) { message ->
                    if (message.isNotEmpty()) {
                        viewModel.onSendMessage(Message(message, true))

//                        getChatResponse(message, previousMessages) {
//                            viewModel.onSendMessage(Message(it, false))
//                        }
                        if (i.intValue < botMessage.size) {
                            bot(viewModel, i.intValue)
                            i.intValue++
                        }
                    } else {
                        Toast.makeText(context, "Please write something", Toast.LENGTH_SHORT).show()
                    }


                }
            }

        ) {
            Column(
                verticalArrangement = Arrangement.Bottom,
                modifier = Modifier
                    .padding(it)
                    .fillMaxSize()
                    .background(color = Color(0xFF121212))
            ) {
                Messages(messages = previousMessages)
            }

        }
    }

fun getChatResponse(message: String, previousMessages: List<Message>, botResponse:(String)->Unit) {
    val apiInterface = ApiUtilities.getApiInterface()
    val list : MutableList<MessageX> = mutableListOf()

    for (i in previousMessages){
        list.add(MessageX(
                        content = i.message,
                        role = if (i.isUser){
                                    "user"
                                }else{
                                    "assistant"
                                }
            )
        )
    }

    list.add(MessageX(
        content = message,
        role = "user"
    ))


    val requestBody = RequestBody.create(
        MediaType.parse("application/json"),
        Gson().toJson(
            ChatRequest(
                model= "gpt-3.5-turbo",
                messages= list,
                temperature= 1,
                top_p= 1,
                n= 1,
                stream= false,
                max_tokens= 250,
                presence_penalty= 0,
                frequency_penalty= 0
            )
        )
    )

    val contentType = "application/json"
    val authorization = "Bearer ApiKey"

    Log.d("ChatScreen", "getChatResponse: $requestBody")

//    CoroutineScope(Dispatchers.IO).launch {
//        try {
//            val response = apiInterface.getChatResponse(contentType,contentType, authorization, requestBody)
//            Log.e("ChatResponse", response.toString())
//            val textResponse = response.choices[0].message.content
//
//            withContext(Dispatchers.Main){
//                botResponse(textResponse)
//            }
//        } catch (e: Exception) {
//            Log.e("ChatResponse", "Error getting chat response", e)
//        }
//
//    }
}


fun bot(viewModel: MainViewModel,i: Int){
    CoroutineScope(Dispatchers.Main).launch {
        delay(2000)
        viewModel.onSendMessage(botMessage[i])
    }
}


@Composable
fun TopBar(navController: NavController) {

    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ){

        IconButton(onClick = {
            navController.popBackStack()
        }) {
            Icon(
                painter = painterResource(id = R.drawable.icon_back),
                contentDescription = null,
                tint = Color.White
            )
        }

        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = null,
            modifier = Modifier
                .size(36.dp)
                .clip(CircleShape))

        Text(text = "Virtual Doc", modifier = Modifier
            .padding(start = 10.dp)
            .background(Color(0xFF121212)),
            color = Color.White,
            fontSize = 20.sp
        )
    }
}

@Composable
fun Messages(modifier:Modifier = Modifier, messages: List<Message>){
    val scrollState = rememberLazyListState()

    LaunchedEffect(key1 = messages) {
        if (messages.isNotEmpty() && scrollState.layoutInfo.visibleItemsInfo.lastOrNull()?.index != messages.lastIndex) {
            scrollState.animateScrollToItem(messages.size - 1)
        }
    }
    Surface(
        modifier = modifier
            .fillMaxSize(),
        color = Color(0xFF121212)
    ) {
        LazyColumn(
            state = scrollState,
            userScrollEnabled = true,

        ){
            items(messages){
                MessageItem(it)
            }
        }
    }

}

@Composable
fun MessageItem(it: Message = Message("Hello", true)) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 5.dp, horizontal = 10.dp),
        horizontalAlignment = if(it.isUser) Alignment.End else Alignment.Start
    ) {
        Surface(
            modifier = Modifier
                .padding(start = if(it.isUser){
                                    40.dp
                                    }else{
                                    0.dp
                                    },
                    end = if(it.isUser){
                                    0.dp
                                    }else{
                                    40.dp
                                    }),
            shape = if(!it.isUser){
                RoundedCornerShape(topStart = 0.dp, topEnd = 16.dp, bottomEnd = 16.dp, bottomStart = 16.dp)
            }
            else{
                RoundedCornerShape(topStart = 16.dp, topEnd = 0.dp, bottomEnd = 16.dp, bottomStart = 16.dp)
            },
            color = if(!it.isUser) Color(0xFF3a3a53)
                    else Color(0xFFffe600),

        ) {
            Text(
                text = it.message,
                modifier = Modifier
                    .padding(8.dp),
                color = if(!it.isUser){
                    Color.White
                    } else {
                        Color.Black
                    }
            )
        }
    }
}

fun cameraBtn(navController: NavController){
    navController.navigate("CameraScreen")
}
