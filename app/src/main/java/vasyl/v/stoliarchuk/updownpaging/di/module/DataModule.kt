package vasyl.v.stoliarchuk.updownpaging.di.module

import dagger.Module
import dagger.Provides
import vasyl.v.stoliarchuk.updownpaging.common.utils.DeviceUtils
import vasyl.v.stoliarchuk.updownpaging.data.tvprogram.TvProgramRepository
import vasyl.v.stoliarchuk.updownpaging.data.tvprogram.datasource.TvProgramDataSource
import vasyl.v.stoliarchuk.updownpaging.data.tvprogram.datasource.remote.RemoteTvProgramDataSource
import vasyl.v.stoliarchuk.updownpaging.data.tvprogram.datasource.remote.retrofit.TvApi
import vasyl.v.stoliarchuk.updownpaging.data.tvprogram.datasource.remote.retrofit.mapper.TvProgramFromRetrofitMapper
import vasyl.v.stoliarchuk.updownpaging.di.DiNames
import javax.inject.Named
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    @Named(DiNames.REMOTE)
    fun provideRemoteTvProgramDataSource(tvApi: TvApi,
                                         deviceUtils: DeviceUtils,
                                         tvProgramFromRetrofitMapper: TvProgramFromRetrofitMapper): TvProgramDataSource {
        return RemoteTvProgramDataSource(tvApi, deviceUtils, tvProgramFromRetrofitMapper)
    }

    @Provides
    @Singleton
    @Named(DiNames.REPOSITORY)
    fun provideTvProgramRepository(@Named(DiNames.REMOTE) remoteTvProgramDataSource: TvProgramDataSource): TvProgramDataSource {
        return TvProgramRepository(remoteTvProgramDataSource)
    }

    @Provides
    fun provideTvProgramFromRetrofitMapper(): TvProgramFromRetrofitMapper = TvProgramFromRetrofitMapper()
}