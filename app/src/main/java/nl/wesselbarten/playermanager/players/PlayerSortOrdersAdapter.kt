package nl.wesselbarten.playermanager.players

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import nl.wesselbarten.playermanager.databinding.ItemPlayerSortOrderBinding
import nl.wesselbarten.playermanager.domain.model.PlayerSortOrder

class PlayerSortOrdersAdapter(
    private val onClickListener: OnClickListener
) :
    ListAdapter<PlayerSortOrder, PlayerSortOrdersAdapter.PlayerSortOrderViewHolder>(
        PlayerSortOrderItemCallback
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerSortOrderViewHolder {
        val itemBinding = ItemPlayerSortOrderBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PlayerSortOrderViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: PlayerSortOrderViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class PlayerSortOrderViewHolder(
        private val itemBinding: ItemPlayerSortOrderBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(playerSortOrder: PlayerSortOrder) {
            itemBinding.playerSortOrder = playerSortOrder
            itemBinding.root.setOnClickListener {
                onClickListener.onPlayerSortOrderClick(playerSortOrder)
            }
        }
    }

    object PlayerSortOrderItemCallback : DiffUtil.ItemCallback<PlayerSortOrder>() {

        override fun areItemsTheSame(oldItem: PlayerSortOrder, newItem: PlayerSortOrder): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(
            oldItem: PlayerSortOrder,
            newItem: PlayerSortOrder
        ): Boolean {
            return oldItem.isActive == newItem.isActive
        }
    }

    interface OnClickListener {
        fun onPlayerSortOrderClick(playerSortOrder: PlayerSortOrder)
    }
}