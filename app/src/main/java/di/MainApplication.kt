package di

import android.app.Application
import di.components.DaggerMainComponent

class MainApplication: Application() {
     val component by lazy {
        DaggerMainComponent.factory().create(this)
    }
}