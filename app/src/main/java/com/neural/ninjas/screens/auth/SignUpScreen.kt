package com.neural.ninjas.screens.auth

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.neural.ninjas.R
import com.neural.ninjas.modules.SignUp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpScreen(
    navController: NavController, context: Context
) {
    val name = remember{
        mutableStateOf("")
    }
    val email = remember{
        mutableStateOf("")
    }
    val password = remember {
        mutableStateOf("")
    }
    val scrollState = rememberScrollState()

    val passwordVisibility = remember { mutableStateOf(false) }
    val emailFocusRequester = remember { FocusRequester() }
    val passwordFocusRequester = remember { FocusRequester() }

    val gradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFF3c3b3d), Color(0xFF1e1b1e))
    )
    val buttonGradient = Brush.horizontalGradient(
        colors = listOf(Color(0xFFB2634A), Color(0xFFFFE600))
    )

    val isLoading = remember{
        mutableStateOf(false)
    }


    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        bottomBar = {
            BottomBar()
        },
        topBar = {
            TopBar()
        }
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF151316))
                .padding(it)
                .verticalScroll(scrollState),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome",
                color = Color.White,
                fontSize = 40.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier
                    .padding(bottom = 30.dp)
            )


            //Name
            OutlinedTextField(
                value =name.value ,
                onValueChange ={ input->
                    name.value = input
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 30.dp, end = 30.dp)
                    .focusRequester(emailFocusRequester)
                    .clip(RoundedCornerShape(10.dp))
                    .background(gradient),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color.Transparent,
                    textColor = Color.White,
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_username),
                        contentDescription = null)
                },
                shape = RoundedCornerShape(10.dp),
                placeholder = {
                    Text(text = "Name")
                },
                singleLine = true,
                keyboardActions = KeyboardActions(onNext = { passwordFocusRequester.requestFocus() }),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)

            )

            //Email
            OutlinedTextField(
                value =email.value ,
                onValueChange ={ input->
                    email.value = input
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 30.dp, end = 30.dp)
                    .focusRequester(emailFocusRequester)
                    .clip(RoundedCornerShape(10.dp))
                    .background(gradient),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color.Transparent,
                    textColor = Color.White,
                ),
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_email),
                        contentDescription = null)
                },
                shape = RoundedCornerShape(10.dp),
                placeholder = {
                    Text(text = "Email")
                },
                singleLine = true,
                keyboardActions = KeyboardActions(onNext = { passwordFocusRequester.requestFocus() }),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next)

            )

            //Password
            OutlinedTextField(
                value =password.value ,
                onValueChange ={input->
                    password.value = input
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp, start = 30.dp, end = 30.dp)
                    .focusRequester(passwordFocusRequester)
                    .clip(RoundedCornerShape(10.dp))
                    .background(gradient),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    containerColor = Color.Transparent,
                    textColor = Color.White
                ),
                shape = RoundedCornerShape(10.dp),
                placeholder = {
                    Text(text = "Password")
                },
                leadingIcon = {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_password),
                        contentDescription = null)
                },
                trailingIcon = {
                    IconButton(onClick = {
                        passwordVisibility.value = !passwordVisibility.value
                    }) {
                        Icon(painter = painterResource(
                            id = if (!passwordVisibility.value) {
                                R.drawable.icon_hide
                            } else {
                                R.drawable.icon_hide
                            }
                        ),
                            contentDescription = null )
                    }
                },
                visualTransformation = if(passwordVisibility.value){
                    VisualTransformation.None}else {
                    PasswordVisualTransformation()
                },
                maxLines = 1,
                singleLine = true,
                keyboardActions = KeyboardActions(onDone = {
                    createUser(name.value.trim(),email.value.trim(),password.value.trim(), context, navController) }),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
            )


            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 30.dp, vertical = 4.dp),
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = "Forgot Password?",
                    fontSize = 12.sp,
                    color = Color(0xFFA4A4A4)
                )
            }

            //Login Button
            Button(
                onClick = {
                    isLoading.value= true
                    hideKeyboard(context)
                    createUser(name.value.trim(),email.value.trim(),password.value.trim(), context, navController)
                },
                colors= ButtonDefaults.buttonColors(
                    containerColor = Color.Transparent,
                ),
                contentPadding = PaddingValues(horizontal = 10.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp, start = 30.dp, end = 30.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(buttonGradient),
                shape = RoundedCornerShape(15.dp)

            ) {
                if (isLoading.value){
                    CircularProgressIndicator(color = Color.White)
                }
                else{
                    Text(
                        text = "Sign Up",
                        color = Color.White,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            //or continue with

            Row(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, start = 30.dp, end = 30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically){

                Image(
                    painter = painterResource(id = R.drawable.line),
                    contentDescription = null )

                Text(
                    text = "or continue with",
                    color = Color.White,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                )

                Image(
                    painter = painterResource(id = R.drawable.line),
                    contentDescription = null,
                    modifier = Modifier
                        .graphicsLayer { rotationZ = 180f })
            }




            Row (
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 60.dp, vertical = 20.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OtherLoginOptions(R.drawable.icon_google)
                OtherLoginOptions(R.drawable.icon_apple)
                OtherLoginOptions(R.drawable.icon_facebook)
            }

        }
    }

}

@SuppressLint("ServiceCast")
fun hideKeyboard(context: Context) {
    val inputMethodManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    val currentFocusedView = (context as Activity).currentFocus
    currentFocusedView?.let {
        inputMethodManager.hideSoftInputFromWindow(it.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }
}

fun createUser(name: String, email:String, password:String, context: Context, navController:NavController){
    if (email.isNotEmpty()&&password.isNotEmpty()){
        val signUpData = SignUp(name,email,password, pic = "")
//        CoroutineScope(Dispatchers.IO).launch {
//            val response = signUpApi.signUp(signUpData)
//            if (response._id.isNotEmpty()){
//                withContext(Dispatchers.Main) {
//                    Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
//                    navController.navigate("HomeScreen")
//                }
//            }
//            else {
//                Toast.makeText(context,"Some error occurred",Toast.LENGTH_SHORT).show()
//            }
//        }

        CoroutineScope(Dispatchers.Main).launch {
            delay(2000)
            Toast.makeText(context, "Login successful", Toast.LENGTH_SHORT).show()
            navController.navigate("TermsAndConditionScreen")
        }
    }
    else{
        Toast.makeText(context,"Enter all Details",Toast.LENGTH_SHORT).show()
    }
}
