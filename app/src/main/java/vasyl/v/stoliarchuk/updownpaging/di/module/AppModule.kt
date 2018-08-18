package vasyl.v.stoliarchuk.updownpaging.di.module

import android.content.Context
import dagger.Binds
import dagger.Module
import vasyl.v.stoliarchuk.updownpaging.App
import vasyl.v.stoliarchuk.updownpaging.di.DiNames
import javax.inject.Named
import javax.inject.Singleton

@Module
abstract class AppModule {

    @Binds
    @Singleton
    @Named(DiNames.APP_CONTEXT)
    abstract fun provideApplicationContext(app: App): Context


}