package com.example.truthordaregame

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.truthordaregame.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = DataBindingUtil.inflate<FragmentSettingsBinding>(inflater, R.layout.fragment_settings, container, false)

        //---------------------"Адаптация для картинки"---------------------
        val image: ImageView = binding.settingsImage
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

        binding.buttonQuestionList.setOnClickListener{
            it.findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToContentListFragment(ContentType.QUESTION))
        }

        binding.buttonActionList.setOnClickListener{
            it.findNavController().navigate(SettingsFragmentDirections.actionSettingsFragmentToContentListFragment(ContentType.DARE))
        }
        return binding.root
    }
}
