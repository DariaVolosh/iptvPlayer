package com.example.iptvplayer.useCases

import android.util.Log
import com.example.iptvplayer.data.ChannelsRepository
import com.example.iptvplayer.data.EpgRepository
import javax.inject.Inject

class GetChannelEpgUseCase @Inject constructor(
    private val epgRepository: EpgRepository,
    private val channelsRepository: ChannelsRepository
){

}