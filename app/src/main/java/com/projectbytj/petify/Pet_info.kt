package com.projectbytj.petify

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.BiasAlignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay

@Composable
fun info_page(navController: NavController, viewModel: PageLink) {

    val Shoe by viewModel.selectedProduct.collectAsState()

    Shoe?.let { pet ->  // Use safe call to prevent null crashes
        Box(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize().clip(RoundedCornerShape(10.dp))
                .background(Color.White)

        ) {

            LazyColumn(modifier = Modifier.fillMaxSize()
            ) {

                item {
                    // Shoe Image
                    Image(
                        painter = painterResource(pet.pic),  // Ensure 'pic' is a Painter
                        contentDescription = "Shoe Image",
                        modifier = Modifier
                            .fillMaxHeight(0.7f)
                            .fillMaxWidth()
                            .align(Alignment.Center)
                            .padding(top = 10.dp),
                        contentScale = ContentScale.Crop
                    )

                    Text(
                        text = pet.name,
                        modifier = Modifier.padding(top = 10.dp, start = 10.dp),
                        fontSize = 25.sp,
                        fontWeight = FontWeight.SemiBold
                    )

                    Text(
                        text = pet.breed,
                        modifier = Modifier.padding(start = 10.dp),
                        fontSize = 15.sp,
                        color = Color.Gray
                    )

                    Text(
                        text = pet.place,
                        modifier = Modifier.padding(top = 10.dp, start = 10.dp),
                        fontSize = 25.sp ,
                        fontWeight = FontWeight.Bold

                    )

                    Text(
                        text = "Inclusive all Taxes \n(Also include all applicable duties)",
                        modifier = Modifier.padding(top = 10.dp, start = 10.dp),
                        fontSize = 10.sp,
                        color = Color.Gray
                    )

                    Text(
                        text = "Description: This is a demo project, so the description is not available.",
                        modifier = Modifier.padding(10.dp),
                        fontSize = 10.sp,
                    )



                    Text(
                        text = "Reviews",
                        modifier = Modifier.padding(top = 10.dp, start = 10.dp),
                        fontSize = 15.sp,
                        color = Color.Gray
                    )
                }
            }


            // Back Button
            IconButton(
                onClick = { navController.navigate("menNAV") },
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(2.dp)
            ) {
                Icon(Icons.Filled.ArrowBack, contentDescription = "Back")
            }
             // Cart Button






            Button(
                onClick ={
                    if(login){
                        navController.navigate("thankyouNAV")
                }
                    else{
                        navController.navigate("signinNAV")
                }
                },
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth(0.85f)
                    .padding(bottom = 10.dp),
                shape = RectangleShape,
                colors = ButtonDefaults.buttonColors(Color.Black),
                border = ButtonDefaults.outlinedButtonBorder
            ) {
                Text("Call the Owner", color = Color.White)
            }
        }



    } ?: run {
        // Show this if product is null
        Text(
            text = "No product selected",
            modifier = Modifier.padding(16.dp),
            fontSize = 20.sp
        )
    }
}




