package com.example.eventmaster.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.eventmaster.models.Category
import com.example.eventmaster.models.Event

@Composable
fun CategoryCard(
    category: Category,
    events: List<Event> = emptyList(),
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable { onClick() }
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = category.name,
                style = MaterialTheme.typography.titleLarge
            )
            
            if (events.isEmpty()) {
                Text(
                    text = "No hay eventos",
                    modifier = Modifier.padding(top = 8.dp),
                    style = MaterialTheme.typography.bodySmall
                )
            } else {
                events.forEach { event ->
                    EventCard(event = event, onClick = { /* Navigate to event detail */ })
                }
            }
        }
    }
}
