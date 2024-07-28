package com.rodricorgom.semibreve.ui

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.SystemClock
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import com.rodricorgom.semibreve.Constants
import com.rodricorgom.semibreve.R
import com.rodricorgom.semibreve.databinding.FragmentFlashcardBinding
import com.rodricorgom.semibreve.databinding.FragmentOptionsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "scale"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FlashcardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FlashcardFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: Boolean = false
    private var param2: String? = null

    private lateinit var notes : MutableSet<String>

    private lateinit var answerButtons : List<Button>
    //private var US_notes = Constants.US_notes.toMutableSet()

    //val notesMap = mapOf(1 to "do_c_light",2 to "")

    private var _binding: FragmentFlashcardBinding? = null

    private val binding get() = _binding!!

    //Use American scale if true
    private var scale: Boolean = false;



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getBoolean(ARG_PARAM1)
            scale = param1 as Boolean
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentFlashcardBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(scale){
            notes = Constants.US_notes.toMutableSet()
        }
        else{
            notes = Constants.EU_notes.toMutableSet()
        }

        answerButtons = mutableListOf(binding.answerBtn1,binding.answerBtn2,binding.answerBtn3,binding.answerBtn4)
        var correctButton = answerButtons.elementAt((0..(answerButtons.size)-1).random())
        Log.d("SET_TEST","${correctButton}")
        (answerButtons as MutableList<Button>).remove(correctButton)
        Log.d("SET_TEST","${answerButtons}")




        //Choose which one will be the correct answer and act accordingly
        Log.d("SET_TEST","${notes}")
        var correctAnswer = notes.elementAt((0..(notes.size - 1)).random())
        notes.remove(correctAnswer)
        Log.d("SET_TEST","${notes}")

        correctButton.text = correctAnswer

        if(scale){
            binding.notesImageView.setImageResource(Constants.noteImageUS.get(correctAnswer)!!)
        }
        else{
            binding.notesImageView.setImageResource(Constants.noteImageEU.get(correctAnswer)!!)
        }



        for (button in answerButtons){
            var note = notes.elementAt((0..(notes.size - 1)).random())
            notes.remove(note)
            button.setText(note)
            Log.d("SET_TEST","${notes}")
            button.setOnClickListener{
                binding.answerTextView.text = getString(R.string.incorrect_answer)
                Handler().postDelayed({
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView,FlashcardFragment.newInstance(param1))
                        .commit()
                },1000)

            }
        }

        correctButton.setOnClickListener{
            binding.answerTextView .text = getString(R.string.correct_answer)
            Handler().postDelayed({
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragmentContainerView,FlashcardFragment.newInstance(param1))
                    .commit()
            },1000)

        }




    }



    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FlashcardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: Boolean) =
            FlashcardFragment().apply {
                arguments = Bundle().apply {
                    putBoolean(ARG_PARAM1, param1)
                }
            }
    }
}