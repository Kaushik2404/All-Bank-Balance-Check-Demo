package com.example.allbalancecheckapp.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.example.allbalancecheckapp.Model.GridData
import com.example.allbalancecheckapp.R
import com.example.allbalancecheckapp.onIteamViewClick

internal class GridRVAdapter(
    private val gridlist: List<GridData>,
    private val context: Context,
     val oniteamViewClick: onIteamViewClick
) :
    BaseAdapter() {
    private var layoutInflater: LayoutInflater? = null
    private lateinit var gridName: TextView
    private lateinit var gridImage: ImageView
    private lateinit var layout:LinearLayout


    override fun getCount(): Int {
        return gridlist.size
    }


    override fun getItem(position: Int): Any? {
        return null
    }


    override fun getItemId(position: Int): Long {
        return 0
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView

        if (layoutInflater == null) {
            layoutInflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        }

        if (convertView == null) {

            convertView = layoutInflater!!.inflate(R.layout.catyegory_view, null)
        }

        gridName = convertView!!.findViewById(R.id.idTVCourse)
        gridImage = convertView.findViewById(R.id.idImageGrid)
        layout = convertView.findViewById(R.id.layout_cat)
        if(position==0){
            gridName.setTextColor(Color.parseColor("#545A00"))
        }
        if(position==1){
            gridName.setTextColor(Color.parseColor("#815501"))
        }
        if(position==2){
            gridName.setTextColor(Color.parseColor("#154460"))
        }
        if(position==3){
            gridName.setTextColor(Color.parseColor("#1B5600"))
        }
        if(position==4){
            gridName.setTextColor(Color.parseColor("#77016B"))
        }

        layout.setOnClickListener {
            oniteamViewClick.onViewClick(position)
        }



        gridImage.setImageResource(gridlist.get(position).pic)

        gridName.setText(gridlist.get(position).name)
        layout.setBackgroundResource(gridlist.get(position).back)

        return convertView
    }
}
