package com.example.randomthingapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.randomthingapp.presentation.screens.YesOrNoScreen
import com.example.randomthingapp.presentation.viewmodels.YesNoViewModel
import com.example.randomthingapp.ui.theme.RandomThingAppTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: YesNoViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RandomThingAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    YesOrNoScreen(
                        modifier = Modifier.padding(innerPadding), viewModel = viewModel
                    )
                }
            }
        }
    }
}
