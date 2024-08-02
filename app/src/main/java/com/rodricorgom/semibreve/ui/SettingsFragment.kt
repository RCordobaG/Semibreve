package com.rodricorgom.semibreve.ui

import android.content.DialogInterface
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.rodricorgom.semibreve.R
import com.rodricorgom.semibreve.data.RuntimeData.RuntimeSettings
import com.rodricorgom.semibreve.data.model.LocalResultsManager
import com.rodricorgom.semibreve.databinding.FragmentOptionsBinding
import com.rodricorgom.semibreve.databinding.FragmentSettingsBinding


/**
 * A simple [Fragment] subclass.
 * Use the [SettingsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSettingsBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val manager = LocalResultsManager(requireContext())
        binding.roundTimerTextInput.setText((RuntimeSettings.newRoundTimer/1000).toString())

        binding.settingsSaveButton.setOnClickListener{
            if(binding.roundTimerTextInput.text.isNotBlank()){
                RuntimeSettings.newRoundTimer = (binding.roundTimerTextInput.text.toString().toLong() * 1000)
                parentFragmentManager.popBackStack()
            }
        }

        binding.backToMenuButton.setOnClickListener{
            parentFragmentManager.popBackStack()
        }

        binding.deleteResultsButton.setOnClickListener{
            AlertDialog.Builder(requireContext())
                .setTitle("Delete saved test results")
                .setMessage("THis will delete all your saved test result data. Are you sure you want to continue?")

                .setPositiveButton("Cancel",null)

                .setNegativeButton("Delete", DialogInterface.OnClickListener(){ dialog,int ->
                    manager.deleteResults()
                    Toast.makeText(requireContext(),"Historical test results deleted",Toast.LENGTH_SHORT).show()
                })

                .show()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment settingsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            SettingsFragment().apply {

            }
    }
}