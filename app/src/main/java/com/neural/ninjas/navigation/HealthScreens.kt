package com.neural.ninjas.navigation

enum class HealthScreens{
    HomeScreen,
    ChatScreen,
    ProfileScreen,
    CameraScreen,
    CameraAnalysisScreen,
    LoginScreen,
    SignUpScreen,
    TreatmentPlanScreen,
    EmergencyScreen,
    TreatmentHistoryScreen,
    AlertsScreen,
    PaymentScreen,
    PersonalDetailsScreen,
    TermsAndConditionScreen;

    companion object{
        fun fromRoute(route: String?): HealthScreens =
            when(route?.substringBefore("/")){
                HomeScreen.name -> HomeScreen
                ChatScreen.name -> ChatScreen
                ProfileScreen.name -> ProfileScreen
                CameraScreen.name -> CameraScreen
                CameraAnalysisScreen.name -> CameraAnalysisScreen
                LoginScreen.name -> LoginScreen
                SignUpScreen.name -> SignUpScreen
                TreatmentPlanScreen.name -> TreatmentPlanScreen
                EmergencyScreen.name -> EmergencyScreen
                TreatmentHistoryScreen.name -> TreatmentHistoryScreen
                AlertsScreen.name -> AlertsScreen
                PaymentScreen.name -> PaymentScreen
                PersonalDetailsScreen.name -> PersonalDetailsScreen
                TermsAndConditionScreen.name -> TermsAndConditionScreen
                else -> HomeScreen
            }
    }
}