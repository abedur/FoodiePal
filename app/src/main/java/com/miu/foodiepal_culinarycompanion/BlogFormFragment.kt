package com.miu.foodiepal_culinarycompanion

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.miu.foodiepal_culinarycompanion.databinding.FragmentBlogFormBinding
import com.miu.foodiepal_culinarycompanion.databinding.FragmentRecipeFormBinding


class BlogFormFragment : DialogFragment() {
    interface BlogFormListener{
        fun onBlogAdded(blog:Blog)
    }

    private lateinit var listener: BlogFormListener
    lateinit var blogFormBinding : FragmentBlogFormBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        blogFormBinding = FragmentBlogFormBinding.inflate(inflater, container, false)


        val blogTitle: EditText = blogFormBinding.editBlogTitle
        val blogDescription:EditText = blogFormBinding.editBlogDescription
        val btnAddBlog:Button = blogFormBinding.btnAddBlog
        val btnCancel: Button = blogFormBinding.btnCancel

        btnAddBlog.setOnClickListener {
            val title = blogTitle.text.toString()
            val desc = blogDescription.text.toString()

            if(title.isNotEmpty() && desc.isNotEmpty() ){
                val newBlog = Blog(title, desc)
                listener.onBlogAdded(newBlog)
                dismiss()
            }else{
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_LONG).show()
            }
        }
        btnCancel.setOnClickListener {
            dismiss()
        }

        return blogFormBinding.root //inflater.inflate(R.layout.fragment_blog_form, container, false)
    }

    fun setListener(listener: BlogFormListener) {
        this.listener = listener
    }
}