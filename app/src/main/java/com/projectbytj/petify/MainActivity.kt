package com.projectbytj.petify

import android.os.Bundle


import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import kotlinx.coroutines.delay


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val viewModel: PageLink = viewModel()
            Nav(viewModel)

        }
    }
}

@Composable
fun startup(navController: NavController){
    var effect by remember { mutableStateOf(true) }

    LaunchedEffect(key1 = true){
        delay(1500)
        effect = false
    }
    if (effect){
        splash()
    }
    else{

        navController.navigate("mainNAV") {
            // popUpTo("mainNAV") { inclusive = true }





        }



    }

}

@Composable
fun splash(){
    Card (
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = androidx.compose.ui.Alignment.Center
        ) {
            Image(painter = painterResource(R.drawable.bg2) , "splash",
                modifier = Modifier.fillMaxSize()
                , contentScale = ContentScale.Crop )

            Box(
                modifier = Modifier
                    .align(Alignment.Center) // Center this Box within the parent
                    .background(Color.White.copy(alpha = 0.6f), shape = CircleShape) // Translucent background with rounded corners

                    .fillMaxWidth(0.5f) // Set width to 80% of the screen

            ) {
            Text(
                 "\nPetify"  , fontWeight = FontWeight.Bold,
                color = colorResource(R.color.mocha),
                fontSize = 40.sp ,
                modifier = Modifier.padding(bottom = 50.dp).
                align(Alignment.Center)
            )
        }}
        }
}