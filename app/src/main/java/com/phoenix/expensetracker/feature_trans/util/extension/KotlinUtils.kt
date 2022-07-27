package com.phoenix.expensetracker.feature_trans.util.extension

import android.content.Context
import android.util.Log
import com.phoenix.expensetracker.R
import com.phoenix.expensetracker.feature_trans.util.Constants
import com.phoenix.expensetracker.feature_trans.util.Constants.EN
import com.phoenix.expensetracker.feature_trans.util.Constants.US
import java.text.SimpleDateFormat
import java.util.*

fun <A, B, C> tripleNonNull(a: A?, b: B?, c: C?): Triple<A, B, C>? =
        if (a != null && b != null && c != null) Triple(a, b, c) else null


fun Long.toDateFormat(): String {
        val dateTime = Date(this)
        val format = SimpleDateFormat(Constants.DATE_FORMAT, Locale.US)
        return format.format(dateTime)
}

fun getFormattedAmount(context: Context, amount: Float): String {
        return try {
                val locale = Locale(EN, US)
                String.format(locale, context.getString(R.string.text_amount), amount)
        } catch (e: Exception) {
                Log.d("Unable to format", "Unable to format value $amount")
                amount.toString()
        }
}
