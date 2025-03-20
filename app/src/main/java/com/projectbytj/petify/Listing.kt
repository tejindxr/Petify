package com.projectbytj.petify


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


public final data class listing(
    val name: String,
    val pic: Int,
    val breed: String,
    val Price: Int,
    var age: Int?
)

class PageLink : ViewModel() {
    private val _selectedListing = MutableStateFlow<listing?>(null)
    val selectedProduct: StateFlow<listing?> = _selectedListing

    fun setProduct(listing: listing) {
        _selectedListing.value = listing
    }
}

@Composable
fun Dogs(navController: NavController , viewModel: PageLink) {

    val dog= listOf(
        listing(name = "Bruno", pic = R.drawable.adp1, breed = "Labrador Retriever", Price = 8000, age = 2),
        listing(name = "Max", pic = R.drawable.adp2, breed = "German Shepherd", Price = 12000, age = 3),
        listing(name = "Bella", pic = R.drawable.adp3, breed = "Golden Retriever", Price = 15000, age = 1),
        listing(name = "Rocky", pic = R.drawable.adp4, breed = "Beagle", Price = 7000, age = 4),
        listing(name = "Charlie", pic = R.drawable.adp5, breed = "Pomeranian", Price = 10000, age = 2)
    )




    Column(
        modifier = Modifier.padding(top = 20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        heading("Dogs")


        LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(8.dp)) {
            items(dog) { item ->


                productCard(item, navController, viewModel)
            }
        }
    }


}



@Composable
fun Cats(navController: NavController, viewModel: PageLink){



    val cat = listOf(
        listing(name = "Luna", pic = R.drawable.adp1, breed = "Persian", Price = 9000, age = 2),
        listing(name = "Milo", pic = R.drawable.adp2, breed = "Siamese", Price = 8000, age = 1),
        listing(name = "Simba", pic = R.drawable.adp3, breed = "Maine Coon", Price = 15000, age = 3),
        listing(name = "Chloe", pic = R.drawable.adp4, breed = "Bengal", Price = 13000, age = 2),
        listing(name = "Oliver", pic = R.drawable.adp5, breed = "Ragdoll", Price = 11000, age = 4)
    )




    Column(
        modifier = Modifier.padding(top = 20.dp),
        verticalArrangement = Arrangement.Center
    ) {

        heading("Cats")

        LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(8.dp)) {
            items(cat) { item ->


                productCard(item, navController, viewModel)
            }
        }
    }

}



@Composable
fun Others(navController: NavController , viewModel: PageLink){

    val others = listOf(
        listing(name = "Coco", pic = R.drawable.adp1, breed = "Rabbit", Price = 2500, age = 1),
        listing(name = "Daisy", pic = R.drawable.adp2, breed = "Guinea Pig", Price = 3000, age = 2),
        listing(name = "Jack", pic = R.drawable.adp3, breed = "Hamster", Price = 1500, age = 1),
        listing(name = "Polly", pic = R.drawable.adp4, breed = "Parrot", Price = 5000, age = 3),
        listing(name = "Mimi", pic = R.drawable.adp5, breed = "Tortoise", Price = 7000, age = 5)
    )



    Column(
        modifier = Modifier.padding(top = 20.dp),
        verticalArrangement = Arrangement.Center
    ) {
        heading("Other Dmestic Pets")


        LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(8.dp)) {
            items(others) { item ->


                productCard(item, navController, viewModel)
            }
        }
    }

}

@Composable
fun productCard(listing: listing, navController: NavController, viewModel: PageLink) {
    Card(
        modifier = Modifier
            .padding(8.dp) // Reduced padding for better grid spacing
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .shadow(6.dp, shape = RoundedCornerShape(12.dp))
            .clickable {
                viewModel.setProduct(listing)
                navController.navigate("info_pageNAV")
            },
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {

        Column(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(4.dp) // Tighter spacing for grid
        ) {
            Image(
                painter = painterResource(listing.pic),
                contentDescription = listing.name,
                modifier = Modifier
                    .height(150.dp) // Adjusted size for grid
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
            Text(
                text = listing.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
            Text(
                text = listing.breed,
                style = MaterialTheme.typography.bodySmall,
                color = Color.Gray,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            Text(
                text = "₹${listing.Price}",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.SemiBold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
fun heading(name : String){
    Text(name, fontSize = 20.sp, fontWeight = FontWeight.Bold ,
        textAlign = TextAlign.Center,
        modifier = Modifier.fillMaxWidth() ,
        color = Color.White)


}
