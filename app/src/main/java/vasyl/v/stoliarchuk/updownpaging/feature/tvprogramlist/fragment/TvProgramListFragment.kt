package vasyl.v.stoliarchuk.updownpaging.feature.tvprogramlist.fragment

import dagger.android.support.DaggerFragment

class TvProgramListFragment : DaggerFragment(), TvProgramListContract.View {
    companion object {
        @JvmStatic
        fun newInstance() = TvProgramListFragment()
    }
}