package com.example.truthordaregame.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.truthordaregame.R
import com.example.truthordaregame.databinding.FragmentGameBinding

/**
 * A simple [Fragment] subclass.
 * Use the [GameFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameFragment : Fragment() {

    private lateinit var viewModel: GameViewModel

    lateinit var currentPair: Pair<String, String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
            Log.i("GameFragment", "Called ViewModelProvider.get")

            viewModel = ViewModelProvider(this).get(GameViewModel::class.java)

            viewModel.currentPair.observe(viewLifecycleOwner, Observer { newPair ->
                currentPair= newPair
            })

            val binding = DataBindingUtil.inflate<FragmentGameBinding>(inflater,
                R.layout.fragment_game, container, false)

            binding.buttonTruth.setOnClickListener{
                Log.i("GameFragment", currentPair.toString())
                it.findNavController().navigate(
                    com.example.truthordaregame.game.GameFragmentDirections.actionGameFragmentToGameContentFragment(
                        currentPair.first
                    )
                )
                viewModel.nextPair()
            }

            binding.buttonAction.setOnClickListener{
                Log.i("GameFragment", currentPair.toString())
                it.findNavController().navigate(
                    com.example.truthordaregame.game.GameFragmentDirections.actionGameFragmentToGameContentFragment(
                        currentPair.second
                    )
                )
                viewModel.nextPair()
            }
            return binding.root
    }
}