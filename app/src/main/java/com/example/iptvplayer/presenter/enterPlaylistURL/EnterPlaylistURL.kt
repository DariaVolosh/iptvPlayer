package com.example.iptvplayer.presenter.enterPlaylistURL

import android.util.Log
import android.widget.ProgressBar
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.example.compose.md_theme_dark_scrim
import com.example.compose.md_theme_dark_shadow
import com.example.iptvplayer.R
import com.example.iptvplayer.presenter.watchChannel.WatchChannel

@Composable
fun EnterPlaylistURL(
    modifier: Modifier = Modifier,
    playlistURLViewModel: PlaylistURLViewModel,
    navigateToChannelsList: () -> Unit,
) {
    var url by rememberSaveable { mutableStateOf("") }
    var displayProgressBar by rememberSaveable { mutableStateOf(false) }
    val channels by playlistURLViewModel.channels.observeAsState()
    val type by playlistURLViewModel.playlistType.observeAsState()
    playlistURLViewModel.getPlaylistType("http://tinyurl.com/4daf6e37")

    LaunchedEffect(key1 = type) {
        playlistURLViewModel.readPlaylistContent("http://tinyurl.com/4daf6e37", type == "application/x-mpegurl")
    }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        LottieIptvAnimation(modifier = Modifier
            .fillMaxWidth(0.3f)
            .fillMaxHeight(0.5f)
        )
        Text(
            text = stringResource(R.string.welcome_to_iptv_player_text),
            color = MaterialTheme.colorScheme.onBackground,
            style = TextStyle(
                fontFamily = FontFamily(Font(R.font.rubik_medium)),
                fontSize = TextUnit(30F, TextUnitType.Sp)
            )
        )

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(top = 30.dp)
        ) {
            Text(
                text = stringResource(R.string.playlist_url_label),
                color = MaterialTheme.colorScheme.onBackground,
                style = TextStyle(
                    fontFamily = FontFamily(Font(R.font.rubik_regular)),
                    fontSize = TextUnit(20F, TextUnitType.Sp)
                ),
                modifier = Modifier.padding(end = 10.dp)
            )
            
            BasicTextField(
                value = url,
                onValueChange = {url = it},
                modifier = Modifier
                    .border(1.dp, MaterialTheme.colorScheme.onBackground)
                    .padding(10.dp)
                    .fillMaxWidth(0.2f),
                singleLine = true,
                textStyle = TextStyle(
                    fontFamily = FontFamily(Font(R.font.rubik_regular)),
                    color = MaterialTheme.colorScheme.onBackground
                )
            )

            Button(
                onClick = {
                    playlistURLViewModel.getPlaylistType(url)
                    displayProgressBar = true
                },
                shape = RectangleShape,
                modifier = Modifier.padding(start = 10.dp)
            ) {
                Text(text = stringResource(R.string.add_url_text_button))
            }

            channels?.let {
                navigateToChannelsList()
            }
        }
    }

    if (displayProgressBar) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.scrim),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }
}