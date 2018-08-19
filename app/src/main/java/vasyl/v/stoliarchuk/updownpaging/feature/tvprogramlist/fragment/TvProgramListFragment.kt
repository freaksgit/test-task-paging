package vasyl.v.stoliarchuk.updownpaging.feature.tvprogramlist.fragment

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import dagger.android.support.DaggerFragment
import vasyl.v.stoliarchuk.updownpaging.R
import vasyl.v.stoliarchuk.updownpaging.data.tvprogram.entity.TvProgram
import vasyl.v.stoliarchuk.updownpaging.feature.tvprogramlist.fragment.adapter.TvProgramListAdapter
import vasyl.v.stoliarchuk.updownpaging.feature.tvprogramlist.fragment.paging.OnPagingDataListener
import vasyl.v.stoliarchuk.updownpaging.feature.tvprogramlist.fragment.paging.PagingVerticalScrollListener
import javax.inject.Inject

class TvProgramListFragment : DaggerFragment(), TvProgramListContract.View {


    @Inject
    lateinit var presenter: TvProgramListContract.Presenter

    @Inject
    lateinit var adapter: TvProgramListAdapter

    @Inject
    lateinit var pagingVerticalScrollListener: PagingVerticalScrollListener

    lateinit var errorSnackbar: Snackbar


    private lateinit var recyclerView: RecyclerView
    private lateinit var progress: ProgressBar
    private var errorMessageVisible = false
    private var progressBarProvider: ProgressBarProvider? = null

    companion object {
        @JvmStatic
        fun newInstance() = TvProgramListFragment()
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        try {
            progressBarProvider = context as ProgressBarProvider
        } catch (e: ClassCastException) {
            throw ClassCastException("$context should implement ProgressBarProvider interface")
        }
    }

    override fun onDetach() {
        super.onDetach()
        progressBarProvider = null
    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = LayoutInflater.from(context)
                .inflate(R.layout.fragment_tv_program_list, container, false)
        initPagingScrollListener()
        initRecycler(rootView)
        progress = progressBarProvider!!.getProgressBar()
        return rootView
    }


    private fun initPagingScrollListener() {
        pagingVerticalScrollListener.addOnPagingDataListener(object : OnPagingDataListener {
            override fun onPrefetchNextPage(direction: Int) {
                presenter.onPrefetchNextPage(direction)
            }
        })
    }


    private fun initRecycler(rootView: View) {
        val orientation = LinearLayoutManager.VERTICAL
        recyclerView = rootView.findViewById(R.id.fragment_tv_program_list_recycler)
        recyclerView.layoutManager = LinearLayoutManager(context, orientation, false)
        recyclerView.addItemDecoration(DividerItemDecoration(context, orientation))
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(pagingVerticalScrollListener)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.subscribe()
    }


    override fun addDataToBeginning(data: List<TvProgram>) {
        adapter.addAll(data, true)
    }


    override fun addData(data: List<TvProgram>) {
        adapter.addAll(data)
    }


    override fun scrollToPosition(position: Int) {
        recyclerView.layoutManager?.scrollToPosition(position)
    }


    override fun getFirstTvProgram(): TvProgram {
        return adapter.getFirstItem()
    }


    override fun getLastTvProgram(): TvProgram {
        return adapter.getLastItem()
    }


    override fun toggleProgressVisibility(visible: Boolean) {
        if (visible) {
            progress.visibility = View.VISIBLE
        } else {
            progress.visibility = View.INVISIBLE
        }
    }


    override fun showErrorMessage() {
        if (!errorMessageVisible) {
            errorMessageVisible = true
            errorSnackbar = Snackbar.make(activity!!.findViewById(android.R.id.content), getString(R.string.fragment_tv_list_error_message),
                    Snackbar.LENGTH_LONG)
                    .addCallback(snackCallback)
            errorSnackbar.show()
        }
    }

    override fun dismissErrorMessage() {
        errorSnackbar.dismiss()
    }

    override fun onDestroy() {
        super.onDestroy()
        dismissErrorMessage()
        presenter.unsubscribe()
    }

    interface ProgressBarProvider {
        fun getProgressBar(): ProgressBar
    }

    private val snackCallback: Snackbar.Callback = object : Snackbar.Callback() {

        override fun onDismissed(transientBottomBar: Snackbar?, event: Int) {
            errorMessageVisible = false
        }
    }
}
