package com.jsv.socialmediaapp.view.newsfeed

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.jsv.socialmediaapp.AppExecutors
import com.jsv.socialmediaapp.databinding.FragmentNewsFeedBinding
import com.jsv.socialmediaapp.di.Injectable
import com.jsv.socialmediaapp.view.PostAdapter
import com.jsv.socialmediaapp.viewmodel.NewsFeedFragmentViewModel
import javax.inject.Inject


class NewsFeedFragment : Fragment(), Injectable {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var appExecutors: AppExecutors

    private lateinit var binder: FragmentNewsFeedBinding

    private val viewModel: NewsFeedFragmentViewModel by lazy {
        ViewModelProvider(this, viewModelFactory).get(NewsFeedFragmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binder = FragmentNewsFeedBinding.inflate(inflater)
        return binder.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        //super.onViewCreated(view, savedInstanceState)
        binder.lifecycleOwner = this
        binder.viewModel = viewModel

        binder.listNewsPost.adapter = PostAdapter(PostAdapter.OnClickListener{it->

        })
        fetchFeed()
        viewModel.userNewsFeed.observe(viewLifecycleOwner, Observer { it->
            println("NewsFeedFragment Data $it")
        })

        viewModel.userPost.observe(viewLifecycleOwner, Observer { it->
          //  println("NewsFeedFragment PostData $it")

        })
    }

    private fun fetchFeed() {
        val userId = arguments?.let { NewsFeedFragmentArgs.fromBundle(it).id }
        userId?.let {
            viewModel.setUserId(it)
        }
    }
}