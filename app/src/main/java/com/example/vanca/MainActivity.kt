package com.example.vanca

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.vanca.ui.Home
import com.example.vanca.ui.Station
import com.example.vanca.ui.theme.VancaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.statusBarColor = android.graphics.Color.argb(100,0,0,0)
        setContent {
            VancaTheme {
                Home(modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = R.color.background_color))
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun VancaPreview() {
    VancaTheme {
        Home(modifier = Modifier.fillMaxSize())
        //Station(modifier = Modifier.fillMaxSize())
    }
}