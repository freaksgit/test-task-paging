package vasyl.v.stoliarchuk.updownpaging.di.component

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import vasyl.v.stoliarchuk.updownpaging.App
import vasyl.v.stoliarchuk.updownpaging.di.module.*
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    AndroidModule::class,
    AppModule::class,
    DataModule::class,
    NetworkModule::class,
    SchedulersModule::class,
    UtilsModule::class
])

interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: App): Builder

        fun build(): AppComponent
    }

    override fun inject(app: App)
}