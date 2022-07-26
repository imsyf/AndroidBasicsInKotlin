package im.syf.forage

import android.app.Application
import im.syf.forage.data.ForageDatabase

/**
 * An application class that inherits from [Application], allows for the creation of a singleton
 * instance of the [ForageDatabase]
 */
class BaseApplication : Application() {

    val database: ForageDatabase by lazy { ForageDatabase.getDatabase(this) }
}
