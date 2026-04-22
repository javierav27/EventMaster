package com.example.eventmaster.ui.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.eventmaster.R
import com.example.eventmaster.ui.components.CategoryCard
import com.example.eventmaster.viewmodel.EventMasterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: EventMasterViewModel,
    onAddCategoryClick: () -> Unit,
    onAddEventClick: (String) -> Unit,
    onEventClick: (String) -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.app_name)) }) },
        floatingActionButton = {
            FloatingActionButton(onClick = onAddCategoryClick) {
                Icon(imageVector = Icons.Default.Favorite, contentDescription = "Nueva categoría")
            }
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            items(viewModel.categories) { category ->
                val events = viewModel.getEventsByCategory(category.id)
                CategoryCard(
                    category = category,
                    events = events,
                    onAddEventClick = { onAddEventClick(category.id) },
                    onEventClick = onEventClick
                )
            }
        }
    }
}
