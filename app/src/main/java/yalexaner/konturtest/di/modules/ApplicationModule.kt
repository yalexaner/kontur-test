package yalexaner.konturtest.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rx.android.BuildConfig
import yalexaner.konturtest.BuildConfig.BASE_URL
import yalexaner.konturtest.network.client.HttpClient
import yalexaner.konturtest.network.client.HttpClientFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient {
        return HttpClientFactory(
            url = BASE_URL,
            debug = BuildConfig.DEBUG
        ).create()
    }
}