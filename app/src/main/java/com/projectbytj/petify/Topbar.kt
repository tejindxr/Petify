package com.projectbytj.petify

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBars
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.TopAppBar
import androidx.compose.material3.DrawerDefaults
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
@Composable
fun TopBar(navController: NavController, content: @Composable () -> Unit) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    val bottomPadding = WindowInsets.navigationBars.asPaddingValues().calculateBottomPadding()
    val topPadding = WindowInsets.statusBars.asPaddingValues().calculateTopPadding() // Get dynamic top padding

    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                modifier = Modifier.fillMaxWidth(0.65f),
                drawerContainerColor = Color.Black
            ) {
                drawerCard(navController)
            }
        }
    ) {
        Scaffold(
            topBar = {
                CustomTopAppBar(drawerState, scope, navController)
            }
        ) { padding-> padding
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 50.dp ,bottom = bottomPadding)
            ) {
                Image(
                    painter = painterResource(R.drawable.bg2),
                    contentDescription = "splash",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.Crop
                )
                content()
            }
        }
    }
}
@Composable
fun CustomTopAppBar(drawerState: DrawerState, scope: CoroutineScope, navController: NavController) {
    TopAppBar(
        backgroundColor = Color.White,
        contentColor = Color.Black,
        modifier = Modifier.fillMaxWidth(),

        title = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Navigation Icon (Menu) - Left-aligned
                IconButton(
                    onClick = { scope.launch { drawerState.open() } },
                    modifier = Modifier.weight(0.8f) // Takes 1 part of available space
                ) {
                    Icon(painter = painterResource(R.drawable.menu), contentDescription = "Menu")
                }

                // Center Title "Petify"
                Box(
                    modifier = Modifier
                        .weight(3f) // Takes 2 parts of space (centers better)
                        .background(Color.White, shape = RoundedCornerShape(12.dp))
                        .padding(vertical = 6.dp, horizontal = 20.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "Petify",
                        modifier = Modifier.clickable(
                            onClick = { navController.navigate("mainNAV") },
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        ),
                        fontSize = 24.sp,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black
                    )
                }

                // Action Icon (Profile) - Right-aligned
                IconButton(
                    onClick = { navController.navigate("profileNAV") },
                    modifier = Modifier.weight(0.8f) // Takes 1 part of available space
                ) {
                    Icon(painter = painterResource(R.drawable.user), contentDescription = "Profile")
                }
            }
        }
    )
}
