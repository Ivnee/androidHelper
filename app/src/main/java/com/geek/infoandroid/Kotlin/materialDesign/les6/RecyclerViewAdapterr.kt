package com.example.fortestcode.ui.main

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.fortestcode.ui.main.MainFragment.Companion.TYPE_EARTH
import com.example.fortestcode.ui.main.MainFragment.Companion.TYPE_HEADER
import com.example.fortestcode.ui.main.MainFragment.Companion.TYPE_MARS
import com.geek.infoandroid.R

class RecyclerViewAdapterr(
    private var onItemClickListener: OnItemClickListener,
    private var data: MutableList<Pair<Data, Boolean>>
) : RecyclerView.Adapter<BaseViewHolder>(), ItemTouchHelperAdapter {

    fun appendItem() {
        data.add(generateItem())
        notifyItemInserted(itemCount - 1)
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        data.removeAt(fromPosition)
            .apply { data.add(if (toPosition > fromPosition) toPosition - 1 else toPosition, this) }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        data.removeAt(position)
        notifyItemRemoved(position)
    }

    private fun generateItem() = Pair(Data("Mars", ""), false)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return when (viewType) {
            TYPE_EARTH -> {
                EarthViewHolder(
                    inflater.inflate(
                        R.layout.recycler_item_earth,
                        parent,
                        false
                    ) as View
                )
            }
            TYPE_MARS -> {
                MarsViewHolder(inflater.inflate(R.layout.recycler_item_mars, parent, false) as View)
            }
            else -> HeaderViewHolder(
                inflater.inflate(
                    R.layout.recycler_item_header,
                    parent,
                    false
                ) as View
            )
        }
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return when {
            position == 0 -> TYPE_HEADER
            data[position].first.someDescription.isNullOrBlank() -> TYPE_MARS
            else -> TYPE_EARTH
        }
    }

    inner class EarthViewHolder(itemView: View) : BaseViewHolder(itemView) {
        override fun bind(data: Pair<Data, Boolean>) {
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<TextView>(R.id.descriptionTextView).text =
                    data.first.someDescription
                itemView.findViewById<ImageView>(R.id.wikiImageView).setOnClickListener {
                    onItemClickListener.onItemClick(data.first)
                }

            }
        }
    }

    inner class MarsViewHolder(itemView: View) : BaseViewHolder(itemView),
        ItemTouchHelperViewHolder {

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.BLUE)//???????? ???? ?????????? ???????????? ?????? ??????????
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)//???????? ?????????? ???????????? ?????? ??????????
        }

        override fun bind(data: Pair<Data, Boolean>) {
            itemView.findViewById<ImageView>(R.id.moveItemUp).setOnClickListener { moveUp() }
            itemView.findViewById<ImageView>(R.id.moveItemDown).setOnClickListener { moveDown() }
            itemView.findViewById<ImageView>(R.id.removeItemImageView)
                .setOnClickListener { removeItem() }
            itemView.findViewById<ImageView>(R.id.addItemImageView).setOnClickListener { addItem() }
            itemView.findViewById<TextView>(R.id.marsTextView).setOnClickListener { toggleText() }
            itemView.findViewById<TextView>(R.id.marsDescriptionTextView).visibility =
                if (data.second) View.VISIBLE else View.GONE
            if (layoutPosition != RecyclerView.NO_POSITION) {
                itemView.findViewById<ImageView>(R.id.marsImageView).setOnClickListener {
                    onItemClickListener.onItemClick(data.first)
                }
            }
        }

        private fun toggleText() {
            data[layoutPosition] = data[layoutPosition].let {
                it.first to !it.second
            }
            notifyItemChanged(layoutPosition)
        }

        private fun moveUp() {
            layoutPosition.takeIf { it > 1 }
                ?.also { currentPosition ->
                    data.removeAt(currentPosition)
                        .apply { data.add(currentPosition - 1, this) }
                    notifyItemMoved(currentPosition, currentPosition - 1)
                }
        }

        private fun moveDown() {
            layoutPosition.takeIf { it < data.size - 1 }?.also { currentPosition ->
                data.removeAt(currentPosition).apply { data.add(currentPosition + 1, this) }
                notifyItemMoved(currentPosition, currentPosition + 1)
            }
        }

        private fun addItem() {
            data.add(layoutPosition, generateItem())
            notifyItemInserted(layoutPosition)
        }

        private fun removeItem() {
            data.removeAt(layoutPosition)
            notifyItemRemoved(layoutPosition)
        }


    }

    inner class HeaderViewHolder(itemView: View) : BaseViewHolder(itemView) {
        override fun bind(data: Pair<Data, Boolean>) {
            itemView.setOnClickListener {
                onItemClickListener.onItemClick(data.first)
            }
        }
    }

}

class ItemTouchHelperCallback(private val adapter: RecyclerViewAdapterr) :
    ItemTouchHelper.Callback() {//?????????????? ?????? ?????????????? ?? ?????? ????????????

    override fun isLongPressDragEnabled(): Boolean {//???????????????? ???????? ???? ?????????????? ??????????????
        return true
    }


    override fun isItemViewSwipeEnabled(): Boolean {//???????????????? ??????????
        return true
    }

    override fun getMovementFlags(//?????? ?????????? ???????????????? ???????? ???????? ??????????????
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ): Int {
        val dragFlag =
            ItemTouchHelper.UP or ItemTouchHelper.DOWN//???????????????????????? ?????????????????????? ?????????? ?? ????????
        val swipeFlag =
            ItemTouchHelper.START or ItemTouchHelper.END//?????????? ?????????????????????? ?????????? ?? ????????????
        return makeMovementFlags(dragFlag, swipeFlag)
    }

    override fun onMove(//?????????? ???????????? ???????????????? ??????????????
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        adapter.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        return true
    }

    override fun onSwiped(
        viewHolder: RecyclerView.ViewHolder,
        direction: Int
    ) {//?????????? ?????????????????? ?????????? ?????? ????????????
        adapter.onItemDismiss(viewHolder.adapterPosition)
    }

    override fun onSelectedChanged(
        viewHolder: RecyclerView.ViewHolder?,
        actionState: Int
    ) {//?????????? ???????????????? ???????? ?????? ??????????????
        if (actionState != ItemTouchHelper.ACTION_STATE_IDLE) {
            val itemViewHolder = viewHolder as ItemTouchHelperViewHolder
            itemViewHolder.onItemSelected()
        }
        super.onSelectedChanged(viewHolder, actionState)
    }

    override fun clearView(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder
    ) {//?????????? ???????????????????? ?????????? ?????????????????? ???????? ??????????????
        super.clearView(recyclerView, viewHolder)
        val itemViewHolder = viewHolder as ItemTouchHelperViewHolder
        itemViewHolder.onItemClear()
    }
}

//?????????? ?????? ???????????????? ????????????
data class Data(
    val someText: String = "text",
    val someDescription: String? = "Description"
)


interface ItemTouchHelperAdapter {
    //?????????????????? ???????????????? ?????? ???????? ?????? ??????????????
    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemDismiss(position: Int)
}

interface ItemTouchHelperViewHolder {
    //?????????????? ?????? ?????????????? ?????? ???????? ?????? ??????????????
    fun onItemSelected()
    fun onItemClear()
}



//null
interface OnItemClickListener {
    fun onItemClick(data: Data)
}