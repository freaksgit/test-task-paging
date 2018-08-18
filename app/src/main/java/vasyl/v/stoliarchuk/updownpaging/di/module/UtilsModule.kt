package vasyl.v.stoliarchuk.updownpaging.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import vasyl.v.stoliarchuk.updownpaging.common.utils.DeviceUtils
import vasyl.v.stoliarchuk.updownpaging.di.DiNames
import javax.inject.Named
import javax.inject.Singleton

@Module
class UtilsModule {

    @Singleton
    @Provides
    fun provideDeviceUtils(@Named(DiNames.APP_CONTEXT) context: Context) = DeviceUtils(context)

}