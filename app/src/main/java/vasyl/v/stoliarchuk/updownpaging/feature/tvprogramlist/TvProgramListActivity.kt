package vasyl.v.stoliarchuk.updownpaging.feature.tvprogramlist

import android.os.Bundle
import android.widget.ProgressBar
import dagger.android.support.DaggerAppCompatActivity
import vasyl.v.stoliarchuk.updownpaging.R
import vasyl.v.stoliarchuk.updownpaging.feature.tvprogramlist.fragment.TvProgramListFragment

class TvProgramListActivity : DaggerAppCompatActivity(), TvProgramListFragment.ProgressBarProvider {

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tv_program_list)
        setSupportActionBar(findViewById(R.id.toolbar))
        progressBar = findViewById(R.id.progress)

        supportFragmentManager.beginTransaction()
                .add(R.id.activity_tv_program_list_fragment_container, TvProgramListFragment.newInstance())
                .commit()
    }

    override fun getProgressBar(): ProgressBar {
        return progressBar
    }
}
