package com.example.eventmaster.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.example.eventmaster.data.Category
import com.example.eventmaster.data.Event

class EventMasterViewModel : ViewModel() {

    private val _categories = mutableStateListOf<Category>()
    val categories: List<Category> = _categories

    // Lista observable de eventos
    private val _events = mutableStateListOf<Event>()
    val events: List<Event> = _events

    init {
        // Datos de ejemplo iniciales
        _categories.addAll(
            listOf(
                Category(name = "Música"),
                Category(name = "Tecnología"),
                Category(name = "Deportes"),
                Category(name = "Educación"),
                Category(name = "Gastronomía"),
                Category(name = "Arte"),
            )
        )
        _events.addAll( //Eventps para cada categoría
            listOf(
                Event("1", "Concierto Rock", "2025-05-20", "Evento al aire libre", _categories[0].id),
                Event("2", "Hackathon", "2025-06-10", "Competencia de código", _categories[1].id),
                Event("3", "Maratón", "2025-07-15", "Carrera 10K", _categories[2].id),
                Event("4", "Exposiciones de arte", "2025-07-27", "Exposición en museo de pinturas y/o esculturas", _categories[5].id),
                Event("5", "Ferias vocacionales", "2026-02-27", "Evento para estudiantes de enseñanza media", _categories[3].id),
                Event("6", "Eventos culinarios temáticos", "2026-04-13", "Evento de exposición gastronómica", _categories[4].id),
            )
        )
    }

    fun addCategory(name: String) {
        if (name.isNotBlank()) {
            _categories.add(Category(name = name.trim()))
        }
    }

    fun addEvent(event: Event) {
        _events.add(event)
    }

    fun getEventsByCategory(categoryId: String): List<Event> {
        return _events.filter { it.categoryId == categoryId }
    }

    fun getEventById(eventId: String): Event? {
        return _events.find { it.id == eventId }
    }
}