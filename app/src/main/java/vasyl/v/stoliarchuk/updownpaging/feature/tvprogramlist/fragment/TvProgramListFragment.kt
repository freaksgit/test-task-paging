package vasyl.v.stoliarchuk.updownpaging.feature.tvprogramlist.fragment

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import dagger.android.support.DaggerFragment
import vasyl.v.stoliarchuk.updownpaging.R
import vasyl.v.stoliarchuk.updownpaging.data.tvprogram.entity.TvProgram
import vasyl.v.stoliarchuk.updownpaging.feature.tvprogramlist.fragment.adapter.TvProgramListAdapter
import javax.inject.Inject

class TvProgramListFragment : DaggerFragment(), TvProgramListContract.View {
    override fun addData(data: List<TvProgram>) {
        adapter.addAll(data)
    }

    @Inject
    lateinit var presenter: TvProgramListContract.Presenter

    @Inject
    lateinit var adapter: TvProgramListAdapter

    companion object {
        @JvmStatic
        fun newInstance() = TvProgramListFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = LayoutInflater.from(context)
                .inflate(R.layout.fragment_tv_program_list, container, false)
        initRecycler(rootView)
        return rootView
    }

    private fun initRecycler(rootView: View) {
        val recyclerView = rootView.findViewById<RecyclerView>(R.id.fragment_tv_program_list_recycler)
        val orientation = LinearLayoutManager.VERTICAL
        recyclerView.layoutManager = LinearLayoutManager(context, orientation, false)
        recyclerView.addItemDecoration(DividerItemDecoration(context,orientation))
        recyclerView.adapter = adapter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.subscribe()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }
}