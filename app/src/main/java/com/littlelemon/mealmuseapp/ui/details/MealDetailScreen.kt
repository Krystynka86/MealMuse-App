package com.littlelemon.mealmuseapp.ui.details

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import coil.compose.AsyncImage
import com.littlelemon.model.response.MealResponse
import java.lang.Float.min

@Composable
fun MealDetailScreen(meal: MealResponse?) {
    val scrollState = rememberLazyListState()
    val offset = min(
        1f,
        1 - (
            scrollState.firstVisibleItemScrollOffset / 600f +
                scrollState.firstVisibleItemIndex
            ),
    ) // shrink image on scroll, collapsing toolbar on LazyColumn
    val size by animateDpAsState(targetValue = max(100.dp, 140.dp * offset))
   /* var profilePicturesState by remember { mutableStateOf(MealProfilePicturesState.Normal) }
    val transition = updateTransition(targetState = profilePicturesState, label = "")
    val imageSizeDp by transition.animateDp(targetValueByState = { it.size }, label = "")
    val color by transition.animateColor(targetValueByState = { it.color }, label = "")
    val widthSize by transition.animateDp(targetValueByState = { it.borderWidth }, label = "")*/

    Surface(color = MaterialTheme.colorScheme.background) {
        Column {
            Surface(shadowElevation = 4.dp) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Card(
                        modifier = Modifier.padding(16.dp),
                        shape = CircleShape,
                        border = BorderStroke(width = 2.dp, color = Color.Green),
                    ) {
                        Box(
                            modifier = Modifier
                                .size(size)
                                .clip(CircleShape),
                        ) {
                            AsyncImage(
                                model = meal?.imageUrl,
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize(),
                            )
                        }
                    }

                    Text(
                        meal?.name ?: "default name",
                        modifier = Modifier
                            .padding(16.dp)
                            .align(Alignment.CenterVertically),
                    )
                }
            }
            val dummyContentList = (0..100).map { it.toString() }
            LazyColumn(state = scrollState) {
                items(dummyContentList) { dummyItem ->
                    Text(text = dummyItem, modifier = Modifier.padding(24.dp))
                }
            }
            /*Button(
                modifier = Modifier
                    .padding(16.dp),
                onClick = {
                    profilePicturesState = if (profilePicturesState == MealProfilePicturesState.Normal) {
                        MealProfilePicturesState.Expanded
                    } else {
                        MealProfilePicturesState.Normal
                    }
                },
            ) {
                Text("Change state of meal profile picture")
            }*/
        }
    }
}

/*enum class MealProfilePicturesState(val color: Color, val size: Dp, val borderWidth: Dp) {
    Normal(Color.Magenta, 120.dp, 8.dp),
    Expanded(Color.Green, 200.dp, 24.dp),
}*/
