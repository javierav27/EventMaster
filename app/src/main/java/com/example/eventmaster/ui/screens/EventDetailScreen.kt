package com.example.eventmaster.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.eventmaster.R
import com.example.eventmaster.viewmodel.EventMasterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventDetailScreen(
    viewModel: EventMasterViewModel,
    eventId: String,
    onNavigateBack: () -> Unit
) {
    val event = viewModel.getEventById(eventId)

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.event_detail_title)) },
                navigationIcon = {
                    IconButton(onClick = onNavigateBack) {
                        Icon(painter = painterResource(R.drawable.ic_arrow_back), contentDescription = "Volver")
                    }
                }
            )
        }
    ) { padding ->
        if (event != null) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
                    .padding(16.dp)
            ) {
                Text(text = event.name, style = MaterialTheme.typography.headlineMedium)
                Text(text = "Fecha: ${event.date}", modifier = Modifier.padding(top = 8.dp))
                Text(text = "Descripción: ${event.description}", modifier = Modifier.padding(top = 8.dp))
            }
        } else {
            Text("Evento no encontrado", modifier = Modifier.padding(16.dp))
        }
    }
}
