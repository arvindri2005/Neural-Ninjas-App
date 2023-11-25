package com.neural.ninjas.navigation

import android.content.Context
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.neural.ninjas.screens.auth.LoginScreen
import com.neural.ninjas.screens.auth.PersonalDetailsScreen
import com.neural.ninjas.screens.auth.SignUpScreen
import com.neural.ninjas.screens.auth.TermsAndConditionScreen
import com.neural.ninjas.screens.camera.CameraAnalysisScreen
import com.neural.ninjas.screens.camera.CameraScreen
import com.neural.ninjas.screens.chat.ChatScreen
import com.neural.ninjas.screens.home.HomeScreen
import com.neural.ninjas.screens.profile.ProfileScreen
import com.neural.ninjas.screens.sidenavigation.EmergencyScreen

@Composable
fun HealthNavigation(context: Context) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = HealthScreens.LoginScreen.name){

        composable(HealthScreens.HomeScreen.name,
            enterTransition = { fadeIn(animationSpec = tween(700))},
            exitTransition = { fadeOut(animationSpec = tween(700))}
        ){
            HomeScreen(navController = navController)
        }

        composable(HealthScreens.ChatScreen.name,
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700))},
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700))}) {
            ChatScreen(navController = navController, context)
        }

        composable(HealthScreens.ProfileScreen.name){
            ProfileScreen(navController = navController)
        }

        composable(HealthScreens.CameraScreen.name,
            enterTransition = { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700))},
            exitTransition = { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700))}
        ) {
            CameraScreen(navController = navController, context)
        }

        composable(HealthScreens.CameraAnalysisScreen.name){
            CameraAnalysisScreen(navController =navController)
        }

        composable(HealthScreens.LoginScreen.name){
            LoginScreen(navController = navController, context)
        }

        composable(HealthScreens.SignUpScreen.name){
            SignUpScreen(navController = navController,context)

        }

        composable(HealthScreens.EmergencyScreen.name){
            EmergencyScreen(navController = navController)
        }

        composable(HealthScreens.PersonalDetailsScreen.name){
            PersonalDetailsScreen(navController = navController, context)
        }

        composable(HealthScreens.TermsAndConditionScreen.name){
            TermsAndConditionScreen(navController = navController)
        }

    }
}