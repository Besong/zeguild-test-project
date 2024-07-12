package com.besonganong.zeguild_test_project.screens

import android.annotation.SuppressLint
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.sharp.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alexstyl.swipeablecard.Direction
import com.alexstyl.swipeablecard.ExperimentalSwipeableCardApi
import com.alexstyl.swipeablecard.rememberSwipeableCardState
import com.alexstyl.swipeablecard.swipableCard
import com.besonganong.zeguild_test_project.R
import com.besonganong.zeguild_test_project.components.CustomIcon
import com.besonganong.zeguild_test_project.components.DateNotSelected
import com.besonganong.zeguild_test_project.components.DateSelected
import com.besonganong.zeguild_test_project.components.ItemInCart
import com.besonganong.zeguild_test_project.dummydata.dates
import com.besonganong.zeguild_test_project.dummydata.products
import com.besonganong.zeguild_test_project.model.Date
import com.besonganong.zeguild_test_project.ui.theme.deepDarkGreen
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(
    modifier: Modifier
) {

    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Scaffold(

    ) {
        // Screen content

        //Main Screen
        MainScreenContent(
            modifier = modifier,
            onItemClicked = {
                showBottomSheet = true
            }
        )


        // Detail Screen
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                modifier = modifier.fillMaxSize(),
                sheetState = sheetState
            ) {
                // Sheet content

                SheetContent(modifier = modifier, count = 1, imageRes = R.drawable.apple_mango) {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet = false
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun MainScreenContent(
    modifier: Modifier,
    onItemClicked: () -> Unit
) {

    // Date selected state
    var currentDate by remember{ mutableStateOf(Date(0, " ")) }

    Column {

        LazyRow(
            modifier = modifier.padding(start = 25.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            content = {

            items(count = dates.size) {
                val date = dates[it]

                if (currentDate == date) {
                    DateSelected(
                        modifier = modifier,
                        textColor = deepDarkGreen,
                        dayofMonth = date.dayofMonth,
                        dayofWeek = date.dayofWeek,
                        textSize1 = 20,
                        textSize2 = 10,
                        dotCounts = date.savedItemsToDate
                    )
                } else {
                    DateNotSelected(
                        modifier = modifier
                            .clickable {
                                currentDate = date
                            },
                        textColor = Color.Black,
                        dayofMonth = date.dayofMonth,
                        dayofWeek = date.dayofWeek,
                        textSize1 = 12,
                        textSize2 = 7,
                        dotCounts = date.savedItemsToDate
                    )
                }
            }
        })


        // Content List
        LazyColumn(
            modifier = modifier.padding(6.dp)
        ) {
            items(count = products.entries.size, itemContent = {
                val product = products[it]
                if (product != null) {
                    Text(
                        text = "${product.first.dayofMonth} ${product.first.month} ${product.first.year}",
                        modifier = modifier.padding(10.dp),
                        fontSize = 21.sp
                    )

                    Row {
                        product.second.forEach {item ->
                            ItemInCart(count = 1, name = item.name, onItemClicked = onItemClicked, imageRes = item.image)
                        }

                    }
                }
            })


        }
    }

}



@OptIn(ExperimentalSwipeableCardApi::class)
@Composable
fun SheetContent(
    modifier: Modifier,
    count: Int,
    @DrawableRes imageRes: Int,
    onSheetClose: () -> Unit
) {


    val swipeState = rememberSwipeableCardState()
    // Track direction of a swipe
    var swipeDirectionState by remember { mutableStateOf(Direction.Down) }

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        Text(
            text = "Produit jeté ou mangé?",
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 24.sp
            ),
            color = deepDarkGreen,
            modifier = modifier.padding(top = 10.dp, start = 31.dp))

        CustomIcon(
            modifier = modifier
                .padding(start = 6.dp)
                .clickable { onSheetClose() },
            icon = Icons.Sharp.Close,
            backgroundColor = Color.LightGray,
            backgroudSize = 40,
            imageColor = Color.Black
        )
    }

    Spacer(modifier = modifier.height(20.dp))

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(14.dp),
        verticalArrangement = Arrangement.Center
    ){
        Text(
            text = "Mangue Kent bateau a murir (calibre moyen) Cote d'Ivoire",
            modifier = modifier,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp
            )
        )

        Text(
            text = "$count x La piece",
            modifier = modifier.align(Alignment.CenterHorizontally),
            fontSize = 16.sp
        )

        Spacer(modifier = modifier.height(10.dp))

        // Add animation to this image
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = "",
            modifier = modifier
                .align(Alignment.CenterHorizontally)
                .size(320.dp)
                .swipableCard(
                    state = swipeState,
                    blockedDirections = listOf(Direction.Down, Direction.Up),
                    onSwiped = { direction ->
                        swipeDirectionState = direction
                    },
                    onSwipeCancel = {
                        swipeDirectionState = Direction.Down
                    })
        )

        Spacer(modifier = modifier.height(10.dp))

        Text(
            text = "Acheté le 27/06/2024 à La Belle Vie",
            modifier = modifier.align(Alignment.CenterHorizontally),
            fontSize = 16.sp
        )

    }

    Spacer(modifier = modifier.height(30.dp))


    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ){
        Text(
            text = "Indiquer la quantité jetée",
            fontSize = 20.sp,
            modifier = modifier.padding(start = 20.dp)
        )

        Switch(
            checked = false,
            onCheckedChange = {},
            modifier = modifier.size(20.dp),
            thumbContent = {
                Icon(
                    imageVector = Icons.Outlined.Close,
                    contentDescription = "close")
            }
        )
    }

    Spacer(modifier = modifier.height(100.dp))


    Row(
        modifier = modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceAround
    ){

        when(swipeDirectionState) {
            Direction.Right -> {
                CustomIcon(
                    modifier = modifier,
                    icon = Icons.Rounded.Delete,
                    backgroundColor = Color.Red,
                    backgroudSize = 75,
                    imageColor = Color.White
                )

                CustomIcon(
                    modifier = modifier,
                    icon = Icons.Rounded.Check,
                    backgroundColor = Color.Green,
                    backgroudSize = 85,
                    imageColor = Color.White
                )
            }
            Direction.Left -> {

                CustomIcon(
                    modifier = modifier,
                    icon = Icons.Rounded.Delete,
                    backgroundColor = Color.Red,
                    backgroudSize = 85,
                    imageColor = Color.White
                )

                CustomIcon(
                    modifier = modifier,
                    icon = Icons.Rounded.Check,
                    backgroundColor = Color.Green,
                    backgroudSize = 75,
                    imageColor = Color.White
                )
            }
            else -> {
                CustomIcon(
                    modifier = modifier,
                    icon = Icons.Rounded.Delete,
                    backgroundColor = Color.Red,
                    backgroudSize = 75,
                    imageColor = Color.White
                )

                CustomIcon(
                    modifier = modifier,
                    icon = Icons.Rounded.Check,
                    backgroundColor = Color.Green,
                    backgroudSize = 75,
                    imageColor = Color.White
                )
            }
        }

    }


}


@Preview()
@Composable
fun MainScreenContentPreview() {
    MainScreenContent(
        modifier = Modifier,
        onItemClicked = {}
    )
}

@Preview()
@Composable
fun AppScreenPreview() {
    AppScreen(
        modifier = Modifier
    )
}
