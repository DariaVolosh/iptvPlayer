package com.example.iptvplayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.compose.PlayerTheme
import com.example.iptvplayer.presenter.channelsList.ChannelsList
import com.example.iptvplayer.presenter.enterPlaylistURL.EnterPlaylistURL
import com.example.iptvplayer.presenter.enterPlaylistURL.PlaylistURLViewModel
import com.example.iptvplayer.presenter.watchChannel.WatchChannel
import java.net.URLEncoder
import java.nio.charset.StandardCharsets
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    companion object {
        init {
            System.loadLibrary("ffmpeg")
     }
    }
    @Inject lateinit var playlistUrlViewModel: PlaylistURLViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as MyApp).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        setContent { 
            PlayerTheme {
                MainScreen(
                    playlistURLViewModel = playlistUrlViewModel
                )
            }
        }
    }
}

@Composable
fun MainScreen(
    playlistURLViewModel: PlaylistURLViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "playlistUrl"
    ) {

        composable("playlistUrl") {
            EnterPlaylistURL(
                playlistURLViewModel = playlistURLViewModel,
            ) {
                navController.navigate("channelsList")
            }
        }

        composable("channelsList") {
            ChannelsList(
                playlistURLViewModel = playlistURLViewModel
            ) {
                val encodedUrl = URLEncoder.encode(it, StandardCharsets.UTF_8.toString())
                navController.navigate("watchChannel/$encodedUrl")
            }
        }

        composable("watchChannel/{channelUrl}") {
            WatchChannel(it.arguments?.getString("channelUrl") ?: "")
        }
    }
}