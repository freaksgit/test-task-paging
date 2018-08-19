package vasyl.v.stoliarchuk.updownpaging.feature.tvprogramlist.fragment

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import vasyl.v.stoliarchuk.updownpaging.common.schedulers.SchedulerProvider
import vasyl.v.stoliarchuk.updownpaging.common.utils.DeviceUtils
import vasyl.v.stoliarchuk.updownpaging.data.tvprogram.datasource.TvProgramDataSource
import vasyl.v.stoliarchuk.updownpaging.di.DiNames
import vasyl.v.stoliarchuk.updownpaging.di.FragmentScope
import vasyl.v.stoliarchuk.updownpaging.feature.tvprogramlist.fragment.adapter.TvProgramListAdapter
import vasyl.v.stoliarchuk.updownpaging.feature.tvprogramlist.fragment.paging.PagingVerticalScrollListener
import vasyl.v.stoliarchuk.updownpaging.gateway.connectivity.AndroidConnectivityTracker
import vasyl.v.stoliarchuk.updownpaging.gateway.connectivity.ConnectivityTracker
import javax.inject.Named

@Module
abstract class TvProgramListFragmentModule {
    @Binds
    abstract fun bindTvProgramListView(tvProgramListFragment: TvProgramListFragment): TvProgramListContract.View

    @Module
    companion object {

        @JvmStatic
        @Provides
        @FragmentScope
        fun provideTvProgramListPresenter(view: TvProgramListContract.View,
                                          @Named(DiNames.REPOSITORY) tvProgramRepository: TvProgramDataSource,
                                          @Named(DiNames.PROVIDER_RX) schedulerProvider: SchedulerProvider,
                                          connectivityTracker: ConnectivityTracker): TvProgramListContract.Presenter {
            return TvProgramListPresenter(view, tvProgramRepository, schedulerProvider, connectivityTracker)
        }

        @JvmStatic
        @Provides
        @FragmentScope
        fun provideTvProgramListAdapter(): TvProgramListAdapter {
            return TvProgramListAdapter()
        }

        @JvmStatic
        @Provides
        @FragmentScope
        fun providePagingVerticalScrollListener(): PagingVerticalScrollListener {
            return PagingVerticalScrollListener(15)
        }

        @JvmStatic
        @Provides
        @FragmentScope
        fun provideConnectivityTracker(@Named(DiNames.APP_CONTEXT) context: Context,
                                       deviceUtils: DeviceUtils): ConnectivityTracker {
            return AndroidConnectivityTracker(context, deviceUtils)
        }
    }
}