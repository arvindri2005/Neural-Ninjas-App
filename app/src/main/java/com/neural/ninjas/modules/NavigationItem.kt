package com.neural.ninjas.modules

data class NavigationItem (
    val title: String,
    val selectedIcon: Int,
    val isSelected: Boolean = false,
    val route: String = "ChatScreen"
)