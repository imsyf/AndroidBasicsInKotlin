package im.syf.inventory

import android.app.Application
import im.syf.inventory.data.ItemRoomDatabase

class InventoryApplication : Application() {

    val database: ItemRoomDatabase by lazy { ItemRoomDatabase.getDatabase(this) }
}
