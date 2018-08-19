package vasyl.v.stoliarchuk.updownpaging.feature.tvprogramlist.fragment.paging

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class PagingVerticalScrollListener(private val prefetchDistance: Int = 15) : VerticalScrollListener() {

    private val listeners: MutableSet<OnPagingDataListener> = HashSet()


    fun addOnPagingDataListener(onPagingDataListener: OnPagingDataListener) {
        listeners.add(onPagingDataListener)
    }


    fun removeOnPagingDataListener(onPagingDataListener: OnPagingDataListener) {
        listeners.remove(onPagingDataListener)
    }


    override fun onScrolledUp(recyclerView: RecyclerView) {
        val layoutManager = recyclerView.layoutManager
        if (layoutManager is LinearLayoutManager) {
            val firstVisible = layoutManager.findFirstVisibleItemPosition()
            if (firstVisible <= prefetchDistance) {
                notifyListeners(DIRECTION_TOP)
            }
        }
    }


    override fun onScrolledDown(recyclerView: RecyclerView) {
        val layoutManager = recyclerView.layoutManager
        if (layoutManager is LinearLayoutManager) {
            val lastVisible = layoutManager.findFirstVisibleItemPosition()
            val size = recyclerView.adapter!!.itemCount
            if (size - lastVisible <= prefetchDistance) {
                notifyListeners(DIRECTION_BOTTOM)
            }
        }
    }


    private fun notifyListeners(direction: Int) {
        for (listener in listeners) {
            listener.onPrefetchNextPage(direction)
        }
    }


    companion object {
        private const val DIRECTION_TOP = -1
        private const val DIRECTION_BOTTOM = 1
    }
}