package com.example.truthordaregame.gamecontent

import android.os.Bundle
import android.text.format.DateUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.truthordaregame.ContentType
import com.example.truthordaregame.R
import com.example.truthordaregame.databinding.FragmentGameContentBinding

class GameContentFragment : Fragment() {

    private lateinit var viewModel: GameContentViewModel
    private lateinit var viewModelFactory: GameContentViewModelFactory
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentGameContentBinding>(inflater, R.layout.fragment_game_content, container, false)
        val gameContentFragmentArgs by navArgs<GameContentFragmentArgs>()

        //----------------------Настройки ViewModel----------------------
        viewModelFactory = GameContentViewModelFactory(gameContentFragmentArgs.content, gameContentFragmentArgs.contentType)
        viewModel = ViewModelProvider(this, viewModelFactory).get(GameContentViewModel::class.java)

        viewModel.contentType.observe(viewLifecycleOwner, { newContentType ->
            val actionBar = (activity as AppCompatActivity?)!!.supportActionBar
            when(newContentType){
                ContentType.QUESTION -> actionBar?.setTitle(R.string.truth_button)
                ContentType.DARE -> actionBar?.setTitle(R.string.action_button)
            }
        })

        viewModel.content.observe(viewLifecycleOwner, {   newContent ->
            binding.textContent.text = newContent
        })

        viewModel.eventGameFinish.observe(viewLifecycleOwner, {   finished ->
            if (finished)
                findNavController().navigateUp()
        })

        viewModel.secondsUntilEnd.observe(viewLifecycleOwner, { secondsUntilEnd ->
            binding.textTimer.text = DateUtils.formatElapsedTime(secondsUntilEnd)
        })

        binding.buttongoNext.setOnClickListener{
            it.findNavController().navigateUp()
        }
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        viewModel.resumeTimer()
    }

    override fun onStop() {
        super.onStop()
        viewModel.pauseTimer()
    }
}
