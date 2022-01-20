package dev.ogabek.kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dev.ogabek.kotlin.R
import dev.ogabek.kotlin.model.Member

class MainAdapter(private val context: Context, private val members: List<Member>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_SIMPLE_VIEW = 0;
    private val TYPE_RECYCLER_VIEW = 1;

    override fun getItemViewType(position: Int): Int {
        if (position % 9 == 0) {
            return TYPE_RECYCLER_VIEW
        } else {
            return TYPE_SIMPLE_VIEW
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_RECYCLER_VIEW) {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_layout, parent, false)
            return ItemRecyclerViewHolder(view)
        } else {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.item_view_layout, parent, false)
            return ItemSimpleViewHolder(view)
        }
    }

    class ItemSimpleViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    }

    class ItemRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val recyclerView = view.findViewById<RecyclerView>(R.id.rv_item_recycler_view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is ItemRecyclerViewHolder) {
            holder.apply {
                recyclerView.layoutManager = GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
                recyclerView.adapter = RecyclerViewAdapter(context, 5)
            }
        }
    }

    override fun getItemCount(): Int {
        return members.size
    }


}