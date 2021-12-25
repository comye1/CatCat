package com.comye1.catcat.utils

import java.text.SimpleDateFormat
import java.util.*

fun getDate(): String {
    val simpleDateFormat: SimpleDateFormat = SimpleDateFormat("yyyyMMdd")
    return simpleDateFormat.format(Date(System.currentTimeMillis()))
}
