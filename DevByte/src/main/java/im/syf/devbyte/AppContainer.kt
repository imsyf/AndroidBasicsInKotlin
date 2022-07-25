package im.syf.devbyte

import android.content.Context
import im.syf.devbyte.data.DevByteRepository
import im.syf.devbyte.data.db.DevByteRoomDatabase
import im.syf.devbyte.data.network.DevByteApi
import im.syf.devbyte.data.network.DevByteService

class AppContainer(context: Context) {

    private val database: DevByteRoomDatabase by lazy { DevByteRoomDatabase.getDatabase(context) }

    private val service: DevByteService by lazy {
        DevByteApi.retrofit.create(DevByteService::class.java)
    }

    val repository: DevByteRepository by lazy { DevByteRepository(database, service) }
}
