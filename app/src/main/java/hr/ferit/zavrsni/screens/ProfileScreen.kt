package hr.ferit.zavrsni.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import hr.ferit.zavrsni.AppNavigation
import hr.ferit.zavrsni.R
import hr.ferit.zavrsni.components.ButtonComponent
import hr.ferit.zavrsni.components.Footer
import hr.ferit.zavrsni.components.ProfileImage
import hr.ferit.zavrsni.components.ProfileInfo
import hr.ferit.zavrsni.data.ProfileDataViewModel
import hr.ferit.zavrsni.data.SignUpViewModel
import hr.ferit.zavrsni.ui.theme.Blue
import hr.ferit.zavrsni.ui.theme.DarkGray
import hr.ferit.zavrsni.ui.theme.LightGray
import hr.ferit.zavrsni.ui.theme.White

@Composable
fun ProfileScreen(
    navController: NavController,
    loginViewModel: SignUpViewModel = viewModel(),
    profileDataViewModel: ProfileDataViewModel = viewModel()
) {
    val getData = profileDataViewModel.state.value
    val energyData = profileDataViewModel.energyDataViewModel.state.value

    LaunchedEffect(Unit) {
        profileDataViewModel.getProfileData()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = White)
            .padding(20.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            ProfileImage(imageResource = R.drawable.person_profile_image_icon)
        }

        ProfileInfo("Name: ${getData.name}")
        ProfileInfo("Age: ${getData.age}")
        ProfileInfo("Gender: ${getData.gender}")
        ProfileInfo("Height: ${getData.height}")
        ProfileInfo("Weight: ${getData.weight}")
        ProfileInfo("Goal: ${getData.goal}")
        ProfileInfo("Activity: ${getData.activity}")

        ProfileInfo("TDEE: ${energyData.energyData.tdee} kcal")
        ProfileInfo("Calories intake: ${energyData.energyData.goalCalories} kcal")
        ProfileInfo("Proteins: ${energyData.energyData.protein} g")
        ProfileInfo("Carbs: ${energyData.energyData.carbohydrates} g")
        ProfileInfo("Fats: ${energyData.energyData.fat} g")

        Spacer(modifier = Modifier.height(15.dp))

        Row(
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ){
            Button(
                modifier = Modifier
                    .widthIn(160.dp)
                    .heightIn(48.dp),
                contentPadding = PaddingValues(),
                colors = ButtonDefaults.buttonColors(Color.Transparent),
                onClick = {
                        navController.navigate(route = AppNavigation.EditProfileScreen.route)
                }
            ){

                Box(
                    modifier = Modifier
                        .widthIn(160.dp)
                        .heightIn(48.dp)
                        .background(
                            brush = Brush.horizontalGradient(listOf(Blue, LightGray)),
                            shape = RoundedCornerShape(50.dp)
                        ),
                    contentAlignment = Alignment.Center
                ){
                    Text(text = "Edit", fontSize = 18.sp, fontWeight = FontWeight.Bold, color = DarkGray)
                }
                Spacer(modifier = Modifier.width(8.dp))
            }
        Row {
            ButtonComponent(
                value = "Logout",
                onButtonClicked = {
                    loginViewModel.logout(navController)
                    profileDataViewModel.getProfileData()
                },
                isEnabled = true
            )
        }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Footer(navController)
    }
}









