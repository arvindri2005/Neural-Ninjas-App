package com.neural.ninjas.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.neural.ninjas.R
import com.neural.ninjas.modules.NavigationItem
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationDrawer(navController: NavController, drawerState: DrawerState) {

    val navigationItem = listOf(
        NavigationItem(
            title = "Home",
            selectedIcon = R.drawable.icon_home,
            isSelected = true
        ),
        NavigationItem(
            title = "Treatment Plan",
            selectedIcon = R.drawable.icon_calendar,
        ),
        NavigationItem(
            title = "Emergency",
            selectedIcon = R.drawable.icon_emergency
        ),
        NavigationItem(
            title = "Treatment History",
            selectedIcon = R.drawable.icon_history
        ),
        NavigationItem(
            title = "Alerts",
            selectedIcon = R.drawable.icon_alert,
            route = "EmergencyScreen"
        ),
        NavigationItem(
            title = "Payment",
            selectedIcon = R.drawable.icon_payment
        ),
        NavigationItem(
            title = "Logout",
            selectedIcon = R.drawable.icon_logout
        )
    )
    val selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }
    val scope = rememberCoroutineScope()

    ModalDrawerSheet(
        drawerContainerColor = Color(0xFF121212),

    ) {
        NavHeader()
        navigationItem.forEachIndexed { index, item ->
            NavigationDrawerItem(
                label = {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text =item.title,
                            fontSize = 18.sp
                        )
                        if (index == selectedItemIndex){
                            Image(
                                painter = painterResource(id = R.drawable.icon_selected_line),
                                contentDescription = null,
                                modifier = Modifier
                                    .width(8.dp)
                                    .fillMaxHeight()
                            )
                        }
                    }
                },
                selected =item.isSelected,
                onClick = {
                    scope.launch {
                        drawerState.close()
                    }
                    navController.navigate(item.route)
                },
                icon = {

                    Icon(
                        painter = painterResource(id = item.selectedIcon),
                        contentDescription = null,
                        modifier = Modifier
                            .size(30.dp),
                        tint  = when (index) {
                            4 -> Color(0xFFe51717)
                            6 -> Color(0xFFe95065)
                            selectedItemIndex -> Color(0xFFFFE600)
                            else -> Color.White
                        }
                    )
                },
                colors = NavigationDrawerItemDefaults.colors(
                    selectedContainerColor = Color.Transparent,
                    selectedTextColor = Color(0xFFffe600),
                    unselectedContainerColor = Color.Transparent,
                    unselectedTextColor = if (index == 6){
                                               Color(0xFFe95065)
                                            }else{
                                                 Color.White
                                            }

                ))
        }
    }
}

@Composable
fun NavHeader() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, top = 20.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = painterResource(id = R.drawable.profile_image),
            contentDescription = null,
            modifier = Modifier
                .size(60.dp)
                .clip(CircleShape)
                .border(2.dp, Color(0xFF91d2e8), CircleShape))

        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .padding(start = 20.dp)
        ) {
            Text(
                text = "Arvind",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Text(
                text = "+91-7357885461",
                fontSize = 18.sp,
                color = Color.White)
        }
    }
}
