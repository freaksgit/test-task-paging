package vasyl.v.stoliarchuk.updownpaging.data.tvprogram

import io.reactivex.Maybe
import vasyl.v.stoliarchuk.updownpaging.data.tvprogram.datasource.TvProgramDataSource
import vasyl.v.stoliarchuk.updownpaging.data.tvprogram.entity.TvProgram

class TvProgramRepository(private val remote: TvProgramDataSource) : TvProgramDataSource {

    override fun getTvPrograms(borderId: Int, direction: Int): Maybe<List<TvProgram>> {
        return remote.getTvPrograms(borderId, direction)
    }
}