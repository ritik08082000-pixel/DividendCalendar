package com.dividendcalendar.data

import java.time.LocalDate

object DividendRepository {

    private val stocks = listOf(
        Stock("RELIANCE", "Reliance Industries", "NSE", "Energy"),
        Stock("TCS", "Tata Consultancy Services", "NSE", "IT"),
        Stock("INFY", "Infosys", "NSE", "IT"),
        Stock("HDFCBANK", "HDFC Bank", "NSE", "Banking"),
        Stock("ITC", "ITC Limited", "NSE", "FMCG"),
        Stock("WIPRO", "Wipro", "NSE", "IT"),
        Stock("HINDUNILVR", "Hindustan Unilever", "NSE", "FMCG"),
        Stock("SBIN", "State Bank of India", "NSE", "Banking"),
        Stock("COALINDIA", "Coal India", "NSE", "Mining"),
        Stock("ONGC", "Oil & Natural Gas Corp", "NSE", "Energy"),
        Stock("POWERGRID", "Power Grid Corp", "NSE", "Utilities"),
        Stock("NTPC", "NTPC Limited", "NSE", "Utilities"),
        Stock("BAJFINANCE", "Bajaj Finance", "NSE", "Finance"),
        Stock("TITAN", "Titan Company", "NSE", "Consumer"),
        Stock("NESTLEIND", "Nestle India", "BSE", "FMCG"),
    )

    val allEvents: List<DividendEvent> = listOf(
        // June 2026
        DividendEvent(stocks[0], LocalDate.of(2026, 5, 10), LocalDate.of(2026, 6, 5), LocalDate.of(2026, 6, 6), LocalDate.of(2026, 6, 20), 9.0, DividendType.FINAL, "Annual"),
        DividendEvent(stocks[1], LocalDate.of(2026, 4, 18), LocalDate.of(2026, 6, 3), LocalDate.of(2026, 6, 4), LocalDate.of(2026, 6, 18), 29.0, DividendType.FINAL, "Annual"),
        DividendEvent(stocks[2], LocalDate.of(2026, 4, 20), LocalDate.of(2026, 6, 10), LocalDate.of(2026, 6, 11), LocalDate.of(2026, 6, 25), 21.0, DividendType.FINAL, "Annual"),
        DividendEvent(stocks[3], LocalDate.of(2026, 5, 5), LocalDate.of(2026, 6, 18), LocalDate.of(2026, 6, 19), LocalDate.of(2026, 7, 2), 19.5, DividendType.FINAL, "Annual"),
        DividendEvent(stocks[4], LocalDate.of(2026, 5, 22), LocalDate.of(2026, 6, 12), LocalDate.of(2026, 6, 13), LocalDate.of(2026, 6, 28), 6.5, DividendType.INTERIM, "Interim"),
        // July 2026
        DividendEvent(stocks[5], LocalDate.of(2026, 6, 1), LocalDate.of(2026, 7, 8), LocalDate.of(2026, 7, 9), LocalDate.of(2026, 7, 22), 5.0, DividendType.FINAL, "Annual"),
        DividendEvent(stocks[6], LocalDate.of(2026, 6, 10), LocalDate.of(2026, 7, 2), LocalDate.of(2026, 7, 3), LocalDate.of(2026, 7, 17), 24.0, DividendType.FINAL, "Annual"),
        DividendEvent(stocks[7], LocalDate.of(2026, 6, 15), LocalDate.of(2026, 7, 15), LocalDate.of(2026, 7, 16), LocalDate.of(2026, 7, 30), 3.5, DividendType.FINAL, "Annual"),
        DividendEvent(stocks[8], LocalDate.of(2026, 6, 20), LocalDate.of(2026, 7, 22), LocalDate.of(2026, 7, 23), LocalDate.of(2026, 8, 5), 25.0, DividendType.FINAL, "Annual"),
        DividendEvent(stocks[9], LocalDate.of(2026, 6, 25), LocalDate.of(2026, 7, 28), LocalDate.of(2026, 7, 29), LocalDate.of(2026, 8, 12), 5.75, DividendType.INTERIM, "Interim"),
        // August 2026
        DividendEvent(stocks[10], LocalDate.of(2026, 7, 5), LocalDate.of(2026, 8, 6), LocalDate.of(2026, 8, 7), LocalDate.of(2026, 8, 20), 7.0, DividendType.FINAL, "Annual"),
        DividendEvent(stocks[11], LocalDate.of(2026, 7, 10), LocalDate.of(2026, 8, 12), LocalDate.of(2026, 8, 13), LocalDate.of(2026, 8, 27), 3.25, DividendType.FINAL, "Annual"),
        DividendEvent(stocks[12], LocalDate.of(2026, 7, 15), LocalDate.of(2026, 8, 20), LocalDate.of(2026, 8, 21), LocalDate.of(2026, 9, 3), 10.0, DividendType.SPECIAL, "Special"),
        DividendEvent(stocks[13], LocalDate.of(2026, 7, 20), LocalDate.of(2026, 8, 25), LocalDate.of(2026, 8, 26), LocalDate.of(2026, 9, 8), 5.0, DividendType.FINAL, "Annual"),
        DividendEvent(stocks[14], LocalDate.of(2026, 7, 25), LocalDate.of(2026, 8, 28), LocalDate.of(2026, 8, 29), LocalDate.of(2026, 9, 12), 140.0, DividendType.FINAL, "Annual"),
        // May 2026 (past)
        DividendEvent(stocks[0], LocalDate.of(2025, 10, 1), LocalDate.of(2025, 10, 20), LocalDate.of(2025, 10, 21), LocalDate.of(2025, 11, 4), 9.0, DividendType.FINAL, "Annual"),
        DividendEvent(stocks[1], LocalDate.of(2025, 10, 10), LocalDate.of(2025, 11, 5), LocalDate.of(2025, 11, 6), LocalDate.of(2025, 11, 20), 28.0, DividendType.FINAL, "Annual"),
        DividendEvent(stocks[2], LocalDate.of(2025, 10, 15), LocalDate.of(2025, 11, 12), LocalDate.of(2025, 11, 13), LocalDate.of(2025, 11, 27), 20.0, DividendType.FINAL, "Annual"),
    )

    fun getEventsForMonth(year: Int, month: Int): List<DividendEvent> =
        allEvents.filter { it.exDividendDate.year == year && it.exDividendDate.monthValue == month }

    fun getEventsForDate(date: LocalDate): List<DividendEvent> =
        allEvents.filter { it.exDividendDate == date }

    fun getEventsForStock(symbol: String): List<DividendEvent> =
        allEvents.filter { it.stock.symbol == symbol }.sortedByDescending { it.exDividendDate }

    fun getAllStocks(): List<Stock> = stocks

    fun searchStocks(query: String): List<Stock> {
        val q = query.trim().lowercase()
        return if (q.isEmpty()) stocks
        else stocks.filter { it.symbol.lowercase().contains(q) || it.name.lowercase().contains(q) }
    }
}
