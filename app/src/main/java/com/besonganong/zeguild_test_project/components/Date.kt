package com.besonganong.zeguild_test_project.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.besonganong.zeguild_test_project.ui.theme.deepDarkGreen

@Composable
fun DateSelected(
    modifier: Modifier,
    textColor: Color,
    dayofMonth: Int,
    dayofWeek: String,
    textSize1: Int,
    textSize2: Int,
    dotCounts: Int
){
    Column(
        modifier = modifier
            .size(width = 50.dp, height = 75.dp)
            .padding(5.dp)
            .background(
                color = deepDarkGreen.copy(alpha = 0.3f),
                shape = RoundedCornerShape(15.dp)
            )
            .padding(5.dp)
            .wrapContentSize(Alignment.Center),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "$dayofMonth",
            style = TextStyle(
                fontFamily = FontFamily.Monospace,
                fontSize = textSize1.sp,
                color = textColor
            ),
            textAlign = TextAlign.Center
        )

        Text(
            text = "$dayofWeek",
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontSize = textSize2.sp,
                color = textColor),
            textAlign = TextAlign.Center
        )

        if (dotCounts >= 1) {
            Row {
                for (i in 1..dotCounts){
                    ItemsCountIndicatorPerDate(modifier = modifier, size = 3)
                }
            }

        }

    }

}
@Composable
fun DateNotSelected(
    modifier: Modifier,
    textColor: Color,
    dayofMonth: Int,
    dayofWeek: String,
    textSize1: Int,
    textSize2: Int,
    dotCounts: Int
) {


    Column(
        modifier = modifier
            .size(width = 50.dp, height = 75.dp)
            .padding(5.dp)
            .wrapContentSize(Alignment.Center),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = "$dayofMonth",
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontSize = textSize1.sp,
                color = textColor.copy(alpha = 0.8f)
            ),
            textAlign = TextAlign.Center
        )

        Text(text = "$dayofWeek",
            style = TextStyle(
                fontFamily = FontFamily.Default,
                fontSize = textSize2.sp,
                color = textColor.copy(alpha = 0.5f)),
            textAlign = TextAlign.Center
        )

        Spacer(modifier = modifier.height(4.dp))

        if (dotCounts >= 1) {
            Row {
                for (i in 1..dotCounts){
                    ItemsCountIndicatorPerDate(modifier = modifier, size = 3)
                }
            }

        }
    }


}

@Composable
fun ItemsCountIndicatorPerDate(
    modifier: Modifier,
    size: Int
) {

    Box(
        modifier = modifier
            .padding(1.dp)
            .size(size.dp)
            .clip(shape = CircleShape)
            .background(color = deepDarkGreen)

    ) {

    }

}


@Preview(showBackground = true)
@Composable
fun ItemsCountIndicatorPerDatePreview() {
    ItemsCountIndicatorPerDate(
        modifier = Modifier,
        size = 3)
}

@Preview(showBackground = true)
@Composable
fun DateNotSelectedPreview() {

    DateNotSelected(
        modifier = Modifier,
        textColor = Color.Black,
        dayofMonth = 24,
        dayofWeek = "Lun",
        textSize1 = 12,
        textSize2 = 7,
        dotCounts = 2)

    // When DateUI is not selected

//    DateComponent(
//        modifier = Modifier,
//        backgroundColor = Color.White,
//        textColor = Color.Black,
//        dayofMonth = 24,
//        dayofWeek = "Lun",
//        textSize1 = 12,
//        textSize2 = 7,
//        dotCounts = 2)

}




@Preview(showBackground = true)
@Composable
fun DateSelectedPreview() {

//    // When DateUI is selected
    DateSelected(
        modifier = Modifier,
        textColor = deepDarkGreen,
        dayofMonth = 24,
        dayofWeek = "Lun",
        textSize1 = 20,
        textSize2 = 10,
        dotCounts = 2,)
}
