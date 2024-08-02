package com.rodricorgom.semibreve.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.rodricorgom.semibreve.R
import com.rodricorgom.semibreve.data.RuntimeData.RuntimeSettings
import com.rodricorgom.semibreve.data.model.LocalResultsManager
import com.rodricorgom.semibreve.data.model.TestResult
import com.rodricorgom.semibreve.databinding.FragmentScoreBinding
import com.rodricorgom.semibreve.ui.adapters.ResultsAdapter
import java.io.File
import java.io.IOException
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.Date

// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ScoreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ScoreFragment : Fragment() {
    private var _binding : FragmentScoreBinding? = null
    private val binding get() = _binding!!

    private var correctAnswers : Int = 0
    private var incorrectAnswers : Int = 0
    private var score : Double = 0.0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentScoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val manager = LocalResultsManager(requireContext())

        correctAnswers = RuntimeSettings.correctAnswers
        incorrectAnswers = RuntimeSettings.incorrectAnswers
        score = (correctAnswers.toDouble()/RuntimeSettings.rounds.toDouble()) * 100

        Log.d("Score","Correct ${correctAnswers}")
        Log.d("Score","Incorrect ${incorrectAnswers}")
        Log.d("Score","Ratio ${score}")

        val result = TestResult(System.currentTimeMillis().toString(), LocalDateTime.now().toString(),correctAnswers, incorrectAnswers,score)
        manager.createResult(result)

        binding.correctAnswersTextView.text = String.format(getString(R.string.results_corrects_answers_text), correctAnswers)
        binding.incorrectAnswersTextView.text = String.format(getString(R.string.results_incorrect_answers_text), incorrectAnswers)
        binding.finalScoreTextView.text = String.format(getString(R.string.computed_score_text),score)

        binding.scoreHomeButton.setOnClickListener{
            parentFragmentManager.popBackStackImmediate()
            parentFragmentManager.popBackStackImmediate()
            parentFragmentManager.popBackStackImmediate()
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView,MainMenuFragment.newInstance())
                .commit()
        }

        binding.scoreNewQuizButton.setOnClickListener{
            parentFragmentManager.popBackStack()
        }

        binding.resultRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = ResultsAdapter(manager.readResults(),manager)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ScoreFragment().apply {

            }
    }
}