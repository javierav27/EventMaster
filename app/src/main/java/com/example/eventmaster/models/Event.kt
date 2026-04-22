package com.example.eventmaster.models

import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class Event(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val date: String,
    val description: String,
    val categoryId: String
)