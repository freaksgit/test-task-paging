package vasyl.v.stoliarchuk.updownpaging.di.module

import dagger.Module
import dagger.android.ContributesAndroidInjector
import vasyl.v.stoliarchuk.updownpaging.di.ActivityScope
import vasyl.v.stoliarchuk.updownpaging.feature.tvprogramlist.TvProgramListActivity
import vasyl.v.stoliarchuk.updownpaging.feature.tvprogramlist.TvProgramListActivityModule

@Module
abstract class AndroidModule {

    @ActivityScope
    @ContributesAndroidInjector(modules = [(TvProgramListActivityModule::class)])
    internal abstract fun tvProgramListActivityInjector(): TvProgramListActivity
}