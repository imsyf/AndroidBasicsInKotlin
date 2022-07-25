package im.syf.devbyte.data.network.video

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PlaylistDto(
    val videos: List<VideoDto>,
)
