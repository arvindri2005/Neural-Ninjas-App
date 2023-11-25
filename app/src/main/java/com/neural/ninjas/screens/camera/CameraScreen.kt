package com.neural.ninjas.screens.camera

import android.content.Context
import androidx.camera.core.CameraSelector
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.neural.ninjas.R

@Composable
fun CameraScreen(navController: NavController, context: Context) {

    val isFlashOn = remember { mutableStateOf(false) }


    Surface(
        modifier = Modifier
            .fillMaxSize()
    ){
        val controller = remember{
            LifecycleCameraController(context).apply {
                setEnabledUseCases(
                    CameraController.IMAGE_CAPTURE
                )
            }
        }

        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = Color(0xFF202027),
        ) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 10.dp, vertical = 10.dp)
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Card (
                    shape= RoundedCornerShape(30.dp)
                ){
                    Box {
                        CameraPreview(
                            controller = controller,
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(600.dp)
                        )
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(end= 20.dp, top = 5.dp, start = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically) {
                            IconButton(onClick = {
                                navController.popBackStack()
                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.icon_back),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(30.dp)
                                        .clip(CircleShape)
                                        .background(Color.Black),
                                    tint = Color.White)
                            }

                            IconButton(onClick = {

                                isFlashOn.value = !isFlashOn.value
                                controller.cameraControl?.enableTorch(isFlashOn.value)

                            }) {
                                Icon(
                                    painter = painterResource(id = R.drawable.icon_flash),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(200.dp)
                                        .clip(CircleShape)
                                        .background(Color.Black)
                                        .padding(5.dp),
                                    tint = Color.White)
                            }

                        }

                    }
                }
                
                Text(
                    text = "AI directed photo taking to never miss any details.",
                    fontSize = 20.sp,
                    color = Color(0xFFFFE600))

                Row (
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 20.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ){
                    IconButton(onClick = {  }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_gallery),
                            contentDescription = null,
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                                .padding(5.dp),
                            tint = Color(0xFFFFE600))
                    }

                    IconButton(
                        onClick = {  },
                        modifier = Modifier
                            .size(100.dp)) {

                        Box(
                            modifier = Modifier
                                .size(90.dp)
                                .clip(CircleShape)
                                .background(Color(0xFFffe600)),
                            contentAlignment = Alignment.Center){

                            Box(
                                modifier = Modifier
                                    .size(60.dp)
                                    .clip(CircleShape)
                                    .background(Color(0xFFd6c100)),
                                contentAlignment = Alignment.Center){
                                Icon(
                                    painter = painterResource(id = R.drawable.icon_shutter),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(50.dp)
                                        .clip(CircleShape)
                                        .padding(5.dp),
                                    tint = Color.White)

                            }
                        }


                    }

                    IconButton(onClick = {
                        if(controller.cameraSelector == CameraSelector.DEFAULT_BACK_CAMERA){
                            controller.cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA
                        }
                        else{
                            controller.cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
                        }
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_switch),
                            contentDescription = null,
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape)
                                .padding(5.dp),
                            tint = Color(0xFFFFE600))
                    }
                }
            }

        }

    }
}

@Composable
fun TopBar(){
    Text(
        text = "Camera")
}