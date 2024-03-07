package com.example.iptvplayer.useCases

import com.example.iptvplayer.data.M3U8PlaylistRepository
import com.example.iptvplayer.data.M3UPlaylistRepository
import com.example.iptvplayer.data.PlaylistRepository
import java.io.File
import javax.inject.Inject

class ReadPlaylistContentByURLUseCase @Inject constructor(
    private val m3UPlaylistRepository: M3UPlaylistRepository,
    private val m3U8PlaylistRepository: M3U8PlaylistRepository
) {
    suspend fun readM3UPlaylistContentByURL(url: String) = m3UPlaylistRepository.readPlaylistContent(url)
    suspend fun readM3U8PlaylistContentByURL(url: String) = m3U8PlaylistRepository.readPlaylistContent(url)
    suspend fun getPlaylistType(url: String) = m3U8PlaylistRepository.getPlaylistFormat(url)
}