package im.syf.devbyte.data

import im.syf.devbyte.data.db.DevByteRoomDatabase
import im.syf.devbyte.data.network.DevByteService

class DevByteRepository(
    private val database: DevByteRoomDatabase,
    private val service: DevByteService,
) {

}
