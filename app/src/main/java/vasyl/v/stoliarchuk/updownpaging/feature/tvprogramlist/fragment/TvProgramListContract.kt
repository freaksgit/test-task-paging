package vasyl.v.stoliarchuk.updownpaging.feature.tvprogramlist.fragment

import vasyl.v.stoliarchuk.updownpaging.data.tvprogram.entity.TvProgram

interface TvProgramListContract {

    interface View {
        fun addData(data: List<TvProgram>)
        fun getFirstTvProgram(): TvProgram
        fun getLastTvProgram(): TvProgram
        fun toggleProgressVisibility(visible: Boolean)
        fun scrollToPosition(position: Int)
        fun addDataToBeginning(data: List<TvProgram>)

    }


    interface Presenter {
        fun subscribe()
        fun onPrefetchNextPage(direction: Int)
        fun unsubscribe()

    }
}