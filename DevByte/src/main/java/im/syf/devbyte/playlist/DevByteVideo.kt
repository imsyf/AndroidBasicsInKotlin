package im.syf.devbyte.playlist

import im.syf.devbyte.ext.smartTruncate

data class DevByteVideo(
    val title: String,
    val description: String,
    val url: String,
    val updated: String,
    val thumbnail: String,
) {
    val shortDescription: String = description.smartTruncate(200)
}
