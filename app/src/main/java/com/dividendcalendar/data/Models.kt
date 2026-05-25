package com.dividendcalendar.data

import java.time.LocalDate

data class Stock(
    val symbol: String,       // e.g. "RELIANCE"
    val name: String,
    val exchange: String,     // "NSE" or "BSE"
    val sector: String
)

data class DividendEvent(
    val stock: Stock,
    val announcementDate: LocalDate,
    val exDividendDate: LocalDate,
    val recordDate: LocalDate,
    val payoutDate: LocalDate?,
    val dividendPerShare: Double,   // in ₹
    val dividendType: DividendType,
    val frequency: String           // "Annual", "Interim", "Special"
)

enum class DividendType { FINAL, INTERIM, SPECIAL }
