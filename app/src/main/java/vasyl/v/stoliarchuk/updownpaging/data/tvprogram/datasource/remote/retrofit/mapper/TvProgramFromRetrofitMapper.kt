package vasyl.v.stoliarchuk.updownpaging.data.tvprogram.datasource.remote.retrofit.mapper

import io.reactivex.functions.Function
import vasyl.v.stoliarchuk.updownpaging.data.tvprogram.datasource.remote.retrofit.entity.RetrofitTvProgram
import vasyl.v.stoliarchuk.updownpaging.data.tvprogram.entity.TvProgram

class TvProgramFromRetrofitMapper : Function<RetrofitTvProgram, TvProgram> {
    override fun apply(t: RetrofitTvProgram): TvProgram {
        return TvProgram(t.id, t.icon, t.name)
    }
}