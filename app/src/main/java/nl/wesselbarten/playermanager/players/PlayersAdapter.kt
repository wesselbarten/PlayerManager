package nl.wesselbarten.playermanager.players

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import nl.wesselbarten.playermanager.databinding.ItemPlayerBinding
import nl.wesselbarten.playermanager.domain.model.Player

class PlayersAdapter : ListAdapter<Player, PlayersAdapter.PlayerViewHolder>(PlayerItemCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayerViewHolder {
        val itemPlayerBinding = ItemPlayerBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PlayerViewHolder(itemPlayerBinding)
    }

    override fun onBindViewHolder(holder: PlayerViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class PlayerViewHolder(
        private val itemPlayerBinding: ItemPlayerBinding
    ) : RecyclerView.ViewHolder(itemPlayerBinding.root) {

        fun bind(player: Player) {
            itemPlayerBinding.player = player
        }
    }

    object PlayerItemCallback : DiffUtil.ItemCallback<Player>() {

        override fun areItemsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Player, newItem: Player): Boolean {
            return oldItem.name == newItem.name &&
                    oldItem.lastName == newItem.lastName &&
                    oldItem.score == newItem.score &&
                    oldItem.createdAt.time == newItem.createdAt.time
        }
    }
}