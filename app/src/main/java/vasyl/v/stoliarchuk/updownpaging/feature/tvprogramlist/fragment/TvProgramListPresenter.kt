package vasyl.v.stoliarchuk.updownpaging.feature.tvprogramlist.fragment

import vasyl.v.stoliarchuk.updownpaging.common.schedulers.SchedulerProvider
import vasyl.v.stoliarchuk.updownpaging.data.tvprogram.datasource.TvProgramDataSource
import vasyl.v.stoliarchuk.updownpaging.data.tvprogram.entity.Direction

class TvProgramListPresenter(
        private val mvpView: TvProgramListContract.View,
        private val programRepository: TvProgramDataSource,
        private val schedulerProvider: SchedulerProvider
) : TvProgramListContract.Presenter {
    override fun subscribe() {
        programRepository.getTvPrograms(0, Direction.DEFAULT.value)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribe(
                        { mvpView.addData(it) },
                        { it.printStackTrace() })
    }

    override fun unsubscribe() {
    }


}