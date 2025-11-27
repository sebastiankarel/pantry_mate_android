package de.slapps.pantrymate.ui.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.ui.NavDisplay
import de.slapps.pantrymate.ui.screens.ItemDetailsScreen
import de.slapps.pantrymate.ui.screens.LoginScreen
import de.slapps.pantrymate.ui.screens.PantryContentScreen
import de.slapps.pantrymate.ui.screens.PantrySelectionScreen


@Composable
fun NavigationRoot(innerPadding: PaddingValues) {
    val backStack = remember { mutableStateListOf<Any>(Login) }

    NavDisplay(
        backStack = backStack,
        entryProvider = entryProvider {
            entry<Login> { _ ->
                LoginScreen(
                    onLoginSuccess = {
                        backStack.add(PantrySelection)
                    },
                )
            }
            entry<PantrySelection> { key ->
                PantrySelectionScreen()
            }
            entry<PantryContent> { key ->
                PantryContentScreen()
            }
            entry<ItemDetails> { key ->
                ItemDetailsScreen()
            }
        },
        modifier = Modifier.padding(innerPadding)
    )
}