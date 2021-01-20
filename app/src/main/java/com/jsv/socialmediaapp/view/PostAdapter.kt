package com.jsv.socialmediaapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jsv.socialmediaapp.databinding.SingleNewPostItemBinding
import com.jsv.socialmediaapp.model.Activity

class PostAdapter (private val onclickListener: OnClickListener): ListAdapter<Activity, PostAdapter.SearchModelViewHolder>(DiffCallBack) {

//    companion object {
//        const val TYPE_POST = 0
//        const val TYPE_COMMENT = 1
//        const val TYPE_GITHUB = 2
//    }

    class OnClickListener(val onclickListener: (itemsResult: Activity) -> Unit) {
        fun saveAsFavorite(itemsResult: Activity) = onclickListener(itemsResult)
    }

    companion object DiffCallBack : DiffUtil.ItemCallback<Activity>() {
        override fun areItemsTheSame(oldItem: Activity, newItem: Activity): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: Activity, newItem: Activity): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class SearchModelViewHolder(private var binding: SingleNewPostItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(activity: Activity) {
            binding.activity = activity
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchModelViewHolder {
        return SearchModelViewHolder(SingleNewPostItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: SearchModelViewHolder, position: Int) {
        val activity = getItem(position)
        println("onBindViewHolder setOnClickListener")

        holder.itemView.setOnClickListener {
            println("onBindViewHolder setOnClickListener")

            //onclickListener.saveAsFavorite(it)
        }

        holder.bind(activity)
    }
}