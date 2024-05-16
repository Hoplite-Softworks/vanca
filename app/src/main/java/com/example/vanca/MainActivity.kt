package com.example.vanca

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.vanca.ui.theme.VancaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        window.statusBarColor = android.graphics.Color.argb(100,0,0,0)
        setContent {
            VancaTheme {
                VancaApp()
            }
        }
    }
}

/*@Preview(showBackground = true)
@Composable
fun VancaPreview() {
    VancaTheme {
        Home(modifier = Modifier.fillMaxSize())
        //Station(modifier = Modifier.fillMaxSize())
    }
}*/