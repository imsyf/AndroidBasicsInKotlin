package im.syf.devbyte

import android.app.Application

class DevByteApp : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()

        container = AppContainer(applicationContext)
    }
}
