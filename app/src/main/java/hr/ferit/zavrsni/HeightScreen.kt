package hr.ferit.zavrsni

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import hr.ferit.zavrsni.ui.theme.Blue
import java.util.Calendar

@Composable
fun HeightScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "How tall are you?",
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            color = Color.Blue,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(top = 70.dp, bottom = 150.dp)
        )

        Box(
            modifier = Modifier
                .padding(vertical = 16.dp)
                .fillMaxWidth(0.3f)
                .height(10.dp)
                .background(color = Blue)
        )

        val currentYear = Calendar.getInstance().get(Calendar.YEAR)
        var selectedYear by remember { mutableStateOf(currentYear) }

        val years = (150..200).toList()

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp),
            contentAlignment = Alignment.Center
        ) {
            LazyRow(
                modifier = Modifier.padding(horizontal = 25.dp)
            ) {
                items(years.take(100).count()) { index ->
                    val year = years[index]
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 33.dp)
                            .clickable { selectedYear = year },
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = year.toString(),
                            fontSize = 20.sp,
                            color = if (year == selectedYear) Color.Blue else Color.Black
                        )
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxWidth(0.3f)
                .padding(vertical = 16.dp)
                .height(10.dp)
                .background(color = Blue)
        )

        Button(
            onClick = {navController.navigate("WeightScreen") },
            colors = ButtonDefaults.buttonColors(containerColor = Blue),
            modifier = Modifier
                .padding(top = 130.dp)
                .height(56.dp)
                .fillMaxWidth(0.7f)
                .clip(RoundedCornerShape(8.dp)),
        ) {
            Text(
                text = "Next",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        }
    }
}