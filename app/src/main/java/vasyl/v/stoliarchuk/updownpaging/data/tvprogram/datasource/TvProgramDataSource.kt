package vasyl.v.stoliarchuk.updownpaging.data.tvprogram.datasource

import io.reactivex.Maybe
import vasyl.v.stoliarchuk.updownpaging.data.tvprogram.entity.TvProgram

interface TvProgramDataSource {
    fun getTvPrograms(borderId: Int, direction: Int): Maybe<List<TvProgram>>
}