package com.example.truthordaregame.gamecontent

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.truthordaregame.R
import com.example.truthordaregame.databinding.FragmentGameContentBinding

/**
 * A simple [Fragment] subclass.
 * Use the [GameContentFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class GameContentFragment : Fragment() {

    private lateinit var viewModel: GameContentViewModel
    private lateinit var viewModelFactory: GameContentViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentGameContentBinding>(inflater,
            R.layout.fragment_game_content, container, false)

        val gameContentFragmentArgs by navArgs<com.example.truthordaregame.gamecontent.GameContentFragmentArgs>()

        viewModelFactory = GameContentViewModelFactory(gameContentFragmentArgs.content)
        viewModel = ViewModelProvider(this, viewModelFactory).get(GameContentViewModel::class.java)

        viewModel.content.observe(viewLifecycleOwner, Observer {   newContent ->
            binding.textContent.text = newContent
        })

        binding.buttongoNext.setOnClickListener{
            it.findNavController().navigateUp()
        }

        return binding.root
    }
}