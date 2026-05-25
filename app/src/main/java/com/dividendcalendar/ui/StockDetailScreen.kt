package com.dividendcalendar.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.dividendcalendar.data.DividendEvent
import com.dividendcalendar.data.DividendRepository
import com.dividendcalendar.data.DividendType
import java.time.format.DateTimeFormatter

private val dateFmt = DateTimeFormatter.ofPattern("dd MMM yyyy")

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StockDetailScreen(symbol: String, onBack: () -> Unit) {
    val events = remember(symbol) { DividendRepository.getEventsForStock(symbol) }
    val stock = events.firstOrNull()?.stock

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stock?.name ?: symbol) },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { padding ->
        if (events.isEmpty()) {
            Box(Modifier.fillMaxSize().padding(padding), contentAlignment = Alignment.Center) {
                Text("No dividend history found for $symbol")
            }
            return@Scaffold
        }

        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(padding),
            contentPadding = PaddingValues(12.dp)
        ) {
            // Summary header
            item {
                stock?.let { StockHeader(it.symbol, it.exchange, it.sector) }
                Spacer(Modifier.height(12.dp))
                Text("Dividend History", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
            }
            items(events) { event ->
                DividendHistoryRow(event)
                HorizontalDivider()
            }
        }
    }
}

@Composable
private fun StockHeader(symbol: String, exchange: String, sector: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.primaryContainer)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(modifier = Modifier.weight(1f)) {
            Text(symbol, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.onPrimaryContainer)
            Text("$exchange  •  $sector", style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onPrimaryContainer.copy(alpha = 0.7f))
        }
    }
}

@Composable
private fun DividendHistoryRow(event: DividendEvent) {
    val typeColor = when (event.dividendType) {
        DividendType.FINAL -> Color(0xFF1B5E20)
        DividendType.INTERIM -> Color(0xFF0D47A1)
        DividendType.SPECIAL -> Color(0xFF6A1B9A)
    }
    Column(modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp)) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                "₹${event.dividendPerShare}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = typeColor,
                modifier = Modifier.weight(1f)
            )
            Surface(shape = RoundedCornerShape(4.dp), color = typeColor.copy(alpha = 0.12f)) {
                Text(
                    "${event.dividendType.name}  •  ${event.frequency}",
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp),
                    style = MaterialTheme.typography.labelSmall,
                    color = typeColor
                )
            }
        }
        Spacer(Modifier.height(6.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            DateLabel("Announced", event.announcementDate.format(dateFmt), Modifier.weight(1f))
            DateLabel("Ex-Dividend", event.exDividendDate.format(dateFmt), Modifier.weight(1f))
            DateLabel("Record", event.recordDate.format(dateFmt), Modifier.weight(1f))
        }
        event.payoutDate?.let {
            Spacer(Modifier.height(2.dp))
            DateLabel("Payout", it.format(dateFmt), Modifier.fillMaxWidth(0.34f))
        }
    }
}

@Composable
private fun DateLabel(label: String, value: String, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        Text(label, style = MaterialTheme.typography.labelSmall, color = MaterialTheme.colorScheme.onSurfaceVariant)
        Text(value, style = MaterialTheme.typography.bodySmall, fontWeight = FontWeight.Medium)
    }
}
