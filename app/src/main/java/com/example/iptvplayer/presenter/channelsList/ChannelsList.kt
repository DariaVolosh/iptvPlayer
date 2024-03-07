package com.example.iptvplayer.presenter.channelsList

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import com.example.iptvplayer.presenter.enterPlaylistURL.PlaylistURLViewModel
import androidx.tv.foundation.lazy.list.TvLazyColumn

@Composable
fun ChannelsList(
    playlistURLViewModel: PlaylistURLViewModel,
    navigateToChannel: (String) -> Unit
) {

    val channels by playlistURLViewModel.channels.observeAsState()

    Row {
        channels?.let {channels ->
            TvLazyColumn(
                modifier = Modifier
                    .weight(0.5F)
                    .background(MaterialTheme.colorScheme.primaryContainer)
            ) {
                items(channels.size) {index ->
                    val channel = channels[index]
                    Channel(
                        channelNumber = index+1,
                        channelLogoURL = channel.tvLogoUrl,
                        channelName = channel.channelName
                    ) { navigateToChannel(channel.channelURL) }
                }
            }
        }
    }
}