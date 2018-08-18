package vasyl.v.stoliarchuk.updownpaging.feature.tvprogramlist

import dagger.Module
import dagger.android.ContributesAndroidInjector
import vasyl.v.stoliarchuk.updownpaging.di.FragmentScope
import vasyl.v.stoliarchuk.updownpaging.feature.tvprogramlist.fragment.TvProgramListFragment
import vasyl.v.stoliarchuk.updownpaging.feature.tvprogramlist.fragment.TvProgramListFragmentModule

@Module
abstract class TvProgramListActivityModule {

    @FragmentScope
    @ContributesAndroidInjector(modules = [(TvProgramListFragmentModule::class)])
    internal abstract fun tvProgramListFragmentInjector(): TvProgramListFragment
}