package com.example.iptvplayer.presenter.watchChannel

import android.media.MediaCodecList
import android.media.MediaCodecList.ALL_CODECS
import android.os.Handler
import android.util.Log
import androidx.annotation.OptIn
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.C
import androidx.media3.common.MediaItem.fromUri
import androidx.media3.common.Player
import androidx.media3.common.Tracks
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.DefaultRenderersFactory
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.Renderer
import androidx.media3.exoplayer.audio.AudioRendererEventListener
import androidx.media3.exoplayer.metadata.MetadataOutput
import androidx.media3.exoplayer.text.TextOutput
import androidx.media3.exoplayer.trackselection.DefaultTrackSelector
import androidx.media3.exoplayer.video.VideoRendererEventListener
import androidx.media3.ui.PlayerView



@OptIn(UnstableApi::class)
@Composable
fun WatchChannel(channelUrl: String) {
    //val defaultHlsExtractorFactory = DefaultHlsExtractorFactory(FLAG_ALLOW_NON_IDR_KEYFRAMES, true)

    //val mediaSource = HlsMediaSource.Factory(
        //DefaultHttpDataSource.Factory()
    //).setExtractorFactory(defaultHlsExtractorFactory)


    var exoPlayer = ExoPlayer.Builder(LocalContext.current)
        .build()


   // exoPlayer.setMediaSource(mediaSource.createMediaSource(
        //MediaItem.Builder()
            //.setUri(Uri.parse(channelUrl))
            //.build()
    //))
    exoPlayer.setMediaItem(fromUri(channelUrl))

    exoPlayer.prepare()
    exoPlayer.play()

    val numCodecs = MediaCodecList.getCodecCount()

    // Iterate through each codec

    // Iterate through each codec
    for (i in 0 until numCodecs) {
        val codecInfo = MediaCodecList(ALL_CODECS).codecInfos

        // Check if the codec supports the MPEG-2 decoder
        for (i in codecInfo.indices) {
            for (j in codecInfo[i].supportedTypes) {
                Log.i("LOL", "----" + j)
            }
        }
    }


    exoPlayer.addListener(object: Player.Listener {
        override fun onTracksChanged(tracks: Tracks) {
            super.onTracksChanged(tracks)
            val trackSelector = exoPlayer.trackSelector as DefaultTrackSelector
            val trackInfo = trackSelector.currentMappedTrackInfo
            var rendererIndexAudio = -1


            trackInfo?.let {
                for (rendererIndex in 0 until trackInfo.rendererCount) {
                    val rendererType = trackInfo.getRendererType(rendererIndex)
                    // Check if the renderer type matches the type of track you want to transcode
                    if (rendererType == C.TRACK_TYPE_AUDIO) {
                        rendererIndexAudio = rendererIndex
                        Log.i("LOL", "audio")
                        break
                    }
                }
            }
        }
    })

    AndroidView(
        factory = {context ->
            PlayerView(context).apply {
                player = exoPlayer
            }
        },
    )
}