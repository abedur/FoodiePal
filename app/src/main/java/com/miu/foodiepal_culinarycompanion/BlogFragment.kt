package com.miu.foodiepal_culinarycompanion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton


class BlogFragment : Fragment(), BlogFormFragment.BlogFormListener  {
    val blogs = ArrayList<Blog>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: BlogAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        blogs.add(Blog("Test blog", "Lorem ipsum, Lorem ipsum Lorem ipsum Lorem ipsum"))
        blogs.add(Blog("Test blog2", "Test blog 2 Test blog 2 Test blog 2 Test blog 2"))

        val rootView = inflater.inflate(R.layout.fragment_blog, container, false)
        recyclerView = rootView.findViewById(R.id.rcvblog)
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = BlogAdapter(blogs)
        recyclerView.adapter = adapter

        val btnAddBlog:FloatingActionButton = rootView.findViewById(R.id.fabBlog)

        btnAddBlog.setOnClickListener {
            val formFragment = BlogFormFragment()
            formFragment.setListener(this)
            formFragment.show(parentFragmentManager, "BlogFormFragment")
        }

        return rootView
    }
    override fun onBlogAdded(blog: Blog) {
        blogs.add(blog)
        adapter.notifyDataSetChanged()
    }

}