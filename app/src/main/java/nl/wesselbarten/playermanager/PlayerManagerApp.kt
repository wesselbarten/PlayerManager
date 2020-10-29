package nl.wesselbarten.playermanager

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PlayerManagerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        System.setProperty("kotlinx.coroutines.debug", if (BuildConfig.DEBUG) "on" else "off")
    }
}