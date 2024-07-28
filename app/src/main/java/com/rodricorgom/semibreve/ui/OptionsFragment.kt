package com.rodricorgom.semibreve.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rodricorgom.semibreve.R
import com.rodricorgom.semibreve.databinding.FragmentOptionsBinding

/**
 * A simple [Fragment] subclass.
 * Use the [OptionsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OptionsFragment : Fragment() {
    private var _binding: FragmentOptionsBinding? = null
    private val binding get() = _binding!!
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    //Use American scale if true
    var scale: Boolean = false;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOptionsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.scaleSwitch.setOnClickListener{
            scale = binding.scaleSwitch.isChecked

            if(scale) {
                binding.scaleSwitch.text = getString(R.string.scale_american)
                binding.scaleTextView.text = getString(R.string.scale_us)
            }

            else{


                binding.scaleSwitch.text = getString(R.string.scale_european)
                binding.scaleTextView.text = getString(R.string.scale_eu)
            }
        }

        binding.button.setOnClickListener{
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView,FlashcardFragment.newInstance(scale))
                .addToBackStack("Flashcard fragment")
                .commit()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OptionsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            OptionsFragment().apply {
            }
    }
}