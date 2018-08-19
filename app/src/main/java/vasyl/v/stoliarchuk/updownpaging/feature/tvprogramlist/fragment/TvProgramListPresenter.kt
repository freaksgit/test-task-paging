package vasyl.v.stoliarchuk.updownpaging.feature.tvprogramlist.fragment

import io.reactivex.disposables.CompositeDisposable
import vasyl.v.stoliarchuk.updownpaging.common.schedulers.SchedulerProvider
import vasyl.v.stoliarchuk.updownpaging.data.tvprogram.datasource.TvProgramDataSource
import vasyl.v.stoliarchuk.updownpaging.data.tvprogram.entity.Direction
import vasyl.v.stoliarchuk.updownpaging.data.tvprogram.entity.QueryData

class TvProgramListPresenter(
        private val mvpView: TvProgramListContract.View,
        private val programRepository: TvProgramDataSource,
        private val schedulerProvider: SchedulerProvider
) : TvProgramListContract.Presenter {

    private val disposables: CompositeDisposable = CompositeDisposable()
    private var loading = false

    private var lastQuery: QueryData? = null

    override fun subscribe() {
        loadData(getBorderId(Direction.DEFAULT), Direction.DEFAULT)
    }

    override fun onPrefetchNextPage(direction: Int) {
        val directionEnum = Direction.get(direction)
        val borderId = getBorderId(directionEnum)
        loadData(borderId, directionEnum)
    }

    private fun loadData(borderId: Int, direction: Direction) {
        if (!loading) {
            setLoading(true)
            lastQuery = QueryData(borderId, direction)
            val disposable = programRepository.getTvPrograms(borderId, direction.value)
                    .subscribeOn(schedulerProvider.io())
                    .observeOn(schedulerProvider.ui())
                    .subscribe(
                            {
                                when (direction) {
                                    Direction.TOP -> mvpView.addDataToBeginning(it)
                                    Direction.DEFAULT -> {
                                        mvpView.addData(it)
                                        mvpView.scrollToPosition(it.size / 2)
                                    }
                                    Direction.BOTTOM -> mvpView.addData(it)
                                }
                                setLoading(false)
                            },
                            { onError(it) })
            disposables.add(disposable)
        }
    }

    private fun onError(t: Throwable) {
        t.printStackTrace()
        mvpView.showErrorMessage()
        setLoading(false)
    }

    override fun onRetryButtonClicked() {
        mvpView.dismissErrorMessage()
        loadLastQuery()
    }

    private fun loadLastQuery() {
        loadData(lastQuery?.borderId ?: 0, lastQuery?.direction ?: Direction.DEFAULT)
    }

    private fun setLoading(loading: Boolean) {
        mvpView.toggleProgressVisibility(loading)
        this.loading = loading
    }

    private fun getBorderId(direction: Direction): Int {
        return when (direction) {
            Direction.TOP -> mvpView.getFirstTvProgram().id!!
            Direction.BOTTOM -> mvpView.getLastTvProgram().id!!
            Direction.DEFAULT -> 0
        }
    }

    override fun unsubscribe() {
        disposables.clear()
    }
}