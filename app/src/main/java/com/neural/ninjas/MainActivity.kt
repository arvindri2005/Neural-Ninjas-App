package com.neural.ninjas

import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.neural.ninjas.navigation.HealthNavigation
import com.neural.ninjas.ui.theme.HealthTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(!hasRequiredPermission()){
            ActivityCompat.requestPermissions(this,CAMERAX_PERMISSIONS,0)
        }
        setContent {
            MyApp {
                HealthNavigation(this)
            }
        }
    }


    private fun hasRequiredPermission():Boolean{
        return CAMERAX_PERMISSIONS.all {
            ContextCompat.checkSelfPermission(
                applicationContext,
                it
            ) == PackageManager.PERMISSION_GRANTED
        }
    }

    companion object{
        private val CAMERAX_PERMISSIONS = arrayOf(
            android.Manifest.permission.CAMERA,
            android.Manifest.permission.RECORD_AUDIO
        )
    }
}

@Composable
fun MyApp(content:  @Composable () -> Unit) {
    HealthTheme {
        content()
    }
}


