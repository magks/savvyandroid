package com.magks.savvy_android.views.ui.help

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.ListFragment

import android.widget.AdapterView.OnItemClickListener
import android.widget.ListView
import android.widget.SimpleAdapter
import android.widget.Toast
import com.magks.savvy_android.R


class DashboardHelpFragment : ListFragment(), OnItemClickListener {
    companion object {
        fun newInstance() = DashboardHelpFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dashboard_help_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val aList = ArrayList<HashMap<String, Any?>>()
        val drawables = resources.obtainTypedArray(R.array.DashboardHelpIcons)
        val strings = resources.getStringArray(R.array.DashboardHelpInfo)
        for (i in 0 until strings.size) {
            val hm = HashMap<String, Any?>()
            hm["icon"] = drawables.getResourceId(i, -1)
            hm["info"] = strings[i]
            aList.add(hm)
        }
        //val drawable = drawables.getResourceId(position, -1)
        //image.setImageResource(drawable)

        val from = arrayOf("icon", "info")
        val to = intArrayOf(R.id.dashboard_help_image_view, R.id.dashboard_help_text_view)

        listView.adapter = SimpleAdapter(context, aList, R.layout.dashboard_help_fragment, from, to)
        drawables.recycle()
    }

    override fun onItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        Toast.makeText(context, "Item: $position", Toast.LENGTH_SHORT).show()
    }

}
