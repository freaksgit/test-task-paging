package vasyl.v.stoliarchuk.updownpaging.data.tvprogram.datasource.remote.retrofit

import io.reactivex.Maybe
import retrofit2.http.GET
import retrofit2.http.Query
import vasyl.v.stoliarchuk.updownpaging.data.tvprogram.datasource.remote.retrofit.entity.RetrofitTvProgramsBlock

interface TvApi {

    @GET(GET_PROGRAMS_BLOCK)
    fun getTvPrograms(
            @Query("serial_number") deviceSerialNumber: String,
            @Query("borderId") borderId: Int,
            @Query("direction") direction: Int): Maybe<RetrofitTvProgramsBlock>


    companion object {
        const val BASE_URL = "http://oll.tv"

        const val GET_PROGRAMS_BLOCK = "/demo"
    }
}