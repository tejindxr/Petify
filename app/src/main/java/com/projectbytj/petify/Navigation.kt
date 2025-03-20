package com.projectbytj.petify


import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

data class  destination(
    val route : String ,
    val title : String
)


@Composable
fun Nav(viewModel: PageLink){


    val tj = rememberNavController()
    NavHost(navController = tj , startDestination = "mainNAV"){
        composable("mainNAV"){
            TopBar(tj) {
                homescreen(tj , viewModel)
            }
        }
        composable("dogsNAV"){
            TopBar(tj){
                Dogs(tj,  viewModel )
            }
        }
        composable("catsNAV"){
            TopBar(tj){
                Cats(tj ,  viewModel )
            }
        }
        composable("othersNAV"){
            TopBar(tj){
                Others(tj ,  viewModel  )
            }
        }
        composable("info_pageNAV"){
            TopBar(tj){
                info_page(tj ,  viewModel)
            }
        }
        composable("profileNAV"){
            TopBar(tj){
                ProfileScreen(tj)
            }
        }
        composable("thankyouNAV") {
            TopBar(tj) {
                ConfirmationScreen(tj)
            }
        }
    }

    startup(navController = tj)



}