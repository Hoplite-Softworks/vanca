package com.example.vanca

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.vanca.ui.Home
import com.example.vanca.ui.Station
import com.example.vanca.ui.theme.VancaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VancaTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Home(modifier = Modifier.padding(innerPadding))
                    //Station(modifier = Modifier.padding(innerPadding))
                }
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