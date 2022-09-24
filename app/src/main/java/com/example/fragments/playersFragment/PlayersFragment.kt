package com.example.fragments.playersFragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.NetworkResult
import com.example.adapterPlayers.AdapterPlayers
import com.example.nba.R
import com.example.nba.databinding.FragmentPlayersBinding
import com.example.nba.databinding.PlayersLayoutBinding
import com.google.android.material.snackbar.Snackbar

class PlayersFragment : Fragment(R.layout.fragment_players) {
    private val binding by lazy { FragmentPlayersBinding.inflate(layoutInflater) }
    private val playersAdapter by lazy { AdapterPlayers() }
    private lateinit var viewModel: PlayersViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(requireActivity())[PlayersViewModel::class.java]
        return inflater.inflate(R.layout.fragment_players, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val viewmodel = ViewModelProvider(requireActivity())[PlayersViewModel::class.java]
        binding.apply {
            playersRecyclerview.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)
            playersRecyclerview.adapter = playersAdapter
        }

        viewModel.getResult()

        viewModel.result.observe(viewLifecycleOwner, Observer {
            when(it) {
                is NetworkResult.Success -> {
                    Log.d("TTT","${it.data?.players}")
                    it.data?.players?.let {
                        playersAdapter.model = it
                    }
                }
                is NetworkResult.Error -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
                is NetworkResult.Loading -> {
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
                }
            }
        })

    }
}