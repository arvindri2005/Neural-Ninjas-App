package com.neural.ninjas.screens.home

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.neural.ninjas.R
import com.neural.ninjas.components.CheckBox
import com.neural.ninjas.components.NavigationBottomBar
import com.neural.ninjas.components.NavigationDrawer
import com.neural.ninjas.components.NavigationTopBar
import com.neural.ninjas.modules.CalendarDataSource
import com.neural.ninjas.modules.Schedule
import java.time.LocalDate
import java.time.LocalTime

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController
) {

    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val dataSource = CalendarDataSource()
    val calendarUiModel = dataSource.getData(lastSelectedDate = dataSource.today)
    val month = listOf(
        "January",
        "February",
        "March",
        "April",
        "May",
        "June",
        "July",
        "August",
        "September",
        "October",
        "November",
        "December")

    val mon = LocalDate.now().monthValue

    val presentMonth = rememberLazyListState(initialFirstVisibleItemIndex = mon )


    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ModalNavigationDrawer(
            drawerContent = {
                NavigationDrawer(navController, drawerState)
            },
            drawerState = drawerState,){
            Scaffold(
                topBar = {
                    NavigationTopBar(drawerState = drawerState)
                },
                containerColor = Color(0xFF121212),
                bottomBar = {
                    NavigationBottomBar(navController = navController)
                },
            ) {
                Card(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                        .padding(top = 10.dp)
                        .shadow(
                            90.dp,
                            spotColor = Color.White,
                            shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                        ),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = 40.dp
                    ),
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF171717)
                    )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Text(
                            text = "Schedule",
                            color = Color.White,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Box(
                            modifier = Modifier
                                .height(60.dp)
                                .fillMaxWidth()
                                .background(Color(0xFF1f1e17).copy(alpha = 0.4f))
                                .border(2.dp, color = Color.White.copy(alpha = 0.05f)),
                            contentAlignment = Alignment.Center
                        ){
                            LazyRow(
                                state = presentMonth,
                            ){
                                items(12){position->
                                    Text(
                                        modifier = Modifier
                                            .height(41.dp)
                                            .padding(start = 25.dp),
                                        text = month[position],
                                        fontSize = 20.sp,
                                        color = if(position==mon-1){
                                            Color(0xFFffe600)
                                        } else{
                                            Color(0xFFbfbfbf)
                                        },
                                        style = TextStyle(
                                            shadow = Shadow(
                                                color = if(position==mon-1){
                                                    Color(0xFFFFB700)
                                                }else{
                                                     Color.White
                                                },
                                                offset = Offset(0f, 0f),
                                                blurRadius = 100f
                                            )
                                        )
                                    )
                                }
                            }
                        }


                        LazyRow(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 4.dp),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            items(calendarUiModel.visibleDates) { date->
                                Card(
                                    modifier = Modifier
                                        .size(70.dp)
                                        .padding(horizontal = 5.dp, vertical = 5.dp),
                                    colors = CardDefaults.cardColors(
                                        containerColor = if (date.isSelected) {
                                            Color(0xFF7077b9)
                                        } else {
                                            Color.White
                                        }
                                    ),
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .clickable {

                                            },
                                        horizontalAlignment = Alignment.CenterHorizontally,
                                        verticalArrangement = Arrangement.Center
                                    ){
                                        Text(
                                            text = date.date.dayOfMonth.toString(),
                                            color = if (!date.isSelected) {
                                                    Color(0xFF7077b9)
                                                } else {
                                                    Color.White
                                                },
                                            fontSize = 15.sp,
                                        )
                                        Text(
                                            text = date.day,
                                            color = if (!date.isSelected) {
                                                    Color(0xFF7077b9)
                                                } else {
                                                    Color.White
                                                },
                                            fontSize = 15.sp
                                        )

                                    }

                                }
                            }
                        }

                        OutlineRow()

                        CalendarView()

                    }
                }
            }
        }
    }
}

