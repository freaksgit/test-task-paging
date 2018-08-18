package vasyl.v.stoliarchuk.updownpaging.di.module


import dagger.Module
import dagger.Provides
import vasyl.v.stoliarchuk.updownpaging.common.schedulers.RxSchedulerProvider
import vasyl.v.stoliarchuk.updownpaging.common.schedulers.SchedulerProvider
import vasyl.v.stoliarchuk.updownpaging.di.DiNames
import javax.inject.Named
import javax.inject.Singleton

@Module
class SchedulersModule {

    @Provides
    @Singleton
    @Named(DiNames.PROVIDER_RX)
    fun provideSchedulerProvider(): SchedulerProvider {
        return RxSchedulerProvider()
    }
}