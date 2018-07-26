package alex.parrotwings.di.modules

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: Application) {

    @Provides
    fun provdesApplication() = app

    @Provides
    fun providesContext(): Context = app


}