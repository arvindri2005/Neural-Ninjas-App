package com.neural.ninjas.screens.auth

import android.app.DatePickerDialog
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun PersonalDetailsScreen(navController: NavController, context: Context){
    val date = remember { mutableStateOf(LocalDate.now()) }
    val showDialog = remember { mutableStateOf(false) }
    var expanded by remember { mutableStateOf(false) }
    val filter = listOf("Select", "Male","Female")
    var selectedFilter by remember { mutableStateOf(filter[0]) }
    val  isLoading = remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .fillMaxSize(),

        ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .background(Color(0xFF121212)),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Personalized Health\nProfile",
                style = TextStyle(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    color = Color.White
                ),
                modifier = Modifier.padding(top = 20.dp)
            )

            Text(
                text = "Please fill in the details below to complete your profile",
                style = TextStyle(
                    fontSize = 18.sp,
                    color = Color(0xFFffe600)
                ),
                modifier = Modifier.padding(10.dp)
            )

            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 20.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF1c1c1c)
                ),
                shape = RoundedCornerShape(15.dp)
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Date of Birth",
                        style = TextStyle(
                            fontSize = 22.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        ),
                    )

                    Text(
                        text = date.value.toString(),
                        modifier = Modifier
                            .clickable {
                                showDialog.value = true
                            },
                        style = TextStyle(
                            color = Color(0xFF858585),
                            fontSize = 18.sp
                        )
                    )

                }
                Divider(
                    color = Color(0xFF272727),
                    thickness = 2.dp
                )

                //Sex
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Sex",
                        style = TextStyle(
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        ),
                    )

                    Column {
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = {
                                expanded = false
                            },
                            modifier = Modifier
                                .background(Color(0xFF1c1c1c))) {
                            filter.forEach {
                                DropdownMenuItem(
                                    text = {
                                        Text(
                                            text = it,
                                            style = TextStyle(
                                                fontSize = 15.sp,
                                                textAlign = TextAlign.Center,
                                                color = Color.White
                                            ),
                                        )
                                    },
                                    onClick = {
                                        selectedFilter = it
                                        expanded = false
                                    },
                                    modifier = Modifier
                                        .background(Color(0xFF1c1c1c))
                                )
                            }
                        }

                        Text(
                            text = selectedFilter,
                            modifier = Modifier
                                .clickable {
                                    expanded = true
                                },
                            style = TextStyle(
                                color = Color(0xFF858585),
                                fontSize = 18.sp
                            )
                        )

                    }

                }
                Divider(
                    color = Color(0xFF272727),
                    thickness = 2.dp
                )

                //Height
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Height",
                        style = TextStyle(
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        ),
                    )

                    Text(
                        text = "5'11",
                        style = TextStyle(
                            color = Color(0xFF858585),
                            fontSize = 18.sp
                        )
                    )

                }
                Divider(
                    color = Color(0xFF272727),
                    thickness = 2.dp
                )

                //Weight
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Weight",
                        style = TextStyle(
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        ),
                    )

                    Text(
                        text = "82 kg",
                        style = TextStyle(
                            color = Color(0xFF858585),
                            fontSize = 18.sp
                        )
                    )

                }
                Divider(
                    color = Color(0xFF272727),
                    thickness = 2.dp
                )

                //Medical Documents
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Medical Documents",
                        style = TextStyle(
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        ),
                    )

                    Text(
                        text = "+Add",
                        style = TextStyle(
                            color = Color(0xFF858585),
                            fontSize = 18.sp
                        )
                    )

                }
                Divider(
                    color = Color(0xFF272727),
                    thickness = 2.dp
                )

                //Language
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Language",
                        style = TextStyle(
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            color = Color(0xFFffe600)
                        ),
                    )

                    Text(
                        text = "English",
                        style = TextStyle(
                            color = Color(0xFF858585),
                            fontSize = 18.sp
                        )
                    )

                }
            }

            Button(
                onClick = {
                    isLoading.value=true
                    CoroutineScope(Dispatchers.Main).launch {
                        delay(1000)
                        Toast.makeText(context, "Save successful", Toast.LENGTH_SHORT).show()
                        navController.navigate("HomeScreen")
                    }

                },
                modifier= Modifier
                    .fillMaxWidth()
                    .padding(20.dp),
                shape = RoundedCornerShape(15.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2c2c2c)
                )
            ) {
                if (isLoading.value){
                    CircularProgressIndicator(
                        color = Color.White
                    )
                }
                else{
                    Text(
                        text ="Continue",
                        style = TextStyle(
                            fontSize = 20.sp,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        ),
                        modifier = Modifier
                            .padding(vertical = 4.dp)
                    )
                }

            }
        }
    }

    if (showDialog.value) {
        ShowDatePicker(date)
        showDialog.value = false
    }
}


@Composable
fun ShowDatePicker(date: MutableState<LocalDate>) {
    val context = LocalContext.current
    DatePickerDialog(context, { _, year, month, dayOfMonth ->
        date.value = LocalDate.of(year, month + 1, dayOfMonth)
    }, date.value.year, date.value.monthValue - 1, date.value.dayOfMonth).show()
}