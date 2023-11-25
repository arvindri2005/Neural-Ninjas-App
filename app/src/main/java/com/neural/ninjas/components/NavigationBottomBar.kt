package com.neural.ninjas.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.neural.ninjas.R
@Composable
fun NavigationBottomBar(navController: NavController) {

    Box(modifier = Modifier
        .fillMaxWidth()
        .background(Color(0xFF121212))){
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, end = 20.dp, top = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ){

            IconButton(
                onClick = {
                    navController.navigate("CameraScreen")
                },
                modifier = Modifier
                    .height(60.dp)
                    .width(70.dp)) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_camera),
                        contentDescription = null,
                        modifier = Modifier
                            .size(25.dp),
                        tint = Color.White)

                    Text(
                        text ="Camera",
                        style = TextStyle(
                            color = Color.White
                        )
                    )
                }

            }
            IconButton(
                onClick = { },
                modifier = Modifier
                    .height(60.dp)
                    .width(70.dp)) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_home),
                        contentDescription = null,
                        modifier = Modifier
                            .size(25.dp),
                        tint = Color(0xFFffe600))

                    Text(
                        text ="Health+",
                        color = Color(0xFFffe600))
                }
            }
            IconButton(
                onClick = {
                    navController.navigate("ChatScreen")
                },
                modifier = Modifier
                    .height(60.dp)
                    .width(70.dp)) {
                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_chat),
                        contentDescription = null,
                        modifier = Modifier
                            .size(25.dp),
                        tint = Color.White)

                    Text(
                        text ="Chat",
                        style = TextStyle(
                            color = Color.White
                        ))
                }
            }

        }
    }

}