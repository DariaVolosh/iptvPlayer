package com.example.iptvplayer.data

import android.util.Log
import com.example.iptvplayer.iptvApiToken
import com.example.iptvplayer.retrofit.IptvService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class EpgRepository @Inject constructor(
    private val iptvService: IptvService
) {
    suspend fun getChannelEpg(channelName: String) =
        withContext(Dispatchers.IO) {

        }
}