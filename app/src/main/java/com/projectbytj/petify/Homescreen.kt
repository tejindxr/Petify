package com.projectbytj.petify


import android.graphics.Typeface
import android.graphics.fonts.FontStyle
import android.icu.text.ListFormatter
import androidx.compose.foundation.Image
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch


@Composable
fun homescreen(navController: NavController , viewModel: PageLink): Unit {

    val imageList = listOf(
        R.drawable.home1,
        R.drawable.scroll1,
        R.drawable.scroll3,
        R.drawable.scroll2
    )
    var selectedIndex by remember { mutableStateOf(0) }
    val lazyListState = rememberLazyListState()
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(6.dp)
            .clip(shape = RoundedCornerShape(16.dp))
            .verticalScroll(rememberScrollState()) // Enables scrolling


    ) {

//horizontal scroll
        /*
            Box(
                modifier = Modifier.fillMaxHeight(0.3f)
            ) {
                LazyRow(
                    modifier = Modifier.fillMaxHeight()
                ) {
                    items(imageList.size) { index ->
                        Image(
                            painter = painterResource(id = imageList[index]),
                            contentDescription = "Scrollable Image",
                            modifier = Modifier
                                .height(350.dp) // Set a fixed height
                                 ,contentScale = ContentScale.Crop
                        )
                    }
                }
            }

         */



        Box(
            modifier = Modifier.fillMaxHeight(0.3f).clip(shape = RoundedCornerShape(16.dp))
                .padding(top =  5.dp)
        ) {
            LazyRow(
                state = lazyListState,
                modifier = Modifier.fillMaxHeight()
            ) {
                items(imageList.size) { index ->
                    Image(
                        painter = painterResource(id = imageList[index]),
                        contentDescription = "Scrollable Image",
                        modifier = Modifier
                            .height(350.dp),
                        contentScale = ContentScale.Crop
                    )
                }
            }

            IconButton(
                onClick = {
                    if (selectedIndex < imageList.size - 1) {
                        selectedIndex++
                        coroutineScope.launch {
                            lazyListState.animateScrollToItem(index = selectedIndex)
                        }
                    }
                },
                modifier = Modifier
                    .align(Alignment.CenterEnd)
                    .background(Color.Black.copy(alpha = 0.5f), shape = CircleShape)
            ) {
                androidx.compose.material3.Icon(
                    imageVector = Icons.Filled.ArrowForward,  // or Icons.Default.ArrowForward
                    contentDescription = "Next",
                    tint = Color.White
                )

            }


            //     Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()
                , verticalAlignment = Alignment.Bottom
            ) {
                imageList.forEachIndexed { index, _ ->
                    Box(
                        modifier = Modifier

                            .size(8.dp)
                            .background(
                                if (index == selectedIndex) Color.Black else Color.Gray,
                                shape = CircleShape
                            )
                            .padding(4.dp)
                    )
                }
            }
        }



                val homepage = listOf(
                    listing(name = "Coco", pic = R.drawable.adp1, breed = "Rabbit", Price = 2500, age = 1),
                    listing(name = "Pikachu", pic = R.drawable.adp2, breed = "Kitty", Price = 10000, age = 2),
                )


        Row {
            for (listing in homepage) {
                Box(
                    modifier = Modifier.fillMaxHeight(0.2f).weight(0.5f)
                ) {
                    productCard(listing, navController, viewModel)

                }
            }
        }


        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            Image(
                painter = painterResource(R.drawable.home2),
                contentDescription = "home1",
                modifier = Modifier.fillMaxSize(),
                contentScale = ContentScale.Crop
            )

            Box(
                modifier = Modifier
                    .align(Alignment.Center) // Center this Box within the parent
                    .background(Color.White.copy(alpha = 0.6f), shape = RoundedCornerShape(16.dp)) // Translucent background with rounded corners
                    .padding(16.dp)
                    .fillMaxWidth(0.8f) // Set width to 80% of the screen
                    .height(250.dp)
            ) {
                Text(
                    text = "Over 100 happy pets\nare being adopted in\nevery hour",
                    color = Color.Black,
                    fontSize = 24.sp, // Adjusted for better readability
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Serif,
                    modifier = Modifier.align(Alignment.Center) // Center text inside the box
                )
            }
        }




        // this is the footer of the front page .
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFF0A1B4E))

        ){
            Text("Contact Us \uD83D\uDCDE\n" +
                    "\uD83D\uDCCD Address: XYZ Tower, 4th Floor, MG Road, Bangalore, India - 560001\n\n" +
                    "\uD83D\uDCE7 Email: support@petify.com\n\n" +
                    "\uD83D\uDCDE Customer Support: +91 98765 43210\n\n" +
                    "\n" +
                    "Follow Us \uD83D\uDCF2\n\n" +
                    "\uD83C\uDF10 Website: www.petify.com\n\n" +
                    "\uD83D\uDCF7 Instagram: @Petify\n\n" +
                    "\uD83D\uDCD8 Facebook: Petify Official\n\n" +
                    "\uD83D\uDC26 Twitter (X): @Petify\n\n" +
                    "\n" +
                    "© 2025 Shoebox App. All rights reserved."

                , color = Color.White , fontSize = 8.sp ,
                fontStyle = androidx.compose.ui.text.font.FontStyle.Normal ,
                // fontFamily = FontFamily(Typeface.SERIF) ,
                modifier = Modifier.padding(10.dp)
            )

        }









        /*






                    Image(
                        painter = painterResource(R.drawable.home3),
                        "home1",
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop
                    )



         */

        }

    }

