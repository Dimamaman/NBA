package com.example.adapterPlayers

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.example.data.Player
import com.example.nba.R
import com.example.nba.databinding.PlayersLayoutBinding

class AdapterPlayers: RecyclerView.Adapter<AdapterPlayers.VH>() {
    var model: List<Player> = mutableListOf()
    @SuppressLint("NotifyDataSetChanged")
    set(value) {
        field = value
        notifyDataSetChanged()
    }
    inner class VH(val binding: PlayersLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(player: Player) {
            binding.apply {
                firstName.text = player.first_name
                lastName.text = player.last_name
                pasition.text = player.position

                teamName.text = itemView.context.getString(R.string.team_name,player.team.full_name)
                teamDevision.text = itemView.context.getString(R.string.team_division,player.team.division)
                teamLocation.text = itemView.context.getString(R.string.team_location,player.team.city)

                itemView.setOnClickListener {
                    aboutPlayersTeam.isVisible = !aboutPlayersTeam.isVisible
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.players_layout,parent,false)
        val binding = PlayersLayoutBinding.bind(view)
        return VH(binding)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.bind(model[position])
    }

    override fun getItemCount(): Int = model.size
}