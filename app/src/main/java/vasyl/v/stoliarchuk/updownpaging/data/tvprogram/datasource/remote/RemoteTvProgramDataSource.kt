package vasyl.v.stoliarchuk.updownpaging.data.tvprogram.datasource.remote

import io.reactivex.Maybe
import io.reactivex.functions.Function
import vasyl.v.stoliarchuk.updownpaging.common.utils.DeviceUtils
import vasyl.v.stoliarchuk.updownpaging.data.tvprogram.datasource.TvProgramDataSource
import vasyl.v.stoliarchuk.updownpaging.data.tvprogram.datasource.remote.retrofit.TvApi
import vasyl.v.stoliarchuk.updownpaging.data.tvprogram.datasource.remote.retrofit.entity.RetrofitTvProgram
import vasyl.v.stoliarchuk.updownpaging.data.tvprogram.entity.TvProgram

class RemoteTvProgramDataSource(private val tvApi: TvApi,
                                private val deviceUtils: DeviceUtils,
                                private val tvProgramFromRetrofitMapper: Function<RetrofitTvProgram, TvProgram>) : TvProgramDataSource {

    override fun getTvPrograms(borderId: Int, direction: Int): Maybe<List<TvProgram>> =
            tvApi.getTvPrograms(deviceUtils.getSecureDeviceId(), borderId, direction)
                    .map { it.items }
                    .toObservable()
                    .flatMapIterable { it }
                    .map(tvProgramFromRetrofitMapper)
                    .toList()
                    .toMaybe()
}