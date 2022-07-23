package im.syf.sqlbasics

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        CaliforniaPark::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun californiaParkDao(): CaliforniaParkDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase = INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(context, AppDatabase::class.java, "app_database")
                .createFromAsset("database/calipark.sqli")
                .build()

            INSTANCE = instance

            instance
        }
    }
}
