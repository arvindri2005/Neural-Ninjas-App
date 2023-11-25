package com.neural.ninjas.screens.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Divider
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.neural.ninjas.modules.Conditions

val terms = listOf<Conditions>(
    Conditions(
        id = 1,
        heading = "Data Use Consent",
        description = " By using this app and sharing medical information via the chatbot, you agree to the app's acquisition, storage, and use of this data solely for facilitating healthcare services within the app."
    ),
    Conditions(
        id = 2,
        heading = "Privacy Assurance",
        description = "We prioritize your data security. Your medical information is confidential, accessed only by authorized personnel for app functionality and user support. We won't share it without your explicit consent, except as required by law or for healthcare purposes."
    ),
    Conditions(
        id = 3,
        heading = "User Accountability",
        description = "You're responsible for the accuracy of shared medical data. We're not liable for unauthorized access resulting from your actions or for the accuracy and reliability of user-provided information within the app."
    ),
)

@Composable
fun TermsAndConditionScreen(navController: NavController) {

    val scrollState = rememberLazyListState()

    Scaffold(
        bottomBar = {
            BottomButton(navController)
        }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {

            Text(
                text ="Terms and Condition",
                style = TextStyle(
                    color = Color(0xFF494949),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier
                    .padding(start = 16.dp, top = 16.dp)
            )

            Text(
                text ="Last Updated on 1st Jan 2021",
                style = TextStyle(
                    color = Color(0xFF7c7c7c),
                    fontSize = 18.sp,
                ),
                modifier = Modifier
                    .padding(start = 16.dp, top = 2.dp, bottom = 5.dp)
            )

            Divider(
                thickness = 2.dp,
                color = Color(0xFFd9d9d9)
            )

            LazyColumn(
                state = scrollState,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ){
                items(terms) {
                    Clause(it)
                }
            }




        }
    }

}

@Composable
fun Clause(condition: Conditions ) {


    Column(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Text(
            text = "${condition.id}. Clause ${condition.id}",
            style = TextStyle(
                color = Color(0xFF616161),
                fontSize = 22.sp,
                fontWeight = FontWeight.SemiBold,
            ),
        )
        Text(
            text = condition.heading,
            style = TextStyle(
                color = Color(0xFF494949),
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
            ),
        )
        Text(
            text = condition.description,
            style = TextStyle(
                color = Color(0xFF494949),
                fontSize = 18.sp,
            ),
            modifier = Modifier
                .padding(top = 5.dp, bottom = 20.dp)
        )



    }

}

@Composable
fun BottomButton(navController: NavController){
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 20.dp, bottom = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            modifier = Modifier,
            onClick = {
                     navController.navigate("PersonalDetailsScreen")
            },
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color.White,
                contentColor = Color(0xFF3c5bb6),
                disabledBackgroundColor = Color.Transparent,
                disabledContentColor = Color(0xFF3c5bb6),
            ),
            border = BorderStroke(2.dp, Color.Black),
            shape = RoundedCornerShape(50.dp)
        ) {
            Text(
                text = "Agree & Continue",
                modifier = Modifier
                    .background(Color.Transparent)
            )

        }

    }
}
