package com.example.testapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.testapp.ui.theme.TestAppTheme
import com.google.accompanist.systemuicontroller.rememberSystemUiController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContent {
            TestAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(color = MaterialTheme.colors.primary) {
                   RotateIcon()
                }
            }
        }
    }
}

@Composable
fun RotateIcon() {
var icon=remember{
    mutableStateOf(Icons.Default.Menu)
}
    var currentRotation by remember { mutableStateOf(0f) }

    val rotation = remember { Animatable(currentRotation) }

   LaunchedEffect(key1 = true, block = {
       rotation.animateTo(
           targetValue = currentRotation + 360f,
           animationSpec = infiniteRepeatable(
               animation = tween(3000, easing = LinearEasing),
               repeatMode = RepeatMode.Restart
           )
       ) {
           currentRotation = value
       }
   })

   Icon(imageVector = Icons.Default.Menu, contentDescription = "Icon Image",modifier=Modifier.rotate(rotation.value))
}

@Composable
fun TopAppBar() {
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(MaterialTheme.colors.primary)
    androidx.compose.material.TopAppBar(
        modifier = Modifier.padding(0.dp),
    ) {

    }

}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    TestAppTheme {

    }
}