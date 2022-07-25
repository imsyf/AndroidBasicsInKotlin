package im.syf.devbyte.data.db.video

import androidx.room.Entity
import androidx.room.PrimaryKey
import im.syf.devbyte.playlist.DevByteVideo

@Entity(tableName = "video")
data class VideoEntity(
    @PrimaryKey val url: String,
    val updated: String,
    val title: String,
    val description: String,
    val thumbnail: String,
)

fun VideoEntity.toDevByteVideo() = DevByteVideo(
    title = title,
    description = description,
    url = url,
    updated = updated,
    thumbnail = thumbnail,
)
