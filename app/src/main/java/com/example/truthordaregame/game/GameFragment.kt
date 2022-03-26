package com.example.truthordaregame.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.truthordaregame.R
import com.example.truthordaregame.database.TruthOrDareGameDatabase
import com.example.truthordaregame.databinding.FragmentGameBinding


class GameFragment : Fragment() {

    private lateinit var viewModel: GameViewModel

    private lateinit var currentPair: Pair<String, String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = DataBindingUtil.inflate<FragmentGameBinding>(inflater, R.layout.fragment_game, container, false)
        val application = requireNotNull(this.activity).application
        val dao = TruthOrDareGameDatabase.getInstance(application).getTruthOrDareGameDatabaseDao()

        //----------------------Настройки ViewModel----------------------
        val viewModelFactory = GameViewModelFactory(dao, application)
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(GameViewModel::class.java)

        viewModel.currentPair.observe(viewLifecycleOwner, { newPair ->
            currentPair= newPair
        })

        viewModel.questions.observe(viewLifecycleOwner, {
            viewModel.dares.observe(viewLifecycleOwner, {
                if (viewModel.generalPairList.value?.isEmpty() == true) {
                    viewModel.resetList()
                }
                viewModel.nextPair()
            })
        })

        binding.buttonTruth.setOnClickListener{
            Log.i("GameFragment", viewModel.generalPairList.value.toString())
            it.findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameContentFragment(
                    currentPair.first, 1
                )
            )
        }

        binding.buttonAction.setOnClickListener{
            Log.i("GameFragment", viewModel.generalPairList.value.toString())
            it.findNavController().navigate(GameFragmentDirections.actionGameFragmentToGameContentFragment(
                    currentPair.second, 2
                )
            )
        }
        return binding.root
    }
}
