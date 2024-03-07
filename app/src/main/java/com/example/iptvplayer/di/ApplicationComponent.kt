package com.example.iptvplayer.di

import com.example.iptvplayer.MainActivity
import com.example.iptvplayer.data.M3U8PlaylistRepository
import com.example.iptvplayer.data.M3UPlaylistRepository
import com.example.iptvplayer.data.PlaylistRepository
import dagger.BindsInstance
import dagger.Component

@Component
interface ApplicationComponent {
    fun inject(activity: MainActivity)
}