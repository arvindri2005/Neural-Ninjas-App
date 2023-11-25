package com.neural.ninjas.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neural.ninjas.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavigationTopBar(drawerState: DrawerState) {
    val scope= rememberCoroutineScope()
    Row(
        horizontalArrangement = Arrangement.Start,
        verticalAlignment = Alignment.CenterVertically
    ) {
        IconButton(
            onClick = {
                scope.launch {
                    drawerState.open()
                }
            }
        ){
            Icon(
                painter = painterResource(id = R.drawable.icon_menu),
                contentDescription = "Menu",
                modifier = Modifier
                    .size(24.dp))

        }
        Text(
            text ="Health+",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = Color(0xFFffe600),
            modifier = Modifier
                .padding(start= 5.dp))
    }
}