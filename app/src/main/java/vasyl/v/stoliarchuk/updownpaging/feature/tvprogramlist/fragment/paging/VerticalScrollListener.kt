package vasyl.v.stoliarchuk.updownpaging.feature.tvprogramlist.fragment.paging

import android.support.v7.widget.RecyclerView

abstract class VerticalScrollListener : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        if (!recyclerView.canScrollVertically(-1)) {
            onScrolledToTop(recyclerView)
        } else if (!recyclerView.canScrollVertically(1)) {
            onScrolledToBottom(recyclerView)
        }
        if (dy < 0) {
            onScrolledUp(recyclerView, dy)
        } else if (dy > 0) {
            onScrolledDown(recyclerView, dy)
        }
    }

    open fun onScrolledUp(recyclerView: RecyclerView, dy: Int) {
        onScrolledUp(recyclerView)
    }

    open fun onScrolledDown(recyclerView: RecyclerView, dy: Int) {
        onScrolledDown(recyclerView)
    }

    open fun onScrolledUp(recyclerView: RecyclerView) {}

    open fun onScrolledDown(recyclerView: RecyclerView) {}

    open fun onScrolledToTop(recyclerView: RecyclerView) {}

    open fun onScrolledToBottom(recyclerView: RecyclerView) {}
}