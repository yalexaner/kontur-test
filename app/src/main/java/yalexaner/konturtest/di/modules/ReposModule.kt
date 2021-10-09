package yalexaner.konturtest.di.modules

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import yalexaner.konturtest.network.repos.HttpMainScreenRepo
import yalexaner.konturtest.network.repos.MainScreenRepo
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ReposModule {

    @Binds
    @Singleton
    abstract fun mainScreen(repo: HttpMainScreenRepo): MainScreenRepo
}