package im.syf.devbyte.data.network.video

import com.squareup.moshi.JsonClass
import im.syf.devbyte.playlist.DevByteVideo

@JsonClass(generateAdapter = true)
data class VideoDto(
    val title: String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String,
    val closedCaptions: String?,
)

fun VideoDto.toDevByteVideo() = DevByteVideo(
    title = title,
    description = description,
    url = url,
    updated = updated,
    thumbnail = thumbnail,
)
