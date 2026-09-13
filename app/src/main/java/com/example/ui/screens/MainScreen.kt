package com.example.ui.screens

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Calculate
import androidx.compose.material.icons.filled.Movie
import androidx.compose.material.icons.filled.QueueMusic
import androidx.compose.material.icons.filled.School
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import com.example.viewmodel.AppViewModel

sealed class AppTab(val index: Int, val title: String, val icon: ImageVector, val tag: String) {
    object Cinema : AppTab(0, "Cine", Icons.Default.Movie, "tab_cinema")
    object Tables : AppTab(1, "Tablas", Icons.Default.Calculate, "tab_tables")
    object Playlist : AppTab(2, "Playlist", Icons.Default.QueueMusic, "tab_playlist")
    object Academic : AppTab(3, "Académico", Icons.Default.School, "tab_academic")
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    viewModel: AppViewModel,
    modifier: Modifier = Modifier
) {
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
    val tabs = listOf(
        AppTab.Cinema,
        AppTab.Tables,
        AppTab.Playlist,
        AppTab.Academic
    )

    val currentTab = tabs.getOrElse(selectedTabIndex) { AppTab.Cinema }

    Scaffold(
        modifier = modifier.fillMaxSize(),
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = when (currentTab) {
                            AppTab.Cinema -> "Clasificador de Cine"
                            AppTab.Tables -> "Tablas de Multiplicar"
                            AppTab.Playlist -> "Playlist de Canciones"
                            AppTab.Academic -> "Evaluador Académico"
                        },
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold
                    )
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface
                )
            )
        },
        bottomBar = {
            NavigationBar(
                modifier = Modifier.testTag("main_bottom_navigation")
            ) {
                tabs.forEach { tab ->
                    NavigationBarItem(
                        selected = selectedTabIndex == tab.index,
                        onClick = { selectedTabIndex = tab.index },
                        icon = {
                            Icon(imageVector = tab.icon, contentDescription = tab.title)
                        },
                        label = {
                            Text(
                                text = tab.title,
                                fontWeight = if (selectedTabIndex == tab.index) FontWeight.Bold else FontWeight.Normal
                            )
                        },
                        modifier = Modifier.testTag(tab.tag)
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Crossfade(targetState = selectedTabIndex, label = "tab_transition") { tabIndex ->
                when (tabIndex) {
                    0 -> CinemaClassifierScreen(viewModel = viewModel)
                    1 -> MultiplicationTableScreen(viewModel = viewModel)
                    2 -> PlaylistScreen(viewModel = viewModel)
                    3 -> AcademicScreen(viewModel = viewModel)
                    else -> CinemaClassifierScreen(viewModel = viewModel)
                }
            }
        }
    }
}
