package com.dividendcalendar.ui

sealed class Screen(val route: String) {
    object Calendar : Screen("calendar")
    object StockList : Screen("stocks")
    object StockDetail : Screen("stock/{symbol}") {
        fun createRoute(symbol: String) = "stock/$symbol"
    }
}
