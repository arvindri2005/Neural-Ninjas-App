package com.neural.ninjas.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.neural.ninjas.R

@Composable
fun CheckBox(type: String) {
    val isChecked = remember { mutableStateOf(false) }
    val checkedText = remember { mutableStateOf(if (type == "Appointment"){
        "To Attended"
    }else{
        "Take it"
    }) }
    val circleSize = remember { mutableStateOf(20.dp) }
    val circleThickness = remember { mutableStateOf(2.dp) }
    val color = remember { mutableStateOf(Color(0xFF5d65b0)) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .padding(end = 10.dp)
            .toggleable(
                value = isChecked.value,
                role = Role.Checkbox
            )
            {
                isChecked.value = it
                if (isChecked.value) {
                    checkedText.value = if (type == "Appointment"){
                        "Attended"
                    }else{
                        "Taken"
                    }
                    circleSize.value = 20.dp
                    circleThickness.value = 2.dp
                    color.value = Color(0xFF5d65b0)
                } else {
                    checkedText.value = if (type == "Appointment"){
                        "To Attended"
                    }else{
                        "Take it"
                    }
                    circleSize.value = 20.dp
                    circleThickness.value = 2.dp
                    color.value = Color(0xff5d65b0)
                }
            }
    ) {
        Box(
            modifier = Modifier
                .clip(CircleShape)
                .size(circleSize.value)
                .background(color.value)
                .padding(circleThickness.value)
                .clip(CircleShape)
                .background(color.value),
            contentAlignment = Alignment.Center) {
            if (isChecked.value) {
                Icon(
                    painter = painterResource(id = R.drawable.icon_check),
                    contentDescription = "")
            }
        }
        Text(
            text = checkedText.value,
            color = Color(0xff9299c9),
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(start = 5.dp)
        )
    }
}