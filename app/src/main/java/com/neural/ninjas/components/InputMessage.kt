package com.neural.ninjas.components

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Icon
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neural.ninjas.R

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("RememberReturnType")
@Composable
fun InputMessage(cameraClick:()->Unit, sendBtn:(message: String)->Unit) {
    val message = remember{
        mutableStateOf("")
    }
    Surface(
        modifier = Modifier
            .fillMaxWidth(),
        color = Color(0xFF121212)

    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            IconButton(onClick = {
                cameraClick()
            }) {
                Card(
                    modifier = Modifier
                        .fillMaxHeight()
                        .border(BorderStroke(1.dp, Color(0xFFFFFFFF)), CircleShape)
                        ,
                    shape = CircleShape,
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color(0xff3a3a53), CircleShape),
                        contentAlignment = Alignment.Center) {
                        Image(
                            modifier = Modifier
                                .padding(4.dp),
                            painter = painterResource(id = R.drawable.camera_icon),
                            contentDescription = null,
                            contentScale = androidx.compose.ui.layout.ContentScale.Inside,)
                    }
                }

            }
            OutlinedTextField(
                value = message.value,
                onValueChange = {
                    message.value = it
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp)
                    .background(Color(0xff2e2e38), shape = RoundedCornerShape(70.dp)),
                shape = RoundedCornerShape(70.dp),
                textStyle = TextStyle(fontSize = 20.sp, color = Color(0xFFFFFFFF)),
                trailingIcon = {
                    IconButton(onClick = {
                        sendBtn(message.value)
                        message.value = ""
                        },
                        modifier = Modifier
                            .padding(end = 4.dp)
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxHeight()
                                .border(BorderStroke(1.dp, Color(0xFFFFFFFF)), CircleShape)
                                .background(Color(0xFF2e2e38), CircleShape),
                            shape = CircleShape,
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(Color(0xffffe600), CircleShape),
                                contentAlignment = Alignment.Center) {
                                Image(
                                    modifier = Modifier
                                        .padding(4.dp),
                                    painter = painterResource(id = R.drawable.icon_send),
                                    contentDescription = null,
                                    contentScale = androidx.compose.ui.layout.ContentScale.Inside,)
                            }
                        }

                    }
                },
                leadingIcon = {
                              IconButton(onClick = {

                                    },
                                  modifier= Modifier
                                      .padding(start = 4.dp)
                              ) {
                                  Icon(
                                      painter = painterResource(id = R.drawable.icon_gallery),
                                      contentDescription = null,
                                      tint = Color(0xFFffe600),
                                      modifier = Modifier
                                          .size(30.dp)
                                  )
                              }
                },
                placeholder = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(Alignment.CenterVertically)
                    ){
                        Text(
                            text = "Type a message...",
                            color = Color(0xFF8E8E93),
                            fontSize = 20.sp,
                        )
                    }

                },
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color(0xFF2e2e38),
                    cursorColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent
                )

            )
        }
    }
}