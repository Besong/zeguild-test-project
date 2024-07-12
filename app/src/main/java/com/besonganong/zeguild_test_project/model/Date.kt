package com.besonganong.zeguild_test_project.model

import java.time.Month

data class Date(
    var dayofMonth: Int,
    var dayofWeek: String,
    val month: String = "juillet",
    val year: String = "2024",
    var savedItemsToDate: Int = 0
)