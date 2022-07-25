package im.syf.devbyte.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import im.syf.devbyte.data.db.video.VideoDao
import im.syf.devbyte.data.db.video.VideoEntity

@Database(
    entities = [
        VideoEntity::class,
    ],
    version = 1,
    exportSchema = false
)
abstract class DevByteRoomDatabase : RoomDatabase() {

    abstract fun videoDao(): VideoDao

    companion object {

        @Volatile
        private var INSTANCE: DevByteRoomDatabase? = null

        fun getDatabase(context: Context): DevByteRoomDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DevByteRoomDatabase::class.java,
                    "dev_byte_database"
                )
                    .build()

                INSTANCE = instance

                instance
            }
        }
    }
}
