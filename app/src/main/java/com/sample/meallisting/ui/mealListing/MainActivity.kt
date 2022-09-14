package com.sample.meallisting.ui.mealListing

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.sample.meallisting.R
import com.sample.meallisting.domain.model.Meal
import com.sample.meallisting.ui.mealListing.ui.theme.MealListingTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MealListingViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getMealByCategory()

        setContent {
            MealListingTheme {
                Column(modifier = Modifier.fillMaxSize()) {
                    FetchMeal(viewModel)
                }
            }
        }
    }
}

@Composable
fun FetchMeal(viewModel: MealListingViewModel) {

    val data = viewModel.mealLiveData.collectAsState()

    data.value.let {

        if(it.loading) {
            DisplayProgressBar()
        }

        if(!it.error.isNullOrEmpty()) {
            Text(text = it.error)
        }

        if(!it.mealList.isNullOrEmpty()) {
            MealList(mealList = it.mealList)
        }

    }
}

@Composable
fun DisplayProgressBar() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator(modifier = Modifier.size(48.dp))
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = "Loading", fontSize = 16.sp, color = Color.Red)
    }
}

@Composable
fun MealList(mealList: List<Meal>) {

    LazyColumn {
        items(
            count = mealList.size,
            key = { mealList[it].id },
            itemContent = { index ->
                MealCard(meal = mealList[index])
                Divider(color = Color.LightGray)

            }
        )
    }
}

@Composable
fun MealCard(meal: Meal) {

    val context = LocalContext.current

    Row(
        modifier = Modifier
            .padding(all = 16.dp)
            .fillMaxWidth()
            .clickable {
                Toast
                    .makeText(context, meal.mealName, Toast.LENGTH_LONG)
                    .show()
            },
        verticalAlignment = Alignment.CenterVertically
    ) {

        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(meal.mealThumb)
                .build(),
            placeholder = painterResource(R.drawable.ic_launcher_foreground),
            contentDescription = meal.mealName,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(48.dp)
                .clip(RoundedCornerShape(4.dp))
        )

        Spacer(modifier = Modifier.width(12.dp))

        Column {
            Text(text = meal.mealName, fontSize = 18.sp, maxLines = 1)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = "#${meal.id}", fontSize = 14.sp)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MealListingTheme {
        // DisplayProgressBar()
        MealCard(
            meal = Meal(
                "12345",
                "Chicken Tikka",
                ""
            )
        )
    }
}