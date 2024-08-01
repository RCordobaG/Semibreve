package com.rodricorgom.semibreve.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodricorgom.semibreve.R
import com.rodricorgom.semibreve.databinding.FragmentMainMenuBinding
import com.rodricorgom.semibreve.databinding.FragmentOptionsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MainMenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainMenuFragment : Fragment() {

    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMainMenuBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.menuNewQuizButton.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView,OptionsFragment.newInstance())
                .addToBackStack("Test settings")
                .commit()
        }

        binding.menuSettingButton.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView,SettingsFragment.newInstance())
                .addToBackStack("Settings fragment")
                .commit()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment MainMenuFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            MainMenuFragment().apply {

            }
    }
}