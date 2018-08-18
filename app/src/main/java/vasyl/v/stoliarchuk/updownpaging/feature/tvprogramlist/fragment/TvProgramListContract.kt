package vasyl.v.stoliarchuk.updownpaging.feature.tvprogramlist.fragment

import vasyl.v.stoliarchuk.updownpaging.data.tvprogram.entity.TvProgram

interface TvProgramListContract {

    interface View {
        fun addData(data: List<TvProgram>)

    }


    interface Presenter {
        fun subscribe()
        fun unsubscribe()

    }
}