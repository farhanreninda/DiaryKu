package org.d3if4021.jurnal08.utils

import java.text.SimpleDateFormat

fun convertLongToDateString(systemTime: Long): String {
    return SimpleDateFormat("EEEE, dd MMMM yyyy")
        .format(systemTime).toString()
}
