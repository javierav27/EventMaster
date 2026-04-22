package com.example.eventmaster

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.eventmaster.ui.screens.*
import com.example.eventmaster.ui.theme.EventMasterTheme
import com.example.eventmaster.viewmodel.EventMasterViewModel
import kotlinx.serialization.Serializable

@Serializable
object Home

@Serializable
data class Events(val categoryId: String)

@Serializable
object AddCategory

@Serializable
data class AddEvent(val categoryId: String)

@Serializable
data class EventDetail(val eventId: String)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            EventMasterTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    EventMasterApp()
                }
            }
        }
    }
}

@Composable
fun EventMasterApp() {
    val navController = rememberNavController()
    val viewModel: EventMasterViewModel = viewModel()

    NavHost(navController = navController, startDestination = Home) {
        composable<Home> {
            HomeScreen(
                viewModel = viewModel,
                onAddCategoryClick = { navController.navigate(AddCategory) },
                onCategoryClick = { categoryId -> navController.navigate(Events(categoryId)) }
            )
        }
        composable<Events> { backStackEntry ->
            val args = backStackEntry.toRoute<Events>()
            EventsScreen(
                viewModel = viewModel,
                categoryId = args.categoryId,
                onAddEventClick = { navController.navigate(AddEvent(args.categoryId)) },
                onEventClick = { eventId -> navController.navigate(EventDetail(eventId)) },
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable<AddCategory> {
            AddCategoryScreen(
                viewModel = viewModel,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable<AddEvent> { backStackEntry ->
            val args = backStackEntry.toRoute<AddEvent>()
            AddEventScreen(
                viewModel = viewModel,
                categoryId = args.categoryId,
                onNavigateBack = { navController.popBackStack() }
            )
        }
        composable<EventDetail> { backStackEntry ->
            val args = backStackEntry.toRoute<EventDetail>()
            EventDetailScreen(
                viewModel = viewModel,
                eventId = args.eventId,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
