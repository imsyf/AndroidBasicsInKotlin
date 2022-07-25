package im.syf.devbyte.data

import im.syf.devbyte.data.db.DevByteRoomDatabase
import im.syf.devbyte.data.db.video.toDevByteVideos
import im.syf.devbyte.data.network.DevByteService
import im.syf.devbyte.data.network.video.VideoDto
import im.syf.devbyte.data.network.video.toVideoEntity
import im.syf.devbyte.playlist.DevByteVideo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class DevByteRepository(
    private val database: DevByteRoomDatabase,
    private val service: DevByteService,
) {

    val videos: Flow<List<DevByteVideo>> = database.videoDao().getVideos().map {
        it.toDevByteVideos()
    }

    suspend fun refreshVideos() {
        withContext(Dispatchers.IO) {
            val playlist = service.getPlaylist()
            database.videoDao().insertAll(playlist.videos.map(VideoDto::toVideoEntity))
        }
    }
}
