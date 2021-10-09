package yalexaner.konturtest.di.modules

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import rx.Scheduler
import rx.android.BuildConfig
import rx.android.schedulers.AndroidSchedulers
import yalexaner.konturtest.BuildConfig.BASE_URL
import yalexaner.konturtest.base.Schedulers
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

    @Provides
    @Singleton
    fun provideSchedulers(): Schedulers {
        return object : Schedulers {
            override val main: Scheduler get() = AndroidSchedulers.mainThread()
            override val io: Scheduler get() = rx.schedulers.Schedulers.io()
        }
    }
}