package com.example.truthordaregame

import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.NavigationUI
import com.example.truthordaregame.databinding.FragmentTitleBinding

import android.widget.ImageView



class TitleFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val binding = DataBindingUtil.inflate<FragmentTitleBinding>(inflater, R.layout.fragment_title, container, false)

        //---------------------"Адаптация для картинки"---------------------
        val image: ImageView = binding.titleImage
        val currentOrientation = resources.configuration.orientation
        val param = image.layoutParams as ViewGroup.MarginLayoutParams
        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            // Portrait
            val dp = 50 * context?.resources?.displayMetrics?.density!!
            param.setMargins(dp.toInt(), dp.toInt(), dp.toInt(), dp.toInt())
        } else {
            // Landscape
            val dp = 15 * context?.resources?.displayMetrics?.density!!
            param.setMargins(0, dp.toInt(), 0, dp.toInt())
        }
        image.layoutParams = param

        //---------------------Смайлики---------------------
        binding.buttonStart.text = binding.buttonStart.text.toString().plus(String(Character.toChars(0x1F3AE)))
        binding.buttonRules.text = binding.buttonRules.text.toString().plus(String(Character.toChars(0x1F4C3)))

        binding.buttonRules.setOnClickListener{
            it.findNavController().navigate(R.id.action_titleFragment_to_rulesFragment)
        }
        binding.buttonStart.setOnClickListener{
            it.findNavController().navigate(R.id.action_titleFragment_to_gameFragment)
        }
        setHasOptionsMenu(true)

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return  NavigationUI.onNavDestinationSelected(item, findNavController()) ||  super.onOptionsItemSelected(item)
    }
}
