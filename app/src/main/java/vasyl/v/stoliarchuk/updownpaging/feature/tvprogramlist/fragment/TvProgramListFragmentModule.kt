package vasyl.v.stoliarchuk.updownpaging.feature.tvprogramlist.fragment

import dagger.Binds
import dagger.Module
import dagger.Provides
import vasyl.v.stoliarchuk.updownpaging.common.schedulers.SchedulerProvider
import vasyl.v.stoliarchuk.updownpaging.data.tvprogram.datasource.TvProgramDataSource
import vasyl.v.stoliarchuk.updownpaging.di.ActivityScope
import vasyl.v.stoliarchuk.updownpaging.di.DiNames
import javax.inject.Named

@Module
abstract class TvProgramListFragmentModule {
    @Binds
    abstract fun bindTvProgramListView(tvProgramListFragment: TvProgramListFragment): TvProgramListContract.View

    @Module
    companion object {

        @JvmStatic
        @Provides
        @ActivityScope
        fun provideTvProgramListPresenter(view: TvProgramListContract.View,
                                          @Named(DiNames.REPOSITORY) tvProgramRepository: TvProgramDataSource,
                                          @Named(DiNames.PROVIDER_RX) schedulerProvider: SchedulerProvider): TvProgramListContract.Presenter {
            return TvProgramListPresenter(view, tvProgramRepository, schedulerProvider)
        }
    }
}