package com.example.iptvplayer.presenter.enterPlaylistURL

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iptvplayer.data.IptvChannel
import com.example.iptvplayer.useCases.ReadPlaylistContentByURLUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlaylistURLViewModel @Inject constructor(
    private val readPlaylistUseCase: ReadPlaylistContentByURLUseCase
) : ViewModel() {
    val channels = MutableLiveData<List<IptvChannel>>()
    val playlistType = MutableLiveData<String>()

    fun readPlaylistContent(url: String, m3u8: Boolean) {
        viewModelScope.launch {
            if (m3u8) channels.postValue(readPlaylistUseCase.readM3U8PlaylistContentByURL(url))
            else channels.postValue(readPlaylistUseCase.readM3UPlaylistContentByURL(url))
        }
    }

    fun getPlaylistType(url: String) {
        viewModelScope.launch {
            playlistType.postValue(readPlaylistUseCase.getPlaylistType(url))
        }
    }
}