package com.learn.mynewsapp.presentation.mainActivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import com.learn.mynewsapp.data.local.db.ArticleDao
import com.learn.mynewsapp.domain.model.Article
import com.learn.mynewsapp.domain.model.Source
import com.learn.mynewsapp.presentation.navigation.NavGraph
import com.learn.mynewsapp.ui.theme.MyNewsAppTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        installSplashScreen().apply {
            setKeepOnScreenCondition(condition = { viewModel.isSplashScreenVisible })
        }

        setContent {
            MyNewsAppTheme {
                Surface(Modifier.fillMaxSize()) {
                    NavGraph(startDestination = viewModel.startDestination)
                }
            }
        }
    }
}