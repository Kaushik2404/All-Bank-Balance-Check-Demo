package com.example.allbalancecheckapp.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.allbalancecheckapp.Model.BankDataItem
import com.example.allbalancecheckapp.R
import com.example.allbalancecheckapp.StickyHeaderItemDecoration
import com.example.allbalancecheckapp.onIteamViewClick

const val TYPE_HEADER = 0
const val TYPE_ITEM = 1


//class BankAdapter(private val context: Context, val bankList:MutableList<BankDataItem>,val onIteamClick: onIteamViewClick):
//    RecyclerView.Adapter<BankAdapter.MyHolder>(), StickyHeaderItemDecoration.StickyHeaderInterface  {
//
//
//
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
//
//        return if(viewType == TYPE_HEADER) {
//            ViewHolderHeader(LayoutInflater.from(parent.context)
//                .inflate(R.layout.row_header, parent, false))
//        } else {
//            MyHolder(LayoutInflater.from(parent.context)
//                .inflate(R.layout.item_view, parent, false))
//        }
//
//    }
//
//    override fun getItemCount(): Int=bankList.size
//
//    override fun onBindViewHolder(holder: MyHolder, position: Int) {
//
//        val bankid=bankList[position].id
//        val img= "$bankid.png"
//            try {
//                val ims = context.assets.open(img)
//                val d = Drawable.createFromStream(ims, null)
//                holder.bankImage.setImageDrawable(d)
//            } catch (ex: Exception) {
//                Log.d("mylog", "Error: $ex")
//            }
//
//        holder.bankName.text=bankList[position].bankName
//        holder.layout.setOnClickListener {
//            onIteamClick.onViewClick(position)
//        }
//
//
//    }
//
//
//    class MyHolder(itemView: View) :
//        ViewHolder(itemView) {
//
//        val bankImage:ImageView=itemView.findViewById(R.id.rvImage);
//        val bankName=itemView.findViewById<TextView>(R.id.rvBankName);
//        val next =itemView.findViewById<ImageView>(R.id.rvNext);
//        val layout =itemView.findViewById<LinearLayout>(R.id.layout);
//    }
//
//    class ViewHolderHeader(view: View) : ViewHolder(view) {
//        val headerView = view.headerView as TextView
//    }
//
//    override fun getHeaderPositionForItem(itemPosition: Int): Int {
//        var headerPosition = 0
//        var position = itemPosition
//        do {
//            if (this.isHeader(position)) {
//                headerPosition = position
//                break
//            }
//            position -= 1
//        } while (position >= 0)
//        return headerPosition
//    }
//
//    override fun getHeaderLayout(headerPosition: Int): Int {
//        return R.layout.row_header
//    }
//
//    override fun bindHeaderData(header: View, headerPosition: Int) {
//        ((header as LinearLayout).getChildAt(0) as TextView).text =
//            bankList[headerPosition].bankType
//    }
//
//    override fun isHeader(itemPosition: Int): Boolean {
//        return bankList[itemPosition].
//    }
//
//}

class BankAdapter(private val context: Context, private val users:MutableList<BankDataItem>, private val onIteamClick: onIteamViewClick): RecyclerView.Adapter<RecyclerView.ViewHolder>(), StickyHeaderItemDecoration.StickyHeaderInterface  {

    override fun isHeader(itemPosition: Int): Boolean {
        return users[itemPosition].header

    }

    override fun bindHeaderData(header: View, headerPosition: Int) {
        ((header as LinearLayout).getChildAt(0) as TextView).text =
            users[headerPosition].bankType
    }

    override fun getHeaderLayout(headerPosition: Int): Int {
        return R.layout.row_header
    }

    override fun getHeaderPositionForItem(itemPosition: Int): Int {
        var headerPosition = 0
        var position = itemPosition
        do {
            if (this.isHeader(position)) {
                headerPosition = position
                break
            }
            position -= 1
        } while (position >= 0)
        return headerPosition
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if(viewType == TYPE_HEADER) {
            ViewHolderHeader(LayoutInflater.from(parent.context)
                .inflate(R.layout.row_header, parent, false))
        } else {
            ViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_view, parent, false))
        }
    }

    override fun getItemCount() = users.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if(holder is ViewHolder) {
          holder.nameView.text = users[position].bankName
            val bankid=users[position].id
            val img= "$bankid.png"
            try {
                val ims = context.assets.open(img)
                val d = Drawable.createFromStream(ims, null)
                holder.bankImage.setImageDrawable(d)
            } catch (e: Exception) {
                Log.d("mylog", "Error: $e")
            }

//        holder.bankName.text=users[position].bankName

        holder.layout.setOnClickListener {
            onIteamClick.onViewClick(position)
        }
        } else if(holder is ViewHolderHeader) {
            holder.headerView.text = users[position].bankType
        }
    }


    override fun getItemViewType(position: Int): Int {
        return if(users[position].header) {
            TYPE_HEADER
        } else {
            TYPE_ITEM
        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val nameView = view.findViewById<TextView>(R.id.rvBankName)
        val bankImage:ImageView=itemView.findViewById(R.id.rvImage);
        val next =itemView.findViewById<ImageView>(R.id.rvNext);
        val layout =itemView.findViewById<LinearLayout>(R.id.layout);


    }

    class ViewHolderHeader(view: View) : RecyclerView.ViewHolder(view) {
        val headerView = view.findViewById<TextView>(R.id.headerTXT)
    }

}