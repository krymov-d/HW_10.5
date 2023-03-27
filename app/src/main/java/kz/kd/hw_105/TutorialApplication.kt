package kz.kd.hw_105

import android.app.Application
import kz.kd.hw_105.di.networkModule
import kz.kd.hw_105.di.repositoryModule
import kz.kd.hw_105.di.useCaseModule
import kz.kd.hw_105.di.viewModelModule
import org.koin.core.context.startKoin

class TutorialApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(networkModule, viewModelModule, repositoryModule, useCaseModule)
        }
    }
}