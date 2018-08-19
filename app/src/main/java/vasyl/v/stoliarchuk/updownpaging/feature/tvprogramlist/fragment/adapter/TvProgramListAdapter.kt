package vasyl.v.stoliarchuk.updownpaging.feature.tvprogramlist.fragment.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_tv_program_list.view.*
import vasyl.v.stoliarchuk.updownpaging.R
import vasyl.v.stoliarchuk.updownpaging.data.tvprogram.entity.TvProgram
import java.util.*

class TvProgramListAdapter() : RecyclerView.Adapter<TvProgramListAdapter.TvProgramViewHolder>() {

    private val data: MutableList<TvProgram> = ArrayList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvProgramViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_tv_program_list, parent, false)
        return TvProgramViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: TvProgramViewHolder, position: Int) {
        holder.bind(data[position])
    }

    fun addAll(data: List<TvProgram>) {
        val dataSizeBefore = this.data.size
        this.data.addAll(data)
        val dataSizeAfter = this.data.size
        notifyItemsInserted(dataSizeBefore, dataSizeAfter)
    }

    private fun notifyItemsInserted(fromPosition: Int, tillPosition: Int) {
        for (position in fromPosition until tillPosition) {
            notifyItemInserted(position)
        }
    }


    fun addAll(data: List<TvProgram>, toBeginning: Boolean) {
        if (toBeginning) {
            for (i in data.indices){
                this.data.add(0, data[i])
                notifyItemInserted(i)
            }
        } else {
            addAll(data)
        }
    }

    fun getFirstItem(): TvProgram {
        return data[0]
    }

    fun getLastItem(): TvProgram {
        return data[data.size - 1]
    }

    class TvProgramViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvName: TextView = itemView.item_tv_program_list_name
        private val ivIcon: ImageView = itemView.item_tv_program_list_icon


        fun bind(tvProgram: TvProgram) {
            tvName.text = tvProgram.name
            Picasso.get()
                    .load(tvProgram.icon)
                    .placeholder(R.drawable.placeholder_progress_animation)
                    .into(ivIcon)
        }
    }
}