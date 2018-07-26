package alex.parrotwings

import alex.parrotwings.di.components.AppComponent
import alex.parrotwings.di.components.DaggerAppComponent
import alex.parrotwings.di.modules.AppModule
import android.app.Application

/**
 * Created by alex on 21.07.2018.
 */
class App : Application() {

    companion object {
        lateinit var component: AppComponent
    }


    override fun onCreate() {
        super.onCreate()

        component = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .build()
        component.inject(this) //инициализация appComponent
    }
}