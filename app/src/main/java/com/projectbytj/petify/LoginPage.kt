package com.projectbytj.petify

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import kotlinx.coroutines.delay
import java.util.jar.Attributes.Name
import kotlin.math.log


@Composable
fun signup(navController: NavController){
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var name by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(24.dp)
    ) {
        Text("Create an Account", fontSize = 28.sp, color = Color.White)
        Spacer(modifier = Modifier.height(20.dp))

        CustomTextField(value = name, label = "Full Name") { name = it }
        CustomTextField(value = email, label = "Email") { email = it }
        CustomTextField(value = password, label = "Password", isPassword = true, showPassword) { password = it }

        Spacer(modifier = Modifier.height(20.dp))
        GradientButton(text = "Sign Up") {
            signUpWithEmailAndPassword(email, password, navController, name) { success, message ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
        TextButton(onClick = { navController.navigate("signinNAV") }) {
            Text("Already have an account? Sign In", color = Color.White)
        }
    }
}


fun signUpWithEmailAndPassword(email: String, password: String ,navController: NavController , name: String ,  onResult: (Boolean, String?) -> Unit) {
    FirebaseAuth.getInstance()
        .createUserWithEmailAndPassword(email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                onResult(true, "Sign Up Successful")
                navController.navigate("signinNAV")

                val user = FirebaseAuth.getInstance().currentUser
                if (user != null) {
                    user.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())
                    user.reload()
                }
            }
            else if (email.isEmpty() || password.isEmpty()){
                onResult(false, "fields are empty")
            }
            else {
                onResult(false, task.exception?.message)
            }
        }
}



// Login

@Composable
fun signin(navController: NavController) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var showPassword by remember { mutableStateOf(false) }
    val context = LocalContext.current

    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize().padding(24.dp)
    ) {
        Text("Welcome Back", fontSize = 28.sp, color = Color.White)
        Spacer(modifier = Modifier.height(20.dp))

        CustomTextField(value = email, label = "Email") { email = it }
        CustomTextField(value = password, label = "Password", isPassword = true, showPassword) { password = it }

        Spacer(modifier = Modifier.height(20.dp))
        GradientButton(text = "Login") {
            signInWithEmailAndPassword(email, password, navController) { success, message ->
                Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            }
        }
        TextButton(onClick = { navController.navigate("signupNAV") }) {
            Text("Don't have an account? Sign Up", color = Color.White)
        }
    }
}

var login = false;

fun signInWithEmailAndPassword(email: String, password: String,navController: NavController ,  onResult: (Boolean, String?) -> Unit ) {
    FirebaseAuth.getInstance()
        .signInWithEmailAndPassword( email, password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                login = true ;
                onResult(true, "Login Successful")
                navController.navigate("profileNAV")
            }
            else if (email.isEmpty() || password.isEmpty()){
                onResult(false, "fields are empty")
            }
            else {
                onResult(false, task.exception?.message)
            }
        }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(value: String, label: String, isPassword: Boolean = false, showPassword: Boolean = false, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label , color = Color.White)  },
        visualTransformation = if (isPassword && !showPassword) PasswordVisualTransformation() else VisualTransformation.None,
        modifier = Modifier.fillMaxWidth().padding(8.dp),
        shape = RoundedCornerShape(16.dp),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color(0x56665C5B),
            unfocusedContainerColor = Color(0x56665C5B),
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White
        )
    )
}

@Composable
fun GradientButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth().height(50.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
        shape = RoundedCornerShape(16.dp)
    ) {
        Box(
            modifier = Modifier.fillMaxSize().background(
                Brush.horizontalGradient(listOf(Color(0xFF6A11CB), Color(0xFF2575FC)))
            ),
            contentAlignment = Alignment.Center
        ) {
            Text(text, color = Color.White, fontSize = 18.sp)
        }
    }
}