@Composable
fun CalendarView() {
    val schedule = listOf<Schedule>(
        Schedule(
            "Theraflux MaxGrip",
            "8:00",
            false,
            "2 pills(15mg)",
            "Medicine"
        ),Schedule(
            "Theraflux MaxGrip",
            "10:00",
            false,
            "2 pills(15mg)",
            "Medicine"
        ),Schedule(
            "Theraflux MaxGrip",
            "12:00",
            false,
            "2 pills(15mg)",
            "Injection"
        ),Schedule(
            "Doctor",
            "17:20",
            false,
            "Appointment",
            "Appointment"
        ),
    )
    val time = LocalTime.now().toString().split(":")[0].toInt()
    Log.d("HomeScreen", "time: $time")

    val scrollState = rememberLazyListState(initialFirstVisibleItemIndex = time)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 4.dp)
    ) {

        LazyColumn(state = scrollState) {
            items(schedule){
                val time = it.time.split(":")[0].toInt()
                Row(
                    modifier = Modifier
                        .padding(top = 10.dp)
                ){
                    Column(
                        modifier = Modifier
                            .width(128.dp)
                    ) {
                        Card(
                            modifier = Modifier
                                .width(110.dp)
                                .height(54.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFffe600)
                            ),
                            shape = RoundedCornerShape(40.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center) {

                                Text(
                                    text ="$time:00",
                                    color = Color.Black,
                                    fontSize = 20.sp)
                            }
                        }
                        Card(
                            modifier = Modifier
                                .width(110.dp)
                                .height(64.dp)
                                .padding(top = 10.dp),
                            colors = CardDefaults.cardColors(
                                containerColor = Color(0xFFffe600)
                            ),
                            shape = RoundedCornerShape(40.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxSize(),
                                contentAlignment = Alignment.Center) {
                                Text(
                                    text ="$time:30",
                                    color = Color.Black,
                                    fontSize = 20.sp)
                            }
                        }
                    }

                    Card (
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(132.dp)
                            .padding(start = 4.dp, end = 5.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFedf2f3)
                        )
                    ){
                        Column(
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Start
                            ) {
                                Image(
                                    painter = painterResource(id =if(it.type=="Medicine"){
                                                                    R.drawable.icon_medicine
                                                                }else if(it.type == "Injection"){
                                                                    R.drawable.icon_injection
                                                                }else{
                                                                    R.drawable.icon_appoitment
                                                                }
                                    ),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(50.dp))
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth(),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        text = it.medicineName,
                                        fontSize = 18.sp,
                                        color = Color.Black
                                    )
                                    Text(
                                        text =it.medicineAmount,
                                        fontSize = 18.sp,
                                        color = Color.Black
                                    )
                                }

                            }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(top = 4.dp, start = 4.dp),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = it.time,
                                    color = Color(0xFF9298ca)
                                )
                                CheckBox(it.type)
                            }
                        }

                    }
                }
            }
        }

    }
}

@Composable
fun OutlineRow() {

    val filter = listOf("All", "Medicine","Appointment")
    var selectedFilter by remember { mutableStateOf(filter[0]) }
    var expanded by remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, top = 10.dp, end = 10.dp),
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Time",
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            modifier = Modifier
                .width(128.dp),
            color= Color.White
        )

        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "Task",
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp,
                color= Color.White
            )

            Column {
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {
                        expanded = false
                    },
                    modifier = Modifier
                        .background(Color.White)) {
                    filter.forEach {
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = it,
                                    color = Color.Black
                                )
                            },
                            onClick = {
                                selectedFilter = it
                                expanded = false
                            },
                            modifier = Modifier
                                .background(Color.White)
                        )
                    }
                }

                Button(
                    onClick = { expanded = true },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White
                    ),
                    shape = RoundedCornerShape(15.dp)
                    ) {
                    Text(
                        text = selectedFilter,
                        style = TextStyle(
                            color = Color.Black,
                        )
                    )

                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = null,
                        tint = Color.Black,
                    )
                }
            }

        }
    }
}