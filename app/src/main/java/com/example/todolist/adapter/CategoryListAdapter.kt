package com.example.todolist.adapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.annotation.IdRes
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.example.todolist.R
import com.example.todolist.model.Category

class CategoryListAdapter(
    private val mContext: Context,
    private val mLayoutResourceId: Int,
    categories: List<Category>
) :
    ArrayAdapter<Category>(mContext, mLayoutResourceId, categories) {
    private val category: MutableList<Category> = ArrayList(categories)
    private var allCategories: List<Category> = ArrayList(categories)

    override fun getCount(): Int {
        return category.size
    }
    override fun getItem(position: Int): Category {
        return category[position]
    }
    override fun getItemId(position: Int): Long {
        return category[position].id?.toLong() ?: 0
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        if (convertView == null) {
            val inflater = (mContext as Activity).layoutInflater
            convertView = inflater.inflate(mLayoutResourceId, parent, false)
        }
        try {
            val category: Category = getItem(position)
            val categoryAutoCompleteView = convertView!!.findViewById<View>(R.id.dropDownItem) as TextView
            categoryAutoCompleteView.text = category.title
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return convertView!!
    }


}