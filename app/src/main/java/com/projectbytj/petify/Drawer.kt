package com.projectbytj.petify



import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialogDefaults.containerColor
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.DrawerDefaults.containerColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController


data class drawerItem(
    val name: String,
    val color: Color,
 //   val bg: Painter,
    val dis: String,
    val route : String ,
    var faltru : String
)

@Composable
fun drawerCard(navController: NavController){
    var items = listOf<drawerItem>(
        drawerItem("DOGS" , color = Color.Black,
     //       painterResource(  R.drawable.man)  ,
            "men" , "dogsNAV"  , "MEN"),

        drawerItem("CATS" , color = Color.Black,
     //       painterResource(  R.drawable.woman)  ,
            "Women" ,"catsNAV" , "WOMEN" ),

        drawerItem("OTHERS" , color = Color.Black,
      //      painterResource(  R.drawable.baby)  ,
            "kids" , "othersNAV" , "KIDS"),

        drawerItem("List for adoption" , color = Color.Black,
     //       painterResource(  R.drawable.coupon)  ,
            "sale" , "", "")
    )

    Box(
        modifier = Modifier.fillMaxSize().padding(top= 10.dp)
        // .fillMaxHeight()
        //.width(100.dp)
    ){
        Image(painter = painterResource(R.drawable.whitebg) ,"" ,
            modifier = Modifier.fillMaxSize()
            //   .matchParentSize()
            ,contentScale = ContentScale.Crop)
        Column(
            modifier = Modifier.fillMaxSize()

        ) {


            for (item in items) {
                Box(
                    modifier = Modifier.fillMaxWidth().height(80.dp)
                        .padding(vertical = 20.dp)

                ) {

                    item.faltru = remember { mutableStateOf(false) }.toString()
                    //  Image( item.bg  , item.dis  , modifier = Modifier.padding(end = 10.dp))

                    var interactionSource = remember { MutableInteractionSource() }
                    val isSelected = remember { mutableStateOf(false) }


                    Text(item.name,
                        color = if (isSelected.value) Color.Blue else item.color,

                        fontSize = 20.sp,

                        modifier = Modifier
                            .fillMaxSize()
                            .clickable(
                                onClick= {
                                    navController.navigate(item.route)
                                    item.faltru = true.toString()
                                    isSelected.value = true
                                },
                                indication = null,
                                interactionSource = interactionSource
                            )
                            .padding(start = 20.dp)
                        ,
                        fontWeight = FontWeight.SemiBold
                    )


                }
            }
        }
    }}