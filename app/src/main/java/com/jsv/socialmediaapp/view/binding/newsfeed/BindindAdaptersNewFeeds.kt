package com.jsv.socialmediaapp.view.binding.newsfeed

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.jsv.socialmediaapp.R
import com.jsv.socialmediaapp.model.Activity
import com.jsv.socialmediaapp.response.IResponse
import com.jsv.socialmediaapp.response.UserFeedResponse
import com.jsv.socialmediaapp.response.Wrapper
import com.jsv.socialmediaapp.util.SocialApiStatus
import com.jsv.socialmediaapp.view.PostAdapter
import com.jsv.socialmediaapp.vo.Resource

object BindindAdaptersNewFeeds {

    fun formatData(userFeedResponse: List<UserFeedResponse>?): List<Activity>? {
        return userFeedResponse?.map { data-> return@map data.dataPost
        }?.get(0)
    }

    @JvmStatic
    @BindingAdapter("listData")
    fun bindRecyclerView(recyclerView: RecyclerView, data: Resource<List<UserFeedResponse>>?) {
        var activity = formatData(data?.data)
        var adapter = recyclerView.adapter as PostAdapter
        val itemDecoration = DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
        itemDecoration.setDrawable(ContextCompat.getDrawable(recyclerView.context, R.drawable.divider)!!)
        recyclerView.addItemDecoration(itemDecoration)
        if (activity != null) {
            adapter.submitList(activity)
        }
    }

    @JvmStatic
    @BindingAdapter("listTitle")
    fun bindTitle(recyclerView: TextView, data: Resource<List<UserFeedResponse>>?) {

    }


    @JvmStatic
    @BindingAdapter("socialApiStatus")
    fun bindStatus(statusImageView: ImageView, status: SocialApiStatus){
        when(status){
            SocialApiStatus.LOADING -> {
                statusImageView.visibility = View.VISIBLE
                statusImageView.setImageResource(R.drawable.loading_listanimation)
            }
            SocialApiStatus.ERROR -> {
                statusImageView.visibility = View.VISIBLE
                statusImageView.setImageResource(R.drawable.ic_connection_error)
            }
            SocialApiStatus.DONE -> {
                statusImageView.visibility = View.GONE
            }
        }
    }
}