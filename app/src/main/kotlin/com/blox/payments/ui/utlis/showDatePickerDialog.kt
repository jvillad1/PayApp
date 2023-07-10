package com.blox.payments.ui.utlis // ktlint-disable filename

import android.app.DatePickerDialog
import android.content.Context
import com.blox.payments.R.style
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

private const val DATE_FORMAT = "yyyy-MM-dd"

fun showDatePickerDialog(
    context: Context,
    birthDate: String,
    onBirthDateSet: (String) -> Unit
) {
    val calendar = getCalendar(birthDate)

    DatePickerDialog(
        /* context = */ context,
        style.DialogTheme,
        /* listener = */ { _, year, month, day ->
            onBirthDateSet(getPickedDateAsString(year, month, day))
        },
        /* year = */ calendar.get(Calendar.YEAR),
        /* month = */ calendar.get(Calendar.MONTH),
        /* dayOfMonth = */ calendar.get(Calendar.DAY_OF_MONTH)
    ).show()
}

private fun getCalendar(birthDate: String): Calendar {
    return if (birthDate.isEmpty()) {
        Calendar.getInstance()
    } else {
        getLastPickedDateCalendar(birthDate)
    }
}

private fun getLastPickedDateCalendar(birthDate: String): Calendar {
    val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.ROOT)
    val calendar = Calendar.getInstance()
    calendar.time = dateFormat.parse(birthDate) as Date
    return calendar
}

private fun getPickedDateAsString(year: Int, month: Int, day: Int): String {
    val calendar = Calendar.getInstance()
    calendar.set(year, month, day)
    val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.ROOT)
    return dateFormat.format(calendar.time)
}
