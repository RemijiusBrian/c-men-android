package com.chatmen.c_men.core.util

import java.text.SimpleDateFormat
import java.util.*

object FormatUtil {

    fun formatTimestamp(timestamp: Long): String =
        SimpleDateFormat.getDateInstance().format(Date(timestamp))
}