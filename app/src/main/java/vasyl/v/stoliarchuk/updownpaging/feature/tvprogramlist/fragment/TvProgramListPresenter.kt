package vasyl.v.stoliarchuk.updownpaging.feature.tvprogramlist.fragment

import vasyl.v.stoliarchuk.updownpaging.common.schedulers.SchedulerProvider
import vasyl.v.stoliarchuk.updownpaging.data.tvprogram.datasource.TvProgramDataSource

class TvProgramListPresenter(
        private val mvpView: TvProgramListContract.View,
        private val programRepository: TvProgramDataSource,
        private val schedulerProvider: SchedulerProvider
) : TvProgramListContract.Presenter {


}