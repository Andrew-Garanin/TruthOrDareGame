package com.example.truthordaregame

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.truthordaregame.databinding.FragmentSettingsBinding
import com.example.truthordaregame.databinding.FragmentTitleBinding


/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentSettingsBinding>(inflater, R.layout.fragment_settings, container, false)

        binding.buttonQuestionList.setOnClickListener{
            //it.findNavController().navigate(R.id.action_settingsFragment_to_contentListFragment)
            it.findNavController().navigate(com.example.truthordaregame.SettingsFragmentDirections.actionSettingsFragmentToContentListFragment(1))
        }

        binding.buttonActionList.setOnClickListener{
            //it.findNavController().navigate(R.id.action_settingsFragment_to_contentListFragment)
            it.findNavController().navigate(com.example.truthordaregame.SettingsFragmentDirections.actionSettingsFragmentToContentListFragment(2))
        }

        return binding.root
    }
}