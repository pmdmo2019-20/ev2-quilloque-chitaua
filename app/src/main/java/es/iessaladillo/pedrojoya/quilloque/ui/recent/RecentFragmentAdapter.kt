package es.iessaladillo.pedrojoya.quilloque.ui.recent

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import es.iessaladillo.pedrojoya.quilloque.R
import es.iessaladillo.pedrojoya.quilloque.data.entity.Call
import kotlinx.android.extensions.LayoutContainer

class RecentFragmentAdapter() : RecyclerView.Adapter<RecentFragmentAdapter.ViewHolder>() {
    var dataList: List<Call> = mutableListOf()
    var onItemClickListener: ((Int) -> Unit)? = null

    override fun getItemCount(): Int = dataList.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) =
        holder.bind(dataList[position])

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.recent_fragment_item, parent, false)
        return ViewHolder(itemView)
    }

    fun submitList(newList: List<Call>) {
        dataList = newList
        notifyDataSetChanged()
    }

    inner class ViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView),
        LayoutContainer {
        fun bind(call: Call) {

        }

        init {
            containerView.setOnClickListener { onItemClickListener?.invoke(adapterPosition) }
        }


    }
}