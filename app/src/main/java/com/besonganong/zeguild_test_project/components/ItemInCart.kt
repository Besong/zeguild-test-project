package com.besonganong.zeguild_test_project.components

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.sharp.Delete
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.besonganong.zeguild_test_project.R
import com.besonganong.zeguild_test_project.ui.theme.deepDarkGreen


@Composable
fun ItemInCart(
    modifier: Modifier = Modifier,
    count: Int,
    @StringRes name: Int,
    @DrawableRes imageRes: Int,
    onItemClicked: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(8.dp)
            .wrapContentSize(Alignment.Center),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {


        Box(
            modifier = modifier
                .clickable(
                    onClick = onItemClicked
                )
                .size(70.dp)
                .border(
                    width = 0.11.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(4.dp)
                )

        ) {

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = "",
                modifier = modifier
                    .size(70.dp)
            )

            ItemInCartCountIndicator(
                modifier = modifier.align(Alignment.TopEnd),
                count = count
            )

        }

        Spacer(modifier = modifier.height(8.dp))

        Text(
            text = stringResource(id = name),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 16.sp,
                color = Color.Black.copy(alpha = 0.6f)
            ),
            modifier = modifier.padding(6.dp)
        )
    }


}


@Composable
fun ItemInCartCountIndicator(
    modifier: Modifier,
    count: Int
) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .size(18.dp)
            .clip(shape = CircleShape)
            .background(color = deepDarkGreen)
    ) {
        Text(
            text = "$count",
            style = TextStyle(
                fontFamily = FontFamily.SansSerif,
                fontSize = 10.sp,
                color = Color.White
            ),
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun CustomIcon(
    modifier: Modifier,
    icon: ImageVector,
    backgroundColor: Color,
    backgroudSize: Int,
    imageColor: Color
) {
    Box(
        modifier = modifier
            .padding(4.dp)
            .size(backgroudSize.dp)
            .clip(shape = CircleShape)
            .background(color = backgroundColor)
    ) {
        Image(
            imageVector = icon,
            colorFilter = ColorFilter.tint(imageColor),
            contentDescription = "",
            modifier = Modifier.align(Alignment.Center)
            )
    }
}

@Preview(showBackground = true)
@Composable
fun ItemInCartPreview() {
    ItemInCart(
        modifier = Modifier,
        count = 2,
        name = R.string.melon_charentais,
        imageRes = R.drawable.charentais_melon,
        onItemClicked = {})
}


@Preview(showBackground = true)
@Composable
fun ItemInCartCountIndicatorPreview() {
    ItemInCartCountIndicator(modifier = Modifier, count = 2)
}

@Preview(showBackground = true)
@Composable
fun CustomIconPreview() {
    CustomIcon(
        modifier = Modifier,
        icon = Icons.Rounded.Delete,
        backgroundColor = Color.Red,
        backgroudSize = 75,
        imageColor = Color.White)
}