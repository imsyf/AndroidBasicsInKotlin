package im.syf.inventory.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        Item::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class ItemRoomDatabase : RoomDatabase() {

    abstract fun itemDao(): ItemDao

    companion object {

        /**
         * The value of a volatile variable will never be cached, and all writes and reads will be
         * done to and from the main memory. This helps make sure the value of INSTANCE is always
         * up-to-date and the same for all execution threads. It means that changes made by
         * one thread to INSTANCE are visible to all other threads immediately.
         */
        @Volatile
        private var INSTANCE: ItemRoomDatabase? = null

        fun getDatabase(context: Context): ItemRoomDatabase = INSTANCE ?: synchronized(this) {
            val instance = Room.databaseBuilder(
                context.applicationContext,
                ItemRoomDatabase::class.java,
                "item_database"
            )
                .fallbackToDestructiveMigration()
                .build()

            INSTANCE = instance

            instance
        }
    }
}
