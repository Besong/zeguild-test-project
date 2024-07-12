package com.besonganong.zeguild_test_project.dummydata

import androidx.compose.runtime.mutableStateMapOf
import com.besonganong.zeguild_test_project.R
import com.besonganong.zeguild_test_project.model.Date
import com.besonganong.zeguild_test_project.model.Item


// Dates
val july1 = Date(1, "Lun.", savedItemsToDate = 1)
val july2 = Date(2, "Mar.", savedItemsToDate = 2)
val july3 = Date(3, "Mer.")
val july4 = Date(4, "Jeu.")
val july5 = Date(5, "Ven.")
val july6 = Date(6, "Sam.", savedItemsToDate = 1)
val july7 = Date(7, "Dim.")

val dates = listOf(
    july1,
    july2,
    july3,
    july4,
    july5,
    july6,
    july7
)

// Items
val item1 = Item(name = R.string.mangue, image = R.drawable.apple_mango)
val item2 = Item(name = R.string.fraise_gariguette, image = R.drawable.gariguette_strawberry)
val item3 = Item(name = R.string.fraise, image = R.drawable.strawberry)
val item4 = Item(name = R.string.melon_charentais, image = R.drawable.charentais_melon)

val items = listOf(
    item1,
    item2,
    item3,
    item4
)

val products = mutableMapOf<Int, Pair<Date, List<Item>>>(
    Pair(0, Pair(july1, listOf(item1))),
    Pair(1, Pair(july2, listOf(item2, item3))),
    Pair(2, Pair(july6, listOf(item4)))
)





