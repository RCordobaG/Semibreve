package com.rodricorgom.semibreve.ui

import android.content.DialogInterface
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.rodricorgom.semibreve.Constants
import com.rodricorgom.semibreve.R
import com.rodricorgom.semibreve.data.RuntimeData.RuntimeSettings
import com.rodricorgom.semibreve.databinding.FragmentFlashcardBinding

/**
 * A simple [Fragment] subclass.
 * Use the [FlashcardFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FlashcardFragment : Fragment() {

    private lateinit var notes : MutableSet<String>

    private lateinit var answerButtons : List<Button>

    private var _binding: FragmentFlashcardBinding? = null

    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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


        binding.endTestButton.setOnClickListener{
            AlertDialog.Builder(requireContext())
                .setTitle("End current test?")
                .setMessage("You have chosen to end the current test. Would you like to save your results up to now?")

                .setPositiveButton(getString(R.string.save_and_exit_button), DialogInterface.OnClickListener(){ dialog, int->
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView,OptionsFragment.newInstance())
                        .commit()
                })

                .setNeutralButton(getString(R.string.exit_without_saving_button), DialogInterface.OnClickListener(){ dialog, int ->
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView,OptionsFragment.newInstance())
                        .commit()
                })

                .setNegativeButton(getString(R.string.cancel_button),null)
                .show()
        }

        RuntimeSettings.currentRound += 1
        binding.roundCounterTextView.text = String.format(getString(R.string.round_counter_text),
            RuntimeSettings.currentRound,
            RuntimeSettings.rounds)
        notes = if(RuntimeSettings.scale){
            Constants.US_notes.toMutableSet()
        } else{
            Constants.EU_notes.toMutableSet()
        }


        //Choose which one will be the correct answer and act accordingly
        answerButtons = mutableListOf(binding.answerBtn1,binding.answerBtn2,binding.answerBtn3,binding.answerBtn4)
        var correctButton = answerButtons.elementAt((0..(answerButtons.size)-1).random())
        Log.d("SET_TEST","${correctButton}")
        (answerButtons as MutableList<Button>).remove(correctButton)
        Log.d("SET_TEST","${answerButtons}")


        Log.d("SET_TEST","${notes}")
        var correctAnswer = notes.elementAt((0..(notes.size - 1)).random())
        notes.remove(correctAnswer)
        Log.d("SET_TEST","${notes}")

        correctButton.text = correctAnswer

        if(RuntimeSettings.scale){
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
                RuntimeSettings.incorrectAnswers += 1
                binding.answerTextView.text = getString(R.string.incorrect_answer)
                Handler().postDelayed({
                    callNextFragment()
                },RuntimeSettings.newRoundTimer)

            }
        }

        correctButton.setOnClickListener{
            RuntimeSettings.correctAnswers += 1
            binding.answerTextView .text = getString(R.string.correct_answer)
            Handler().postDelayed({
                callNextFragment()
            },RuntimeSettings.newRoundTimer)

        }

    }

    private fun callNextFragment(){
        Log.d("Score","Correct ${RuntimeSettings.correctAnswers}")
        Log.d("Score","Incorrect ${RuntimeSettings.incorrectAnswers}")
        Log.d("Score","Ratio ${(RuntimeSettings.correctAnswers)/ RuntimeSettings.rounds}")
        if(RuntimeSettings.currentRound >= RuntimeSettings.rounds){
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView,ScoreFragment.newInstance())
                .commit()
        }
        else{
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, newInstance())
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
         * @return A new instance of fragment FlashcardFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance() =
            FlashcardFragment().apply {

            }
    }
}