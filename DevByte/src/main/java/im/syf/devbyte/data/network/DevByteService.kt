package im.syf.devbyte.data.network

import im.syf.devbyte.data.network.video.PlaylistDto
import retrofit2.http.GET

interface DevByteService {

    @GET("devbytes")
    suspend fun getPlaylist(): PlaylistDto
}
