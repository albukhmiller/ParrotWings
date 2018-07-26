package alex.parrotwings.di.modules

import alex.parrotwings.domain.ApiServer
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class DataModule {

    @Singleton
    @Provides
    fun providesApiServer() = Retrofit.Builder()
            .baseUrl("http://193.124.114.46:3001/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiServer::class.java)
}