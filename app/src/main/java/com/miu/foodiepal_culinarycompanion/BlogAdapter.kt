package com.miu.foodiepal_culinarycompanion

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationItemView

class BlogAdapter(var blogs: ArrayList<Blog>):
    RecyclerView.Adapter<BlogAdapter.BlogViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BlogAdapter.BlogViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.blog_item, parent, false)
        return BlogViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: BlogAdapter.BlogViewHolder, position: Int) {
        val currentBlog = blogs[position]
        holder.itemView.findViewById<TextView>(R.id.titleTextView).text  = "Title: " + currentBlog.blogTitle
        holder.itemView.findViewById<TextView>(R.id.descriptionTextView).text  = "Description: " + currentBlog.blogDescription
    }

    override fun getItemCount() = blogs.size

    inner class BlogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}