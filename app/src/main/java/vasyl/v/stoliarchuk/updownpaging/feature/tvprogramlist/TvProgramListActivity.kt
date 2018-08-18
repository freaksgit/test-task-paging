package vasyl.v.stoliarchuk.updownpaging.feature.tvprogramlist

import android.os.Bundle
import dagger.android.support.DaggerAppCompatActivity
import vasyl.v.stoliarchuk.updownpaging.R
import vasyl.v.stoliarchuk.updownpaging.feature.tvprogramlist.fragment.TvProgramListFragment

class TvProgramListActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_program_list)

        supportFragmentManager.beginTransaction()
                .add(R.id.activity_tv_program_list_fragment_container, TvProgramListFragment.newInstance())
                .commit()
    }
}
