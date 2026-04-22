package com.example.eventmaster.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.eventmaster.R
import com.example.eventmaster.models.Event
import com.example.eventmaster.ui.components.CustomTextField
import com.example.eventmaster.viewmodel.EventMasterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddEventScreen(
    viewModel: EventMasterViewModel,
    categoryId: String,
    onNavigateBack: () -> Unit
) {
    var name by remember { mutableStateOf("") }
    var date by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var errors by remember { mutableStateOf(mapOf<String, Boolean>()) }

    fun isValid(): Boolean {
        val newErrors = mutableMapOf<String, Boolean>()
        if (name.isBlank()) newErrors["name"] = true
        if (date.isBlank()) newErrors["date"] = true
        if (description.isBlank()) newErrors["description"] = true
        errors = newErrors
        return newErrors.isEmpty()
    }

    Scaffold(
        topBar = { TopAppBar(title = { Text(stringResource(R.string.add_event_title)) }) }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp)
        ) {
            CustomTextField(
                value = name,
                onValueChange = { name = it },
                label = stringResource(R.string.event_name_label),
                isError = errors["name"] == true,
                errorMessage = if (errors["name"] == true) stringResource(R.string.field_required) else null
            )
            CustomTextField(
                value = date,
                onValueChange = { date = it },
                label = stringResource(R.string.event_date_label),
                isError = errors["date"] == true,
                errorMessage = if (errors["date"] == true) stringResource(R.string.field_required) else null
            )
            CustomTextField(
                value = description,
                onValueChange = { description = it },
                label = stringResource(R.string.event_description_label),
                isError = errors["description"] == true,
                errorMessage = if (errors["description"] == true) stringResource(R.string.field_required) else null
            )
            Button(
                onClick = {
                    if (isValid()) {
                        val event = Event(
                            name = name.trim(),
                            date = date.trim(),
                            description = description.trim(),
                            categoryId = categoryId
                        )
                        viewModel.addEvent(event)
                        onNavigateBack()
                    }
                }
            ) {
                Text(stringResource(R.string.save))
            }
        }
    }
}